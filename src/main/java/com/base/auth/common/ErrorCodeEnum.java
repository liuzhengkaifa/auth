package com.base.auth.common;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:26
 * @Description TODO
 */
public enum ErrorCodeEnum {
    UNKNOWN_ERROR("00000", "未知系统异常"),
    SUCCESS("10000", "成功"),
    PARAM_ERROR("00002", "参数校验错误"),
    NOT_FOUND_ERROR("00003", "所请求的资源不存在或已被删除"),
    SYSTEM_ERROR("00004", "系统内部异常"),
    UNKNOWN_ERROR_CODE("00005", "未知错误码");


    String code;

    String message;

    private ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
