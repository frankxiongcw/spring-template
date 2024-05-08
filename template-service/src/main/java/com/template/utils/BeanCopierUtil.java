package com.template.utils;



import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象拷贝工具
 * <p>使用基于CGLIB的BeanCopier实现，BeanCopier是目前性能最好的</p>
 * <p>Notice：该拷贝是浅拷贝，如果实在需要深拷贝，需另行使用 DozerBeanMapper 实现，但其性能非常差</p>
 * @author li.guoqiang
 **/
public class BeanCopierUtil {

    /**
     * beanName - copier 的缓存对象，避免每次都重新创建
     **/
    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>(64);

    /**
     * 对象属性拷贝
     * @param source 源对象
     * @param target 目标对象
     * @author li.guoqiang
     **/
    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }
        if (target == null) {
            throw new IllegalArgumentException("target can not be null");
        }
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
    }

    /**
     * 对象属性拷贝
     * @param source 源对象
     * @param targetClz 目标对象类类型
     * @author li.guoqiang
     **/
    public static <T> T copyProperties(Object source, Class<T> targetClz) {
        T target = null;
        try {
            target = targetClz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        copyProperties(source, target);
        return target;
    }

    /**
     * 列表对象拷贝
     * @param sourceList 源对象
     * @param targetClass 目标对象类类型
     * @author li.guoqiang
     **/
    public static <T> List<T> copyPropertiesForList(List<?> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return null;
        }
        List<T> resultList = new ArrayList<>(sourceList.size());
        for (Object o : sourceList) {
            T target = copyProperties(o, targetClass);
            resultList.add(target);
        }
        return resultList;
    }

    /**
     * 生成缓存key
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String generateCacheKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + "_" + tgtClazz.getName();
    }

    private static BeanCopier getBeanCopier(Class<?> srcClazz, Class<?> tgtClazz) {
        String key = generateCacheKey(srcClazz, tgtClazz);
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(srcClazz, tgtClazz, false);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        return beanCopier;
    }

    static class DefaultConverter implements Converter {

        @Override
        public Object convert(Object o, Class aClass, Object o1) {
            return null;
        }
    }
}
