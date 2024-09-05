package com.jemmy.http;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;



public class DemoTest {
    private static final String ACCOUNT = "y8_1719295995-sh-26408529860608";
    private static final String PASSWORD = "282fa573";
    private static final String PROXY_HOST = "175.178.27.5";
    private static final int PROXY_PORT = 59999;
    private static final String TARGET_URL = "https://checkip.amazonaws.com";

//    private static final String ACCOUNT = "oejvdttc";
//    private static final String PASSWORD = "evjZQIpKpTTC";
//    private static final String PROXY_HOST = "111.230.10.122";
//    private static final int PROXY_PORT = 25911;
//    private static final String TARGET_URL = "http://checkip.amazonaws.com";

    public static void main(String[] args) throws IOException {
        testSocks5Proxy();
    }

    /**
     * Socks5代理
     */
    private static void testSocks5Proxy() throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        //设置全局默认的认证器（Authenticator），用于处理所有网络连接需要的基本身份验证。这里预设了一个用户名和密码：
        java.net.Authenticator.setDefault(new java.net.Authenticator() {
            private final java.net.PasswordAuthentication authentication =
                    new java.net.PasswordAuthentication(ACCOUNT, PASSWORD.toCharArray());

            @Override
            protected java.net.PasswordAuthentication getPasswordAuthentication() {//当网络连接需要身份验证时，系统会调用此方法获取认证信息。
                return authentication;
            }
        });

        X509TrustManager trustManager = systemDefaultTrustManager();

        OkHttpClient client = new OkHttpClient.Builder()
//            .sslSocketFactory(tlsSslSocketFactory(trustManager), trustManager)
            .proxy(proxy)
                .build();
        Request request = new Request.Builder().url(TARGET_URL).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);

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

    private static SSLSocketFactory tlsSslSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("The system has no TLS"); // The system has no TLS. Just give up.
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

    private static SSLSocketFactory tlsV1_3SslSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("The system has no TLSv1.3"); // The system has no TLS. Just give up.
        }
    }

}
