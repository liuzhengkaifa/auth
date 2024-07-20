package com.base.auth.service.user.service;

import com.base.auth.entity.RoleMain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.to.UserRoleRes;

import java.util.List;

/**
 * <p>
 * 角色主表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
public interface IRoleMainService extends IService<RoleMain> {

    List<UserRoleRes> roleList();
}
