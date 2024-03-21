package com.base.auth.service.corp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.auth.entity.CorpInfo;
import com.base.auth.to.CorpInfoDetail;
import com.base.auth.to.CorpQueryReq;
import com.base.auth.to.SaveCorpInfoReq;

import javax.servlet.http.HttpServletResponse;

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

    Page<CorpInfoDetail> queryList(CorpQueryReq corpQueryReq);

    boolean edit(SaveCorpInfoReq saveCorpInfoReq);

    boolean add(SaveCorpInfoReq saveCorpInfoReq);

    boolean deleteCorp(Integer id);

    void exportCorpList(CorpQueryReq corpQueryReq, HttpServletResponse response);
}
