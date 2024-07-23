package com.base.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("corp_info_extern")
public class CorpInfoExtern implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer corpId;

    /**
     * 主要产品
     */
    private String mainProducts;

    /**
     * 产品领先情况
     */
    private String productLeadershipStatus;

    /**
     * 国内外市场
     */
    private String domesticInternationalMarket;

    /**
     * 参加重点项目情况
     */
    private String keyProjectParticipation;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private Integer delFlag;

}
