package com.base.auth.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.entity.SysAuth;
import com.base.auth.to.AddUserReq;
import com.base.auth.to.AddUserRes;
import com.base.auth.to.AuthReqTo;
import com.base.auth.to.AuthResTo;

/**
 * <p>
 * 系统认证信息表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
public interface ISysAuthService extends IService<SysAuth> {

    AddUserRes add(AddUserReq addUserReq) throws Exception;

    AuthResTo login(AuthReqTo authReq) throws Exception;
}
