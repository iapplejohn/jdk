package com.jemmy.util;

import java.security.MessageDigest;

/**
 * @author zhujiang.cheng
 * @since 2022/8/5
 */
public class MD5Util {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = messageDigest.digest(input);
            return byteToHex(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String byteToHex(byte[] bytes) {
        return byteToHex(bytes, 0, bytes.length);
    }

    private static String byteToHex(byte[] bytes, int offset, int length) {
        StringBuilder builder = new StringBuilder(2 * length);
        int k = offset + length;
        for (int l = offset; l < k; l++) {
            char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
            char c1 = hexDigits[bytes[l] & 0xf];
            builder.append(c0);
            builder.append(c1);
        }

        return builder.toString();
    }
}
