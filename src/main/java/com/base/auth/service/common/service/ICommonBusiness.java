package com.base.auth.service.common.service;

import com.base.auth.to.UserPermissionRes;

import java.util.List;

public interface ICommonBusiness {

    List<UserPermissionRes> checkUserPermissions(Integer id);
}
