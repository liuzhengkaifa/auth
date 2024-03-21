package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:47
 * @Description TODO
 */
@Data
public class CorpQueryReq  extends PageRequestTO {


    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 领域分类
     */
    @ApiModelProperty(value = "领域分类")
    private String categoryName;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    private String district;

    /**
     * 是否参与项目 1:是，0否
     */
    @ApiModelProperty(value = "是否参与项目 1:是，0否")
    private Integer participateOld;

    /**
     * 是否纳统 1:是，0否
     */
    @ApiModelProperty(value = "是否纳统 1:是，0否")
    private Integer isStatistical;

}
