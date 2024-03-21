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
 * 密保信息表
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("security_question")
public class SecurityQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * authId
     */
    private Integer authId;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

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
