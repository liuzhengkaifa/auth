package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
public class UserPermissionRes {
    /**
     * 是否超级管理员
     */
    @ApiModelProperty(value = "是否超级管理员")
    private boolean isSuperAdmin;

    /**
     * 是否区管理员
     */
    @ApiModelProperty(value = "是否区管理员")
    private boolean isSysAdmin;

    /**
     * 管理的区id
     */
    @ApiModelProperty(value = "管理的区id")
    private List<Integer> ids;
}
