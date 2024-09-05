package com.jemmy.dp.chain;

import java.math.BigDecimal;

/**
 * @author zhujiang.cheng
 * @since 2022/4/21
 */
public class Request {

    private String name;

    private BigDecimal amount;

    public Request(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
