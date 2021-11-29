package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
public class OKHttpProxyInterceptor implements Interceptor {

    private static final String boardingIp = "1.12.231.152";

    private static final int boardingPort = 58133;

    private static final String username = "xfggenlb";

    private static final String password = "JqY5lNNFeOBcxx";

    private Proxy proxy = new Proxy(Type.SOCKS, InetSocketAddress.createUnresolved(boardingIp, boardingPort));

    @Override
    public Response intercept(Chain chain) throws IOException {
        ProxyConfig proxyConfig = new ProxyConfig(proxy, new PasswordAuthentication(username, password.toCharArray()));
        boolean clearCredentials = false;
        if (proxyConfig != null) {
            if (proxyConfig.getAuthentication() != null) {
                ThreadLocalProxyAuthenticator.setCredentials(proxyConfig.getAuthentication());
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
