package com.base.auth.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.entity.SecurityQuestion;
import com.base.auth.mapper.SecurityQuestionMapper;
import com.base.auth.service.user.service.ISecurityQuestionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 密保信息表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
@Service
public class SecurityQuestionServiceImpl extends ServiceImpl<SecurityQuestionMapper, SecurityQuestion> implements ISecurityQuestionService {

}
