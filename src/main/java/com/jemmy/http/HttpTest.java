package com.jemmy.http;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhujiang.cheng
 * @since 2021/4/14
 */
public class HttpTest {

    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQaW5nUG9uZyIsImRpc3RyaWJ1dGUiOjE2MTg0MDA1MTksImFwcF9pZCI6IjgwMzg2MTg3MTY3NDQ5IiwidmVyc2lvbiI6InYyIn0.A8wSgoIQLyncWHrQyduva1JxHhsZFWAl9704PH-IijY";

    public static void main(String[] args) throws IOException {
        getKeywords("一眼レフ モニター", "JP", token, 1);
//        getKeywords("oppo", "US", token, 1);
    }

    private static void getKeywords(String keyword, String region, String token, Integer pageNo) throws IOException {
        Headers.Builder headerBuilder = new Builder();
        headerBuilder.add("Authorization", "Bearer " + token);
        headerBuilder.add("Content-Type", "application/json;charset=utf8");
        Headers headers = headerBuilder.build();

        StringBuilder builder = new StringBuilder(128);
        builder.append("https://gw.oalur.com/v2/oalur/v1/asin/keyword?keyword=")
            .append(keyword).append("&region=").append(region).append("&page_no=").append(pageNo);

        Request request = new Request.Builder()
            .url(builder.toString())
            .addHeader("Authorization", "Bearer " + token)
            .addHeader("Content-Type", "application/json;charset=utf8")
            .build();

        long start = System.currentTimeMillis();
        Response response = OkHttpClientUtil.getWithResponse(request);
        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(response.body().string());
    }
}
