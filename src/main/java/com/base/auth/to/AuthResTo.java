package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:49
 * @Description TODO
 */
@Data
public class AuthResTo {
    @ApiModelProperty(value = "账号id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String principal;
}
