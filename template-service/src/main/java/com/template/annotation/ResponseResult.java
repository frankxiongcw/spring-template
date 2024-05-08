package com.template.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 中文类名: Response结果返回注解
 * 中文描述: Response结果返回注解
 *
 * @author zhouxinlei
 * @date 2019-11-04 11:22:26
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseResult {

}
