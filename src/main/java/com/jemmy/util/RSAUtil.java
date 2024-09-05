package com.jemmy.util;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtil {

    private static String publicKey = "AAAAB3NzaC1yc2EAAAADAQABAAABAQC68BpY3gdOfCkMj5hlOO9jSLavwHz5TbY1ljnURsEZxktVeoFr5OJX1S1x3z4GaaZEHD4f4MIFbvaIIS+IQ3L9jqPA7vPuGNxSMVD7Dt3eg92+EBOrEop9XASBH97krqEHT3Cwokmnwgmh6+zRzBKL0IaX/xw3jjh0XNWzsKx0Hlu8Ob3c2b/CR/9ZPtbpx9A4utdfWhbZCfYDvvsZkjz0/PCXd//mNweVh+vt6Mp0nA1bKt9WtxvOnuS8j0k6+HIxVw5iWnxJ65vXaoT00A5JKR1cjkrg54BJnK5nCaa/wf1u14+95ukEke/59NjwXxEiP9GLXp/IyjFia0cYHLpX";
    private static String privateKey = "MIIEowIBAAKCAQEAuvAaWN4HTnwpDI+YZTjvY0i2r8B8+U22NZY51EbBGcZLVXqB\n"
        + "a+TiV9Utcd8+BmmmRBw+H+DCBW72iCEviENy/Y6jwO7z7hjcUjFQ+w7d3oPdvhAT\n"
        + "qxKKfVwEgR/e5K6hB09wsKJJp8IJoevs0cwSi9CGl/8cN444dFzVs7CsdB5bvDm9\n"
        + "3Nm/wkf/WT7W6cfQOLrXX1oW2Qn2A777GZI89Pzwl3f/5jcHlYfr7ejKdJwNWyrf\n"
        + "Vrcbzp7kvI9JOvhyMVcOYlp8Seub12qE9NAOSSkdXI5K4OeASZyuZwmmv8H9bteP\n"
        + "vebpBJHv+fTY8F8RIj/Ri16fyMoxYmtHGBy6VwIDAQABAoIBAQCfyeqLecW/Faw0\n"
        + "221FYKRnNllJ43wv0XhlV7K4u34FnRT5zMxhwHSk5UiCJHOtIZOIdqe3sfPGlzHN\n"
        + "7Kfnjtf02n8oxFfndoKlExiIcQ2/TPI2qyFUwnV0cDo9cVOiH47vY70Mjrvs1JRN\n"
        + "Pw/fyNM1hZn2xI6HAl9JP60/ek3+thnbybYwFccAfzDvRHyTM+r7pZFIyroV+6fK\n"
        + "dulkr+TygT+C7oAOvD4zU+FDFjMjvp/u11GVXVVv1y2OpOuub8l0Mb7w7iy8ToD5\n"
        + "7P8a9jq0AruH0braHaib+0urPJ7yOctQnv2Pbsod2MkkmOH4rpAzSP0eR5EKyNCt\n"
        + "edn5TdJRAoGBAOdZEhusrHnSgEnFKpO/k+SXMQmwat05WFPZ4Gqd9QNZtgVVkdcm\n"
        + "9iaXR61RUUAOurtxF/BfyZmYQb7wDxkoWV4jSgVk+8tG9ITP8J+chwbI1oar6l9t\n"
        + "vQRJYMG7QccQSJ0cUJcnuMiVrTEwSUHd0kUbSLY77ozVJcB3ZBpmIQVJAoGBAM7b\n"
        + "krS1+VFehWX8wml4ku+9xxJ2gphXJfObfK/4ngbDzBgjKqS2jQ8dRVsG/9xauxEn\n"
        + "CPSZxWwuIEnsrfxRNn9HLGlJpSttYgKWD6C28bbT/r4bdJh3Vf7VZqsMuY41tNLF\n"
        + "5/dDllFBPauqaFnVYTXEB4Y/sQGGch2P6nubcOKfAoGAOrcPcnijmZOEPbTNtovj\n"
        + "xBtfNb9Rj+3y7e0ZGNYYDg6htL7f8cQQqwCwD1vZDDHw0IvGcJpO9y1+WjBhFJh5\n"
        + "/ftk2POlOBU9D8wanNfEKUOltq5BIJEgbMsWxHR7IEOQulcqWQKa0kWynA5OOWpL\n"
        + "6IAhNycExzd9iliCznRnIwkCgYAnadDHG9EYPuE98G3+eJnOkiNHEkuJyTkS9HJK\n"
        + "eR1q/o15n+G1Jo8z7QjU8j1399WizAQ5zr6+AP/n9e+knri6gY4ltYGCB8Jf6cWq\n"
        + "gpEFm9jy0fpiPoKlHnYEa1VDkloUut6az0bpznt4pOxjCXQPMX2Zh7MrLjm6BjzQ\n"
        + "NmrI0wKBgBmu1rsTL5773AjGi8r7BcKi5yzhF6ZZgOGszKuduYLNuShyOGtMH1N+\n"
        + "sBOEyqGqBHSLziGwNYOpl1swR1SfqohixfrnGuX3mJbqmjPf5XvThn++avRzaCW5\n"
        + "ms0OaUCuW7BM8d2HTNU9ZOaT1PhlspJQPBhCJ0r9vnuwmdRCZMRL";

    public static void main(String[] args) throws GeneralSecurityException {

        String encryptedString = encrypt("this is a test!", publicKey);
        System.out.println(encryptedString);
        String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
        System.out.println(decryptedString);

    }

    public static String encrypt(String data, String publicKey) throws GeneralSecurityException {

        EncodedKeySpec keySpec = new X509EncodedKeySpec(base64Decode(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(keySpec));
        return base64Encode(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(String data, String privateKey) throws GeneralSecurityException {

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64Decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(keySpec));
        return new String(cipher.doFinal(base64Decode(data)));
    }

    static String base64Encode(byte[] bytes) {

        return Base64.getEncoder().encodeToString(bytes);
    }

    static byte[] base64Decode(String str) {

        return Base64.getDecoder().decode(str);
    }
}