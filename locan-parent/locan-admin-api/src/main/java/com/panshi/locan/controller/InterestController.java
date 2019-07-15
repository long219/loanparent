package com.panshi.locan.controller;

import com.panshi.locan.domain.InterestsVO;
import com.panshi.locan.domain.Message;
import com.panshi.locan.service.impl.InterestsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {

    @Autowired
    private InterestsServiceImpl interestsService;

    @GetMapping("/query")
    public Message query(){
        Message message = new Message();

        List<InterestsVO> query = interestsService.query();

        if (query!=null){
            message.setMessage("查询成功");
            message.setCode("0");
            message.setData(query);
        }else {

            message.setMessage("查询成功");
            message.setCode("002");
        }
        return message;
    }


    @PostMapping("/update")
    public Message update(@RequestBody InterestsVO interestsVO){
        Message message = new Message();
        String update = interestsService.update(interestsVO);
        if ("修改成功".equals(update)){
            message.setMessage(update);
            message.setCode("0");
        }else {
            message.setMessage(update);
            message.setCode("002");
        }
        return  message;
    }
}
