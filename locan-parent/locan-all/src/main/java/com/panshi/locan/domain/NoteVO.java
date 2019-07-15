package com.panshi.locan.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;


@Alias("noteVO")
@Data
public class NoteVO {
    //id
    private Integer id;

    /**
     * 短信类型  (1: 登录  2:借款   3:注册)
     */
    private String type;

    /**
     * 手机号
     */
    private String iphone;

    /**
     * 发送短信时间
     */
    private String note_time;

    /**
     *
     */
    private String short_message;
}
