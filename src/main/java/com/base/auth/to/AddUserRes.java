package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:21
 * @Description 
 */
@Data
public class AddUserRes {

    @ApiModelProperty(value = "账号id")
    private Integer authId;

    @ApiModelProperty(value = "用户名")
    private String principal;

}
