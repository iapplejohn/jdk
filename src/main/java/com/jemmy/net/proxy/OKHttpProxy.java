package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
@Slf4j
@Component
public class OKHttpProxy {

    private static final String boardingIp = "1.12.217.49";

    private static final int boardingPort = 58278;

    private static final String username = "xfggenlb";

    private static final String password = "JqY5lNNFeOBc";

    static OkHttpClient client;

    static {
        Proxy proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(boardingIp, boardingPort));

        Authenticator proxyAuthenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
//                    .header("Proxy-Authorization", credential)
                    .header("Authorization", credential)
                    .build();
            }
        };

        client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .proxy(proxy)
            .proxyAuthenticator(proxyAuthenticator)
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
