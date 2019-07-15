package com.panshi.locan.service;

import com.panshi.locan.domain.RepaymentVO;

import java.util.List;
import java.util.Map;

public interface RepaymentService {

    /**
     * 查询还款所以信息
     * @return
     */
    List<RepaymentVO> queryAll();


    /**
     * 根据借款id查询还款记录
     * @param repaymentVO
     * @return
     */

    List<RepaymentVO> queryById(RepaymentVO repaymentVO);


    /**
     * 根据借款id 查询还款数据
     * @param borrowId
     * @return
     */
    List<RepaymentVO> queryByBorrowId(Integer borrowId);


    /**
     * 查询所有还款已逾期的数据
     * @return
     */
    List<RepaymentVO> queryAllRepayType();

    /**
     * 添加还款数据
     * @param repaymentVO
     * @return
     */
    String add(RepaymentVO repaymentVO);


    String dept(Map<String, Object> map);

    /**
     * 修改状态 为 已还款的
     * @param repaymentVO
     * @return
     */
    String updateRepay(RepaymentVO repaymentVO);

    /**
     * 查询总条还款记录
     * @return
     */
    //int total();


    /**
     * 根据用户id 查询还款信息
     * @param userId
     * @return
     */
    List<RepaymentVO> queryByUserid(Integer userId);
}
