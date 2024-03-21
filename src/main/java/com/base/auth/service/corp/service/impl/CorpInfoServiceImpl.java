package com.base.auth.service.corp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.entity.CorpInfo;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.mapper.CorpInfoMapper;
import com.base.auth.service.corp.service.ICorpInfoService;
import com.base.auth.to.CorpInfoDetail;
import com.base.auth.to.CorpQueryReq;
import com.base.auth.to.SaveCorpInfoReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 公司信息表 服务实现类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
@Service
public class CorpInfoServiceImpl extends ServiceImpl<CorpInfoMapper, CorpInfo> implements ICorpInfoService {

    @Override
    public CorpInfoDetail detail(Integer id) {
        CorpInfoDetail corpInfoDetail = new CorpInfoDetail();
        CorpInfo corpInfo = this.getById(id);
        if (ObjectUtils.isNull(corpInfo) || corpInfo.getDelFlag().equals(DelFlagEnum.DELETED.getValue())) {
            throw new BizException("公司不存在");
        }
        BeanUtils.copyProperties(corpInfo, corpInfoDetail);
        return corpInfoDetail;
    }

    @Override
    public Page<CorpInfoDetail> queryList(CorpQueryReq corpQueryReq) {
        if (ObjectUtils.isNull(corpQueryReq.getCurrent()) || corpQueryReq.getCurrent() < 1) {
            corpQueryReq.setCurrent(1L);
        }
        if (ObjectUtils.isNull(corpQueryReq.getSize()) || corpQueryReq.getSize() < 1) {
            corpQueryReq.setSize(20L);
        }

        Page<CorpInfo> page = new Page<>(corpQueryReq.getCurrent(), corpQueryReq.getSize());
        Page<CorpInfoDetail> resPage = new Page<>(corpQueryReq.getCurrent(), corpQueryReq.getSize());
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<CorpInfo>()
                .eq(CorpInfo::getDelFlag, DelFlagEnum.NOT_DELETE)
                .like(StringUtils.isNotBlank(corpQueryReq.getCompanyName()), CorpInfo::getCompanyName, corpQueryReq.getCompanyName())
                .orderByDesc(CorpInfo::getUpdateTime);

        Page pageDto = this.page(page, lambdaQueryWrapper);
        List<CorpInfo> records = pageDto.getRecords();
        List<CorpInfoDetail> resList = records.stream().map(x -> {
            CorpInfoDetail corpInfoDetail = new CorpInfoDetail();
            BeanUtils.copyProperties(x, corpInfoDetail);
            return corpInfoDetail;
        }).collect(Collectors.toList());
        resPage.setRecords(resList);
        resPage.setTotal(pageDto.getTotal());
        return resPage;
    }

    @Override
    public boolean edit(SaveCorpInfoReq saveCorpInfoReq) {
        CorpInfo corpInfo = this.getById(saveCorpInfoReq.getId());
        if (ObjectUtils.isNull(corpInfo)) {
            return false;
        }
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfo);
        return this.updateById(corpInfo);
    }

    @Override
    public boolean add(SaveCorpInfoReq saveCorpInfoReq) {
        CorpInfo corpInfo = new CorpInfo();
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfo);
        corpInfo.setCreateUserId(saveCorpInfoReq.getUpdateUserId());
        corpInfo.setCreateUserName(saveCorpInfoReq.getCompanyName());
        return this.save(corpInfo);
    }

    @Override
    public boolean deleteCorp(Integer id) {
        return this.update(new LambdaUpdateWrapper<CorpInfo>()
                .set(CorpInfo::getDelFlag, DelFlagEnum.DELETED.getValue())
                .eq(CorpInfo::getId, id));
    }
}
