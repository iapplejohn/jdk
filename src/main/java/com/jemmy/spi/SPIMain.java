package com.jemmy.spi;

import java.util.ServiceLoader;

/**
 * @author zhujiang.cheng
 * @since 2020/9/8
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<Cmd> loader = ServiceLoader.load(Cmd.class);
        System.out.println(loader);

        for (Cmd cmd : loader) {
            cmd.execute();
        }
    }
}
