package com.jemmy.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class ClassToBase64 {

    public static void main(String[] args) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("target/classes/com/jemmy/serialize/leak/Test.class"))) {
            int len = bis.available();
            byte[] bytes = new byte[len];
            int count = bis.read(bytes);
            System.out.println("count = " + count);
            String base64Str = new String(Base64.getEncoder().encode(bytes), "UTF-8");
            System.out.println("base64Str = " + base64Str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
