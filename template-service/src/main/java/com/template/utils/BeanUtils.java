package com.template.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.dozer.DozerBeanMapper;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

/**
 * 通过json序列化及反系列化copy属性信息
 */
public class BeanUtils {

    private static final DozerBeanMapper mapper = new DozerBeanMapper();
    static {
        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
    }

    @SuppressWarnings("all")
    public static <T> T copyPropertiesByJSON(Object source, Class<T> clazz) {
        if(source == null) {
            throw new RuntimeException("source object is null!");
        }

        //SerializerFeature.DisableCircularReferenceDetect 解决循环引用的问题
        String jsonStr = JSON.toJSONString(source, SerializerFeature.DisableCircularReferenceDetect);
        return JSON.parseObject(jsonStr, clazz);
    }


    @SuppressWarnings("all")
    public static <T> T copyProperties(Object source, TypeReference<T> type) {
        if(source == null) {
            throw new RuntimeException("source object is null!");
        }

        String jsonStr = JSON.toJSONString(source, SerializerFeature.DisableCircularReferenceDetect);
        return JSON.parseObject(jsonStr, type);
    }


    public static <S, T> List<T> copyProperties(List<S> origLst, Class<T> destClz) {
        List<T> destLst = new ArrayList<>();
        if(origLst == null || origLst.isEmpty()) return destLst;
        for(S orig : origLst) {
            T dest = copyProperties(orig, destClz);
            destLst.add(dest);
        }
        return destLst;
    }

    public static <S, T> T copyProperties(S orig, T dest)  {
        if(orig == null) return null;
        mapper.map(orig, dest);
        return dest;
    }

    public static <S, T> T copyProperties(S orig, Class<T> destClz) {
        if(orig == null) return null;
        return mapper.map(orig, destClz);
    }


    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    public static void fastBeanCopy(Object source,Object target){
        if(source == null || target == null){
            return;
        }
        String key = genKey(source, target);
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(source, target, null);
    }

    public static <S, T>T fastBeanCopy(S source,Class<T> targetClazz){
        if(source == null){
            return null;
        }
        T target = null;
        try {
            target = targetClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fastBeanCopy(source,target);
        return target;
    }

    private static String genKey(Object source,Object target){
        return source.getClass().getName() + target.getClass().getName();
    }
}
