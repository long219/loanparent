package com.panshi.locan.controller;

import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.RepaymentVO;
import com.panshi.locan.service.impl.RepaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("repayment")
public class RepaymentController {

    @Autowired
    private RepaymentServiceImpl repaymentService;



    @GetMapping("/queryAllRepayment")
    public Message queryAll(){
        Message message = new Message();

        List<RepaymentVO> repaymentVOS = repaymentService.queryAll();

        if (repaymentVOS!=null){
            message.setMessage("查询成功");
            message.setCode("0");
            message.setData(repaymentVOS);
        }else {

            message.setMessage("查询失败");
            message.setCode("002");
        }
        return message;

    }

    @GetMapping("/queryRepayOverdue")
    public Message queryRepayOverdue(){

        Message message = new Message();

        List<RepaymentVO> repaymentVOS = repaymentService.queryAllRepayType();

        if (repaymentVOS!=null){
            message.setMessage("查询成功");
            message.setCode("0");
            message.setData(repaymentVOS);
        }else {

            message.setMessage("查询失败");
            message.setCode("002");
        }
        return message;
    }

    @PostMapping("/dept")
    public Message dept(@RequestBody Map<String , Object> map) throws IOException {

        Message message = new Message();


       String dept = repaymentService.dept(map);

        message.setCode("0");
        message.setMessage(dept);
        return message;
    }
}
