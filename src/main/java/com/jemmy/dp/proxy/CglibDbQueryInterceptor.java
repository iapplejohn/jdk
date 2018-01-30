/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: CglibDbQueryInterceptor.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/19 15:18
 * Description: 
 */
package com.jemmy.dp.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <pre>
 * CglibDbQueryInterceptor
 *
 * @author Cheng Zhujiang
 * @date 2017/9/19
 */
public class CglibDbQueryInterceptor implements MethodInterceptor {

    IDBQuery real = null;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request();
    }
}
