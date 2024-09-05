package com.jemmy.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * @author zhujiang.cheng
 * @since 2022/5/19
 */
public class FileAttributeTest {

    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("/Users/xxx/Documents/xxx/amazon_us-active-asin/test.gz");
//        UserPrincipal userPrincipal = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("{\"retryTimes\":0,\"spiderTime\":1651049588040,\"originalStatus\":38,\"marketplace\":\"AMAZON_JP\",\"serviceStatus\":200,\"topic\":\"amazon_jp-active-asin\",\"asin\":\"B00A4CH4B2\",\"taskId\":\"480d6a5a17fe4cbfa8c77ffacf8e46d3\",\"url\":\"https://www.amazon.co.jp/dp/B00A4CH4B2?language=ja_JP&th=1&psc=1\"}");
//        Files.setOwner(path, userPrincipal);
//        Files.setAttribute(path, "file:owner", "{\"retryTimes\":0,\"spiderTime\":1651049588040,\"originalStatus\":38,\"marketplace\":\"AMAZON_JP\",\"serviceStatus\":200,\"topic\":\"amazon_jp-active-asin\",\"asin\":\"B00A4CH4B2\",\"taskId\":\"480d6a5a17fe4cbfa8c77ffacf8e46d3\",\"url\":\"https://www.amazon.co.jp/dp/B00A4CH4B2?language=ja_JP&th=1&psc=1\"}");

//        userDefined();
//        userDefined2();
//        saveFileAndAttribute();

//        writeUserDefinedAttr();
//        readUserDefinedAttr();
        saveAndReadUserDefinedAttr();

//        String path1 = "/Users/xxx/Documents/xxx/future1.gz";
//        String path2 = "/Users/xxx/Documents/xxx/future2.gz";
//        String path3 = "/Users/xxx/Documents/xxx/future3.gz";
//        String path4 = "/Users/xxx/Documents/xxx/future4.gz";
//
//        byte[] content = Files.readAllBytes(Paths.get("/Users/xxx/Documents/xxx/amazon_us-active-asin/test.gz"));
//
//        for (int i = 0; i < 10; i++) {
//            long start = System.nanoTime();
//            bufferOutputStreamTest(path2, content);
//            System.out.println("bufferOutputStreamTest 写入用时:" + (System.nanoTime() - start));
//
//            start = System.nanoTime();
//            fileOutputStreamTest(path1, content);
//            System.out.println("fileOutputStreamTest 写入用时:" + (System.nanoTime() - start));
//
//            start = System.nanoTime();
//            newOutputStreamTest(path3, content);
//            System.out.println("newOutputStreamTest 写入用时:" + (System.nanoTime() - start));
//
//            start = System.nanoTime();
//            newBufferOutputStreamTest(path4, content);
//            System.out.println("newBufferOutputStreamTest 写入用时:" + (System.nanoTime() - start));
//        }
    }

    public static void userDefined() throws IOException {
        Path path = Paths.get("/Users/xxx/Documents/xxx/amazon_us-active-asin/test.gz");
        String key = "{\"retryTimes\":0,\"spiderTime\":1651049588040,\"originalStatus\":38,\"marketplace\":\"AMAZON_JP\",\"serviceStatus\":200,\"topic\":\"amazon_jp-active-asin\",\"asin\":\"B00A4CH4B2\",\"taskId\":\"480d6a5a17fe4cbfa8c77ffacf8e46d3\",\"url\":\"https://www.amazon.co.jp/dp/B00A4CH4B2?language=ja_JP&th=1&psc=1\"}";
        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        FileStore fileStore = Files.getFileStore(path);
        view.write("key", StandardCharsets.UTF_8.encode(key));
        System.out.println("user defined completed");
    }

    public static void writeUserDefinedAttr() throws IOException {
        Path path = Paths.get("/data/project/parser-console/cache/amazon_us-active-asin/2022-05-21/cc799e64337748e786d66b3fee0de6b9.gz");
        String key = "{\"retryTimes\":0,\"spiderTime\":1651049588040,\"originalStatus\":38,\"marketplace\":\"AMAZON_JP\",\"serviceStatus\":200,\"topic\":\"amazon_jp-active-asin\",\"asin\":\"B00A4CH4B2\",\"taskId\":\"480d6a5a17fe4cbfa8c77ffacf8e46d3\",\"url\":\"https://www.amazon.co.jp/dp/B00A4CH4B2?language=ja_JP&th=1&psc=1\"}";
        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        view.write("key", StandardCharsets.UTF_8.encode(key));
        System.out.println("write finished");
    }

    public static void readUserDefinedAttr() throws IOException {
        Path path = Paths.get("/data/project/parser-console/cache/amazon_us-active-asin/2022-05-21/cc799e64337748e786d66b3fee0de6b9.gz");
        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        int size = view.size("key");
        byte[] bytes = new byte[size];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int count = view.read("key", byteBuffer);
        System.out.println("count=" + count + ",size=" + size);
//        System.out.println("key read from file: " + new String(StandardCharsets.UTF_8.decode(byteBuffer).array()));
        System.out.println("key read from file: " + new String(bytes, StandardCharsets.UTF_8));
        System.out.println("read finished");
    }

    public static void saveAndReadUserDefinedAttr() throws IOException {
        String filePath = "/data/project/parser-console/cache/amazon_us-active-asin/2022-05-21/cc799e64337748e786d66b3fee0de6b9.gz";
        String destPath = "/data/project/parser-console/cache/amazon_us-active-asin/2022-05-21/future.gz";

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath))) {
            int count;
            byte[] bytes = new byte[4096];
            while ((count = (bis.read(bytes))) != -1) {
                bos.write(bytes, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Path path = Paths.get("/data/project/parser-console/cache/amazon_us-active-asin/2022-05-21/future.gz");
        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        int size = view.size("key");
        byte[] bytes = new byte[size];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int count = view.read("key", byteBuffer);
        System.out.println("count=" + count + ",size=" + size);
//        System.out.println("key read from file: " + new String(StandardCharsets.UTF_8.decode(byteBuffer).array()));
        System.out.println("key read from file: " + new String(bytes, StandardCharsets.UTF_8));
        System.out.println("read complete");
    }

    public static void saveFileAndAttribute() {
        String cacheDir = "/Users/xxx/Documents/xxx";
        String taskId = "fabulous";
        Path path = Paths.get(cacheDir, taskId + ".txt");

        byte[] bytes = "Fight for future".getBytes(StandardCharsets.UTF_8);
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE))) {
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String key = "Stay hungry, stay foolish";
        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        if (view != null) {
            try {
                view.write("key", StandardCharsets.UTF_8.encode(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileOutputStreamTest(String path, byte[] content) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bufferOutputStreamTest(String path, byte[] content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path))) {
            bos.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newOutputStreamTest(String path, byte[] content) {
        try (OutputStream ops = Files.newOutputStream(Paths.get(path), StandardOpenOption.CREATE)) {
            ops.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newBufferOutputStreamTest(String path, byte[] content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(Paths.get(path), StandardOpenOption.CREATE))) {
            bos.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
