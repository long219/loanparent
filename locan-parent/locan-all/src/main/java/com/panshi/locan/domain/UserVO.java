package com.panshi.locan.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;


/**
 * @author
 */
@Alias("userVO")
@Data
public class UserVO {

    /**
     * 用户Id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String  name ;

    /**
     * 手机号
     */
    private String  iphone;

    /**
     *  密码
     */
    private String password;

    /**
     * 身份证号
     */
    private String identity_card;

    /**
     * 注册时间
     */
    private String regist_time;

    /**
     * 银行卡开户行
     */
    private String opening_bank;

    /**
     * 银行卡卡号
     */
    private String bank_card;

    /**
     * 身份证图片
     */
    private String identity_card_img ;

    /**
     * 银行卡图片
     */
    private String bank_img;

    /**
     *  额度
     */
    private Integer quota;

    /**
     * 审核状态（0：待审核，1：审核通过，2：驳回）
     */
    private String audit_status;

    /**
     * 验证码
     */
    private String vftcode;


}
