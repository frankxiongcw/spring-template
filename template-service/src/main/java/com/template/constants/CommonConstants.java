package com.template.constants;

/**
 * 常量
 *
 * @author roamer
 * @version V1.0
 * @date 2020/2/18 20:14
 */
public final class CommonConstants {


    /**
     * Token过期时间 毫秒
     */
    public static final long E_TOKEN_EXPIRE_TIME = 30L * 60L * 1000L;
    /**
     * Token过期时间 毫秒
     */
    public static final long C_TOKEN_EXPIRE_TIME = 300L * 60L * 1000L;

    /**
     * C端Token过期时间 毫秒
     */
    public static final long C_TOKEN_EXPIRE_TIME_OF_DAY = 30;

    /**
     * Token属性名称
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 前端密码加解密key
     */
    public static final String SECRET_KEY = "starhomestarhome";

    /**
     * 默认密码
     */
    public static final String DEFAULT_USER_LOGIN_KEY = "123456";

    /**
     * 图片验证码过期时间 毫秒
     */
    public static final long VERIFY_IMAGE_CODE_EXPIRE_TIME = 60000;

    public static final Boolean COMMON_FALSE = false;

    public static final Boolean COMMON_TRUE = true;

    /**
     * 管理平台登陆用户信息
     */
    public static final String E_LOGIN_USER_INFO = "USER:INFO:E:";

    public static final String OPEN_ID ="open_id";

    public static final String SESSION_KEY ="session_key";

    public static final String UNION_ID ="open_id";

    public static final String ERR_CODE ="err_code";
}
