package com.jemmy.http;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author chengzhujiang create at 2018/11/30 上午10:23
 */
public class OkHttpClientUtil {

    private static final MediaType MEDIA_TYPE_APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient;

    static {
        X509TrustManager trustManager = systemDefaultTrustManager();
        SSLSocketFactory sslSocketFactory = tlsV1_2SslSocketFactory(trustManager); // JDK7默认启用TLSv1.0(因安全合规已禁用),手动启用TLSv1.2,JDK8不受影响

        okHttpClient = new OkHttpClient.Builder()
            .readTimeout(70, TimeUnit.SECONDS)
//            .sslSocketFactory(sslSocketFactory, trustManager)
            .build();
    }

    private static X509TrustManager systemDefaultTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore)null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) {
                return (X509TrustManager)trustManagers[0];
            } else {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("No System TLS", e);
        }
    }

    private static SSLSocketFactory tlsV1_2SslSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("The system has no TLSv1.2"); // The system has no TLS. Just give up.
        }
    }

    /**
     * get方式请求url
     *
     * @param url 请求链接
     * @return 响应字符串
     * @throws IOException ~
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();
        Call call = okHttpClient.newCall(request);
        return doExecute(call);
    }

    /**
     * get方式请求url
     *
     * @return 响应字符串
     * @throws IOException ~
     */
    public static String get(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        return doExecute(call);
    }

    /**
     * get方式请求url
     *
     * @param url 请求链接
     * @return 响应对象
     * @throws IOException ~
     */
    public static Response getWithResponse(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public static Response getWithResponse(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    /**
     * post方式请求url
     *
     * @param url 请求链接
     * @param content 请求内容
     * @return 响应字符串
     * @throws IOException ~
     */
    public static String post(String url, String content) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON, content))
            .build();
        Call call = okHttpClient.newCall(request);
        return doExecute(call);
    }

    /**
     * post方式请求url
     *
     * @param url 请求链接
     * @param content 请求JSON字符串
     * @return 响应对象
     * @throws IOException ~
     */
    public static Response postJson(String url, String content) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(MEDIA_TYPE_APPLICATION_JSON, content))
            .build();
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    private static String doExecute(Call call) throws IOException {
        Response response;
        response = call.execute();
        if (response.body() != null) {
            return response.body().string();
        }
        return null;
    }
}
