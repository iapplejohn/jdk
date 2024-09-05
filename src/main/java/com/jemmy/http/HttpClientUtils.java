package com.jemmy.http;

import com.alibaba.fastjson.JSONObject;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * java使用HttpClient发送post请求并携带JSON参数
 * https://blog.csdn.net/jianxiualiang/article/details/120350705
 */
//模拟浏览器发送http请求完成固定地址的请求
public class HttpClientUtils {

    /**
     * 发送post请求
     */
    public static JSONObject doPost(String url, JSONObject json) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("Authorization",
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYaWFuZmVuZyIsImRpc3RyaWJ1dGUiOjE2ODQwNjMxNDUsImFwcF9pZCI6IjgwODMzMTkxMzc5NTUxIiwidmVyc2lvbiI6InYxIn0.EWK-EFZ-trfHqSh4f5wU8xDpi1zN603XdXxDT96tX7Y");
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString(), StandardCharsets.UTF_8);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}