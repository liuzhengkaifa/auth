package com.base.auth.service.corp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.entity.CorpInfo;
import com.base.auth.entity.SysAuth;
import com.base.auth.to.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 公司信息表 服务类
 * </p>
 *
 * @author liuzheng
 * @since 2024-03-20
 */
public interface ICorpInfoService extends IService<CorpInfo> {

    CorpInfoDetail detail(Integer id);

    Page<CorpInfoDetail> queryList(CorpQueryReq corpQueryReq,SysAuth sysAuth);

    boolean edit(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth);

    boolean add(SaveCorpInfoReq saveCorpInfoReq, SysAuth sysAuth);

    boolean deleteCorp(Integer id, SysAuth sysAuth);

    void exportCorpList(CorpQueryReq corpQueryReq, HttpServletResponse response);

    List<FocusAreasRes> focusAreas(SysAuth currentUser);

    List<SpatialDistributionRes> spatialDistribution(SysAuth currentUser);
}
