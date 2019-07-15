package com.panshi.locan.controller;

import com.panshi.locan.domain.BorrowVO;
import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.RepaymentVO;
import com.panshi.locan.service.impl.BorrowServiceImpl;
import com.panshi.locan.service.impl.RepaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class BorrowController {

    @Autowired
    private BorrowServiceImpl borrowService;

    @Autowired
    private RepaymentServiceImpl repaymentService;

    @GetMapping("/queryAll")
    public Message queryAll(){

        Message message = new Message();

        List<BorrowVO> borrowVOS = borrowService.queryAll();

        if (borrowVOS!=null){
            message.setCode("0");
            message.setMessage("查询成功");
            message.setData(borrowVOS);
        }else {
            message.setCode("002");
            message.setMessage("查询失败");
        }

        return  message;
    }


    @PostMapping("/addRepayment")
    public  Message  addRepayment(@RequestBody BorrowVO borrowVO){

        Message message = new Message();

        String s = borrowService.updateState(borrowVO);

        if ("修改成功".equals(s)){

            String state = null;

            //获取期数
            Integer borrow_number = borrowVO.getBorrow_number();

            Calendar calendar = Calendar.getInstance();

            //添加还款数据
            for (Integer i = 0; i < borrow_number; i++) {

                // 每次跳一月
                calendar.roll(Calendar.MONTH,true);
                // 跳了一月，月为1
                if(calendar.get(Calendar.MONTH) == 0){
                    // 年跳一年
                    calendar.roll(Calendar.YEAR,true);
                }

                //格式化日期
                String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime());

                //初始化还款实例
                RepaymentVO repaymentVO = new RepaymentVO();

                //设置还款数据
                repaymentVO.setRepayment_money(BigDecimal.valueOf(((borrowVO.getBorrow_money()+borrowVO.getTotal_interest())/borrow_number))); //还款金额
                repaymentVO.setBorrow_id(borrowVO.getId()); //借款id
                repaymentVO.setRepayment_time(format); //还款时间

                state = repaymentService.add(repaymentVO);

            }

            if ("0".equals(state)){
                message.setCode("0");
                message.setMessage("审核通过");
            }else {
                message.setCode("002");
                message.setMessage("驳回");
            }

        }else {
            message.setCode("002");
            message.setMessage("修改失败");
        }

        return  message;
    }



    //public void
}
