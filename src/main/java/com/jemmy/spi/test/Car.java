package com.jemmy.spi.test;

import com.jemmy.spi.annotation.Spi;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author zhujiang.cheng
 * @since 2023/5/20
 */
@SPI
@Spi("Skoda")
public interface Car {

    void run();

}
