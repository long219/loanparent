package com.panshi.locan.mapper;

import com.panshi.locan.domain.InterestsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InterestsMapper {


    List<InterestsVO> query();

    /**
     * 修改利率
     * @param interestsVO
     */
    void update(InterestsVO interestsVO);
}
