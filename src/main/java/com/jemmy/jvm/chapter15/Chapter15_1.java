package com.jemmy.jvm.chapter15;

import java.util.ArrayList;

/**
 * @author zhujiang.cheng
 * @since 2020/5/27
 */
public class Chapter15_1 {

    public int foo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int result = list.get(0);
        return result;
    }

}
