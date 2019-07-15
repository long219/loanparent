package com.panshi.locan.controller;

import com.panshi.locan.domain.BorrowVO;
import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.NoteVO;
import com.panshi.locan.domain.UserVO;
import com.panshi.locan.service.impl.BorrowServiceImpl;
import com.panshi.locan.service.impl.NoteServiceImpl;
import com.panshi.locan.service.impl.UserServiceImpl;
import com.panshi.locan.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/loan")
@Slf4j
public class BorrowController {
    

    @Autowired
    private BorrowServiceImpl borrowService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NoteServiceImpl noteService;

    @PostMapping("/add")
    public Message loanAdd(@RequestBody BorrowVO borrowVO) throws ParseException {

        //打印日志
        log.info(borrowVO.toString());


        //获取客户信息
        UserVO userVO = new UserVO();
        userVO.setId(borrowVO.getUser_id());
        List<UserVO> userVOList = userService.queryById(userVO);


        //获取短信信息
        NoteVO note = new NoteVO();
        note.setIphone(userVOList.get(0).getIphone());
        note.setType("2");
        //校验验证码是否一致
        List<NoteVO> noteVOS = noteService.queryByIphone(note);

        //响应信息
        Message message=null;

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

            if (noteVO.getShort_message().equals(borrowVO.getVftcode())){

                String add = borrowService.add(borrowVO);

                message = getMessage(add,"添加成功");

                Integer borrow_money = borrowVO.getBorrow_money();

                userService.updateByIdQuota(BigDecimal.valueOf(borrow_money), borrowVO.getUser_id());
            }
        }


        return message;
    }


    @GetMapping("/queryById")
    public  Message queryById(){

        return null;
    }

    /**
     * 获取响应信息体
     * @param object
     * @param state
     * @return
     */
    public Message getMessage(Object object , String state){
        Message message = new Message();

        if (state.equals(object)){
            message.setMessage(object.toString());
            message.setCode("0");
        }else {
            message.setMessage(object.toString());
            message.setCode("002");
        }
        return message;
    }
}
