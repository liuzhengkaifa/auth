package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
public class SpatialDistributionRes {
    /**
     * 所属区编码
     */
    @ApiModelProperty(value = "所属区编码")
    private Integer districtCode;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    private String district;


    /**
     * 企业数量
     */
    @ApiModelProperty(value = "企业数量")
    private Integer companyCount;

    /**
     * 代表企业名称
     */
    @ApiModelProperty(value = "代表企业名称")
    private String companyName;

}
