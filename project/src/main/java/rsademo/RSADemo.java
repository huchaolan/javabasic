package rsademo;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSADemo {
    public static final String KEY_ALGORITHM = "RSA"; 
     // 公 钥 
    private static final String PUBLIC_KEY = "RSAPublicKey"; 
    //私 钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    /** 
     * RSA 密钥 长度 
     * 默认 1024 位，
     * 密钥 长度 必须 是 64 的 倍数，
     * 范围 在 512 ～ 65536 位 之间。
     */ 
    private static final int KEY_SIZE = 512; 
    
    /**
    * 私 钥 解密
    * @param data 待解 密 数据
    * @param key 私 钥 
    * @return byte[] 解密 数据
    * @throws Exception 
    **/ 
    public static byte[] decryptByPrivateKey( byte[] data, byte[] key) throws Exception { 
        // 取得 私 钥 
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( key); 
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM); 
        // 生成 私 钥
        PrivateKey privateKey = keyFactory. generatePrivate(pkcs8KeySpec); 
        // 对数 据 解密 
        Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm()); 
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher. doFinal( data); 
     } 
    
    /** 
     * 公 钥 解密 
     * @param data 待解 密 数据  
     * @param key 公 钥 
     * @return byte[] 解密 数据  
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 取得 公 钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key); 
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM); 
        // 生成 公 钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec); 
        // 对数 据 解密 
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()); 
        cipher.init( Cipher.DECRYPT_MODE, publicKey);
        return cipher. doFinal( data); 
    }
    
    /** 
     * 公 钥 加密
     * @param data 待 加密 数据
     * @param key 公 钥
     * @return byte[] 加密 数据
     * @throws Exception 
     **/ 
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        //取得 公 钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( key); 
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec); 
        //对数 据 加密 
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()); 
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); 
        return cipher.doFinal(data);
    }
    
    
    /**
     * 私 钥 加密 
     * @param data 待 加密 数据
     * @param key 私 钥 
     * @return byte[] 加密 数据
     * @throws Exception 
     **/ 
    public static byte[] encryptByPrivateKey( byte[] data, byte[] key) throws Exception { 
        // 取得 私 钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( key); 
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM); 
        // 生成 私 钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec); 
        // 对数 据 加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()); 
        cipher.init(Cipher.ENCRYPT_MODE, privateKey); 
        return cipher.doFinal( data); 
     }
    /** 
     * 取得 私 钥
     * @param keyMap 密钥 Map 
     * @return byte[] 私 钥 
     * @throws Exception 
     **/ 
    public static byte[] getPrivateKey(Map< String, Object> keyMap) throws Exception { 
        Key key = (Key) keyMap.get(PRIVATE_KEY); 
        return key.getEncoded(); 
    } 
    /** 
     * 取得 公 钥 
     * @param keyMap 密钥 Map 
     * @return byte[] 公 钥 
     * @throws Exception 
     */ 
    public static byte[] getPublicKey(Map< String, Object> keyMap) throws Exception { 
        Key key = (Key) keyMap.get(PUBLIC_KEY); 
        return key.getEncoded(); 
    }
    
    /** 
     * 初始化 密钥 
     * @return Map 密钥 Map 
     * @throws Exception 
     */ 
    public static Map< String, Object> initKey() throws Exception { 
        // 实例 化 密钥 对 生 成器 
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM); 
        // 初始化 密钥 对 生 成器 
        keyPairGen.initialize(KEY_SIZE);
        // 生成 密钥 对 
        KeyPair keyPair = keyPairGen.generateKeyPair(); 
        // 公 钥 
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
        // 私 钥 
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
        // 封装 密钥 
        Map<String, Object> keyMap = new HashMap<String, Object>( 2); 
        keyMap.put(PUBLIC_KEY, publicKey); 
        keyMap.put(PRIVATE_KEY, privateKey); 
        return keyMap;
    }
    
    public static void main(String[] args) throws Exception {
        Map< String, Object> keyMap = RSADemo.initKey(); 
        byte[] publicKey = RSADemo.getPublicKey(keyMap); 
        byte[] privateKey = RSADemo.getPrivateKey(keyMap); 
        System.err.println("公 钥:\n" + Base64.encodeBase64String(publicKey)); 
        System.err.println("私 钥:\n" + Base64.encodeBase64String(privateKey));
        System.err.println("\n--- 私 钥 加密-- 公 钥 解密---"); 
        String inputStr1 = "RSA 加密 算法"; 
        byte[] data1 = inputStr1.getBytes(); 
        System.err.println("原文:" + inputStr1); 
        // 加密 
        byte[] encodedData1 = RSADemo.encryptByPrivateKey(data1, privateKey); 
        System.err.println("加密 后:" + Base64.encodeBase64String(encodedData1));
        // 解密 
        byte[] decodedData1 = RSADemo.decryptByPublicKey(encodedData1, publicKey); 
        String outputStr1 = new String( decodedData1); 
        System.err.println("解密 后:" + outputStr1);
    }

}
