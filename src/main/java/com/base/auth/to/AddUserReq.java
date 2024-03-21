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

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String principal;

    /**
     * 凭证/密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String credential;

    /**
     * 问题
     */
    @ApiModelProperty(value = "密保问题", required = true)
    @NotBlank(message = "密保问题不能为空")
    private String question;

    /**
     * 答案
     */
    @ApiModelProperty(value = "密保答案", required = true)
    @NotBlank(message = "密保答案不能为空")
    private String answer;

}
