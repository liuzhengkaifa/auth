package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionReq {

    @ApiModelProperty(value = "问题")
    private String prompt;
}
