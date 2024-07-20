package com.base.auth.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.common.Response;
import com.base.auth.entity.SysAuth;
import com.base.auth.to.*;

import java.util.List;

/**
 * <p>
 * 系统认证信息表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
public interface ISysAuthService extends IService<SysAuth> {

    AddUserRes register(AddUserReq addUserReq);

    AuthResTo login(AuthReqTo authReq);
    /**
     * 根据登录账号查询注册信息。
     *
     * @param principal 登录账号。
     * @return SysAuth 注册信息。
     */
    SysAuth findByPrincipal(String principal);

    List<UserDetail> queryList(UserQueryReq userQueryReq,SysAuth sysAuth);

    AddUserRes editUser(AddUserReq addUserReq);

    UserDetail info(Integer id);
}
