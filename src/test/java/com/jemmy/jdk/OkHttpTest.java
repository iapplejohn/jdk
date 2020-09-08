package com.jemmy.jdk;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2020/4/20
 */
public class OkHttpTest {

    OkHttpClient okHttpClient = new OkHttpClient();

    @Test
    public void heartBeatTest() {
        String host = "172.16.49.61";
        Integer httpPort = 8010;

        String url = "http://" + host + ":" + httpPort + "/heart.jsp";
        Long timeCost;
        try {
            Request request = new Request.Builder()
                .url(url)
                .build();
            Call call = okHttpClient.newCall(request);
            long start = DateTime.now().getMillis();
            Response response = call.execute();
            long end = DateTime.now().getMillis();

            timeCost = end - start;

            String appResult = response.body().string();
            if (appResult == null || !"1".equals(StringUtils.trim(appResult))) {
                System.out.printf("心跳检测失败，耗时%d毫秒", timeCost);
            } else {
                System.out.printf("心跳检测成功，耗时%d毫秒", timeCost);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
