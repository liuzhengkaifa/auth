package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
public class SaveCorpInfoReq {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键，新增不传，编辑必传")
    private Integer id;

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
     * 关键环节
     */
    @ApiModelProperty(value = "关键环节")
    private String keyProcess;

    /**
     * 企业代码
     */
    @ApiModelProperty(value = "企业代码")
    private String companyCode;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 注册资本
     */
    @ApiModelProperty(value = "注册资本")
    private String registeredCapital;

    /**
     * 企业资质 高新企业 1， 专精特新 2
     */
    @ApiModelProperty(value = "企业资质 高新企业 1， 专精特新 2")
    private Integer qualifications;


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

    /**
     * 纳统行业
     */
    @ApiModelProperty(value = "纳统行业")
    private String statisticalIndustry;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String subName;

    /**
     * 二级分类代码
     */
    @ApiModelProperty(value = "二级分类代码")
    private String subCategoryCode;

    /**
     * 二级分类名称
     */
    @ApiModelProperty(value = "二级分类名称")
    private String subCategoryName;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业")
    private String major;

    /**
     * 单列 研究机构 1，代加工企业2，通信核心企业3，通信相关企业4
     */
    @ApiModelProperty(value = "单列 研究机构 1，代加工企业2，通信核心企业3，通信相关企业4")
    private Integer type;

    /**
     * 主要产品
     */
    @ApiModelProperty(value = "主要产品")
    private String mainProducts;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    private Integer updateUserId;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;
}
