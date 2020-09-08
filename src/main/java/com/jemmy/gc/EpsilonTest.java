package com.jemmy.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 11
 * -XX:+UnlockExperimentalVMOptions
 * -XX:+UseEpsilonGC
 *
 * @author zhujiang.cheng
 * @since 2020/5/17
 */
public class EpsilonTest {

    public static void main(String[] args) {
        boolean flag = true;
        List<Garbage> garbageList = new ArrayList<>();
        int count = 0;
        while (flag) {
            garbageList.add(new Garbage());
            if (count++ == 500) {
                garbageList.clear();
            }
        }
    }
}

class Garbage {
    private double d1 = 1;
    private double d2 = 2;

    /**
     * 在GC清除对象时会调用一次
     */
    @Override
    protected void finalize() {
        System.out.println(this + " collecting");
    }
}
