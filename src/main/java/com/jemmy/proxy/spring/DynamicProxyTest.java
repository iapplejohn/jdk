package com.jemmy.proxy.spring;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        BaseClass baseClass = new BaseClass();
        BaseClass proxy = createCglibDynamicProxy(baseClass);
        proxy.test("fantastic");
    }

    private static BaseClass createCglibDynamicProxy(BaseClass delegate) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setSuperclass(BaseClass.class);
        BaseClass cglibProxy = (BaseClass) enhancer.create();
        return cglibProxy;
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        CglibInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(obj, args);
        }
    }
}
