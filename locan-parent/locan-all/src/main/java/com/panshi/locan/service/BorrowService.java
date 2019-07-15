package com.panshi.locan.service;

import com.panshi.locan.domain.BorrowVO;

import java.math.BigDecimal;
import java.util.List;

public interface BorrowService {

    /**
     * 查询所有贷款信息
     * @return
     */
    List<BorrowVO> queryAll();

    /**
     * 添加贷款信息
     * @param borrowVO
     * @return
     */
    String add(BorrowVO borrowVO);

    /**
     * 根据贷款id查询
     * @param borrowVO
     * @return
     */
    List<BorrowVO> queryById(BorrowVO borrowVO);


    /**
     * 修改状态
     * @param borrowVO
     * @return
     */
    String updateState(BorrowVO borrowVO);


}
