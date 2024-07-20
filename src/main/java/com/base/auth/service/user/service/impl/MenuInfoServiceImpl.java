package com.base.auth.service.user.service.impl;

import com.base.auth.entity.MenuInfo;
import com.base.auth.mapper.MenuInfoMapper;
import com.base.auth.service.user.service.IMenuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-07-20
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements IMenuInfoService {

}
