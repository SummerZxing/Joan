/**
 * Copyright (c) 2015-2017, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pengji.config;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.pengji.config.annotation.PropertieConfig;
import com.pengji.config.server.ConfigFileScanner;
import com.pengji.utils.ClassNewer;
import com.pengji.utils.StringUtils;

/**
 * 配置管理类
 * <p>
 * 用于读取配置信息，包括本地配置信息和分布式远程配置信息
 */
public class ConfigManager {

    private static final Log LOG = Log.getLog(ConfigManager.class);

    private static ConfigManager me = new ConfigManager();

    public static ConfigManager me() {
        return me;
    }


    private Properties mainProperties;
    private ConfigConfig config;

    private PropInfoMap propInfoMap = new PropInfoMap();

    private ConfigFileScanner configFileScanner;

    private ConcurrentHashMap<String, Object> configs = new ConcurrentHashMap<>();


    /**
     * 用于在配置文件读取的时候，记录每个key应对的方法，方便在远程配置文件更新的时候
     * 快速的找对对于的方法，然后执行。
     */
    private Map<String, Method> keyMethodMapping = new ConcurrentHashMap<>();

    /**
     * 用于记录方法和其已经被实例化的对象
     * 方便方法执行的时候，能找对对应的实例化对象
     */
    private Multimap<String, Object> keyInstanceMapping = ArrayListMultimap.create();

    /**
     * 类的set方法缓存，用于减少对类的反射工作
     */
    private Multimap<Class<?>, Method> classMethodsCache = ArrayListMultimap.create();


    public ConfigManager() {
        try {
            Prop prop = PropKit.use("jboot.properties");
            mainProperties = prop.getProperties();
        } catch (Throwable ex) {
            LOG.warn("Could not find jboot.properties in your class path.");
            mainProperties = new Properties();
        }

        initModeProp();

        config = get(ConfigConfig.class);

        /**
         * 定时扫描本地配置文件
         */
        if (config.isServerEnable()) {
            initConfigFileScanner();
        }

    }


    public <T> T get(Class<T> clazz) {
        PropertieConfig propertieConfig = clazz.getAnnotation(PropertieConfig.class);
        if (propertieConfig == null) {
            return get(clazz, null);
        }
        return get(clazz, propertieConfig.prefix());
    }


    /**
     * 获取配置信息，并创建和赋值clazz实例
     *
     * @param clazz  指定的类
     * @param prefix 配置文件前缀
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> clazz, String prefix) {

        T obj = (T) configs.get(clazz.getName() + prefix);
        if (obj != null) {
            return obj;
        }

        obj = ClassNewer.newInstance(clazz);
        Collection<Method> setMethods = getSetMethods(clazz);

        for (Method method : setMethods) {

            String key = getKeyByMethod(prefix, method);
            String value = getValueByKey(key);

            /**
             * 记录 key 和其对于的方法，方便远程配置文件修改的时候，能找到其对于的方法
             * 这里记录了 所有set方法对于的key
             */
            keyMethodMapping.put(key, method);

            /**
             * keyInstanceMapping 为 Multimap，一个key有多个object拥有
             */
            keyInstanceMapping.put(key, obj);

            try {
                if (StringUtils.isNotBlank(value)) {
                    Object val = convert(method.getParameterTypes()[0], value);
                    method.invoke(obj, val);
                }
            } catch (Throwable ex) {
                LogKit.error(ex.toString(), ex);
            }
        }

        configs.put(clazz.getName() + prefix, obj);

        return obj;
    }

    private String getKeyByMethod(String prefix, Method method) {

        String key = StrKit.firstCharToLowerCase(method.getName().substring(3));

        if (StringUtils.isNotBlank(prefix)) {
            key = prefix.trim() + "." + key;
        }

        return key;
    }

    /**
     * 根据 key 获取value的值
     * <p>
     * 优先获取系统启动设置参数
     * 第二 获取远程配置
     * 第三 获取本地配置
     *
     * @param key
     * @return
     */
    private String getValueByKey(String key) {

        String value = Config.getStartArg(key);

        if (StringUtils.isBlank(value)) {
            value = (String) mainProperties.get(key);
        }
        return value;
    }


    private Collection<Method> getSetMethods(Class clazz) {
        Collection<Method> setMethods = classMethodsCache.get(clazz);
        if (setMethods == null || setMethods.isEmpty()) {
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("set") && m.getName().length() > 3 && m.getParameterCount() == 1) {
                    classMethodsCache.put(clazz, m);
                }
            }
        }
        return setMethods;
    }

    /**
     * 或者Jboot默认的配置信息
     *
     * @return
     */
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.putAll(mainProperties);

        if (Config.getStartArgs() != null) {
            for (Map.Entry<String, String> entry : Config.getStartArgs().entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }

        return properties;
    }


    /**
     * 初始化不同model下的properties文件
     */
    private void initModeProp() {
        String mode = (String) mainProperties.get("jboot.mode");
        if (StringUtils.isBlank(mode)) {
            return;
        }

        Prop modeProp = null;
        try {
            String p = String.format("jboot-%s.properties", mode);
            modeProp = PropKit.use(p);
        } catch (Throwable ex) {
        }

        if (modeProp == null) {
            return;
        }

        mainProperties.putAll(modeProp.getProperties());
    }


    /**
     * 数据转化
     *
     * @param type
     * @param s
     * @return
     */
    private static final Object convert(Class<?> type, String s) {

        if (type == String.class) {
            return s;
        }


        // mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt(s);
        }

        // mysql type: bigint
        if (type == Long.class || type == long.class) {
            return Long.parseLong(s);
        }


        // mysql type: real, double
        if (type == Double.class || type == double.class) {
            return Double.parseDouble(s);
        }

        // mysql type: float
        if (type == Float.class || type == float.class) {
            return Float.parseFloat(s);
        }

        // mysql type: bit, tinyint(1)
        if (type == Boolean.class || type == boolean.class) {
            String value = s.toLowerCase();
            if ("1".equals(value) || "true".equals(value)) {
                return Boolean.TRUE;
            } else if ("0".equals(value) || "false".equals(value)) {
                return Boolean.FALSE;
            } else {
                throw new RuntimeException("Can not parse to boolean type of value: " + s);
            }
        }

        // mysql type: decimal, numeric
        if (type == java.math.BigDecimal.class) {
            return new java.math.BigDecimal(s);
        }

        // mysql type: unsigned bigint
        if (type == java.math.BigInteger.class) {
            return new java.math.BigInteger(s);
        }

        // mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob. I have not finished the test.
        if (type == byte[].class) {
            return s.getBytes();
        }

        throw new RuntimeException(type.getName() + " can not be converted, please use other type in your config class!");
    }


    private void initConfigFileScanner() {
        configFileScanner = new ConfigFileScanner(config.getPath(), 5) {
            @Override
            public void onChange(String action, String file) {
                switch (action) {
                    case ConfigFileScanner.ACTION_ADD:
                        propInfoMap.put(HashKit.md5(file), new PropInfoMap.PropInfo(new File(file)));
                        break;
                    case ConfigFileScanner.ACTION_DELETE:
                        propInfoMap.remove(HashKit.md5(file));
                        break;
                    case ConfigFileScanner.ACTION_UPDATE:
                        propInfoMap.put(HashKit.md5(file), new PropInfoMap.PropInfo(new File(file)));
                        break;
                }
            }
        };

        configFileScanner.start();
    }

    public PropInfoMap getPropInfoMap() {
        return propInfoMap;
    }

    public void destroy() {
        if (configFileScanner != null) {
            configFileScanner.stop();
        }
    }

}
