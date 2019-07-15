package com.panshi.locan.service.impl;

import com.panshi.locan.domain.InterestsVO;
import com.panshi.locan.mapper.InterestsMapper;
import com.panshi.locan.service.InterestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InterestsServiceImpl implements InterestsService {

    @Autowired
    private InterestsMapper interestsMapper;


    @Override
    public List<InterestsVO> query() {
        return interestsMapper.query();
    }

    @Override
    public String update(InterestsVO interestsVO) {
        int check = check(interestsVO);
        if (check!=-1){
            interestsMapper.update(interestsVO);
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 校验利息信息
     * @param interestsVO
     * @return
     */
    public  int check(InterestsVO interestsVO){
        List<InterestsVO> query = interestsMapper.query();
        for (int i = 0; i < query.size(); i++) {
            InterestsVO interestsVO1 = query.get(i);
            if (interestsVO1.getId().equals(interestsVO.getId())){
                return  i;
            }
        }
        return  -1;
    }
}
