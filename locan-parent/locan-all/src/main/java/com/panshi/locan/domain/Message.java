package com.panshi.locan.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message<T> {

    /**
     * 错误编码
     */
    @ApiModelProperty(example="0")
    private String code;

    /**
     * 消息提示
     */
    @ApiModelProperty(example="成功")
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 总条记录
     */
    @ApiModelProperty(example="2")
    private Integer total;

}
