package com.base.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公司信息表
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("corp_info")
public class CorpInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 所属区
     */
    private String district;

    /**
     * 关键环节
     */
    private String keyProcess;

    /**
     * 企业代码
     */
    private String companyCode;

    /**
     * 注册资本
     */
    private String registeredCapital;

    /**
     * 企业资本
     */
    private String capital;

    /**
     * 企业资质
     */
    private String qualifications;

    /**
     * 是否纳统;1:是，0否
     */
    private Integer isStatistical;

    /**
     * 纳统行业
     */
    private String statisticalIndustry;

    /**
     * 是否参与老03
     */
    private Integer participateOld;

    /**
     * 企业方案
     */
    private String businessPlan;

    /**
     * 主要产品
     */
    private String mainProducts;

    /**
     * 名词解释
     */
    private String termExplanation;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Integer createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    private Integer updateUserId;

    /**
     * 修改人名称
     */
    private String updateUserName;

    /**
     * 删除标记 0未删除 1已删除
     */
    private Integer delFlag;


}
