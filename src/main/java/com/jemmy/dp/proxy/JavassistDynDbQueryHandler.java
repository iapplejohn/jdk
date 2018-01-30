/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: JavassistDynDbQueryHandler.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/23 8:10
 * Description: 
 */
package com.jemmy.dp.proxy;

import javassist.*;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * <pre>
 * JavassistDynDbQueryHandler
 *
 * @author Cheng Zhujiang
 * @date 2017/9/23
 */
public class JavassistDynDbQueryHandler implements MethodHandler {

    IDBQuery real = null;

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        if (real == null) {
            real = new DBQuery();
        }
        return real.request();
    }
}
