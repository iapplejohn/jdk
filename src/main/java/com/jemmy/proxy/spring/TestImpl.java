package com.jemmy.proxy.spring;

/**
 * @author zhujiang.cheng
 * @since 2021/6/9
 */
public class TestImpl implements TestInterface {

    @Override
    public String doSth() {
        System.out.println("do something");
        return "success";
    }
}
