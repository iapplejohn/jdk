package com.jemmy.spi.test;

/**
 * @author zhujiang.cheng
 * @since 2020/9/8
 */
public class ShutdownCmd implements Cmd {

    @Override
    public void execute() {
        System.out.println("shutdown ...");
    }
}
