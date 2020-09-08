/*
 * Copyright (C), 2014-2016, 杭州小卡科技有限公司
 * FileName: SecUtil.java
 * Author:   Cheng Zhujiang
 * Date:     Mar 17, 2016 1:17:48 PM
 * Description: 
 */
package com.jemmy.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 * 证书工具
 * 证书相关方法
 * 
 * @author Cheng Zhujiang
 * @see
 * @since 1.0.2
 */
public class CertUtil {

	public static final String SunX509 = "SunX509";
	public static final String JKS = "JKS";
	public static final String PKCS12 = "PKCS12";
	public static final String TLS = "TLSv1";

	/** X509(1024位)头字节数组 */
	private static final byte[] bX509HEAD = new byte[]{(byte)48, (byte)-127, (byte)-97, (byte)48, (byte)13, (byte)6, (byte)9, (byte)42, (byte)-122, (byte)72, (byte)-122, (byte)-9, (byte)13, (byte)1, (byte)1, (byte)1, (byte)5, (byte)0, (byte)3, (byte)-127, (byte)-115, (byte)0};

	/**
	 * 获取SSLContext
	 * 
	 * @param trustFile 存储ca证书的jks文件
	 * @param trustPasswd 存储ca证书的jks密码
	 * @param keyFile 存储证书的jks文件
	 * @param keyPasswd 存储证书的jks密码
	 * @param certJksType 存储证书的jks类型
	 * @return SSL上下文
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	public static SSLContext getSSLContext(
	        File trustFile, String trustPasswd,
	        File keyFile, String keyPasswd, String certJksType)
	        throws NoSuchAlgorithmException, KeyStoreException,
        CertificateException, IOException, UnrecoverableKeyException,
        KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(SunX509);
		KeyStore trustKeyStore = getKeyStore(JKS, trustFile, trustPasswd);
		tmf.init(trustKeyStore);

		// cert
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(SunX509);
		KeyStore ks = getKeyStore(certJksType, keyFile, keyPasswd);
		kmf.init(ks, str2CharArray(keyPasswd));

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}

	/**
	 * 获取CA证书信息
	 * 
	 * @param cafile CA证书文件
	 * @return Certificate
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static Certificate getCertificate(File cafile)
	        throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		BufferedInputStream in = null;
		Certificate cert;
		try {
			in = new BufferedInputStream(new FileInputStream(cafile));
			cert = cf.generateCertificate(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return cert;
	}

	/**
	 * 存储ca证书成JKS格式
	 * 
	 * @param cert
	 * @param alias
	 * @param password
	 * @param jksCAFile
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static void storeCACert(Certificate cert, String alias,
	        String password, File jksCAFile) throws KeyStoreException,
        NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		BufferedOutputStream jksOutStream = null;
		try {
			jksOutStream = new BufferedOutputStream(new FileOutputStream(jksCAFile));

			// store keystore
			ks.store(jksOutStream, str2CharArray(password));
		} finally {
			IOUtils.closeQuietly(jksOutStream);
		}
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 存储证书成JKS格式
	 * 
	 * @param certFile 证书文件
	 * @param certPasswd 证书密码
	 * @param jksFile JKS文件
	 * @throws FileNotFoundException
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 */
	public static void storeCert(File certFile, String certPasswd, File jksFile)
	        throws FileNotFoundException,
        KeyStoreException, IOException,
        NoSuchAlgorithmException, CertificateException {
		KeyStore ks = KeyStore.getInstance("PKCS12");

		final char[] kp = str2CharArray(certPasswd);

		BufferedInputStream certInStream = null;
		BufferedOutputStream jksOutStream = null;
		try {
			certInStream = new BufferedInputStream(new FileInputStream(certFile));
			jksOutStream = new BufferedOutputStream(new FileOutputStream(jksFile));

			ks.load(certInStream, kp);
			ks.store(jksOutStream, kp);
		} finally {
			IOUtils.closeQuietly(certInStream);
			IOUtils.closeQuietly(jksOutStream);
		}
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 加载密钥库
	 * 
	 * @param keyStoreType 密钥库类型
	 * @param keyStoreFile 密钥库路径
	 * @param keyStorePasswd 密钥库密码
	 * 
	 * @return 密钥库对象
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static KeyStore getKeyStore(String keyStoreType, File keyStoreFile, String keyStorePasswd)
	        throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
        IOException {
		KeyStore keyStore = null;
		BufferedInputStream bis = null;

		try {
			keyStore = KeyStore.getInstance(keyStoreType);

			FileInputStream fis = new FileInputStream(keyStoreFile);
			bis = new BufferedInputStream(fis);
			keyStore.load(bis, str2CharArray(keyStorePasswd)); // 加载密钥库
		} finally {
			IOUtils.closeQuietly(bis);
		}

		return keyStore;
	}

	/**
	 * 得到私钥 -----BEGIN PRIVATE KEY-----
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = java.util.Base64.getDecoder().decode(key);
//		byte[] keyBytes = Base64.decodeBase64(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	/**
	 * 得到私钥 -----BEGIN RSA PRIVATE KEY-----
	 *
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getRSAPrivateKey(String key) throws Exception {
		byte[] keyBytes = key.getBytes();

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 从密钥库中获取私钥
	 * 
	 * @param keyStore 密钥库
	 * @param keyPasswd 密钥
	 * @param keyAlias 别名
	 * @return 私钥对象
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 */
	public static PrivateKey getPrivateKey(KeyStore keyStore, String keyAlias, String keyPasswd)
	        throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException,
        UnrecoverableKeyException {
		// 从密钥仓库得到私钥
		PrivateKey priK = (PrivateKey) keyStore.getKey(keyAlias, keyPasswd.toCharArray());
		return priK;
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 从PKCS8文件中获取私钥
	 *
	 * @param keyFile PKCS8文件
	 * @return 私钥字符串
	 * @throws IOException
	 */
	public static String getPrivateKeyFromKeyFile(File keyFile) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(keyFile)));

