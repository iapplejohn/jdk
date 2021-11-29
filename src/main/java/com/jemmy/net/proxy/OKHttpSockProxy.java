package com.jemmy.net.proxy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
@Slf4j
@Component
public class OKHttpSockProxy {

    static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new OKHttpProxyInterceptor())
            .connectionPool(new ConnectionPool(10, 60, TimeUnit.SECONDS))
            .build();
    }

    public String exec(String boardingIp, int boardingPort, String username, String password) throws IOException {
        Request request = new Request.Builder()
            .url("https://checkip.amazonaws.com")
            .get()
            .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        String result = response.body().string();
        log.info("result:{}", result);
        return result;
    }

    public String crawler(String boardingIp, int boardingPort, String username, String password, String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        Call call = client.newCall(request);
        long start = System.currentTimeMillis();
        Response response = call.execute();
        String result = response.body().string();
        long elapse = System.currentTimeMillis() - start;
        Files.write(Paths.get("crawler_result.html"), result.getBytes(StandardCharsets.UTF_8));
        log.info("URL: {}, Response code:{}, cost {} ms", url, response.code(), elapse);
        return result;
    }

}
