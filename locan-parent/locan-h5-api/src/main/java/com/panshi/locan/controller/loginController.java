package com.panshi.locan.controller;

import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.NoteVO;
import com.panshi.locan.domain.UserVO;
import com.panshi.locan.service.impl.NoteServiceImpl;
import com.panshi.locan.service.impl.UserServiceImpl;
import com.panshi.locan.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

import java.util.List;

@RestController
@RequestMapping("/login")
@Slf4j
public class loginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NoteServiceImpl noteService;


    /**
     * 登录
     */
    @PostMapping("/login/{type}")
    public Message login(@RequestBody UserVO userVO,@PathVariable String type ) throws ParseException {

        //打印日志
        log.info(userVO.getPassword()+"-----------"+userVO.getIphone()+"----"+userVO.getVftcode());

        Message message =null;

        //获取客户信息
        List<UserVO> userList = userService.queryByIphone(userVO);


        NoteVO note = new NoteVO();
        note.setIphone(userVO.getIphone());
        note.setType(type);

        //获取短信信息
        List<NoteVO> noteVOS = noteService.queryByIphone(note);

        if (noteVOS.size()!=0 && userList!=null){
            NoteVO noteVO = noteVOS.get(0);

            //获取当前时间毫秒数
            long currentTimeMillis = System.currentTimeMillis();

            long msec = TimeUtil.getMsec(noteVO.getNote_time())+(60*1000);

            log.info(currentTimeMillis+"------"+msec);

            if (currentTimeMillis>msec){
                message.setCode("002");
                message.setMessage("验证码已失效!");

            }else {

                //校验账户是否正确
                UserVO userVO1 = userList.get(0);

                //判断 手机号 和 密码及验证码 是否一致
                if (userVO1.getIphone().equals(userVO.getIphone()) && userVO1.getPassword().equals(userVO.getPassword())){

                    if (noteVO.getShort_message().equals(userVO.getVftcode())){
                        message = getMessage("登录成功");
                        message.setData(userVO1);
                    }else{
                        message = getMessage("登录失败");
                    }
                }
            }
        }


        return message;
    }


    /**
     * 注册
     */
    @PostMapping("/register/{type}")
    public Message register(@RequestBody UserVO userVO,@PathVariable String type) throws ParseException {

        //打印日志
        log.info(userVO.getPassword()+"-----------"+userVO.getIphone()+"----"+userVO.getVftcode());

        Message message = null;


        NoteVO note = new NoteVO();
        note.setIphone(userVO.getIphone());
        note.setType(type);
        //校验验证码是否一致
        List<NoteVO> noteVOS = noteService.queryByIphone(note);

        for (NoteVO noteVO : noteVOS) {

            //将日期化为毫秒
            long msec = TimeUtil.getMsec(noteVO.getNote_time())*(60*1000);

            //获取当前时间毫秒数
            long currentTime = System.currentTimeMillis();

            //校验验证码是否有效
            if (currentTime>msec){

                message.setCode("002");
                message.setMessage("验证码已失效!");
                return message;
            }

            if ( noteVO.getType().equals(type) && noteVO.getShort_message().equals(userVO.getVftcode()) ){

                //成功后 ,将信息添加到用户表里
                String s = userService.userAdd(userVO);
                message= getMessage(s);
            }
        }

        return message;

    }

    /**
     * 发送验证码
     * @param iphone
     */
    @PostMapping("/addNote/{type}")
    public void  sendNote(@RequestParam String iphone,@PathVariable String type) throws IOException {

        //获取验证码
        String vftCode = noteService.getVftCode();

        NoteVO noteVO = new NoteVO();
        noteVO.setIphone(iphone);
        noteVO.setType(type);
        noteVO.setShort_message(vftCode);

        log.info("验证码:----"+vftCode+"----"+iphone+"----"+type);


        Request.Get("http://v.juhe.cn/sms/send?mobile="+iphone+"&tpl_id=168816&tpl_value="+vftCode+"&dtype=&key=40fcff806619808f9b9a36e2579e0e12")
                .execute().returnContent();

        noteService.addNote(noteVO);
    }


    /**
     * 获取响应信息
     * @return
     */
    public Message getMessage(String state) {

        Message message = new Message();

        if ("登录成功".equals(state)){
            message.setMessage(state);
            message.setCode("0");
        }else if ("添加成功".equals(state)){
            message.setMessage(state);
            message.setCode("0");
        }else {
            message.setMessage(state);
            message.setCode("002");
        }
        return message;
    }





}
