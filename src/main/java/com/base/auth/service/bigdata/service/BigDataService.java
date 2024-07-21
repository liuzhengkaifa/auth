package com.base.auth.service.bigdata.service;

import com.base.auth.common.Response;
import com.base.auth.to.QuestionReq;
import com.base.auth.to.QuestionRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;


public interface BigDataService {
    Response question(QuestionReq questionReq);
}
