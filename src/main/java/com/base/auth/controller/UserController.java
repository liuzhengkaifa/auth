package com.base.auth.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.base.auth.annotation.CurrentUser;
import com.base.auth.common.Response;
import com.base.auth.entity.SysAuth;
import com.base.auth.entity.UserDepartment;
import com.base.auth.service.user.service.IDepartmentService;
import com.base.auth.service.user.service.IRoleMainService;
import com.base.auth.service.user.service.ISysAuthService;
import com.base.auth.to.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    ISysAuthService iSysAuthService;

    @Resource
    IDepartmentService iDepartmentService;

    @Resource
    IRoleMainService iRoleMainService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    Response<UserDetail> info(@CurrentUser @ApiIgnore SysAuth currentUser) {
        return Response.ok(iSysAuthService.info(currentUser.getId()));
    }


    @RequestMapping(value = "/query-list", method = RequestMethod.POST)
    @ApiOperation(value = "根据参数查询用户列表信息", httpMethod = "POST")
    Response<List<UserDetail>> queryList(@Validated @RequestBody UserQueryReq userQueryReq, @CurrentUser @ApiIgnore SysAuth currentUser) {
        return Response.ok(iSysAuthService.queryList(userQueryReq,currentUser));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加、编辑系统用户数据", httpMethod = "POST")
    Response<AddUserRes> register(@Validated @RequestBody AddUserReq addUserReq) {
        if(ObjectUtils.isNull(addUserReq.getId())){
            return Response.ok(iSysAuthService.register(addUserReq));
        }else{
            return Response.ok(iSysAuthService.editUser(addUserReq));
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录认证", httpMethod = "POST")
    Response<AuthResTo> login(@Validated @RequestBody AuthReqTo authReq) {
        return Response.ok(iSysAuthService.login(authReq));
    }

    @RequestMapping(value = "/department-list", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有区域信息", httpMethod = "GET")
    Response<List<UserDepartmentRes>> departmentList() {
        return Response.ok(iDepartmentService.departmentList());
    }

    @RequestMapping(value = "/role-list", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有角色信息", httpMethod = "GET")
    Response<List<UserRoleRes>> roleList() {
        return Response.ok(iRoleMainService.roleList());
    }
}
