package com.jemmy.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

/**
 * gzip 压缩解压工具类
 *
 * @author zhujiang.cheng
 * @since 2020/12/14
 */
public class GZipUtil {

    /**
     * gzip 方式压缩文件
     *
     * @param inputFile 待压缩的文件
     * @param outputFile 输出的 .gz 压缩文件
     * @throws IOException
     */
    public static void compressFile(String inputFile, String outputFile) throws IOException {
        Path path = Paths.get(inputFile);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("待压缩文件不存在: " + path.toString());
        }
        File file = new File(inputFile);
        if (file.isDirectory()) {
            throw new IllegalArgumentException("GZip 不支持对目录进行压缩：" + inputFile);
        }

        try (
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
            OutputStream fos = Files.newOutputStream(Paths.get(outputFile));
            GzipCompressorOutputStream output = new GzipCompressorOutputStream(new BufferedOutputStream(fos))) {

            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                output.write(buf, 0, count);
            }

            output.finish();
        }
    }

    /**
     * 解压 .gz 文件
     *
     * @param inputFile 待解压的 .gz 文件
     * @param outputFile 解压文件输出路径
     * @throws IOException
     */
    public static void decompressFile(String inputFile, String outputFile) throws IOException {
        Path path = Paths.get(inputFile);
        // 判断压缩文件是否需存在
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("待解压文件不存在：" + path.toString());
        }

        File output = new File(outputFile);
        if (!output.getParentFile().exists()) {
            boolean parentOk = output.getParentFile().mkdirs();
            if (!parentOk) {
                throw new IllegalStateException("创建父目录失败：" + output.getParent());
            }
        }

        // 解压
        try (
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
            GzipCompressorInputStream input = new GzipCompressorInputStream(bis);
            BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputFile)))) {

            int count;
            byte[] buf = new byte[1024];
            while ((count = input.read(buf)) != -1) {
                bos.write(buf, 0, count);
            }
        }
    }

    public static String decompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压
        try (
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bytes));
            GzipCompressorInputStream input = new GzipCompressorInputStream(bis);
            BufferedOutputStream bos = new BufferedOutputStream(baos)) {

            int count;
            byte[] buf = new byte[1024];
            while ((count = input.read(buf)) != -1) {
                bos.write(buf, 0, count);
            }
        }

        return baos.toString();
    }

    public static boolean isGzipped(byte[] input) {
        if (input.length < 2) {
            return false;
        }
        return (input[0] & 0xff | (input[1] << 8 & 0xff00)) == GZIPInputStream.GZIP_MAGIC;
    }

}
