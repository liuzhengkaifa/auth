package com.base.auth.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.auth.common.Response;
import com.base.auth.service.corp.service.ICorpInfoService;
import com.base.auth.to.CorpInfoDetail;
import com.base.auth.to.CorpQueryReq;
import com.base.auth.to.SaveCorpInfoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:49
 * @Description TODO
 */
@RestController
@RequestMapping("/corp")
@Api(tags = "公司相关接口")
public class CorpController {

    @Resource
    ICorpInfoService iCorpInfoService;

    @RequestMapping(value = "/save-update", method = RequestMethod.POST)
    @ApiOperation(value = "新增编辑公司", httpMethod = "POST")
    Response add(@Validated @RequestBody SaveCorpInfoReq saveCorpInfoReq) {
        if(ObjectUtils.isNull(saveCorpInfoReq.getId())){
            iCorpInfoService.add(saveCorpInfoReq);
        }else{
            iCorpInfoService.edit(saveCorpInfoReq);
        }
        return Response.ok();
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "查看公司信息", httpMethod = "GET")
    Response<CorpInfoDetail> detail(@RequestParam(value = "id", required = true) Integer id) {
        return Response.ok(iCorpInfoService.detail(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除公司信息", httpMethod = "GET")
    Response deleteCorp(@RequestParam(value = "id", required = true) Integer id) {
        return Response.ok(iCorpInfoService.deleteCorp(id));
    }

    @RequestMapping(value = "/query-list", method = RequestMethod.POST)
    @ApiOperation(value = "根据参数查询单据列表信息", httpMethod = "POST")
    Response<Page<CorpInfoDetail>> queryList(@Validated @RequestBody CorpQueryReq corpQueryReq) {
        return Response.ok(iCorpInfoService.queryList(corpQueryReq));
    }

    @RequestMapping(value = "/export-corp-list", method = RequestMethod.POST)
    @ApiOperation(value = "导出公司列表", notes = "导出订单使用列表", produces = "application/octet-stream")
    void exportCorpList(@Valid @RequestBody CorpQueryReq corpQueryReq, HttpServletResponse response){
         iCorpInfoService.exportCorpList(corpQueryReq,response);
    }

}
