package com.jemmy.jvm.chapter15;

/**
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class SwitchString {

    public void foo(String str) {
        switch (str) {
            case "fabulous":
                break;
            case "wonderful":
            default:
                break;
        }
    }
}
