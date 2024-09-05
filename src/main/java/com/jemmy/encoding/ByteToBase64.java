package com.jemmy.encoding;

import org.apache.commons.codec.binary.Base64;

/**
 * @author zhujiang.cheng
 * @since 2023/11/3
 */
public class ByteToBase64 {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{-52, 64, -36, 19, 113, -7, -47, 9, 23, -93, 71, 61, 46, -114, 34, -80, -38, 79, 18, -54, -96, 107, 122, 54, -88, 31, -67, -4, 16, 64, -107, 48, 126, -60, 105, 36, -94, 29, -78, 54, -88, 71, -1, -92, -2, 90, -88, -83, 88, -38, -14, -38, 88, 85, -109, 4, 111};
        String result = Base64.encodeBase64String(bytes);
        System.out.println("result = " + result);
        System.out.println("new String(bytes) = " + new String(bytes));
    }
}
