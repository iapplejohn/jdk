package com.jemmy.jvm.chapter15;

/**
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class Finally {

    public void foo() {
        try {
            System.out.println("Brilliant");
        } finally {
            System.out.println("finally");
        }
    }
}
