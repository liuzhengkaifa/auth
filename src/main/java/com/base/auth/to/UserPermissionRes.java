package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
public class UserPermissionRes {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer authId;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private Integer departmentId;
}
