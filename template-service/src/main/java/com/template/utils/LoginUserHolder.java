package com.template.utils;


import com.template.constants.ExceptionDef;
import com.template.exception.ServiceException;
import com.template.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.NamedThreadLocal;

/**
 * 获取当前登陆用户
 */
@Log4j2
public class LoginUserHolder {

    private static final ThreadLocal<User> E_USER_INFO_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal_E_UserInfo");

    public static void set(User userInfo) {
        E_USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    public static void remove() {
        E_USER_INFO_THREAD_LOCAL.remove();
    }

    public static User get() {
        User userInfo = E_USER_INFO_THREAD_LOCAL.get();
        if (userInfo == null) {
            log.info("用户未登录");
            throw new ServiceException(ExceptionDef.USER_AUTHORIZATION_ERROR);
        }
        return userInfo;
    }

    public static User getCurrentUser() {
        return  LoginUserHolder.get();
    }

    private LoginUserHolder() {
    }
}
