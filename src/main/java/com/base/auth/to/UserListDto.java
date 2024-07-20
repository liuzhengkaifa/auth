package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListDto {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户账号")
    private String principal;
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "区id")
    private Integer departmentId;
    @ApiModelProperty(value = "区名称")
    private String departmentName;
    @ApiModelProperty(value = "菜单id")
    private Integer menuId;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;


}
