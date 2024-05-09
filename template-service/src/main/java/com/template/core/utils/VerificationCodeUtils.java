package com.template.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 生成验证码
 */
public class VerificationCodeUtils {


    /**
     * 生成四位数的随机验证码
     * @return
     */
    public static int makeAuthCode() {
       return  (int) Math.round(Math.random() * (9999-1000) +1000);
    }

    public static void main(String[] args) {
        System.out.println(makeAuthCode());
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}

