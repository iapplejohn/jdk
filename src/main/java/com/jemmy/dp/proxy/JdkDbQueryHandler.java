/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: JdkDbQueryHandler.java
 * Author:   Cheng Zhujiang
 * Date:     2017/9/19 14:08
 * Description: 
 */
package com.jemmy.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <pre>
 * JdkDbQueryHandler
 *
 * @author Cheng Zhujiang
 * @date 2017/9/19
 */
public class JdkDbQueryHandler implements InvocationHandler {

    IDBQuery real = null; // 主题接口

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (real == null) {
            real = new DBQuery(); // 如果是第一次调用，则生成真实对象
        }
        return real.request(); // 使用真实主题完成实际的操作
    }
}
