package com.jemmy.spi.test;

/**
 * @author zhujiang.cheng
 * @since 2023/5/20
 */
public class Skoda implements Car {

    @Override
    public void run() {
        System.out.println("Skoda is running");
    }
}
