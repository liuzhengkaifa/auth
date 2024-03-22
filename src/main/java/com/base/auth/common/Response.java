package com.base.auth.common;

import com.base.auth.enums.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:23
 * @Description
 */

@ApiModel("接口公共响应")
@Data
public class Response<T> implements Serializable {
    @ApiModelProperty("成功标识，true：成功，false：失败")
    private boolean success = true;

    @ApiModelProperty("错误码")
    private String errorCode = ErrorCodeEnum.SUCCESS.getCode();

    @ApiModelProperty("错误信息")
    private String message = ErrorCodeEnum.SUCCESS.getMessage();

    @ApiModelProperty("日志Id")
    private String logId;
    /**
     * 用来格式化消息
     */
    @JsonIgnore
    private Object[] arguments = new Object[0];

    @ApiModelProperty("返回的具体数据")
    private T data;

    public Response<T> success(String message) {
        this.message = message;
        this.errorCode = ErrorCodeEnum.SUCCESS.getCode();
        this.success = true;
        return this;
    }

    public static <T> Response<T> ok() {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setErrorCode(ErrorCodeEnum.SUCCESS.getCode());
        r.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * @param msg
     * @param <T>
     * @return
     * @see #okData(Object)
     */
    @Deprecated
    public static <T> Response<T> ok(String msg) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setErrorCode(ErrorCodeEnum.SUCCESS.getCode());
        r.setMessage(msg);
        return r;
    }

    public static <T> Response<T> okData(T data) {
        return ok(data);
    }

    public static <T> Response<T> ok(String msg, T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setErrorCode(ErrorCodeEnum.SUCCESS.getCode());
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setErrorCode(ErrorCodeEnum.SUCCESS.getCode());
        r.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static <T> Response<T> error() {
        return error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
    }

    public static <T> Response<T> error(String msg) {
        return error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), msg);
    }

    public static <T> Response<T> error(Enum errorEnum) {
        try {
            Class cls = errorEnum.getClass();
            Method getCodeMethod = cls.getMethod("getCode");
            Method getMessage = cls.getMethod("getMessage");
            String code = (String) getCodeMethod.invoke(errorEnum);
            String message = (String) getMessage.invoke(errorEnum);
            return error(code, message);
        } catch (Exception e) {
            return Response.error(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    public static <T> Response<T> error(String code, String msg) {
        return error(code, msg, new Object[0]);
    }

    public static <T> Response<T> error(String code, String msg, Object... arguments) {
        Response<T> r = new Response<T>();
        r.setErrorCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        r.setArguments(arguments);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}