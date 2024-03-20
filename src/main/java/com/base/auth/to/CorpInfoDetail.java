package com.base.auth.to;

import com.base.auth.enums.YesNoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description TODO
 */
@Data
public class CorpInfoDetail {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

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
     * 注册资本
     */
    @ApiModelProperty(value = "注册资本")
    private String registeredCapital;

    /**
     * 企业资本
     */
    @ApiModelProperty(value = "企业资本")
    private String capital;

    /**
     * 企业资质
     */
    @ApiModelProperty(value = "企业资质")
    private String qualifications;

    /**
     * 是否纳统;1:是，0否
     */
    @ApiModelProperty(value = "是否纳统")
    private Integer isStatistical;

    @ApiModelProperty(value = "是否纳统")
    private String isStatisticalDesc;

    /**
     * 纳统行业
     */
    @ApiModelProperty(value = "纳统行业")
    private String statisticalIndustry;

    /**
     * 是否参与老03
     */
    @ApiModelProperty(value = "是否参与老03")
    private Integer participateOld;

    @ApiModelProperty(value = "是否参与老03")
    private String participateOldDesc;

    /**
     * 企业方案
     */
    @ApiModelProperty(value = "企业方案")
    private String businessPlan;

    /**
     * 主要产品
     */
    @ApiModelProperty(value = "主要产品")
    private String mainProducts;

    /**
     * 名词解释
     */
    @ApiModelProperty(value = "名词解释")
    private String termExplanation;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;

    public void setIsStatistical(Integer isStatistical){
        this.isStatistical= isStatistical;
        this.isStatisticalDesc = YesNoEnum.getNameByCode(isStatistical);
    }

    public void setParticipateOld(Integer participateOld){
        this.participateOld = participateOld;
        this.participateOldDesc = YesNoEnum.getNameByCode(participateOld);
    }
}
