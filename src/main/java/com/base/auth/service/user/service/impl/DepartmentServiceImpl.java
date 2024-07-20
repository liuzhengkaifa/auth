package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.base.auth.entity.Department;
import com.base.auth.mapper.DepartmentMapper;
import com.base.auth.service.user.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.to.UserDepartmentRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-19
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public List<UserDepartmentRes> departmentList() {
        List<Department> departmentList = this.list();
        return departmentList.stream().map(x->{
            UserDepartmentRes userDepartmentRes = new UserDepartmentRes();
            userDepartmentRes.setDepartmentId(x.getId());
            userDepartmentRes.setDepartmentName(x.getDepartmentName());
            return userDepartmentRes;
        }).collect(Collectors.toList());
    }
}
