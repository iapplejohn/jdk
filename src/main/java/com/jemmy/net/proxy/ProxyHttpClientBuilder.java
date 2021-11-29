package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author zhujiang.cheng
 * @since 2021/10/29
 */
public class ProxyHttpClientBuilder {

    private ProxyConfigProvider configProvider;

    private ProxyConfig proxyConfig;

    public ProxyHttpClientBuilder(String boardingIp, int boardingPort, String username, String password) {
        Proxy proxy = new Proxy(Type.SOCKS, InetSocketAddress.createUnresolved(boardingIp, boardingPort));
        this.proxyConfig = new ProxyConfig(proxy, new PasswordAuthentication(username, password.toCharArray()));
        this.configProvider = new ProxyConfigProvider() {
            @Override
            public ProxyConfig getProxyConfig() {
                return ProxyHttpClientBuilder.this.proxyConfig;
            }
        };
    }

    public static final X509TrustManager NOP_TRUST_MANAGER = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    public static final HostnameVerifier NOP_HOSTNAME_VERIFIER = new HostnameVerifier() {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    };

    private static SSLContext NOP_TLSV12_SSL_CONTEXT;

//    static {
//        try {
//            NOP_TLSV12_SSL_CONTEXT = SSLContext.getInstance("TLSv1.2");
//            NOP_TLSV12_SSL_CONTEXT.init(null, new TrustManager[]{NOP_TRUST_MANAGER}, new SecureRandom());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//    }

    public OkHttpClient buildOkHttpClient() throws NoSuchAlgorithmException {
        OkHttpClient client = new OkHttpClient.Builder()
//            .sslSocketFactory(new ProxySSLSocketFactory(configProvider, SSLContext.getDefault().getSocketFactory()), NOP_TRUST_MANAGER)
            .socketFactory(new ProxySocketFactory(configProvider))
            .hostnameVerifier(NOP_HOSTNAME_VERIFIER)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new OKHttpProxyInterceptor())
            .build();
        return client;
    }

    private class OKHttpProxyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            ProxyConfig proxyConfig = ProxyHttpClientBuilder.this.getProxyConfig();
            boolean clearCredentials = false;
            if (proxyConfig != null) {
                if (proxyConfig.getAuthentication() != null) {
                    ThreadLocalProxyAuthenticator.setCredentials(proxyConfig.getAuthentication());
                    Authenticator.setDefault(new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return proxyConfig.getAuthentication();
                        }
                    });
                    clearCredentials = true;
                }
            }
            try {
                return chain.proceed(chain.request());
            } finally {
                if (clearCredentials) {
                    ThreadLocalProxyAuthenticator.clearCredentials();
                }
            }
        }
    }

    public ProxyConfigProvider getConfigProvider() {
        return configProvider;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }
}
