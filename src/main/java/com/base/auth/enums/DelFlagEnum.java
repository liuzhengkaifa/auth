package com.base.auth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DelFlagEnum {
    NOT_DELETE(0,"未删除"),
    DELETED(1,"已删除");

    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
