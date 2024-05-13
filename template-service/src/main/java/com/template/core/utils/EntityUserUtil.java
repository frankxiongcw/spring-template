package com.template.core.utils;

import com.alibaba.fastjson.JSON;
import com.template.api.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Slf4j
public class EntityUserUtil {

    public static void addCreateAndModifyUser(Object obj, User user) {
        addCreateUser(obj, user);
        addModifyUser(obj, user);
    }

    public static void addCreateUser(Object obj, User user) {
        Class<?> clazz = null;
        try {
            clazz = obj.getClass();
            Field field = clazz.getDeclaredField("createCode");
            field.setAccessible(true);
            field.set(obj, user.getUserCode());

            field = clazz.getDeclaredField("createName");
            field.setAccessible(true);
            field.set(obj, user.getUserName());

            field = clazz.getDeclaredField("createTime");
            field.setAccessible(true);
            field.set(obj, LocalDateTime.now());
        } catch (Exception e) {
            log.error("addCreateUser error, clazz:{}, obj:{}, user:{}", JSON.toJSONString(clazz), JSON.toJSONString(obj), JSON.toJSONString(user), e);
        }
    }

    public static void addModifyUser(Object obj, User user) {
        Class<?> clazz = null;
        try {
            clazz = obj.getClass();
            Field field = clazz.getDeclaredField("modifyCode");
            field.setAccessible(true);
            field.set(obj, user.getUserCode());

            field = clazz.getDeclaredField("modifyName");
            field.setAccessible(true);
            field.set(obj, user.getUserName());

            field = clazz.getDeclaredField("modifyTime");
            field.setAccessible(true);
            field.set(obj, LocalDateTime.now());
        } catch (Exception e) {
            log.error("addCreateUser error, clazz:{}, obj:{}, user:{}", JSON.toJSONString(clazz), JSON.toJSONString(obj), JSON.toJSONString(user));
        }
    }

}
