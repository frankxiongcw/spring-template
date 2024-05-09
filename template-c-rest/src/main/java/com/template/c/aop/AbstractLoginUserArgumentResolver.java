package com.fookoo.template.c.aop;

import com.template.api.pojo.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.lang.reflect.Method;

/**
 * 登陆用户 参数解析器
 * <p>
 * 自动注入当前登陆用户
 * </p>
 *
 * @author roamer
 * @version v1.0
 * @date 2020/2/19 10:42
 */
public abstract class AbstractLoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 用于判断当前参数是否需要转换类型
     *
     * @param parameter 源参数
     * @return {@code true} 参数类型为 {@link CurrentUser}
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        final Method method = parameter.getMethod();
        if (method != null) {
            return parameter.getParameterType().isAssignableFrom(User.class);
        }
        return false;
    }
}
