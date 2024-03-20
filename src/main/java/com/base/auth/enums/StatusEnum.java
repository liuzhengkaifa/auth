package com.base.auth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusEnum {
    INVALID(0, "失效"),
    VALID(1, "有效");

    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
