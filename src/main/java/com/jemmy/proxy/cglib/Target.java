package com.jemmy.proxy.cglib;

/**
 * @author zhujiang.cheng
 * @since 2021/6/10
 */
public class Target {

    public void a() {
        System.out.println(this);
        System.out.println(" a 方法");
        b();
    }

    public void b() {
        System.out.println(" b 方法");
    }
}
