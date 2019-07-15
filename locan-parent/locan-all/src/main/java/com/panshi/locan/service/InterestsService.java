package com.panshi.locan.service;

import com.panshi.locan.domain.InterestsVO;

import java.util.List;

public interface InterestsService {

    /**
     * 查询利息信息
     * @return
     */
    List<InterestsVO> query();

    /**
     * 修改利率
     * @param interestsVO
     * @return
     */

    String update(InterestsVO interestsVO);
}
