package com.jemmy.limit;

import java.util.concurrent.TimeUnit;

/**
 * 简单限流器
 *
 * @author zhujiang.cheng
 * @since 2022/6/18
 */
public class SimpleLimiter {
    // 下一令牌产生时间
    long next = System.nanoTime();

    // 发放令牌间隔：纳秒
    long interval = 1000_000_000;
    // 预占令牌，返回能够获取令牌的时间
    synchronized long reverse(long now) {
        // 请求时间在下一令牌产生时间之后
        // 重新计算下一令牌产生时间
        if (now > next) {
            // 将下一令牌产生时间重置为当前时间
            next = now;
        }
        // 能够获取令牌的时间
        long at = next;
        // 设置下一令牌产生时间
        next += interval;
        // 返回线程需要等待的时间
        return Math.max(at, 0L);
    }

    void acquire() {
        // 申请令牌的时间
        long now = System.nanoTime();
        // 预占令牌
        long at = reverse(now);
        long waitTime = Math.max(at - now, 0);
        // 按照条件等待
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
