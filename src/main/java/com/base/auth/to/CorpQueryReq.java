package com.base.auth.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzheng
 * @date 2024年03月20日 10:47
 * @Description TODO
 */
@Data
public class CorpQueryReq  extends PageRequestTO {


    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

}
