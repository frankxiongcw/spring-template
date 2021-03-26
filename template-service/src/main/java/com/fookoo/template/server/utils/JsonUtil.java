package com.fookoo.template.server.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Set;

/**
 * Json序列化的工具。使用Jackson作为序列化的library  jackson 2.0
 * <p/>
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory jsonFactory = new JsonFactory();
    /**
     * 序列化忽略filter
     */
    private static final String IGNORE_FILTER = "ignore";
//    private static ObjectMapper filterObjectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    /**
     * 将json发序列化为对象
     *
     * @param jsonAsString json字符串
     * @param pojoClass    需要反序列化的类型
     */
    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonAsString, pojoClass);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JSON[{}]反序列化失败", jsonAsString);
        }
        return result;
    }

    /**
     * 将json发序列化为对象
     *
     * @param jsonAsString json字符串
     * @param valueTypeRef 需要反序列化的类型
     */
    public static <T> T fromJson(String jsonAsString, TypeReference valueTypeRef) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonAsString, valueTypeRef);
        } catch (Exception e) {
            logger.error("JSON[{}]反序列化失败", jsonAsString);
        }
        return result;
    }

    public static String toJson(Object pojo) {
        return toJson(pojo, false);
    }

    public static String toJson(Object pojo, String exceptProperty) {
        return toJson(pojo, Sets.newHashSet(exceptProperty));
    }

    public static String toJson(Object pojo, String... exceptProperty) {
        return toJson(pojo, Sets.newHashSet(exceptProperty));
    }

    public static String toJson(Object pojo, Set<String> exceptProperties) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        SimpleBeanPropertyFilter fieldFilter = SimpleBeanPropertyFilter.serializeAllExcept(exceptProperties);
        filterProvider.addFilter(IGNORE_FILTER, fieldFilter);
        ObjectMapper filterObjectMapper = new ObjectMapper();
        filterObjectMapper.setFilterProvider(filterProvider);
        filterObjectMapper.addMixIn(pojo.getClass(), IgnoreFilter.class);
        String result = null;
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator jg = jsonFactory.createGenerator(sw);
            filterObjectMapper.writeValue(jg, pojo);
            result = sw.toString();
        } catch (Exception e) {
            logger.error("[{}]序列化成JSON失败", pojo, e);
        }
        return result;
    }

    /**
     * 将对象转化为json
     *
     * @param pojo        需要序列化的对象
     * @param prettyPrint 是否打印优化，即换行
     * @return String 返回序列化的字符串
     */
    public static String toJson(Object pojo, boolean prettyPrint) {
        String result = null;
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator jg = jsonFactory.createGenerator(sw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            objectMapper.writeValue(jg, pojo);
            result = sw.toString();
        } catch (Exception e) {
            logger.error("[{}]序列化成JSON失败", pojo);
        }
        return result;
    }

    public static String toJsonIgnoreEmpty(Object pojo) {
        return toJson(pojo, false, JsonInclude.Include.NON_EMPTY);
    }

    /**
     * 将对象转化为json
     *
     * @param pojo        需要序列化的对象
     * @param prettyPrint 是否打印优化，即换行
     * @param incl        /Include.Include.ALWAYS 默认
     *                    //Include.NON_DEFAULT 属性为默认值不序列化
     *                    //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
     *                    //Include.NON_NULL 属性为NULL 不序列化
     * @return String 返回序列化的字符串
     */
    public static String toJson(Object pojo, boolean prettyPrint, JsonInclude.Include incl) {
        String result = null;
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator jg = jsonFactory.createGenerator(sw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            objectMapper.setSerializationInclusion(incl);
            objectMapper.writeValue(jg, pojo);
            result = sw.toString();
        } catch (Exception e) {
            logger.error("[{}]序列化成JSON失败", pojo);
        }
        return result;
    }

    @JsonFilter(IGNORE_FILTER)
    private interface IgnoreFilter {
    }

    public static String toJsonStringNullValue(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
    }

}
