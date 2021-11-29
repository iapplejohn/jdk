package com.jemmy.net.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

/**
 * @author zhujiang.cheng
 * @since 2021/10/29
 */
public class ProxySocketFactory extends SocketFactory {

    private ProxyConfigProvider proxyConfigProvider;

    public ProxySocketFactory(ProxyConfigProvider proxyConfigProvider) {
        this.proxyConfigProvider = proxyConfigProvider;
    }

    @Override
    public Socket createSocket() throws IOException {
        ProxyConfig proxyConfig = proxyConfigProvider.getProxyConfig();
        if (proxyConfig != null) {
            return new Socket(proxyConfig.getProxy());
        } else {
            return new Socket();
        }
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        Socket socket = createSocket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress clientAddress, int clientPort)
        throws IOException, UnknownHostException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress address, int port) throws IOException {
        Socket socket = createSocket();
        try {
            socket.connect(new InetSocketAddress(address, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress clientAddress, int clientPort) throws IOException {
        Socket socket = createSocket();
        try {
            socket.bind(new InetSocketAddress(clientAddress, clientPort));
            socket.connect(new InetSocketAddress(address, port));
        } catch (IOException e) {
            socket.close();
            throw e;
        }

        return socket;
    }
}
