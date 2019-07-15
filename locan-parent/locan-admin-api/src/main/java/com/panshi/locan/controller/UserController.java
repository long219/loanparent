package com.panshi.locan.controller;

import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.UserVO;
import com.panshi.locan.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/queryAllUser")
    public Message queryAllUser(){

        Message message = new Message();

        List<UserVO> userVOList = userService.queryAll();
        if (userVOList!=null){
            message.setCode("0");
            message.setMessage("查询成功");
            message.setTotal(userService.total());
            message.setData(userVOList);
        }else {
            message.setCode("002");
            message.setMessage("查询失败");
        }

        return message;
    }


   /* @GetMapping("/queryAllUser")
    public Message queryAllPageUser(@RequestParam int page ,@RequestParam int  limit){


        Message message = new Message();

        List<UserVO> userVOList = userService.queryAll();
        if (userVOList!=null){
            message.setCode("0");
            message.setMessage("查询成功");
            message.setTotal(userService.total());
            message.setData(userVOList);
        }else {
            message.setCode("002");
            message.setMessage("查询失败");
        }

        return message;
    }*/

    @PostMapping("/update")
    public Message updateState(@RequestBody UserVO userVO){
        Message message = new Message();

        String s = userService.updateState(userVO);
        if ("修改成功".equals(s)){
            message.setCode("0");
            message.setMessage(s);
        }else {
            message.setCode("002");
            message.setMessage(s);
        }
        return message;
    }

}
