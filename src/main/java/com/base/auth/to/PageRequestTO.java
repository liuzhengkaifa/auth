package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhudong
 */
@Data
public class PageRequestTO {

    @ApiModelProperty(value = "页码，从1开始")
    @NotNull(message = "页码不能为空")
    private Long current = 1L;

    @ApiModelProperty(value = "每页查询数量,默认10")
    @NotNull(message = "每页查询数量不能为空")
    private Long size = 10L;
}
