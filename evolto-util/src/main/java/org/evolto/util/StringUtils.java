/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util
 * 类名称：StringUtils
 * <p>
 * 创建日期：2021/8/30
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util;

/**
 * @description: String工具类
 * @date: 2021/8/30 21:27
 * @author IFallowed
 * @version 1.0
 */
public class StringUtils {

    private StringUtils() {}

    private static final String STRING_EMPTY = "";

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:30
     * @param src 源字符串
     * @return java.lang.String
     */
    public static String getStr(Object src) {
        return getStr(src, -1);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:44
     * @param src 源字符串
     * @param defaultValue null时返回值
     * @return java.lang.String
     */
    public static String getStr(Object src, String defaultValue) {
        return getStr(src,-1, defaultValue);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:44
     * @param src 源字符串
     * @param length 获取长度
     * @return java.lang.String
     */
    public static String getStr(Object src, int length) {
        return getStr(src, 0, length);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:44
     * @param src 源字符串
     * @param length 获取长度
     * @param defaulrValue null时返回值
     * @return java.lang.String
     */
    public static String getStr(Object src, int length, String defaulrValue) {
        return getStr(src, 0, length, defaulrValue);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:45
     * @param src 源字符串
     * @param start 起始位置
     * @param length 获取长度
     * @return java.lang.String
     */
    public static String getStr(Object src, int start, int length) {
        return getStr(src, start, length, STRING_EMPTY, false);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:45
     * @param src 源字符串
     * @param start 起始位置
     * @param length 获取长度
     * @param defaulrValue null时返回值
     * @return java.lang.String
     */
    public static String getStr(Object src, int start, int length, String defaulrValue) {
        return getStr(src, start, length, defaulrValue, false);
    }

    /**
     * @description: 获取字符串，null转换成空字符串
     * @author IFallowed
     * @date:  2021/8/30 21:45
     * @param src 源字符串
     * @param start 起始位置
     * @param length 获取长度获取长度
     * @param defaultValue 获取长度
     * @param trim 是否去除空字符
     * @return java.lang.String
     */
    public static String getStr(Object src, int start, int length, String defaultValue, boolean trim) {
        if (null == src) {
            return defaultValue;
        }
        String value = src.toString();
        if (value.length() > start) {
            if (length < 0 || value.length() < start + length) {
                value = value.substring(start);
            } else {
                value = value.substring(start,start + length);
            }
        } else {
            value = STRING_EMPTY;
        }
        return trim ? value.trim() : value;
    }
}
