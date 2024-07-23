package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.entity.SysAuth;
import com.base.auth.entity.UserDepartment;
import com.base.auth.entity.UserRoleRelation;
import com.base.auth.enums.AuthErrorCodeEnum;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.enums.StatusEnum;
import com.base.auth.mapper.SysAuthMapper;
import com.base.auth.service.common.service.ICommonBusiness;
import com.base.auth.service.user.service.ISysAuthService;
import com.base.auth.service.user.service.IUserDepartmentService;
import com.base.auth.service.user.service.IUserRoleRelationService;
import com.base.auth.to.*;
import com.base.auth.util.PasswordSecurityUtils;
import com.base.auth.util.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.security.auth.message.AuthException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    ICommonBusiness iCommonBusiness;

    @Resource
    IUserRoleRelationService iUserRoleRelationService;

    @Resource
    IUserDepartmentService iUserDepartmentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddUserRes register(AddUserReq registerReq) {
        try {
            log.info("{} register req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(registerReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
        AddUserRes res = new AddUserRes();
        // 验证用户名和密码
        validateUsernameAndPassword(registerReq.getPrincipal(), registerReq.getCredential());

        SysAuth sysAuth = new SysAuth();
        BeanUtils.copyProperties(registerReq, sysAuth);
        sysAuth.setCredential(PasswordSecurityUtils.hashPasswordWithSalt(registerReq.getCredential()));
        sysAuth.setStatus(StatusEnum.VALID.getValue());

        if (!this.save(sysAuth)) {
            throw new RuntimeException("Failed to save SysAuth");
        }
        List<UserRoleRelation> userRoleRelationList = registerReq.getRoleIds().stream()
                .map(roleId -> {
                    UserRoleRelation userRoleRelation = new UserRoleRelation();
                    userRoleRelation.setRoleId(roleId);
                    userRoleRelation.setUserId(sysAuth.getId());
                    return userRoleRelation;
                }).collect(Collectors.toList());

        if (!userRoleRelationList.isEmpty()) {
            iUserRoleRelationService.saveBatch(userRoleRelationList);
        }

        List<UserDepartment> userDepartmentList = registerReq.getDepartmentIds().stream()
                .map(departmentId -> {
                    UserDepartment userDepartment = new UserDepartment();
                    userDepartment.setUserId(sysAuth.getId());
                    userDepartment.setDepartmentId(departmentId);
                    return userDepartment;
                }).collect(Collectors.toList());
        if (!userDepartmentList.isEmpty()) {
            iUserDepartmentService.saveBatch(userDepartmentList);
        }

        res.setAuthId(sysAuth.getId());
        res.setPrincipal(sysAuth.getPrincipal());
        return res;
    }

    @Override
    public AddUserRes editUser(AddUserReq addUserReq) {
        try {
            log.info("{} editUser req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(addUserReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
        iUserRoleRelationService.deleteByUserId(addUserReq.getId());
        iUserDepartmentService.deleteByUserId(addUserReq.getId());
        List<UserRoleRelation> userRoleRelationList = addUserReq.getRoleIds().stream()
                .map(roleId -> {
                    UserRoleRelation userRoleRelation = new UserRoleRelation();
                    userRoleRelation.setRoleId(roleId);
                    userRoleRelation.setUserId(addUserReq.getId());
                    return userRoleRelation;
                }).collect(Collectors.toList());

        if (!userRoleRelationList.isEmpty()) {
            iUserRoleRelationService.saveBatch(userRoleRelationList);
        }

        List<UserDepartment> userDepartmentList = addUserReq.getDepartmentIds().stream()
                .map(departmentId -> {
                    UserDepartment userDepartment = new UserDepartment();
                    userDepartment.setUserId(addUserReq.getId());
                    userDepartment.setDepartmentId(departmentId);
                    return userDepartment;
                }).collect(Collectors.toList());
        if (!userDepartmentList.isEmpty()) {
            iUserDepartmentService.saveBatch(userDepartmentList);
        }
        AddUserRes res = new AddUserRes();
        res.setAuthId(addUserReq.getId());
        res.setPrincipal(addUserReq.getPrincipal());
        return res;
    }

    @Override
    public UserDetail info(Integer id) {
        UserQueryReq userQueryReq = new UserQueryReq();
        userQueryReq.setAuthId(id);
        List<UserDetail> userDetailList = this.queryList(userQueryReq, null);
        if (!userDetailList.isEmpty()) {
            return userDetailList.get(0);
        }
        return null;
    }

    private void validateUsernameAndPassword(String principal, String password) {
        SysAuth existingUser = this.findByPrincipal(principal);
        if (existingUser != null) {
            throw new BizException("Username already exists");
        }

        // 这里可以添加自定义的密码验证逻辑，比如密码强度等
        // 例如，确保密码长度至少为6个字符
        if (password.length() < 6) {
            throw new BizException("Password must be at least 6 characters long");
        }
    }


    @Override
    public AuthResTo login(AuthReqTo authReq) {
        try {
            log.info("{} login req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(authReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
        AuthResTo authResTo = new AuthResTo();
        List<SysAuth> list = this.list(new LambdaQueryWrapper<SysAuth>()
                .eq(SysAuth::getDelFlag, DelFlagEnum.NOT_DELETE.getValue())
                .eq(SysAuth::getPrincipal, authReq.getPrincipal()));

        if (CollectionUtils.isNotEmpty(list)) {
            SysAuth sysAuth = list.get(0);
            boolean pwdFlag = PasswordSecurityUtils.validatePassword(sysAuth.getCredential(), authReq.getCredential());
            if(!pwdFlag){
                throw new BizException(AuthErrorCodeEnum.USER_LOGIN_ERROR);
            }
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

    @Override
    public List<UserDetail> queryList(UserQueryReq userQueryReq, SysAuth sysAuth) {
        List<UserDetail> userDetailList = new ArrayList<>();
//        UserPermissionRes userPermissionRes = iCommonBusiness.checkUserPermissions(sysAuth.getId());
//        if (!userPermissionRes.isSuperAdmin()) {
//            return null;
//        }
        List<UserListDto> userListDtoList = this.baseMapper.queryUserList(userQueryReq);
        Map<Integer, List<UserListDto>> userMap = userListDtoList.stream().collect(Collectors.groupingBy(UserListDto::getId));
        userMap.forEach((id, userListDtos) -> {
            UserDetail userDetail = new UserDetail();
            userDetail.setId(id);
            if (!CollectionUtils.isEmpty(userListDtos)) {
                userDetail.setPrincipal(userListDtos.get(0).getPrincipal());
            }

            // 将UserListDto中的角色信息转换为UserRoleRes列表，同时去除重复项
            List<UserRoleRes> roleMains = userListDtos.stream()
                    .filter(x -> x.getRoleId() != null)
                    .map(dto -> new UserRoleRes(dto.getRoleId(), dto.getRoleName()))
                    .collect(Collectors.toMap(
                            UserRoleRes::getRoleId, // 使用roleId作为Map的key
                            role -> role,           // value就是UserRoleRes对象本身
                            (oldValue, newValue) -> oldValue // 解决key冲突时保留旧值
                    )).values()                 // 获取Map的values集合
                    .stream()                   // 再次转换为Stream
                    .collect(Collectors.toList()); // 收集为List
            if (!roleMains.isEmpty()) {
                userDetail.setRoleMains(roleMains);
            }

            // 将UserListDto中的部门信息转换为UserDepartmentRes列表，同时去除重复项
            List<UserDepartmentRes> departments = userListDtos.stream()
                    .filter(x -> x.getDepartmentId() != null)
                    .map(dto -> new UserDepartmentRes(dto.getDepartmentId(), dto.getDepartmentName()))
                    .collect(Collectors.toMap(
                            UserDepartmentRes::getDepartmentId,
                            dept -> dept,
                            (oldValue, newValue) -> oldValue
                    )).values()
                    .stream()
                    .collect(Collectors.toList());
            if (!departments.isEmpty()) {
                userDetail.setDepartments(departments);
            }

            List<UserMenuRes> userMenuRes = userListDtos.stream()
                    .filter(x -> x.getMenuId() != null)
                    .map(dto -> new UserMenuRes(dto.getMenuId(), dto.getMenuName()))
                    .collect(Collectors.toMap(
                            UserMenuRes::getMenuId,
                            dept -> dept,
                            (oldValue, newValue) -> oldValue
                    )).values()
                    .stream()
                    .collect(Collectors.toList());
            if (!userMenuRes.isEmpty()) {
                userDetail.setUserMenuRes(userMenuRes);
            }

            userDetailList.add(userDetail);

        });
        return userDetailList;
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
