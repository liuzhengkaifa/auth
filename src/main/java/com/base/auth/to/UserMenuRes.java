package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuRes {

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;
}
