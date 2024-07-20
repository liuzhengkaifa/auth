package com.base.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.auth.entity.SysAuth;
import com.base.auth.to.UserListDto;
import com.base.auth.to.UserQueryReq;

import java.util.List;

/**
 * <p>
 * 系统认证信息表 Mapper 接口
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    List<UserListDto> queryUserList(UserQueryReq userQueryReq);
}
