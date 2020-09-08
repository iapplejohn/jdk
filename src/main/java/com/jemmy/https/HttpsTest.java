package com.jemmy.https;

import com.jemmy.util.CertUtil;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiang.cheng
 * @since 2020/8/21
 */
public class HttpsTest {

    private static final Logger log = LoggerFactory.getLogger(HttpsTest.class);

    static {
        java.security.Security.addProvider(
            new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
    }

    public static void main(String[] args) throws Exception {
        HttpsTest test = new HttpsTest();
        CloseableHttpClient client = test.httpClientYahooJp();
        System.out.println(client);
        client = test.httpClientYahooJp();
        System.out.println(client);
    }

    public SSLConnectionSocketFactory getSSL2()
        throws Exception {
        SSLConnectionSocketFactory sslsf;

        try {
            File certFile = new File("src/main/resources/p12/SHP-bellaandsugar.crt");
            Certificate cert = CertUtil.getCertificate(certFile);

            String privateKeyStr = CertUtil
                .getPrivateKeyFromKeyFile(new File("src/main/resources/p12/bellaandsugar.key"));
            PrivateKey privateKey = CertUtil.getPrivateKey(privateKeyStr);

            final char[] PASSWORD = "yahoo".toCharArray();

            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null);
            keyStore.setCertificateEntry("yahoo-cert", cert);
            keyStore.setKeyEntry("yahoo-key", privateKey, PASSWORD, new Certificate[]{cert});

            KeyManagerFactory keyManagerFactory;
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, PASSWORD);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            sslsf = new SSLConnectionSocketFactory(sslContext, null, null, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            log.error("证书加载失败");
            e.printStackTrace();
            throw new RuntimeException("证书加载失败");
        }

        return sslsf;
    }


    public SSLConnectionSocketFactory getSSLSocktetBidirectional()
        throws Exception {
        SSLConnectionSocketFactory sslsf;

        try {
            final String P12FILE = "src/main/resources/p12/yahoo-shopping.p12";
            final char[] PASSWORD = "yahoo".toCharArray();
            // PKCS12ファイル読み込み
            KeyManagerFactory keyManagerFactory;
            try (FileInputStream inputStream = new FileInputStream(P12FILE)) {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(inputStream, PASSWORD);

                keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, PASSWORD);
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

                sslsf = new SSLConnectionSocketFactory(sslContext, null, null, NoopHostnameVerifier.INSTANCE);
            }
        } catch (Exception e) {
            log.error("证书加载失败");
            e.printStackTrace();
            throw new RuntimeException("证书加载失败");
        }
        return sslsf;
    }

    public CloseableHttpClient httpClientYahooJp() throws Exception {
//        SSLConnectionSocketFactory sslsf = getSSLSocktetBidirectional();
        SSLConnectionSocketFactory sslsf = getSSL2();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
//            .setProxy(new HttpHost(proxyHostUS, proxyPortUS))
            .build();
        return httpClient;
    }
}
