package com.base.auth.service.corp.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.auth.common.BizException;
import com.base.auth.convert.LocalDateConverter;
import com.base.auth.convert.LocalDateTimeConverter;
import com.base.auth.entity.CorpInfo;
import com.base.auth.entity.CorpInfoExtern;
import com.base.auth.entity.SysAuth;
import com.base.auth.enums.DelFlagEnum;
import com.base.auth.enums.ErrorCodeEnum;
import com.base.auth.enums.YesNoEnum;
import com.base.auth.mapper.CorpInfoExternMapper;
import com.base.auth.mapper.CorpInfoMapper;
import com.base.auth.service.common.service.ICommonBusiness;
import com.base.auth.service.corp.service.ICorpInfoService;
import com.base.auth.service.user.service.ICorpInfoExternService;
import com.base.auth.to.*;
import com.base.auth.to.excel.CorpInfoDetailExcelTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
@Slf4j
public class CorpInfoServiceImpl extends ServiceImpl<CorpInfoMapper, CorpInfo> implements ICorpInfoService {

    @Resource
    ICommonBusiness iCommonBusiness;

    @Resource
    ICorpInfoExternService iCorpInfoExternService;

    @Override
    public CorpInfoDetail detail(Integer id) {


        CorpInfoDetail corpInfoDetail = new CorpInfoDetail();
        CorpInfo corpInfo = this.getById(id);
        if (ObjectUtils.isNull(corpInfo) || corpInfo.getDelFlag().equals(DelFlagEnum.DELETED.getValue())) {
            throw new BizException("公司不存在");
        }
        BeanUtils.copyProperties(corpInfo, corpInfoDetail);
        CorpInfoExtern corpInfoExtern = iCorpInfoExternService.getOne(new LambdaQueryWrapper<CorpInfoExtern>()
                .eq(CorpInfoExtern::getCorpId, id)
                .eq(CorpInfoExtern::getDelFlag, DelFlagEnum.NOT_DELETE));
        BeanUtils.copyProperties(corpInfoExtern, corpInfoDetail);

        return corpInfoDetail;
    }

    @Override
    public Page<CorpInfoDetail> queryList(CorpQueryReq corpQueryReq, SysAuth sysAuth) {
        if (ObjectUtils.isNull(corpQueryReq.getCurrent()) || corpQueryReq.getCurrent() < 1) {
            corpQueryReq.setCurrent(1L);
        }
        if (ObjectUtils.isNull(corpQueryReq.getSize()) || corpQueryReq.getSize() < 1) {
            corpQueryReq.setSize(20L);
        }

        Page<CorpInfo> page = new Page<CorpInfo>(corpQueryReq.getCurrent(), corpQueryReq.getSize());
        Page<CorpInfoDetail> resPage = new Page<>(corpQueryReq.getCurrent(), corpQueryReq.getSize());
        LambdaQueryWrapper<CorpInfo> lambdaQueryWrapper = new LambdaQueryWrapper<CorpInfo>()
                .eq(CorpInfo::getDelFlag, DelFlagEnum.NOT_DELETE)
                .like(StringUtils.isNotBlank(corpQueryReq.getCompanyName()), CorpInfo::getCompanyName, corpQueryReq.getCompanyName())
                .like(StringUtils.isNotBlank(corpQueryReq.getCategoryName()), CorpInfo::getCategoryName, corpQueryReq.getCategoryName())
                .like(StringUtils.isNotBlank(corpQueryReq.getDistrict()), CorpInfo::getDistrict, corpQueryReq.getDistrict())
                .eq(ObjectUtils.isNotNull(corpQueryReq.getParticipateOld()), CorpInfo::getParticipateOld, corpQueryReq.getParticipateOld())
                .eq(ObjectUtils.isNotNull(corpQueryReq.getIsStatistical()), CorpInfo::getIsStatistical, corpQueryReq.getIsStatistical());

        //如果是区管理员，非超级管理员，只能查看指定区数据，如果是超级管理员，可以查看所有数据，否则只能查看自己创建的数据

        UserPermissionRes userPermissionRes = iCommonBusiness.checkUserPermissions(sysAuth.getId());
        if (!userPermissionRes.isSuperAdmin() && !userPermissionRes.isSysAdmin()) {
            lambdaQueryWrapper.eq(CorpInfo::getCreateUserId, sysAuth.getId());
        }

        if (userPermissionRes.isSysAdmin()) {
            if (CollectionUtils.isNotEmpty(userPermissionRes.getIds())) {
                lambdaQueryWrapper.in(CorpInfo::getDistrictCode, userPermissionRes.getIds());
            } else {
                lambdaQueryWrapper.eq(CorpInfo::getCreateUserId, sysAuth.getId());
            }
        }
        lambdaQueryWrapper.orderByDesc(CorpInfo::getUpdateTime);
        Page<CorpInfo> pageDto = this.page(page, lambdaQueryWrapper);
        List<Integer> corpIds = pageDto.getRecords().stream().map(CorpInfo::getId).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(corpIds)){
            return resPage;
        }
        Map<Integer, CorpInfoExtern> corpInfoExternMap = iCorpInfoExternService.list(new LambdaQueryWrapper<CorpInfoExtern>()
                        .in(CorpInfoExtern::getCorpId, corpIds)
                        .eq(CorpInfoExtern::getDelFlag, DelFlagEnum.NOT_DELETE))
                .stream().collect(Collectors.toMap(CorpInfoExtern::getCorpId, Function.identity()));

