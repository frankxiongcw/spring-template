package com.template.c.aop;

import java.lang.annotation.*;

/**
 * 非统一返回类型
 *
 * @author xiong.canwei
 * @version V1.0
 * @date 2020/2/19 10:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NoRestResult {
}
