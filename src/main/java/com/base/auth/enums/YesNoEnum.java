package com.base.auth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YesNoEnum {
    NO(0,"否"),
    YES(1,"是");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getNameByCode(Integer code) {
        for (YesNoEnum value : YesNoEnum.values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "";
    }
}
