package com.jemmy.http;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * 测试 OkHttpClient 的创建
 * 1. okHttpClient.newBuilder()
 * 2. new OkHttpClient.newBuilder()
 * 两者有什么区别？
 *
 *
 * @author zhujiang.cheng
 * @since 2022/1/24
 */
public class OkHttpClientTest {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) {
        OkHttpClient okHttpClient2 = okHttpClient.newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

        OkHttpClient okHttpClient3 = okHttpClient.newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(80, TimeUnit.SECONDS)
            .build();

        OkHttpClient okHttpClient4 = new OkHttpClient().newBuilder()
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

        System.out.println(okHttpClient2 == okHttpClient3);
        System.out.println(okHttpClient);
    }
}
