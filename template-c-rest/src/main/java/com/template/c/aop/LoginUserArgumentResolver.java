package com.fookoo.template.c.aop;

import com.template.core.utils.LoginUserHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

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
public class LoginUserArgumentResolver extends AbstractLoginUserArgumentResolver {

    /**
     * 参数转换
     *
     * @param parameter     源参数
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return LoginUserHolder.getCurrentUser();
    }
}
