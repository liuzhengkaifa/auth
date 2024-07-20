package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:21
 * @Description
 */
@Data
public class AddUserReq {

    @ApiModelProperty(value = "账号id")
    private Integer id;

    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank(message = "登录账号不能为空")
    private String principal;

    @ApiModelProperty(value = "密码")
    private String credential;

    @ApiModelProperty(value = "角色id")
    private List<Integer> roleIds;

    @ApiModelProperty(value = "关联区id")
    private List<Integer> departmentIds;
}
