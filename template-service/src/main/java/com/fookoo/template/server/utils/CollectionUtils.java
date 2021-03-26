package com.fookoo.template.server.utils;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author xiong.canwei
 * @version V1.0
 * @date 2020/2/17 10:49
 * @see org.apache.commons.collections4.CollectionUtils
 */
public final class CollectionUtils {

    /**
     * 检查是否为空
     *
     * @param coll 集合
     * @return {@code boolean} true:集合是空的
     */
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 检查是否不为空
     *
     * @param coll 集合
     * @return {@code boolean} true:集合非空
     */
    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 颠倒顺序
     *
     * @param array 数组
     */
    public static void reverseArray(final Object[] array) {
        org.apache.commons.collections4.CollectionUtils.reverseArray(array);
    }

}
