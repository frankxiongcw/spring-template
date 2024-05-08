package com.template.pojo;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = -4696898674758059398L;
    private int code;
    private String message;
    private T data;
    private boolean ok = true;

    public ResultResponse(int code, String message, T data) {
        this.code = code;
        this.ok = code == 200;
        this.message = message;
        if (null != data) {
            this.data = data;
        }

    }

    public ResultResponse(int code, T data) {
        this.code = code;
        this.ok = code == 200;
        this.data = data;
    }

    public ResultResponse(T data) {
        this.data = data;
    }

    public ResultResponse() {
    }

    public static <T> ResultResponse<T> success() {
        return new ResultResponse(ResultCode.C200.getCode(), (String)null, (Object)null);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse(ResultCode.C200.getCode(), data);
    }

    public static <T> ResultResponse<T> success(T data, String message) {
        return new ResultResponse(ResultCode.C200.getCode(), message, data);
    }

    public static <T> ResultResponse<T> error(String message) {
        return new ResultResponse(ResultCode.C500.getCode(), message, (Object)null);
    }

    public static <T> ResultResponse<T> error(int code, String message) {
        ResultResponse<T> resultResponse = new ResultResponse();
        resultResponse.setOk(Boolean.FALSE);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        return resultResponse;
    }

    public static <T> ResultResponse<T> bizError(String message) {
        return new ResultResponse(ResultCode.C9999.getCode(), message, (Object)null);
    }

    public static <T> ResultResponse<T> custom(int code, String message) {
        return new ResultResponse(code, message, (Object)null);
    }

    public static <T> ResultResponse<T> custom(int code, String message, T data) {
        return new ResultResponse(code, message, data);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
        this.ok = code == 200;
    }

    public boolean getOk() {
        return this.ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}