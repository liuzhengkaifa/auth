package com.base.auth.controller;


import com.base.auth.common.Response;
import com.base.auth.service.user.service.ISysAuthService;
import com.base.auth.to.AddUserReq;
import com.base.auth.to.AddUserRes;
import com.base.auth.to.AuthReqTo;
import com.base.auth.to.AuthResTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    ISysAuthService iSysAuthService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加系统用户数据", httpMethod = "POST")
    Response<AddUserRes> register(@Validated @RequestBody AddUserReq addUserReq) throws Exception {
        return Response.ok(iSysAuthService.register(addUserReq));
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录认证", httpMethod = "POST")
    Response<AuthResTo> login(@Validated @RequestBody AuthReqTo authReq) {
        return Response.ok(iSysAuthService.login(authReq));
    }
}
