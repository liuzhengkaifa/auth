package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryReq {

    @ApiModelProperty(value = "用户id")
    private Integer authId;


}
