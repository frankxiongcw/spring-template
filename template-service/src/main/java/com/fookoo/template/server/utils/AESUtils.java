package com.fookoo.template.server.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 加密工具类
 */
public class AESUtils {

    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);

    /**
     * 算法/模式/补码方式
     */
    public static String encrypt(String content, String secretKey, String iv) {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), secretKey.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param content   需要加密的内容
     * @param secretKey 密钥
     * @param iv        向量
     * @return
     */
    public static String encrypt(byte[] content, byte[] secretKey, byte[] iv) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
            //向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            //加密
            byte[] result = cipher.doFinal(content);
            //Base64编码
            return new String(Base64.encodeBase64(result));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public static String decrypt(String content, String secretKey, String iv) {
        return decrypt(content.getBytes(StandardCharsets.UTF_8), secretKey.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param content   需要解密的内容
     * @param secretKey 密钥
     * @param iv        向量
     * @return
     */
    public static String decrypt(byte[] content, byte[] secretKey, byte[] iv) {
        try {
            //解码
            byte[] byteContent = Base64.decodeBase64(content);
            //密码key
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
            //向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            //执行操作
            byte[] bytes = cipher.doFinal(byteContent);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * AES加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        return encrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(), key.substring(0, 16).getBytes());
    }

    /**
     * AES解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        return decrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(), key.substring(0, 16).getBytes());
    }

}
