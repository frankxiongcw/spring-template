package com.fookoo.template.c.aop;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.api.pojo.ResultResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 响应体返回之前做一些处理
 * <p>统一返回参数处理</p>
 *
 * @author xiong.canwei
 * @version v1.0
 * @date 2020/2/19 10:42
 */
@Order(1)
@ControllerAdvice(basePackages = "com.fookoo.template.c.controller")
public class ResponseBodyWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 方法未标注 @NoRestResult 注解，并且返回类型 非ResultResponse 类型
        return !returnType.hasMethodAnnotation(NoRestResult.class) && !returnType.getGenericParameterType()
                .equals(ResultResponse.class);
    }

    @Override
    public ResultResponse beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
                // 将数据包装在ResultResponse里后，再转换为json字符串响应给前端
                return ResultResponse.success(body);
        }
        return ResultResponse.success(body);
    }
}
