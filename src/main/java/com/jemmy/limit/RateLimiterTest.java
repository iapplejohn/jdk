package com.jemmy.limit;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2022/11/8
 */
public class RateLimiterTest {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterTest.class);

    private final RateLimiter rateLimiter = RateLimiter.create(2, 1, TimeUnit.SECONDS);

    public void test() throws InterruptedException {
        while (true) {
            double d = rateLimiter.acquire();
            log.info("acquire {} permits", d);
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }


}
