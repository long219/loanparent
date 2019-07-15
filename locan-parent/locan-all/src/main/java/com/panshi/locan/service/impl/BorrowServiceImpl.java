package com.panshi.locan.service.impl;

import com.panshi.locan.domain.BorrowVO;
import com.panshi.locan.mapper.BorrowMapper;
import com.panshi.locan.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;


    @Override
    public List<BorrowVO> queryAll() {
        return borrowMapper.queryAll();
    }

    @Override
    public String add(BorrowVO borrowVO) {

        int index = checkUserBorrow(borrowVO);

        if (index!=-1){
            borrowMapper.add(borrowVO);
            return "添加成功";
        }

        return "添加失败";
    }

    @Override
    public List<BorrowVO> queryById(BorrowVO borrowVO) {

        return borrowMapper.queryById(borrowVO);
    }

    @Override
    public String updateState(BorrowVO borrowVO) {

        int check = check(borrowVO.getId());
        if (check!=-1){
            borrowMapper.updateState(borrowVO);
            return "修改成功";
        }

        return "修改失败";
    }


    /**
     * 校验用户是否贷过的款是否还清
     * @return
     */
    public int checkUserBorrow(BorrowVO borrowVO){

        int count = 0;
        //根据用户id 获取贷款信息
        List<BorrowVO> borrowVOS = borrowMapper.queryByUserId(borrowVO);

        for (int i = 0; i < borrowVOS.size(); i++) {

            BorrowVO vo = borrowVOS.get(i);
            //判断还款的状态（3：已还清）

            if ("3".equals(vo.getRepayment_state())){
                count++;
            }
        }

        if (count==borrowVOS.size()){

            System.out.println("count: "+count);
            return count;

        }
        return -1;
    }


    /**
     * 校验借款信息
     * @param id
     * @return
     */
    public int check(int id){

        List<BorrowVO> borrowVOS = borrowMapper.queryAll();
        for (int i = 0; i < borrowVOS.size(); i++) {
            BorrowVO borrowVO = borrowVOS.get(i);
            if (borrowVO.getId()==id){
                return  i;
            }
        }
        return  -1;

    }
}
