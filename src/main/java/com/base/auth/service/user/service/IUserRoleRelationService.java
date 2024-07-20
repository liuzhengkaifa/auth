package com.base.auth.service.user.service;

import com.base.auth.entity.UserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户与角色关联关系表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
public interface IUserRoleRelationService extends IService<UserRoleRelation> {

    boolean deleteByUserId(Integer id);
}
