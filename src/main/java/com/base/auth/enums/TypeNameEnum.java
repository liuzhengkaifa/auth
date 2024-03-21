package com.base.auth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeNameEnum {
    YJJJ(1, "研究机构"),
    DJGQY(2, "代加工企业"),
    TXHXQY(3, "通信核心企业"),
    TXXGZY(4, "通信相关企业");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getNameByCode(Integer code) {
        for (TypeNameEnum value : TypeNameEnum.values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "";
    }
}
