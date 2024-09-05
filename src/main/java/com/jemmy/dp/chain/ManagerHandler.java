package com.jemmy.dp.chain;

import java.math.BigDecimal;

/**
 * @author zhujiang.cheng
 * @since 2022/4/21
 */
public class ManagerHandler implements Handler {

    @Override
    public Boolean process(Request request) {
        // 如果超过1000元，处理不了，交下一个处理:
        if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            return null;
        }
        // 对Bob有偏见:
        return !request.getName().equalsIgnoreCase("bob");
    }
}
