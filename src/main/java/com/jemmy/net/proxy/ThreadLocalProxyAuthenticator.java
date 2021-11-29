package com.jemmy.net.proxy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
public class ThreadLocalProxyAuthenticator extends Authenticator {

    private static ThreadLocal<PasswordAuthentication> credentials = new ThreadLocal<>();

    private static class SingletonHolder {
        private static final ThreadLocalProxyAuthenticator instance = new ThreadLocalProxyAuthenticator();
    }

    public static final ThreadLocalProxyAuthenticator getInstance() {
        return SingletonHolder.instance;
    }

    public static void setCredentials(PasswordAuthentication authentication) {
        credentials.set(authentication);
    }

    public static void clearCredentials() {
        ThreadLocalProxyAuthenticator authenticator = ThreadLocalProxyAuthenticator.getInstance();
        Authenticator.setDefault(authenticator);
        authenticator.credentials.set(null);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return credentials.get();
    }
}
