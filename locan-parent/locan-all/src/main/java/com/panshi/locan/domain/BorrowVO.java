package com.panshi.locan.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("borrowVO")
@Data
public class BorrowVO {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 借款总金额
     */
    private Integer borrow_money;

    /**
     * 借款日期
     */
    private Integer borrow_number;

    /**
     * 借款时间
     */
    private String borrow_time;

    /**
     * 客户id
     */
    private Integer user_id;

    /**
     * 总利息
     */
    private Integer total_interest;

    /**
     * 审核状态（0：待审核。 1：审核通过。3：驳回）
     */
    private String audit_status;

    /**
     * 还款的状态（  1 : 未开始  ,  2 : 待还款 , 3 : 已还清 , 4 : 已逾期）
     */
    private String repayment_state;

    /**
     * 用户对象
     */
    private UserVO userVO;

    /**
     * 验证码
     */
    private String vftcode;

}
