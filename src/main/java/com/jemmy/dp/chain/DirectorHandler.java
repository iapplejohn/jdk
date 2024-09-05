package com.jemmy.dp.chain;

import java.math.BigDecimal;

/**
 * @author zhujiang.cheng
 * @since 2022/4/21
 */
public class DirectorHandler implements Handler {

    @Override
    public Boolean process(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0) {
            return null;
        }
        return !request.getName().equals("jim");
    }
}
