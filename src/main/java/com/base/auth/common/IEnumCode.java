package com.base.auth.common;

/**
 * 枚举接口
 * @param <T>
 */
public interface IEnumCode<T> {

    T getCode();

    String getMessage();

    default boolean equals(IEnumCode errorCode) {
        if (this == errorCode) return true;
        if (errorCode != null && this.getCode().equals(errorCode.getCode())) return true;
        return false;
    }

    default boolean equal(T code) {
        return getCode().equals(code);
    }
}
