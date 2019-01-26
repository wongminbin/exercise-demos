package com.wong.security;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * create by: HuangZhiBin
 * 2019年1月26日 上午10:04:30
 */
public class RSATest {
	
	/** 算法名称 */
    private static final String ALGORITHM =  "RSA";
    
    private static final int KEYSIZE =  4096; // 2048
    
    private PublicKey publicKey;
    
    private PrivateKey privateKey;

	/**
	 *  <pre>生成密钥对
	 */
	public void genKey() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
			
			generator.initialize(KEYSIZE);
		
			KeyPair keyPair = generator.generateKeyPair();
			
			publicKey = keyPair.getPublic();
			
			privateKey = keyPair.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
    
    @Test
    public void genKeyStr() {
    	genKey();
    	
    	String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
    	
    	String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
    	
    	// publicKey: MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAyfpqVU915S/ECjJmfvq4OabAaskPlNoP2cINWZcuLe/dSxI77dO/PEd0Ft3zwtQcroqRnWrDJaIkXSJKjeVrvqUmZRipPz4ZJOkbpc/if8MeRi9OJvTc4x7a0WEY8K5slkzSt4rWDS2ovKXl6sTUXp0FG2DVOGROZT8jZ+37pf8VZgaOgZYxOXtWk8ehB5LDZjO2mLOP2FKnm8joVxSaymwq89QQIO1Y/eEIEuxK0QSh0GLopshhhytP0XaaK+SXKhPz85JLx4yMdH1GcGWHQIoKCZ08gk1YTdsszOCZsC4VIdldEpa8AsAAs37qi9IvBDaGixh6VFeoMCjdM3MN4diAQWlndPQU33Kt39vF+qxBwKbQ9zF/jyaeKiDxizaO+toskw04smrh1jn9KvbRR8G5PcY4VbKhP2u32scadaOZVVjpR2Fk2MFAOc9MSE/OSN5vLGTFjNOyQjtuJkZ5VkXqUV2m3gib0zmltxJgNEU4V8toSTKyaQzHNZoxvlhXcjDVLz8l8cR7h5ZI+7A+N/mJJXXKUeEngPYb9THT4kwmNO+AFR4QMKxo5mjoOTeFxGln1ymZ5HULxWsfnNXI34S0tLxxHE3WquBaLwQaOWCdO5bauDYHhFNp5lsZo1TCXWIHzogpUU5rMh0mlozqxPT3KMxPLr23Ca3IQZX3hmsCAwEAAQ==
    	// privateKey: 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

    	System.out.println("publicKey: " + publicKeyStr);
    	System.out.println("privateKey: " + privateKeyStr);
    }
    
    @Test
	public void keyStrToKey() {
    	String publicKeyStr = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAyfpqVU915S/ECjJmfvq4OabAaskPlNoP2cINWZcuLe/dSxI77dO/PEd0Ft3zwtQcroqRnWrDJaIkXSJKjeVrvqUmZRipPz4ZJOkbpc/if8MeRi9OJvTc4x7a0WEY8K5slkzSt4rWDS2ovKXl6sTUXp0FG2DVOGROZT8jZ+37pf8VZgaOgZYxOXtWk8ehB5LDZjO2mLOP2FKnm8joVxSaymwq89QQIO1Y/eEIEuxK0QSh0GLopshhhytP0XaaK+SXKhPz85JLx4yMdH1GcGWHQIoKCZ08gk1YTdsszOCZsC4VIdldEpa8AsAAs37qi9IvBDaGixh6VFeoMCjdM3MN4diAQWlndPQU33Kt39vF+qxBwKbQ9zF/jyaeKiDxizaO+toskw04smrh1jn9KvbRR8G5PcY4VbKhP2u32scadaOZVVjpR2Fk2MFAOc9MSE/OSN5vLGTFjNOyQjtuJkZ5VkXqUV2m3gib0zmltxJgNEU4V8toSTKyaQzHNZoxvlhXcjDVLz8l8cR7h5ZI+7A+N/mJJXXKUeEngPYb9THT4kwmNO+AFR4QMKxo5mjoOTeFxGln1ymZ5HULxWsfnNXI34S0tLxxHE3WquBaLwQaOWCdO5bauDYHhFNp5lsZo1TCXWIHzogpUU5rMh0mlozqxPT3KMxPLr23Ca3IQZX3hmsCAwEAAQ==";
    	String privateKeyStr = "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";
		
    	try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			
			byte[] publicDecodes = Base64.getDecoder().decode(publicKeyStr);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicDecodes);
			publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			
			byte[] privateDecodes = Base64.getDecoder().decode(privateKeyStr);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateDecodes);
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			
			
			// 加解密
			String contentStr = "你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。你的主机中的软件中止了一个已建立的连接。";
			String encryptStr = encryptByPublic(contentStr, publicKey);
			
			String decryptStr = decryptByPrivate(encryptStr, privateKey);
			
			System.out.println(decryptStr);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

	}
    
    /**
     * RSA公钥加密
     * @param content 等待加密的数据
     * @param publicKey RSA 公钥 if null then getPublicKey()
     * @return 加密后的密文(16进制的字符串) Base64.getEncoder().encodeToString(byte[])
     */
    public String encryptByPublic(String content, PublicKey publicKey) {
    	byte[] bytes = content.getBytes(StandardCharsets.UTF_8);

    	StringBuilder sb = new StringBuilder();
    	
    	Cipher cipher;
		try {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			// publicKey最大可加密的长度
			// publicKey是分块加密 每次加密的字节数，不能超过密钥的长度值减去11,而每次加密得到的密文长度，却恰恰是密钥的长度
			int len = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11;
			
			System.out.println("publicKey最大可加密的长度 ==============" + len);
			
			int size = 0;
			for (int i = 0; i < bytes.length; i += size) {
				size = bytes.length - i > len ? len : bytes.length - i;
				
				byte[] temp = new byte[size];
				System.arraycopy(bytes, i, temp, 0, size);
				
				byte[] encrypts = cipher.doFinal(temp);

				sb.append(Hex.encodeHexString(encrypts));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
    
    

    /**
     * RSA私钥解密
     * @param content 等待解密的数据
     * @param privateKey RSA 私钥 if null then getPrivateKey()
     * @return 解密后的明文
     */
    public String decryptByPrivate(String content, PrivateKey privateKey) {
    	StringBuilder sb = new StringBuilder();
    	
    	byte[] bytes;
    	Cipher cipher;
    	try {
    		bytes = Hex.decodeHex(content);
    		
    		cipher = Cipher.getInstance(ALGORITHM);
    		cipher.init(Cipher.DECRYPT_MODE, privateKey);
    		
    		// privateKey最大可解密的长度
    		int len = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;
    		
    		System.out.println("privateKey最大可解密的长度 ==============" + len);
    		
    		int size = 0;
    		for (int i = 0; i < bytes.length; i+=size) {
				size = bytes.length - i > len ? len : bytes.length - i;
				byte[] temp = new byte[size];
				System.arraycopy(bytes, i, temp, 0, size);
				
				byte[] decrypts = cipher.doFinal(temp);
				
				sb.append(new String(decrypts));
			}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return sb.toString();
    }

    
}
