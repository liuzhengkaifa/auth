package com.base.auth.service.user.service.impl;

import com.base.auth.entity.RoleMain;
import com.base.auth.mapper.RoleMainMapper;
import com.base.auth.service.user.service.IRoleMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.to.UserRoleRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色主表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
@Service
public class RoleMainServiceImpl extends ServiceImpl<RoleMainMapper, RoleMain> implements IRoleMainService {

    @Override
    public List<UserRoleRes> roleList() {

        List<RoleMain> roleMainList = this.list();
        return roleMainList.stream().map(x->{
            UserRoleRes userRoleRes = new UserRoleRes();
            userRoleRes.setRoleId(x.getId());
            userRoleRes.setRoleName(x.getRoleName());
            return userRoleRes;
        }).collect(Collectors.toList());
    }
}
