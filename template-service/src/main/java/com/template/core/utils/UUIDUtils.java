package com.template.core.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author xiong.canwei
 * @version V1.0
 * @date 2020/2/19 23:55
 */
public class UUIDUtils {

    /**
     * 生成UUID
     * 受系统随机数生成速度的影响
     *
     * @return {@code String} UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
