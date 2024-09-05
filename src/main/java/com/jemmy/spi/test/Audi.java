package com.jemmy.spi.test;

/**
 * @author zhujiang.cheng
 * @since 2023/5/20
 */
public class Audi implements Car {

    @Override
    public void run() {
        System.out.println("Audi is running");
    }
}
