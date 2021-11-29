package com.jemmy.proxy.cglib;

import java.io.IOException;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class TestApp {

    public static void main(String[] args) throws IOException {
//        invokeSuper();
        invoke();
        System.in.read();
    }

    private static void invokeSuper() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Target t = (Target) enhancer.create();
        t.a();
    }

    private static void invoke() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/chengzhujiang/Documents");

        Target target = new Target();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MyMethodInterceptor2(target));
        Target t = (Target) enhancer.create();
        t.a();
    }
}
