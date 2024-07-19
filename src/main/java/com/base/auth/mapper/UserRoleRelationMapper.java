package com.base.auth.mapper;

import com.base.auth.entity.UserRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.auth.to.UserPermissionRes;
import com.base.auth.to.UserRoleDepartmentDto;

import java.util.List;

/**
 * <p>
 * 用户与角色关联关系表 Mapper 接口
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {

    List<UserRoleDepartmentDto> findUserRoleRelationDepartment(Integer id);
}
