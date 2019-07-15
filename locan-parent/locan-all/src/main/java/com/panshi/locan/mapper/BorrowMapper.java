package com.panshi.locan.mapper;

import com.panshi.locan.domain.BorrowVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowMapper {

    /**
     * 查询所以贷款信息
     * @return
     */
    List<BorrowVO> queryAll();

    /**
     * 根据贷款Id,和 用户id 查询贷款信息
     * @param borrowVO
     * @return
     */
    List<BorrowVO> queryById(BorrowVO borrowVO);

    /**
     * 添加贷款
     * @param borrowVO
     */
    void add(BorrowVO borrowVO);

    /**
     * 根据用户id 查询贷款信息
     * @param borrowVO
     */
    List<BorrowVO> queryByUserId(BorrowVO borrowVO);

    /**
     * 修改状态
     * @param borrowVO
     */
    void updateState(BorrowVO borrowVO);
}
