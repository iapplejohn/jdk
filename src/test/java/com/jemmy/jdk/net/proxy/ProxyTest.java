package com.jemmy.jdk.net.proxy;

import com.jemmy.net.proxy.ProxyHttpClientBuilder;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author zhujiang.cheng
 * @since 2021/10/29
 */
@Slf4j
public class ProxyTest {

    @Test
    public void testIp() throws IOException, NoSuchAlgorithmException {
        String boardingIp = "1.12.217.49";
        int boardingPort = 58278;
        String username = "xfggenlb";
        String password = "JqY5lNNFeOBc";

        ProxyHttpClientBuilder builder = new ProxyHttpClientBuilder(boardingIp, boardingPort, username, password);
        OkHttpClient client = builder.buildOkHttpClient();
        Request request = new Request.Builder()
            .url("https://checkip.amazonaws.com")
            .get()
            .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        String result = response.body().string();
        log.info("result:{}", result);
        Assertions.assertNotNull(request);
    }
}
