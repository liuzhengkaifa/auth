package com.base.auth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QualificationTypeEnum {
    GXQY(0,"高新企业"),
    ZZTX(1,"专精特新");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getNameByCode(Integer code) {
        for (QualificationTypeEnum value : QualificationTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "";
    }
}
