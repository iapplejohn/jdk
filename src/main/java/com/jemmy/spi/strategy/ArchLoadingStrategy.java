package com.jemmy.spi.strategy;

import com.jemmy.spi.LoadingStrategy;

/**
 * @author zhujiang.cheng
 * @since 2023/6/1
 */
public class ArchLoadingStrategy implements LoadingStrategy {

    @Override
    public String directory() {
        return "META-INF/arch/";
    }

    @Override
    public boolean overridden() {
        return true;
    }

    @Override
    public int getPriority() {
        return NORMAL_PRIORITY;
    }
}
