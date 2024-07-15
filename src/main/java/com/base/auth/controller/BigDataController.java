package com.base.auth.controller;

import com.base.auth.common.Response;
import com.base.auth.service.bigdata.service.BigDataService;
import com.base.auth.to.CorpInfoDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:49
 * @Description
 */
@RestController
@RequestMapping("/corp")
@Api(tags = "公司相关接口")
@Slf4j
public class BigDataController {

    @Resource
    BigDataService bigDataService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "test", httpMethod = "GET")
    Response test() {
        return Response.ok(bigDataService.test());
    }
}
