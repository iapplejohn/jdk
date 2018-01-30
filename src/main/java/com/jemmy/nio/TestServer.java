/*
 * Copyright (C), 2014-2017, 杭州盎然科技有限公司
 * FileName: TestServer.java
 * Author:   Cheng Zhujiang
 * Date:     2017/11/6 18:47
 * Description: 
 */
package com.jemmy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <pre>
 * TestServer
 *
 * @author Cheng Zhujiang
 * @date 2017/11/6
 */
public class TestServer {

    private static final long SLEEP_PERIOD = 5000L; // 5 seconds
    private static final int BUFFER_SIZE = 8192;
    private int port;

    public TestServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Throwable {
        if (args.length < 1) {
            System.err.println("Usage : java TestServer <port>");
            System.exit(0);
        }

        new TestServer(Integer.parseInt(args[0])).start();
    }

    public void start() throws Throwable {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket server = serverChannel.socket();
        server.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        SocketChannel clientChanel = null;

        System.out.println("0. Server started to listen");
        boolean writeNow = false;

        while (true) {
            try {
                // wait for selection
                int numKeys = selector.select();

                if (numKeys == 0) {
                    System.err.println("selector wakes up with zero");
                }

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey selected = it.next();
                    int ops = selected.interestOps();

                    try {
                        // process new connection
                        if ((ops & SelectionKey.OP_ACCEPT) != 0) {
                            clientChanel = serverChannel.accept();
                            clientChanel.configureBlocking(false);

                            // register channel to selector
                            clientChanel.register(selector, SelectionKey.OP_READ, null);
                            System.out.println("2. Server accepted and register read op: client-" + clientChanel.socket().getInetAddress());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
