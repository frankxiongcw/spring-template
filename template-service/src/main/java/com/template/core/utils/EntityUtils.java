package com.template.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityUtils {

    public static boolean checkFieldValueNull(Object bean) {
        boolean result = false;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                if (fieldVal == null) {
                    result = true;
                }
                if("".equals(fieldVal)){
                    result = true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return result;
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

}
