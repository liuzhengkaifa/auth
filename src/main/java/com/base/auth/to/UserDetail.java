package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDetail {

    @ApiModelProperty(value = "账号id")
    private Integer id;

    @ApiModelProperty(value = "登录账号")
    private String principal;

    @ApiModelProperty(value = "用户角色")
    private List<UserRoleRes> roleMains;

    @ApiModelProperty(value = "用户管理区")
    private List<UserDepartmentRes> departments;

    @ApiModelProperty(value = "用户可查看菜单")
    private List<UserMenuRes> userMenuRes;
}
