package com.jemmy.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2023/9/6
 */
public class FileChannelTest {

    private static final int MAX_WRITE_RETRY = 5;
    private static FileChannel currFileChannel;

    // default 16kb
    private static final int DEFAULT_WRITE_BUFFER_SIZE = 1024 * 18;

    private static final ByteBuffer writeBuffer = ByteBuffer.allocateDirect(DEFAULT_WRITE_BUFFER_SIZE);

    private static final Logger LOGGER = LoggerFactory.getLogger(FileChannelTest.class);

    public static void main(String[] args) throws FileNotFoundException {
        File currDataFile = new File("/Users/xxx/data/xxx-browser-extension.jar");
        RandomAccessFile currRaf = new RandomAccessFile(currDataFile, "rw");
        currFileChannel = currRaf.getChannel();
        for (int i = 0; i < 4100; i++) {
            writeBuffer.putInt(i);
        }
        flushWriteBuffer(writeBuffer);
    }

    private static boolean flushWriteBuffer(ByteBuffer writeBuffer) {
        writeBuffer.flip();
        if (!writeDataFileByBuffer(writeBuffer)) {
            return false;
        }
        writeBuffer.clear();
        return true;
    }

    private static boolean writeDataFileByBuffer(ByteBuffer byteBuffer) {
        for (int retry = 0; retry < MAX_WRITE_RETRY; retry++) {
            try {
                while (byteBuffer.hasRemaining()) {
                    currFileChannel.write(byteBuffer);
                }
                return true;
            } catch (IOException exx) {
                LOGGER.error("write data file error:" + exx.getMessage());
            }
        }
        LOGGER.error("write dataFile failed,retry more than :" + MAX_WRITE_RETRY);
        return false;
    }
}
