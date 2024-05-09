package com.template.c.aop;

import java.lang.annotation.*;

/**
 * 无需登陆
 *
 * @author roamer
 * @version v1.0
 * @date 2019/12/26 14:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Documented
public @interface PassLogin {
}
