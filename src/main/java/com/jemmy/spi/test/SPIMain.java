package com.jemmy.spi.test;

import java.util.ServiceLoader;

/**
 * @author zhujiang.cheng
 * @since 2020/9/8
 */
public class SPIMain {

    public static void main(String[] args) {
//        cmdSpi();
        carSpi();
    }

    private static void cmdSpi() {
        ServiceLoader<Cmd> loader = ServiceLoader.load(Cmd.class);
        System.out.println(loader);

        for (Cmd cmd : loader) {
            cmd.execute();
        }
    }

    private static void carSpi() {
        ServiceLoader<Car> loader = ServiceLoader.load(Car.class);
        System.out.println(loader);

        for (Car car : loader) {
            car.run();
        }
    }
}
