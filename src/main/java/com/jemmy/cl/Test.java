package com.jemmy.cl;

/**
 * @author zhujiang.cheng
 * @since 2020/9/13
 */
public class Test {

    static {
        // 给变量赋值可以正常编译通过
        i = 0;

        // 这句编译器会提示"非法向前引用"
//        System.out.println(i);
    }

    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
