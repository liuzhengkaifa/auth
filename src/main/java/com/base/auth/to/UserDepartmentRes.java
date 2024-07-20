package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDepartmentRes {

    @ApiModelProperty(value = "区id")
    private Integer departmentId;

    @ApiModelProperty("区名称")
    private String departmentName;
}
