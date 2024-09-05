package com.jemmy.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * vcc 林柯，渠道方使用 php，我们对加密后的数据进行解密
 *
 * @author zhujiang.cheng
 * @since 2023/9/28
 */
public class EncryptTest {

    public static void main(String[] args)
        throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        String str = "SQYFf6xm9ww=";
        String key = "1817fbc770598b8938e577aca4f9e8ca";

        byte[] encryptedBytes = Base64.getDecoder().decode(str);

        byte[] keyBytes = hexToBytes(key);
        System.out.println("str2 = " + new String(keyBytes));
        byte[] newBytes = new byte[keyBytes.length + 8];
        System.arraycopy(keyBytes, 0, newBytes, 0, keyBytes.length);
        System.arraycopy(keyBytes, 0, newBytes, keyBytes.length, 8);
        System.out.println(Base64.getEncoder().encodeToString(newBytes));

        String ivString = getIv();
        //decrypt
        SecretKey secretKey = new SecretKeySpec(newBytes, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivString.getBytes()));
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedData = new String(decryptedBytes);
        System.out.println("Decrypted Data: " + decryptedData);
    }

    private static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            String hexByte = hex.substring(i, i + 2);
            int value = Integer.parseInt(hexByte, 16);
            bytes[i / 2] = (byte) value;
        }
        return bytes;
    }

    private static String getIv() {
        int repeatCount = 8;
        char character = '\u0000';
        char[] chars = new char[repeatCount];
        Arrays.fill(chars, character);
        String repeatedString = new String(chars);
        return repeatedString;
    }

    private static String hexToBinary(String hex) {
        StringBuilder binaryBuilder = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            int hexValue = Character.digit(hexChar, 16);
            String binaryDigit = String.format("%4s", Integer.toBinaryString(hexValue)).replace(' ', '0');
            binaryBuilder.append(binaryDigit);
        }
        return binaryBuilder.toString();
    }
}
