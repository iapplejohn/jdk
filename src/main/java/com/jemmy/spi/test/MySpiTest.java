package com.jemmy.spi.test;

import com.jemmy.spi.ExtLoader;

/**
 * @author zhujiang.cheng
 * @since 2023/5/20
 */
public class MySpiTest {

    public static void main(String[] args) {
//        ExtLoader<Car> extLoader = getCarExtLoader();
        ExtLoader<Car> extLoader = getCarExtLoaderStatic();

        // 获取斯柯达的实现
//        Car skoda = extLoader.getExtension("Skoda");
        Car skoda = extLoader.getDefaultExtension();

        // 运行
        skoda.run();
    }

    private static ExtLoader<Car> getCarExtLoader() {
        return new ExtLoader<>(Car.class);
    }

    private static ExtLoader<Car> getCarExtLoaderStatic() {
        return ExtLoader.getExtensionLoader(Car.class);
    }
}
