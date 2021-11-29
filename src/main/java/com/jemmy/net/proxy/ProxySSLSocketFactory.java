package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author zhujiang.cheng
 * @since 2021/10/29
 */
public class ProxySSLSocketFactory extends SSLSocketFactory {

    private ProxyConfigProvider configProvider;
    private SSLSocketFactory socketFactory;

    public ProxySSLSocketFactory(ProxyConfigProvider configProvider, SSLSocketFactory socketFactory) {
        this.configProvider = configProvider;
        this.socketFactory = socketFactory;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return socketFactory.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return socketFactory.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket() throws IOException {
        ProxyConfig proxyConfig = configProvider.getProxyConfig();
        if (proxyConfig != null) {
            return new Socket(proxyConfig.getProxy());
        } else {
            return new Socket();
        }
    }

    @Override
    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
        return null;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        Socket socket = createSocket();

        try {
            return socketFactory.createSocket(socket, host, port, true);
        } catch (IOException e) {
            socket.close();
            throw e;
        }
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress clientAddress, int clientPort)
        throws IOException, UnknownHostException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            return socketFactory.createSocket(socket, host, port, true);
        } catch (IOException e) {
            socket.close();
            throw e;
        }
    }

    @Override
    public Socket createSocket(InetAddress address, int port) throws IOException {
        Socket socket = createSocket();
        try {
            return socketFactory.createSocket(socket, address.getHostAddress(), port, true);
        } catch (IOException e) {
            socket.close();
            throw e;
        }
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort) throws IOException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            return socketFactory.createSocket(socket, address.getHostAddress(), port, true);
        } catch (IOException e) {
            socket.close();
            throw e;
        }
    }
}
