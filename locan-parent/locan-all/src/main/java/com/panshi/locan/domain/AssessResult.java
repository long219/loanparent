package com.panshi.locan.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="演示类",description="请求参数类" )
public class AssessResult {

    private Integer id;
    private String res;
    private String code;
    private String money;
    private String idcard;
}
