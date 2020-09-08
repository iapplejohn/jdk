package com.jemmy.nio;

import com.google.common.base.Charsets;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SourceChannel;
import java.util.concurrent.TimeUnit;

/**
 * Java NIO 管道是2个线程之间的单向数据连接。
 * Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 * http://ifeve.com/pipe/
 *
 * @author zhujiang.cheng
 * @since 2020/5/17
 */
public class PipeTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 打开管道
        Pipe pipe = Pipe.open();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 读取管道的数据，需要访问source通道
                SourceChannel sourceChannel = pipe.source();

                // 调用source通道的read()方法来读取数据
                ByteBuffer buf = ByteBuffer.allocate(48);

                try {
                    int bytesRead = sourceChannel.read(buf);
                    byte[] array = buf.array();
                    System.out.println("Received " + new String(array, Charsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 向管道写数据，需要访问sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();

        // 通过调用SinkChannel的write方法，将数据写入SinkChannel
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        TimeUnit.SECONDS.sleep(200);
    }
}
