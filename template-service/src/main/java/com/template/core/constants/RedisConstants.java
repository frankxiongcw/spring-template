package com.template.core.constants;

/**
 * Redis Key
 *
 * @author roamer
 * @version V1.0
 * @date 2020/2/19 20:14
 */
public final class RedisConstants {


    /**
     * 前缀
     */
    private static final String SYSTEM_KEY_PREFIX = "STARHOME:";

    /**
     * 管理平台登陆用户信息
     */
    public static final String E_LOGIN_USER_INFO = SYSTEM_KEY_PREFIX.concat("USER:INFO:E:");

    /**
     * 会员系统登陆用户信息
     */
    public static final String C_LOGIN_USER_INFO = SYSTEM_KEY_PREFIX.concat("USER:INFO:C:");

    /**
     * 验证码
     */
    public static final String VERIFY_IMAGE_CODE = SYSTEM_KEY_PREFIX.concat("VERIFYIMAGE:");

    /**
     * 会员系统登陆验证码
     */
    public static final String C_LOGIN_CAPTCHA = SYSTEM_KEY_PREFIX.concat("C:LOGIN:CAPTCHA:");
}
