package com.pengji.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings({"rawtypes","unchecked"})
public class UserUtils {
	  /** * 保存变量的ThreadLocal，保持在同一线程中同步数据. */
   
	private static final ThreadLocal SESSION_MAP = new ThreadLocal();

    /** * 工具类的protected构造方法. */
    protected UserUtils() {
    }

    /**
     * 获得线程中保存的属性.
     * 
     * @param attribute
     *            属性名称
     * @return 属性值
     */
    public static Object get(String attribute) {
        Map map = (Map) SESSION_MAP.get();
        System.out.println(map.toString());
        System.out.println(map.containsKey("user"));

        return map.get(attribute);
    }

    /**
     * 获得线程中保存的属性，使用指定类型进行转型.
     * 
     * @param attribute
     *            属性名称
     * @param clazz
     *            类型
     * @param <T>
     *            自动转型
     * @return 属性值
     */
	public static <T> T get(String attribute, Class<T> clazz) {
        return (T) get(attribute);
    }

    /**
     * 设置制定属性名的值.
     * 
     * @param attribute
     *            属性名称
     * @param value
     *            属性值
     */
    public static void set(String attribute, Object value) {
        Map map = (Map) SESSION_MAP.get();

        if (map == null) {
            map = new HashMap();
            SESSION_MAP.set(map);
        }

        map.put(attribute, value);
    }
   
    

	public static void preInsert(Model entity) {
/*		SessionUser<SysUser> u  =  (SessionUser<SysUser>) UserUtils.get("user");
		entity.set("createBy", u.getId());
		entity.set("createDate", new Date());
		entity.set("updateBy", u.getId());
		entity.set("updateDate", new Date());
		entity.set("delFlag","0");*/
	}

	public static void preUpdate(Model entity) {
/*		SessionUser<SysUser> u  =  (SessionUser<SysUser>) UserUtils.get("user");
		entity.set("updateBy", u.getId());
		entity.set("updateDate", new Date());*/
	}
   
}
