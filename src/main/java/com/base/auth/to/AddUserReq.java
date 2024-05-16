package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:21
 * @Description
 */
@Data
public class AddUserReq {

    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank(message = "登录账号不能为空")
    private String principal;

    /**
     * 凭证/密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String credential;

}
