package com.fookoo.template.server.utils;

import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

/**
 * 字符串 工具类
 *
 * @author xiong.canwei
 * @version V1.0
 * @date 2019/1/20 14:06
 * @see org.apache.commons.lang3.StringUtils
 */
public final class StringUtils {

    /**
     * Checks if a CharSequence is empty ("") or null
     *
     * <pre>
     *      StringUtils.isEmpty(null)      = true
     *      StringUtils.isEmpty("")        = true
     *      StringUtils.isEmpty(" ")       = false
     *      StringUtils.isEmpty("bob")     = false
     *      StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is empty or null
     * @see org.apache.commons.lang3.StringUtils#isEmpty(CharSequence)
     */
    public static boolean isEmpty(final CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isEmpty(cs);
    }

    /**
     * Checks if a CharSequence is not empty ("") and not null
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is not null and not empty ("")
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * Checks if a CharSequence is empty (""), null or whitespace only
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is empty, null or whitespace only
     * @see org.apache.commons.lang3.StringUtils#isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    /**
     * Checks if a CharSequence is not empty ("") and not null and not whitespace only
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is not empty and not null and not whitespace only
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Checks if a CharSequence is not null and length > 0
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is not null and length > 0
     */
    public static boolean hasLength(final CharSequence cs) {
        return isNotEmpty(cs);
    }

    /**
     * Checks if a CharSequence is not empty and not null and not whitespace only
     *
     * @param cs 需检查的字符串
     * @return {@code true} if the CharSequence is not empty and not null and not whitespace only
     */
    public static boolean hasText(final CharSequence cs) {
        return isNotBlank(cs);
    }


    /**
     * Replace all occurrences of a substring within a string with another string.
     *
     * @param inString   {@code String} to examine
     * @param oldPattern {@code String} to replace
     * @param newPattern {@code String} to insert
     * @return a {@code String} with the replacements
     * @see org.apache.commons.lang3.StringUtils#replace(String, String, String)
     */
    public static String replace(final String inString, final String oldPattern,
                                 final String newPattern) {
        return org.apache.commons.lang3.StringUtils.replace(inString,
                oldPattern,
                newPattern);
    }

    /**
     * 格式化字符串
     *
     * @param formatStr 原始字符串
     * @param args      参数
     * @return 格式化后的字符串
     * @see Formatter#format(String, Object...)
     */
    public static String formatIfArgs(String formatStr, Object... args) {
        formatStr = formatStr.replace("{}", "%s");
        Formatter formatter = new Formatter();
        return formatter.format(formatStr, args).toString();
    }

    /**
     * 拼接元素
     *
     * @param element 元素
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Object[])
     */
    public static String join(Object... element) {
        return org.apache.commons.lang3.StringUtils.join(element);
    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Object[], char)
     */
    public static String join(Object[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(long[], char)
     */
    public static String join(long[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(int[], char)
     */
    public static String join(int[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);

    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(short[], char)
     */
    public static String join(short[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);

    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(byte[], char)
     */
    public static String join(byte[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);

    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(char[], char)
     */
    public static String join(char[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);

    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(float[], char)
     */
    public static String join(float[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);

    }

    /**
     * 拼接元素
     *
     * @param array     元素
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(double[], char)
     */
    public static String join(double[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    /**
     * 拼接元素
     *
     * @param array      元素
     * @param separator  拼接字符
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Object[], String, int, int)
     */
    public static String join(final Object[] array, String separator,
                              final int startIndex, final int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator,
                startIndex, endIndex);
    }

    /**
     * 拼接元素
     *
     * @param iterator  元素迭代器
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Iterator, char)
     */
    public static String join(final Iterator<?> iterator,
                              final char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    /**
     * 拼接元素
     *
     * @param iterator  元素迭代器
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Iterator, String)
     */
    public static String join(final Iterator<?> iterator,
                              final String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    /**
     * 拼接元素
     *
     * @param iterable  迭代
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Iterator, char)
     */
    public static String join(final Iterable<?> iterable,
                              final char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }


    /**
     * 拼接元素
     *
     * @param iterable  迭代
     * @param separator 拼接字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(Iterable, String)
     */
    public static String join(final Iterable<?> iterable,
                              final String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }

    /**
     * 拼接元素
     *
     * @param list       元素
     * @param separator  拼接字符
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(List, char, int, int)
     */
    public static String join(final List<?> list, final char separator,
                              final int startIndex, final int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(list, separator,
                startIndex, endIndex);
    }


    /**
     * 拼接元素
     *
     * @param list       元素
     * @param separator  拼接字符
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#join(List, String, int, int)
     */
    public static String join(final List<?> list, final String separator,
                              final int startIndex, final int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(list, separator,
                startIndex, endIndex);
    }

    /**
     * 拼接元素
     *
     * @param separator 拼接字符
     * @param objects   元素
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#joinWith(String, Object...)
     */
    public static String joinWith(final String separator,
                                  final Object... objects) {
        return org.apache.commons.lang3.StringUtils.joinWith(separator,
                objects);
    }

    /**
     * 删除字符中的空格
     *
     * @param str 字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#deleteWhitespace(String)
     */
    public static String deleteWhitespace(final String str) {
        return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
    }

    /**
     * 删除开头的子字符串
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#removeStart(String, String)
     */
    public static String removeStart(final String str, final String remove) {
        return org.apache.commons.lang3.StringUtils.removeStart(str, remove);
    }

    /**
     * 删除开头的子字符串，忽视大小写
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#removeStartIgnoreCase(String, String)
     */
    public static String removeStartIgnoreCase(final String str,
                                               final String remove) {
        return org.apache.commons.lang3.StringUtils.removeStartIgnoreCase(str,
                remove);
    }

    /**
     * 删除结尾的子字符串
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#removeEnd(String, String)
     */
    public static String removeEnd(final String str, final String remove) {
        return org.apache.commons.lang3.StringUtils.removeEnd(str, remove);
    }

    /**
     * 删除结尾的子字符串，忽视大小写
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#removeEndIgnoreCase(String, String)
     */
    public static String removeEndIgnoreCase(final String str,
                                             final String remove) {
        return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(str,
                remove);
    }

    /**
     * 删除子字符串
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#remove(String, String)
     */
    public static String remove(final String str, final String remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    /**
     * 删除子字符串，忽视大小写
     *
     * @param str    字符串
     * @param remove 子字符串
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#removeIgnoreCase(String, String)
     */
    public static String removeIgnoreCase(final String str,
                                          final String remove) {
        return org.apache.commons.lang3.StringUtils.removeIgnoreCase(str,
                remove);
    }

    /**
     * 删除子字符
     *
     * @param str    字符串
     * @param remove 子字符
     * @return {@code String}
     * @see org.apache.commons.lang3.StringUtils#remove(String, char)
     */
    public static String remove(final String str, final char remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }
}