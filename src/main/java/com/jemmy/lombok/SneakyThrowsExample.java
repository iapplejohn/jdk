package com.jemmy.lombok;

import java.io.UnsupportedEncodingException;
import lombok.SneakyThrows;

/**
 * https://juejin.cn/post/6940572345589301262
 *
 * @author zhujiang.cheng
 * @since 2022/4/14
 */
public class SneakyThrowsExample implements Runnable {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "utf-8");
    }

    @SneakyThrows
    @Override
    public void run() {
        throw new Throwable();
    }
}
