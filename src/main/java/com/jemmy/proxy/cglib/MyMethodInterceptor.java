package com.jemmy.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * https://www.cnblogs.com/lvbinbin2yujie/p/10284316.html
 *
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // o 是代理后的子类，method 是调用方法，args是方法入参，proxy 是MethodProxy 代理对象
        System.out.println("myMethodInterceptor invoked ");
        Object result = methodProxy.invokeSuper(o, args);
        return result;
    }
}
