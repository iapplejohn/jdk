package com.jemmy.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class MyMethodInterceptor2 implements MethodInterceptor {

    private Object target;

    public MyMethodInterceptor2(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("myMethodInterceptor invoked ");
        Object result = methodProxy.invoke(target, args);
        return result;
    }
}