			while (true) {
				char[] firstFive = new char[5]; // 每行前5个字符
				int count = bufferedReader.read(firstFive, 0, 5);
				boolean isCommentLine = true;
				if (count == -1) {
					break;
				}
				if (count == 5) {
					for (char c : firstFive) {
						if (c != '-') { // 不是连字符:不是注释行
							isCommentLine = false;
							break;
						}
					}
				}
				if (isCommentLine) {
					bufferedReader.readLine(); // 跳过注释行
				} else {
					sb.append(firstFive);
					char ch = (char) bufferedReader.read();
					while (ch != '\n') {
						sb.append(ch);
						ch = (char) bufferedReader.read();
					}
				}
			}
		} finally {
			IOUtils.closeQuietly(bufferedReader);
		}

		return sb.toString();
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 从经过Base64编码的字符串中获取公钥(512位)
	 * 
	 * @param base64PubKey Base64编码的字符串
	 * @return 公钥对象(512位)
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String base64PubKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] b509Cert = Base64.decodeBase64(base64PubKey);
		return getPublicKey(b509Cert);
	}

	/**
	 * <pre>
	 * 功能描述:
	 * 从经过Base64编码的字符串中获取公钥(1024位)
	 *
	 * @param base64PubKey Base64编码的字符串
	 * @return 公钥对象(1024位)
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey1024(String base64PubKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] e = Base64.decodeBase64(base64PubKey);
		int iCertLen = e.length;
		int iHeadLen = bX509HEAD.length;
		byte[] b509Cert = new byte[iCertLen + iHeadLen];
		System.arraycopy(bX509HEAD, 0, b509Cert, 0, iHeadLen);
		System.arraycopy(e, 0, b509Cert, iHeadLen, iCertLen);
		return getPublicKey(b509Cert);
	}

	private static PublicKey getPublicKey(byte[] b509Cert) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b509Cert);
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 字符串转换成char数组
	 * 
	 * @param str
	 * @return char[]
	 */
	public static char[] str2CharArray(String str) {
		if (null == str) {
			return null;
		}

		return str.toCharArray();
	}

}
