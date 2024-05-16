package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.entity.SysAuth;
import com.base.auth.enums.AuthErrorCodeEnum;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.enums.StatusEnum;
import com.base.auth.mapper.SysAuthMapper;
import com.base.auth.service.user.service.ISysAuthService;
import com.base.auth.to.AddUserReq;
import com.base.auth.to.AddUserRes;
import com.base.auth.to.AuthReqTo;
import com.base.auth.to.AuthResTo;
import com.base.auth.util.PasswordSecurityUtils;
import com.base.auth.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddUserRes register(AddUserReq registerReq) {
        AddUserRes res = new AddUserRes();
        try {
            // 验证用户名和密码
            validateUsernameAndPassword(registerReq.getPrincipal(), registerReq.getCredential());

            SysAuth sysAuth = new SysAuth();
            BeanUtils.copyProperties(registerReq, sysAuth);
            sysAuth.setCredential(PasswordSecurityUtils.hashPasswordWithSalt(registerReq.getCredential()));
            sysAuth.setStatus(StatusEnum.VALID.getValue());

            if (!this.save(sysAuth)) {
                throw new RuntimeException("Failed to save SysAuth");
            }
            res.setAuthId(sysAuth.getId());
            res.setPrincipal(sysAuth.getPrincipal());
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Error registering user", e);
        }
    }

    private void validateUsernameAndPassword(String principal, String password) {
        SysAuth existingUser = this.findByPrincipal(principal);
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 这里可以添加自定义的密码验证逻辑，比如密码强度等
        // 例如，确保密码长度至少为8个字符
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }


    @Override
    public AuthResTo login(AuthReqTo authReq) {
        AuthResTo authResTo = new AuthResTo();
        List<SysAuth> list = this.list(new LambdaQueryWrapper<SysAuth>()
                .eq(SysAuth::getDelFlag, DelFlagEnum.NOT_DELETE.getValue())
                .eq(SysAuth::getPrincipal, authReq.getPrincipal())
                .eq(SysAuth::getCredential, authReq.getCredential()));

        if (CollectionUtils.isNotEmpty(list)) {
            SysAuth sysAuth = list.get(0);
            BeanUtils.copyProperties(sysAuth, authResTo);

            String token = TokenUtils.sign(sysAuth);
            authResTo.setToken(token);
        } else {
            throw new BizException(AuthErrorCodeEnum.USER_LOGIN_ERROR);
        }
        return authResTo;
    }

    @Override
    public SysAuth findByPrincipal(String principal) {
        // 通过独立方法进行参数校验
        validatePrincipal(principal);

        log.info("Query SysAuth by principal: {}", principal);

        return getByPrincipal(principal);
    }

    private void validatePrincipal(String principal) {
        if (principal == null || principal.trim().isEmpty()) {
            log.error("Invalid principal input: {}. Principal cannot be null or empty.", principal);
            throw new BizException("Invalid principal input: " + principal);
        }
    }

    private SysAuth getByPrincipal(String principal) {
        LambdaQueryWrapper<SysAuth> queryWrapper = new LambdaQueryWrapper<SysAuth>()
                .eq(SysAuth::getPrincipal, principal)
                .eq(SysAuth::getDelFlag, DelFlagEnum.NOT_DELETE.getValue());

        try {
            // 明确使用List<SysAuth>类型，避免类型安全问题
            List<SysAuth> list = this.list(queryWrapper);

            // 优化前的判断逻辑保持不变，仅是类型使用上更为安全
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0);
            }
        } catch (Exception e) {
            // 添加对数据库操作异常的处理
            log.error("Error querying SysAuth by principal: {}", principal, e);
            // 根据业务需要，这里可以选择抛出自定义异常或返回null，此处保持原逻辑返回null
        }

        return null;
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
