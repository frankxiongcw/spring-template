package com.template.e.interceptor;



import com.template.e.aop.PassLogin;
import com.template.core.constants.CommonConstants;
import com.template.core.constants.ExceptionDef;
import com.template.core.exception.ServiceException;
import com.template.core.utils.LoginUserHolder;
import com.template.core.utils.StringUtils;


import com.template.api.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author jinbin
 * @date 2018-07-08 20:41
 */
@Log4j2
public class AuthenticationInterceptor  extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConstants.TOKEN_HEADER);
        if (token == null) {
            token = request.getParameter("token");
            if (StringUtils.isBlank(token)) {
                if (isPassLogin(handler)) {
                    return super.preHandle(request, response, handler);
                }
                log.info("用户未登录");
                throw new ServiceException(ExceptionDef.USER_AUTHORIZATION_ERROR);
            }
        }
        String tokenKey = CommonConstants.E_LOGIN_USER_INFO.concat(token);
        User user = redisTemplate.opsForValue().get(tokenKey);
        if (user == null) {
            log.info("用户未登录");
            throw new ServiceException(ExceptionDef.USER_AUTHORIZATION_ERROR);
        }
        Long keyExpire = redisTemplate.getExpire(tokenKey, TimeUnit.MINUTES);
        if (null != keyExpire) {
            redisTemplate.opsForValue().set(tokenKey, user, CommonConstants.E_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        }
        LoginUserHolder.set(user);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 是否无需授权
     *
     * @param handler {@link HandlerMethod}
     * @return {@code true} 无需登陆
     */
    private boolean isPassLogin(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            PassLogin passLogin = handlerMethod.getMethod().getAnnotation(PassLogin.class);
            // 如果方法上的注解为空 则获取类的注解
            if (Objects.isNull(passLogin)) {
                passLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(PassLogin.class);
            }
            // 如果标记了注解，则判断是否存在toKen
            return !Objects.isNull(passLogin);
        }
        return true;
    }
}
