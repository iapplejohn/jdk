package com.jemmy.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/**
 * Zip 压缩解压工具类
 *
 * @author zhujiang.cheng
 * @since 2020/12/14
 */
public class ZipUtil {

    /**
     * Zip 方式压缩文件/夹
     *
     * @param inputFile 待压缩的文件或目录
     * @param outputFile 输出的 .zip 压缩文件
     * @throws IOException
     */
    public static void compressFile(String inputFile, String outputFile) throws IOException {
        Path path = Paths.get(inputFile);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("待压缩文件不存在: " + path.toString());
        }

        try (ZipArchiveOutputStream output = new ZipArchiveOutputStream(new File(outputFile))) {
            output.setUseZip64(Zip64Mode.AsNeeded);
            compressFile(new File(inputFile), output, null);
            output.finish();
        }
    }

    public static void compressFile(List<String> inputFiles, String outputFile) throws IOException {
        try (ZipArchiveOutputStream output = new ZipArchiveOutputStream(new File(outputFile))) {
            output.setUseZip64(Zip64Mode.AsNeeded);

            for (String inputFile : inputFiles) {
                compressFile(new File(inputFile), output, null);
            }

            output.finish();
        }
    }

    public static byte[] compress(Map<String, byte[]> nameStreamMap) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipArchiveOutputStream output = new ZipArchiveOutputStream(outputStream)) {
            output.setUseZip64(Zip64Mode.AsNeeded);

            for (Entry<String, byte[]> entry : nameStreamMap.entrySet()) {
                ZipArchiveEntry archiveEntry = new ZipArchiveEntry(entry.getKey());
                output.putArchiveEntry(archiveEntry);
                output.write(entry.getValue());
                output.closeArchiveEntry();
            }
            output.finish();
        }

        return outputStream.toByteArray();
    }

    private static void compressFile(File input, ZipArchiveOutputStream output, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }

        if (input.isDirectory()) {
            File[] files = input.listFiles();

            if (files == null) {
                return;
            }

            if (files.length == 0) {
                // 文件夹为空，往目标 zip 文件中写一个目录进去
                ZipArchiveEntry entry = new ZipArchiveEntry(name + File.separatorChar);
                output.putArchiveEntry(entry);
            } else {
                // 文件夹不为空，递归调用 compress 方法进行压缩
                for (int i = 0; i < files.length; i++) {
                    compressFile(files[i], output, name + File.separatorChar + files[i].getName());
                }
            }
        } else {
            try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(input.toPath()))) {
                ZipArchiveEntry entry = new ZipArchiveEntry(input, name);
                output.putArchiveEntry(entry);

                int count;

                byte[] buf = new byte[1024];
                while ((count = bis.read(buf)) != -1) {
                    output.write(buf, 0, count);
                }
            } finally {
                output.closeArchiveEntry();
            }
        }
    }

    /**
     * 解压 .zip 文件
     *
     * @param inputFile 待解压的 .zip 文件
     * @param outputPath 解压文件/夹输出路径
     * @throws IOException
     */
    public static void decompressFile(String inputFile, String outputPath) throws IOException {
        Path inputPath = Paths.get(inputFile);
        decompressFile(inputPath, outputPath);
    }

    /**
     * 解压 .zip 文件
     *
     * @param inputPath 待解压的 .zip 文件路径对象
     * @param outputPath 解压文件/夹输出路径
     * @throws IOException
     */
    public static void decompressFile(Path inputPath, String outputPath) throws IOException {
        // 判断压缩文件是否需存在
        if (!Files.exists(inputPath)) {
            throw new IllegalArgumentException("待解压文件不存在：" + inputPath);
        }

        // 解压
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(inputPath));
            ZipArchiveInputStream input = new ZipArchiveInputStream(bis)) {
            ArchiveEntry entry;
            File file;
            while ((entry = input.getNextEntry()) != null) {
                file = new File(outputPath, entry.getName());
                if (!file.getParentFile().exists()) {
                    // 创建上级目录
                    boolean parentOk = file.getParentFile().mkdirs();
                    if (!parentOk) {
                        throw new IllegalStateException("创建父目录失败：" + file.getParent());
                    }
                }
                try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
                    int count;
                    byte[] buf = new byte[1024];
                    while ((count = input.read(buf)) != -1) {
                        bos.write(buf, 0, count);
                    }
                }
            }
        }
    }

    public static String decompress(byte[] bytes) throws IOException {
        // 解压
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ZipArchiveInputStream input = new ZipArchiveInputStream(bis)) {
            ArchiveEntry entry;

                try (BufferedOutputStream bos = new BufferedOutputStream(baos)) {
                    int count;
                    byte[] buf = new byte[1024];
                    while ((count = input.read(buf)) != -1) {
                        bos.write(buf, 0, count);
                    }
                }
        }

        return baos.toString();
    }
}
