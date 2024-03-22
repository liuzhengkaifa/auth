package com.base.auth.to.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:46
 * @Description
 */
@Data
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER)
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
@ContentRowHeight(25)
@HeadRowHeight(25)
@ColumnWidth(25)
public class CorpInfoDetailExcelTo {

    /**
     * 领域分类
     */
    @ApiModelProperty(value = "领域分类")
    @ExcelProperty(value = "领域分类")
    private String categoryName;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    @ExcelProperty(value = "所属区")
    private String district;

    /**
     * 关键环节
     */
    @ApiModelProperty(value = "关键环节")
    @ExcelProperty(value = "关键环节")
    private String keyProcess;

    /**
     * 企业代码
     */
    @ApiModelProperty(value = "企业代码")
    @ExcelProperty(value = "企业代码")
    private String companyCode;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @ExcelProperty(value = "公司名称")
    private String companyName;

    /**
     * 注册资本
     */
    @ApiModelProperty(value = "注册资本")
    @ExcelProperty(value = "注册资本")
    private String registeredCapital;

    /**
     * 企业资质 高新企业 1， 专精特新 2
     */
    @ApiModelProperty(value = "企业资质 高新企业 1， 专精特新 2")
    @ExcelProperty(value = "企业资质")
    private String qualificationsDesc;

    /**
     * 是否参与项目 1:是，0否
     */
    @ApiModelProperty(value = "是否参与项目 1:是，0否")
    @ExcelProperty(value = "是否参与项目")
    private String participateOldDesc;

    /**
     * 是否纳统 1:是，0否
     */
    @ApiModelProperty(value = "是否纳统 1:是，0否")
    @ExcelProperty(value = "是否纳统")
    private String isStatisticalDesc;

    /**
     * 纳统行业
     */
    @ApiModelProperty(value = "纳统行业")
    @ExcelProperty(value = "纳统行业")
    private String statisticalIndustry;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @ExcelProperty(value = "名称")
    private String subName;

    /**
     * 二级分类代码
     */
    @ApiModelProperty(value = "二级分类代码")
    @ExcelProperty(value = "二级分类代码")
    private String subCategoryCode;

    /**
     * 二级分类名称
     */
    @ApiModelProperty(value = "二级分类名称")
    @ExcelProperty(value = "二级分类名称")
    private String subCategoryName;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业")
    @ExcelProperty(value = "专业")
    private String major;

    /**
     * 企业性质备注 研究机构 1，代加工企业2，通信核心企业3，通信相关企业4
     */
    @ApiModelProperty(value = "企业性质备注 研究机构 1，代加工企业2，通信核心企业3，通信相关企业4")
    @ExcelProperty(value = "企业性质备注")
    private Integer typeDesc;

    /**
     * 主要产品
     */
    @ApiModelProperty(value = "主要产品")
    @ExcelProperty(value = "主要产品")
    private String mainProducts;

}
