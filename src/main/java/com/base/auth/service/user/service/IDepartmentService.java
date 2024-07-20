package com.base.auth.service.user.service;

import com.base.auth.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.to.UserDepartmentRes;

import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
public interface IDepartmentService extends IService<Department> {


    List<UserDepartmentRes> departmentList();
}
