package com.base.auth.service.common.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.base.auth.mapper.UserRoleRelationMapper;
import com.base.auth.service.common.service.ICommonBusiness;
import com.base.auth.to.UserPermissionRes;
import com.base.auth.to.UserRoleDepartmentDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liuzheng
 * @date 2024年07月19日 14:38
 * @Description 公共方法
 */
@Service
public class CommonBusinessImpl implements ICommonBusiness {

    @Resource
    UserRoleRelationMapper userRoleRelationMapper;

    // 将角色ID定义为常量
    private static final int ROLE_ID_SUPER_ADMIN = 1;
    private static final int ROLE_ID_SYSTEM_ADMIN = 3;

    @Override
    public UserPermissionRes checkUserPermissions(Integer id) {
        UserPermissionRes userPermissionRes = new UserPermissionRes();

        // 添加异常处理和空值检查
        List<UserRoleDepartmentDto> userRoleDepartmentDtos = null;
        try {
            userRoleDepartmentDtos = userRoleRelationMapper.findUserRoleRelationDepartment(id);
        } catch (Exception e) {
            // 异常处理逻辑，例如记录日志或返回错误信息
            // 此处的处理依赖具体的应用需求
            return userPermissionRes; // 可能需要根据实际情况调整返回值
        }

        if (userRoleDepartmentDtos == null || userRoleDepartmentDtos.isEmpty()) {
            // 如果没有找到用户角色关系，则直接返回空权限结果
            return userPermissionRes;
        }

        // 使用单次流操作完成所有检查和集合构建
        Map<Integer, Boolean> roleMap = userRoleDepartmentDtos.stream()
                .collect(Collectors.toMap(
                        UserRoleDepartmentDto::getRoleId,
                        roleDto -> true,
                        (a, b) -> true // 如果角色重复，保持为true，适用于此场景
                ));

        userPermissionRes.setSuperAdmin(roleMap.containsKey(ROLE_ID_SUPER_ADMIN));
        userPermissionRes.setSysAdmin(roleMap.containsKey(ROLE_ID_SYSTEM_ADMIN));

        userPermissionRes.setIds(userRoleDepartmentDtos.stream()
                .map(UserRoleDepartmentDto::getDepartmentId)
                .filter(x->ObjectUtils.isNotNull(x))
                .collect(Collectors.toList()));

        return userPermissionRes;
    }
}
