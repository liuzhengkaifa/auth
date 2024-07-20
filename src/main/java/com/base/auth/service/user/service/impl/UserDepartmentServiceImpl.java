package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.base.auth.entity.UserDepartment;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.mapper.UserDepartmentMapper;
import com.base.auth.service.user.service.IUserDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与组织关联关系表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
@Service
public class UserDepartmentServiceImpl extends ServiceImpl<UserDepartmentMapper, UserDepartment> implements IUserDepartmentService {

    @Override
    public boolean deleteByUserId(Integer id) {
        LambdaUpdateWrapper<UserDepartment> lambdaUpdateWrapper = new LambdaUpdateWrapper<UserDepartment>()
                .set(UserDepartment::getDelFlag, DelFlagEnum.DELETED.getValue())
                .eq(UserDepartment::getUserId, id);
        return this.update(lambdaUpdateWrapper);
    }
}
