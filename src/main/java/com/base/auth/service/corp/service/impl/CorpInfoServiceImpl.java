package com.base.auth.service.corp.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.convert.LocalDateConverter;
import com.base.auth.convert.LocalDateTimeConverter;
import com.base.auth.entity.CorpInfo;
import com.base.auth.entity.SysAuth;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.enums.ErrorCodeEnum;
import com.base.auth.mapper.CorpInfoMapper;
import com.base.auth.service.corp.service.ICorpInfoService;
import com.base.auth.to.CorpInfoDetail;
import com.base.auth.to.CorpQueryReq;
import com.base.auth.to.SaveCorpInfoReq;
import com.base.auth.to.excel.CorpInfoDetailExcelTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                .like(StringUtils.isNotBlank(corpQueryReq.getCategoryName()), CorpInfo::getCategoryName, corpQueryReq.getCategoryName())
                .like(StringUtils.isNotBlank(corpQueryReq.getDistrict()), CorpInfo::getDistrict, corpQueryReq.getDistrict())
                .eq(ObjectUtils.isNotNull(corpQueryReq.getParticipateOld()), CorpInfo::getParticipateOld, corpQueryReq.getParticipateOld())
                .eq(ObjectUtils.isNotNull(corpQueryReq.getIsStatistical()), CorpInfo::getIsStatistical, corpQueryReq.getIsStatistical())
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
    public boolean edit(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth) {
        CorpInfo corpInfo = this.getById(saveCorpInfoReq.getId());
        if (ObjectUtils.isNull(corpInfo)) {
            return false;
        }
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfo);
        corpInfo.setUpdateTime(LocalDateTime.now());
        corpInfo.setUpdateUserId(sysAuth.getId());
        corpInfo.setUpdateUserName(sysAuth.getPrincipal());
        return this.updateById(corpInfo);
    }

    @Override
    public boolean add(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth) {
        CorpInfo corpInfo = new CorpInfo();
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfo);
        corpInfo.setCreateUserId(saveCorpInfoReq.getUpdateUserId());
        corpInfo.setCreateUserName(saveCorpInfoReq.getCompanyName());
        corpInfo.setCreateTime(LocalDateTime.now());
        corpInfo.setCreateUserId(sysAuth.getId());
        corpInfo.setCreateUserName(sysAuth.getPrincipal());
        corpInfo.setUpdateTime(LocalDateTime.now());
        corpInfo.setUpdateUserId(sysAuth.getId());
        corpInfo.setUpdateUserName(sysAuth.getPrincipal());
        return this.save(corpInfo);
    }

    @Override
    public boolean deleteCorp(Integer id, SysAuth sysAuth) {
        return this.update(new LambdaUpdateWrapper<CorpInfo>()
                .set(CorpInfo::getDelFlag, DelFlagEnum.DELETED.getValue())
                .set(CorpInfo::getUpdateTime, LocalDateTime.now())
                .set(CorpInfo::getUpdateUserId, sysAuth.getId())
                .set(CorpInfo::getUpdateUserName, sysAuth.getPrincipal())
                .eq(CorpInfo::getId, id));
    }

    @Override
    public void exportCorpList(CorpQueryReq corpQueryReq, HttpServletResponse response) {
        try {
            corpQueryReq.setCurrent(1L);
            corpQueryReq.setSize(10000L);
            List<CorpInfoDetail> corpInfoDetailList = this.queryList(corpQueryReq).getRecords();
            List<CorpInfoDetailExcelTo> excelToList = corpInfoDetailList.stream().map(x -> {
                CorpInfoDetailExcelTo corpInfoDetailExcelTo = new CorpInfoDetailExcelTo();
                BeanUtils.copyProperties(x, corpInfoDetailExcelTo);
                return corpInfoDetailExcelTo;
            }).collect(Collectors.toList());
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String file = "企业信息" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            String fileName = URLEncoder
                    .encode(file + ".xlsx", StandardCharsets.UTF_8).replaceAll("\\+", "%20").replaceAll("-", "");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName);
            EasyExcel.write(response.getOutputStream(), CorpInfoDetailExcelTo.class)
                    .registerConverter(new LocalDateTimeConverter()).registerConverter(new LocalDateConverter()).sheet("企业信息").doWrite(excelToList);
        } catch (Exception e) {
            log.error("导出文件异常", e);
            throw new BizException(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
        }
    }
}
