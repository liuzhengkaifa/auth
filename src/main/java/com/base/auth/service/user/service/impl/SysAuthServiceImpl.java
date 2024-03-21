package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.entity.SecurityQuestion;
import com.base.auth.entity.SysAuth;
import com.base.auth.enums.AuthErrorCodeEnum;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.enums.StatusEnum;
import com.base.auth.mapper.SysAuthMapper;
import com.base.auth.service.user.service.ISecurityQuestionService;
import com.base.auth.service.user.service.ISysAuthService;
import com.base.auth.to.AddUserReq;
import com.base.auth.to.AddUserRes;
import com.base.auth.to.AuthReqTo;
import com.base.auth.to.AuthResTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统认证信息表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
@Service
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    @Resource
    ISecurityQuestionService iSecurityQuestionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddUserRes add(AddUserReq addUserReq) throws Exception {
        checkAccount(addUserReq);

        AddUserRes res = new AddUserRes();
        SysAuth sysAuth = new SysAuth();
        BeanUtils.copyProperties(addUserReq, sysAuth);
        sysAuth.setStatus(StatusEnum.VALID.getValue());
        this.save(sysAuth);

        SecurityQuestion securityQuestion = new SecurityQuestion();
        BeanUtils.copyProperties(addUserReq, securityQuestion);
        securityQuestion.setAuthId(sysAuth.getId());
        iSecurityQuestionService.save(securityQuestion);

        res.setAuthId(sysAuth.getId());
        res.setPrincipal(sysAuth.getPrincipal());
        return res;
    }

    @Override
    public AuthResTo login(AuthReqTo authReq){
        AuthResTo authResTo = new AuthResTo();
        List<SysAuth> list = this.list(new LambdaQueryWrapper<SysAuth>()
                .eq(SysAuth::getDelFlag, DelFlagEnum.NOT_DELETE.getValue())
                .eq(SysAuth::getPrincipal, authReq.getPrincipal())
                .eq(SysAuth::getCredential, authReq.getCredential()));

        if (CollectionUtils.isNotEmpty(list)) {
            SysAuth sysAuth = list.get(0);
            BeanUtils.copyProperties(sysAuth, authResTo);
        } else {
            throw new BizException(AuthErrorCodeEnum.USER_LOGIN_ERROR);
        }
        return authResTo;
    }

    private void checkAccount(AddUserReq addUserReq) throws Exception {
        List<SysAuth> list = this.list(new LambdaQueryWrapper<SysAuth>()
                .eq(SysAuth::getPrincipal, addUserReq.getPrincipal())
                .eq(SysAuth::getDelFlag, DelFlagEnum.NOT_DELETE.getValue()));
        if (CollectionUtils.isNotEmpty(list)) {
            throw new Exception("账号已存在");
        }

    }
}
