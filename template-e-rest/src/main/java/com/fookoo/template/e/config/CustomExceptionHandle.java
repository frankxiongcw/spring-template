package com.fookoo.template.e.config;


import com.template.constants.ExceptionDef;
import com.template.exception.ServiceException;
import com.template.utils.StringUtils;
import com.template.pojo.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandle.class);

    /**
     * 拦截ServiceException
     *
     * @param ex ServiceException
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    @Order(4)
    public ResultResponse handleException(ServiceException ex) {
        ResultResponse<Object> result = ResultResponse.error(ex.getCode(), ex.getMessage());
        logger.error("CustomGlobalExceptionHandler ServiceException error", ex);
        return result;
    }

    /**
     * 拦截spring validation exception
     *
     * @param ex BindException
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    @Order(5)
    public ResultResponse handleValidationException(Exception ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        BindingResult bindingResult = null;
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            bindingResult = e.getBindingResult();
        } else if (ex instanceof BindException) {
            BindException e = (BindException) ex;
            bindingResult = e.getBindingResult();
        }
        String message = null;
        if (bindingResult != null && bindingResult.getFieldError() != null) {
            FieldError fieldError = bindingResult.getFieldError();
            message = StringUtils.isBlank(fieldError.getDefaultMessage()) ? new ServiceException().matchMessage(
                    ExceptionDef.REQUIRED_PARAMS_NOT_EXISTS) : fieldError.getDefaultMessage();
        }
        result.setCode(ExceptionDef.REQUIRED_PARAMS_NOT_EXISTS);
        result.setMessage(message);
        result.setData(null);
        logger.error("CustomGlobalExceptionHandler BindException error", ex);
        return result;
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    @Order(6)
    public ResultResponse<Object> handleException(HttpMediaTypeNotSupportedException ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ExceptionDef.C415);
        result.setMessage(new ServiceException().matchMessage(ExceptionDef.C415));
        result.setData(null);
        logger.error("CustomGlobalExceptionHandler HttpMediaTypeNotSupportedException error", ex);
        return result;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    @Order(7)
    public ResultResponse<Object> handleException(HttpRequestMethodNotSupportedException ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ExceptionDef.C405);
        result.setMessage(new ServiceException().matchMessage(ExceptionDef.C405));
        result.setData(null);
        logger.error("CustomGlobalExceptionHandler HttpRequestMethodNotSupportedException error", ex);
        return result;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @Order(8)
    public ResultResponse<Object> handleException(Exception ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ExceptionDef.C500);
        result.setMessage(new ServiceException().matchMessage(ExceptionDef.C500));
        result.setData(null);
        logger.error("CustomGlobalExceptionHandler Exception error", ex);
        return result;
    }
}