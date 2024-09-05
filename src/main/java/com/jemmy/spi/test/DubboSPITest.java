package com.jemmy.spi.test;

import com.jemmy.spi.test.Car;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author zhujiang.cheng
 * @since 2023/5/20
 */
public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car skoda = extensionLoader.getExtension("Skoda");
        skoda.run();
    }
}
