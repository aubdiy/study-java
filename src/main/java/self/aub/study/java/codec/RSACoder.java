package self.aub.study.java.codec;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA Coder<br/>
 * secret key length: 512~65536(必须是64的倍数)bit, default: 1024bit<br/>
 * mode: RSA<br/>
 * padding: Nopadding/PKCS1Padding/OAEPWITHMD5AndMGF1Padding
 * /OAEPWITSHA1AndMGF1Padding/OAEPWITSHA256AndMGF1Padding
 * /OAEPWITSHA384AndMGF1Padding/OAEPWITSHA512AndMGF1Padding
 * 
 * @author Aub
 * 
 */
public class RSACoder {
	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "RSA";

	private static byte[] publicKey;
	private static byte[] privateKey;

	/**
	 * 密钥长度
	 */
	private static final int KEY_SIZE = 512;

	/**
	 * 初始化密钥
	 * 
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static void initSecretKey() {
		KeyPairGenerator keyPairGenerator = null;

		try {
			keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		publicKey = ((RSAPublicKey) keyPair.getPublic()).getEncoded();
		privateKey = ((RSAPrivateKey) keyPair.getPrivate()).getEncoded();
	}

	public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	private static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		initSecretKey();
		System.out.println("初始化密钥对");
		System.out.println("public key：" + showByteArray(publicKey));
		System.out.println("private key：" + showByteArray(privateKey));
		System.out.println();

		String data = "RSA数据";
		System.out.println("加密前数据: string:" + data);
		System.out.println("加密前数据: byte[]:" + showByteArray(data.getBytes()));
		System.out.println();

		byte[] encryptData;
		byte[] decryptData;

		encryptData = encryptByPrivateKey(data.getBytes(), privateKey);
		System.out.println("私钥加密后数据: byte[]:" + showByteArray(encryptData));
		System.out.println("私钥加密后数据: hexStr:" + Hex.encodeHexStr(encryptData));
		System.out.println();
		decryptData = decryptByPublicKey(encryptData, publicKey);
		System.out.println("公钥解密后数据: byte[]:" + showByteArray(decryptData));
		System.out.println("公钥解密后数据: string:" + new String(decryptData));
		System.out.println();

		encryptData = encryptByPublicKey(data.getBytes(), publicKey);
		System.out.println("公钥加密后数据: byte[]:" + showByteArray(encryptData));
		System.out.println("公钥加密后数据: hexStr:" + Hex.encodeHexStr(encryptData));
		System.out.println();
		decryptData = decryptByPrivateKey(encryptData, privateKey);
		System.out.println("私钥解密后数据: byte[]:" + showByteArray(decryptData));
		System.out.println("私钥解密后数据: string:" + new String(decryptData));
	}
}
