package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2021/10/27
 */
@Slf4j
@Component
public class SockProxy {

    private static final ThreadLocal<Authenticate> authenticate_local = new ThreadLocal<>();

    static {
        Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                Authenticate auth = authenticate_local.get();
                return new PasswordAuthentication(auth.getUsername(), auth.getPassword().toCharArray());
            }
        });
    }

    public String exec(String boardingIp, int boardingPort, String username, String password) throws IOException {
        authenticate_local.set(new Authenticate(username, password));

        Proxy proxy = new Proxy(Type.SOCKS, InetSocketAddress.createUnresolved(boardingIp, boardingPort));
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .proxy(proxy)
            .build();

        Request request = new Request.Builder()
            .url("https://checkip.amazonaws.com")
            .get()
            .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        String result = response.body().string();
        log.info("result:{}", result);


        Request request2 = new Request.Builder()
            .url("https://www.amazon.com/dp/B082P2GZR1?th=1&psc=1")
            .get()
            .build();

        Call call1 = httpClient.newCall(request2);
        Response response1 = call1.execute();
        String result1 = response1.body().string();

        return result;
    }

    public String crawler(String boardingIp, int boardingPort, String username, String password, String url) throws IOException {
        authenticate_local.set(new Authenticate(username, password));


        Proxy proxy = new Proxy(Type.SOCKS, InetSocketAddress.createUnresolved(boardingIp, boardingPort));
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .proxy(proxy)
            .build();

        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        Call call = httpClient.newCall(request);
        long start = System.currentTimeMillis();
        Response response = call.execute();
        String result = response.body().string();
        long elapse = System.currentTimeMillis() - start;
        Files.write(Paths.get("crawler_result.html"), result.getBytes(StandardCharsets.UTF_8));
        log.info("URL: {}, Response code:{}, cost {} ms", url, response.code(), elapse);
        return result;
    }

    @Data
    @AllArgsConstructor
    static class Authenticate {
        private String username;
        private String password;
    }
}
