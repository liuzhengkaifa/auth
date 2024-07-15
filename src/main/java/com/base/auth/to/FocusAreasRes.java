package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
public class FocusAreasRes {
    /**
     * 领域分类
     */
    @ApiModelProperty(value = "领域分类")
    private String categoryName;


    /**
     * 企业数量
     */
    @ApiModelProperty(value = "企业数量")
    private Integer companyCount;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

}
