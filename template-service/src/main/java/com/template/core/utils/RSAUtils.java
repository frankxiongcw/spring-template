package com.template.core.utils;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.Cipher;
//import java.io.IOException;
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * RSA工具类
// *
// * @author xiong.canwei
// * @version v1.0
// * @date 2020/2/18 21:28
// */
//public final class RSAUtils {
//
//    /**
//     * 生成秘钥对
//     *
//     * @return
//     * @throws Exception
//     */
//    public static KeyPair getKeyPair() throws Exception {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(512);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        return keyPair;
//    }
//
//    /**
//     * 获取公钥(Base64编码)
//     *
//     * @param keyPair
//     * @return
//     */
//    public static String getPublicKey(KeyPair keyPair) {
//        PublicKey publicKey = keyPair.getPublic();
//        byte[] bytes = publicKey.getEncoded();
//        return byte2Base64(bytes);
//    }
//
//    /**
//     * 获取私钥(Base64编码)
//     *
//     * @param keyPair
//     * @return
//     */
//    public static String getPrivateKey(KeyPair keyPair) {
//        PrivateKey privateKey = keyPair.getPrivate();
//        byte[] bytes = privateKey.getEncoded();
//        return byte2Base64(bytes);
//    }
//
//    /**
//     * 将Base64编码后的公钥转换成PublicKey对象
//     *
//     * @param pubStr
//     * @return
//     * @throws Exception
//     */
//    public static PublicKey string2PublicKey(String pubStr) throws Exception {
//        byte[] keyBytes = base642Byte(pubStr);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey publicKey = keyFactory.generatePublic(keySpec);
//        return publicKey;
//    }
//
//    /**
//     * 将Base64编码后的私钥转换成PrivateKey对象
//     *
//     * @param priStr
//     * @return
//     * @throws Exception
//     */
//    public static PrivateKey string2PrivateKey(String priStr) throws Exception {
//        byte[] keyBytes = base642Byte(priStr);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//        return privateKey;
//    }
//
//    /**
//     * 公钥加密
//     *
//     * @param content
//     * @param publicKey
//     * @return
//     * @throws Exception
//     */
//    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] bytes = cipher.doFinal(content);
//        return bytes;
//    }
//
//    /**
//     * 私钥解密
//     *
//     * @param content
//     * @param privateKey
//     * @return
//     * @throws Exception
//     */
//    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] bytes = cipher.doFinal(content);
//        return bytes;
//    }
//
//    /**
//     * 字节数组转Base64编码
//     *
//     * @param bytes
//     * @return
//     */
//    public static String byte2Base64(byte[] bytes) {
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(bytes);
//    }
//
//    /**
//     * Base64编码转字节数组
//     *
//     * @param base64Key
//     * @return
//     * @throws IOException
//     */
//    public static byte[] base642Byte(String base64Key) throws IOException {
//        BASE64Decoder decoder = new BASE64Decoder();
//        return decoder.decodeBuffer(base64Key);
//    }
//
//    public static void main(String[] args) {
//        try {
//            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
//            //生成RSA公钥和私钥，并Base64编码
//            KeyPair keyPair = RSAUtils.getKeyPair();
//            String publicKeyStr = RSAUtils.getPublicKey(keyPair);
//            String privateKeyStr = RSAUtils.getPrivateKey(keyPair);
//            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
//            System.out.println("RSA私钥Base64编码:" + privateKeyStr);
//
//            //=================客户端=================
//            //hello, i am infi, good night!加密
//            String message = "admin";
//            //将Base64编码后的公钥转换成PublicKey对象
//            PublicKey publicKey = RSAUtils.string2PublicKey(publicKeyStr);
//            //用公钥加密
//            byte[] publicEncrypt = RSAUtils.publicEncrypt(message.getBytes(), publicKey);
//            //加密后的内容Base64编码
//            String byte2Base64 = RSAUtils.byte2Base64(publicEncrypt);
//            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);
//
//
//            //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################
//
//
//            //===================服务端================
//            //将Base64编码后的私钥转换成PrivateKey对象
//            PrivateKey privateKey = RSAUtils.string2PrivateKey(privateKeyStr);
//            //加密后的内容Base64解码
//            byte[] base642Byte = RSAUtils.base642Byte(byte2Base64);
//            //用私钥解密
//            byte[] privateDecrypt = RSAUtils.privateDecrypt(base642Byte, privateKey);
//            //解密后的明文
//            System.out.println("解密后的明文: " + new String(privateDecrypt));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
