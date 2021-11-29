package com.jemmy.proxy.spring;

/**
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class BaseClass {

    private A a = new A();

    public Object test(String str) {
        System.out.println("str = " + str);

        TestImpl impl = new TestImpl();
        a.fetch(str, impl);
        return "good";
    }

}