        List<CorpInfo> records = pageDto.getRecords();
        List<CorpInfoDetail> resList = records.stream().map(x -> {
            CorpInfoDetail corpInfoDetail = new CorpInfoDetail();
            BeanUtils.copyProperties(x, corpInfoDetail);
            BeanUtils.copyProperties(corpInfoExternMap.get(x.getId()), corpInfoDetail);
            return corpInfoDetail;
        }).collect(Collectors.toList());
        resPage.setRecords(resList);
        resPage.setTotal(pageDto.getTotal());
        return resPage;
    }

    @Override
    public boolean edit(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth) {
        try {
            log.info("{} editCorp req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(saveCorpInfoReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
        CorpInfo corpInfo = this.getById(saveCorpInfoReq.getId());
        if (ObjectUtils.isNull(corpInfo)) {
            return false;
        }
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfo);
        corpInfo.setUpdateTime(LocalDateTime.now());
        corpInfo.setUpdateUserId(sysAuth.getId());
        corpInfo.setUpdateUserName(sysAuth.getPrincipal());

        List<CorpInfoExtern> list = iCorpInfoExternService.list(new LambdaQueryWrapper<CorpInfoExtern>()
                .eq(CorpInfoExtern::getCorpId, corpInfo.getId())
                .eq(CorpInfoExtern::getDelFlag, DelFlagEnum.NOT_DELETE.getValue()));
        CorpInfoExtern corpInfoExtern = list.get(0);
        Integer corpInfoExternId = corpInfoExtern.getId();
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfoExtern);
        corpInfoExtern.setId(corpInfoExternId);
        return iCorpInfoExternService.updateById(corpInfoExtern);
    }

    @Override
    public boolean add(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth) {
        try {
            log.info("{} addCorp req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(saveCorpInfoReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
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
        this.save(corpInfo);

        CorpInfoExtern corpInfoExtern = new CorpInfoExtern();
        BeanUtils.copyProperties(saveCorpInfoReq, corpInfoExtern);
        corpInfoExtern.setCorpId(corpInfo.getId());
        return iCorpInfoExternService.save(corpInfoExtern);
    }

    @Override
    public boolean deleteCorp(Integer id, SysAuth sysAuth) {
        this.update(new LambdaUpdateWrapper<CorpInfo>()
                .set(CorpInfo::getDelFlag, DelFlagEnum.DELETED.getValue())
                .set(CorpInfo::getUpdateTime, LocalDateTime.now())
                .set(CorpInfo::getUpdateUserId, sysAuth.getId())
                .set(CorpInfo::getUpdateUserName, sysAuth.getPrincipal())
                .eq(CorpInfo::getId, id));
        return iCorpInfoExternService.update(new LambdaUpdateWrapper<CorpInfoExtern>()
                .set(CorpInfoExtern::getDelFlag, DelFlagEnum.DELETED.getValue())
                .eq(CorpInfoExtern::getCorpId, id));
    }

    @Override
    public void exportCorpList(CorpQueryReq corpQueryReq, HttpServletResponse response) {
        try {
            log.info("{} exportCorpList req {}", LocalDateTime.now(),new ObjectMapper().writeValueAsString(corpQueryReq));
        } catch (Exception e) {
            log.error("req to json error", e);
        }
        try {
            corpQueryReq.setCurrent(1L);
            corpQueryReq.setSize(10000L);
            List<CorpInfoDetail> corpInfoDetailList = this.queryList(corpQueryReq, null).getRecords();
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

    @Override
    public List<FocusAreasRes> focusAreas(SysAuth currentUser) {
        List<FocusAreasRes> resList = new ArrayList<>();

        List<CorpInfo> corpInfoList = this.list(new LambdaQueryWrapper<CorpInfo>()
                .select(CorpInfo::getCategoryName, CorpInfo::getCompanyName)
                .eq(CorpInfo::getDelFlag, DelFlagEnum.NOT_DELETE.getValue()));
        Map<String, List<CorpInfo>> companyMap = corpInfoList.stream().collect(Collectors.groupingBy(CorpInfo::getCategoryName));
        companyMap.forEach((key, value) -> {
            if (StringUtils.isNotBlank(key)) {
                FocusAreasRes res = new FocusAreasRes();
                res.setCategoryName(key);
                res.setCompanyCount(value.size());
                res.setCompanyName(value.stream().map(x -> x.getCompanyName()).collect(Collectors.joining(",")));
                resList.add(res);
            }
        });
        return resList;
    }

    @Override
    public List<SpatialDistributionRes> spatialDistribution(SysAuth currentUser) {
        List<SpatialDistributionRes> resList = new ArrayList<>();
        List<CorpInfo> corpInfoList = this.list(new LambdaQueryWrapper<CorpInfo>()
                .select(CorpInfo::getDistrictCode, CorpInfo::getDistrict, CorpInfo::getRepresentsCompanyFlag, CorpInfo::getCompanyName)
                .eq(CorpInfo::getDelFlag, DelFlagEnum.NOT_DELETE.getValue()));
        Map<Integer, List<CorpInfo>> companyMap = corpInfoList.stream().collect(Collectors.groupingBy(CorpInfo::getDistrictCode));
        companyMap.forEach((key, value) -> {
            if (ObjectUtils.isNotNull(key)) {
                SpatialDistributionRes res = new SpatialDistributionRes();
                res.setDistrictCode(key);
                res.setDistrict(value.get(0).getDistrict());
                List<CorpInfo> representsCompanyList = value.stream().filter(x -> x.getRepresentsCompanyFlag().equals(YesNoEnum.YES.getCode())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(representsCompanyList)) {
                    res.setCompanyCount(representsCompanyList.size());
                    res.setCompanyName(representsCompanyList.stream().map(x -> x.getCompanyName()).collect(Collectors.joining(",")));
                    resList.add(res);
                }
            }
        });
        return resList;
    }
}
