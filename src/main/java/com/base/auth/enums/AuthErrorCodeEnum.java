package com.base.auth.enums;

import com.base.auth.common.IEnumCode;

public enum AuthErrorCodeEnum implements IEnumCode {
    USER_LOGIN_ERROR("30000", "用户名/密码错误"),
    ;

    private String code;
    private String message;

    AuthErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
