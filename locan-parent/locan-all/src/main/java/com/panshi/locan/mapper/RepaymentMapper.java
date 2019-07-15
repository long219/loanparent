package com.panshi.locan.mapper;

import com.panshi.locan.domain.RepaymentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RepaymentMapper {
    /**
     * 查询还款所以信息
     * @return
     */
    List<RepaymentVO> queryAll();


    /**
     * 根据借款id 和 用户id查询
     * @return
     */
    List<RepaymentVO> queryByIdAndUserId(RepaymentVO repaymentVO);

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
     */
    void add(RepaymentVO repaymentVO);


    /**
     * 计算时间差
     * @param dateTime
     * @return
     */
    int timeDifference(String dateTime);

    /**
     * 修改还款状态(为已逾期)
     */
    void  udpateRepayType(RepaymentVO repaymentVO);

    /**
     * 修改还款状态(为已还款)
     * @param repaymentVO
     */
    void updateRepay(RepaymentVO repaymentVO);

    /**
     * 查询总条还款记录
     * @return
     */
    int total();

    /**
     * 根据用户id 查询还款信息
     * @param userId
     * @return
     */
    List<RepaymentVO> queryByUserid(Integer userId);
}
