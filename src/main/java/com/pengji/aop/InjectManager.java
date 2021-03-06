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
package com.pengji.aop;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.pengji.aop.annotation.Bean;
import com.pengji.utils.ClassScanner;

/**
 * Inject管理器
 */
public class InjectManager implements com.google.inject.Module, TypeListener {

    /**
     * 这个manager的创建不能来之ClassNewer
     * 因为 ClassNewer 需要 JbootInjectManager，会造成循环调用。
     */
    private static InjectManager manager = new InjectManager();

    public static InjectManager me() {
        return manager;
    }


    private Injector injector; 

    private InjectManager() {
        injector = Guice.createInjector(this);
    }


    public Injector getInjector() {
        return injector;
    }


    /**
     * module implements
     *
     * @param binder
     */
    @Override
    public void configure(Binder binder) {


        // 设置 TypeListener
        binder.bindListener(Matchers.any(), this);
  
        // 设置 Jfinal AOP 相关的拦截器
/*        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Before.class), new JFinalBeforeInterceptor());
        binder.bindInterceptor(Matchers.annotatedWith(Before.class), Matchers.any(), new JFinalBeforeInterceptor());

        // 设置缓存相关的拦截器
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cacheable.class), new JbootCacheInterceptor());
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(CacheEvict.class), new JbootCacheEvictInterceptor());
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(CachePut.class), new JbootCachePutInterceptor());
*/
        /**
         * Bean 注解
         */
        beanBind(binder);
    }

    /**
     * auto bind interface impl
     *
     * @param binder
     */
    private void beanBind(Binder binder) {
        List<Class> classes = ClassScanner.scanClassByAnnotation(Bean.class, true);
        for (Class beanClass : classes) {
            Class<?>[] interfaceClasses = beanClass.getInterfaces();
            for (Class interfaceClass : interfaceClasses) {
                if (interfaceClass == Serializable.class) {
                    continue;
                }
                try {
                    binder.bind(interfaceClass).to(beanClass);
                } catch (Throwable ex) {
                    System.err.println(String.format("can not bind [%s] to [%s]", interfaceClass, beanClass));
                }
            }
        }
    }

    /**
     * TypeListener  implements
     *
     * @param type
     * @param encounter
     * @param <I>
     */
    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        Class clazz = type.getRawType();
        if (clazz == null) return;

/*        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(JbootrpcService.class)) {
                encounter.register(new JbootrpcMembersInjector(field));
            }
        }*/
    }
}
