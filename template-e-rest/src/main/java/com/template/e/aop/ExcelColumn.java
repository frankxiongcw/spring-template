package com.template.e.aop;

import java.lang.annotation.*;

/**
 * excel字段注解
 *
 * @author wang.tao435
 * @version 2020/2/24. 21:37
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    String value() default "";

    int col() default 0;
}