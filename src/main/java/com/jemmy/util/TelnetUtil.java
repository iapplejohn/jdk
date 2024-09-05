package com.jemmy.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Telnet 工具类
 *
 * @author zhujiang.cheng
 * @since 2023/9/8
 */
public class TelnetUtil {

    private static final Logger log = LoggerFactory.getLogger(TelnetUtil.class);

    private static final int CACHE_LENGTH = 32;

    public static Socket telnet(String hostname, int port, int timeout) {
        Socket socket = new Socket();

        try {
            socket.connect(new InetSocketAddress(hostname, port), timeout);
        } catch (Throwable t) {
            log.error("Failed to connect to hostname: " + hostname + " port: " + port, t);
        }

        return socket;
    }

    public static String send(Socket socket, String command) {
        try {
            OutputStream stream = socket.getOutputStream();
            stream.write(command.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            InputStream inputStream = socket.getInputStream();
            byte[] respBytes = new byte[CACHE_LENGTH];
            int count;
            StringBuilder builder = new StringBuilder(CACHE_LENGTH);
            while ((count = inputStream.read(respBytes)) <= CACHE_LENGTH) {
                builder.append(new String(respBytes, 0, count));
                if (count < CACHE_LENGTH) {
                    break;
                }
            }
            return builder.toString();
        } catch (Throwable t) {
            log.error("Failed to send command: " + command + " socket: " + socket, t);
        }

        return null;
    }

    public static boolean close(Socket socket) {
        try {
            socket.close();
        } catch (Throwable t) {
            log.error("Failed to close socket: " + socket, t);
        }
        return true;
    }

}
