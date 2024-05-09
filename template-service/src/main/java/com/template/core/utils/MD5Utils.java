package com.template.core.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * MD5工具类
 *
 * @author xiong.canwei
 * @version v1.0
 * @date 2020/2/18 21:28
 */
public final class MD5Utils {

    /**
     * 密码+盐值 md5密码
     *
     * @param password 密码
     * @param salt     盐值
     * @return 加密后值
     */
    public static String getMD5(String password, String salt) {
        return getMD5(getMD5(password) + salt);
    }

    public static String getMD5(String numStr) {
        return Hashing.md5().newHasher().putString(numStr, StandardCharsets.UTF_8).hash().toString();
    }

    public static void main(String[] args) {
        String numStr = "123456";
        String salt = UUID.randomUUID().toString();
        System.out.println("salt:" + salt);
        System.out.println("password:" + getMD5(numStr, salt));
    }
}