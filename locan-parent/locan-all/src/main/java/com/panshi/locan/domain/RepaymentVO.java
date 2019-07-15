package com.panshi.locan.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("repaymentVO")
@Data
public class RepaymentVO {

    /**
     * //还款id
     */
    private Integer id;

    /**
     *  //借款ID
     */
    private Integer borrow_id;

    /**
     * //还款金额
     */
    private BigDecimal repayment_money;

    /**
     * //应还款时间
     */
    private String repayment_time;

    /**
     * //实际还款时间
     */
    private String practical_time;

    /**
     * //还款状态
     */
    private String repayment_type;

    /**
     * //借款实例
     */
    private BorrowVO borrowVO;



}
