package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.base.auth.entity.UserDepartment;
import com.base.auth.entity.UserRoleRelation;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.mapper.UserRoleRelationMapper;
import com.base.auth.service.user.service.IUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联关系表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements IUserRoleRelationService {

    @Override
    public boolean deleteByUserId(Integer id) {
        LambdaUpdateWrapper<UserRoleRelation> lambdaUpdateWrapper = new LambdaUpdateWrapper<UserRoleRelation>()
                .set(UserRoleRelation::getDelFlag, DelFlagEnum.DELETED.getValue())
                .eq(UserRoleRelation::getUserId, id);
        return this.update(lambdaUpdateWrapper);
    }
}
