package com.base.auth.controller;

import com.base.auth.common.Response;
import com.base.auth.service.bigdata.service.BigDataService;
import com.base.auth.to.CorpInfoDetail;
import com.base.auth.to.QuestionReq;
import com.base.auth.to.QuestionRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    @ApiOperation(value = "智能体问答", httpMethod = "POST")
    Response question(@Validated @RequestBody QuestionReq questionReq) {
        return Response.ok(bigDataService.question(questionReq));
    }
}
