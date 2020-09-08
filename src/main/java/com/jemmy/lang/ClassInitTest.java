package com.jemmy.lang;

/**
 * @author zhujiang.cheng
 * @since 2020/4/3
 */
public class ClassInitTest {

    static {
        System.out.println("........");
    }

    public static void main(String[] args) {
        System.out.println("测试代码开始");
        new Child();
        System.out.println("测试代码结束");
    }

}
