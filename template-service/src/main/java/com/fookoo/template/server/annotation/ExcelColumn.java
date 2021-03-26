package com.fookoo.template.server.annotation;

import java.lang.annotation.*;

/**
 * excel字段注解
 *
 * @author  xiong.canwei
 * @version 2020/11/4. 21:37
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    String value() default "";

    int col() default 0;
}