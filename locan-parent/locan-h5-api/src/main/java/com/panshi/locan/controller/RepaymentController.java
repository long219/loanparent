package com.panshi.locan.controller;

import com.alibaba.fastjson.JSONObject;
import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.RepaymentVO;

import com.panshi.locan.service.impl.RepaymentServiceImpl;
import com.panshi.locan.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import java.util.*;

@RestController
@RequestMapping("/repayment")
@Slf4j
public class RepaymentController {


    @Autowired
    private RepaymentServiceImpl repaymentService;


    @GetMapping("/queryById")
    public Message queryById(@RequestBody RepaymentVO repaymentVO){

        Message message = new Message();

        List<RepaymentVO> repaymentVOS = repaymentService.queryById(repaymentVO);

        if (repaymentVOS!=null){
            message.setCode("0");
            message.setMessage("查询成功");
            message.setData(repaymentVOS);
        }else {
            message.setCode("002");
            message.setMessage("查询失败");
        }

        return message;
    }


    @GetMapping("/queryAll")
    public Message queryAll(){

        Message message = new Message();

        List<RepaymentVO> repaymentVOS = repaymentService.queryAll();

        if (repaymentVOS!=null){
            message.setCode("0");
            message.setMessage("查询成功");
            message.setData(repaymentVOS);
        }else {
            message.setCode("002");
            message.setMessage("查询失败");
        }
        return message;
    }

    @RequestMapping("/queryRepayByUserId")
    public Message queryRepaymentByUserId(@RequestParam Integer userId){

        System.out.println("-------------------");

        Message message = new Message();

        List<RepaymentVO> repaymentVOS = repaymentService.queryByUserid(userId);
        if (repaymentVOS.size()!=0){
            message.setCode("0");
            message.setData(repaymentVOS);
        }else {
            message.setCode("002");

        }
        return  message;
    }


    @PostMapping("/repayByBorrowId")
    public void repayByBorrowId(@RequestParam Integer borrowId) throws IOException {

        List<RepaymentVO> repaymentVOS = repaymentService.queryByBorrowId(borrowId);

        if (repaymentVOS!=null){

            for (RepaymentVO repaymentVO : repaymentVOS) {

                //商家指定Id
                String key = "long";
                //还款id
                String orderId = repaymentVO.getId()+"";
                //通知的url
                String notice_url="http://192.168.3.99:9999/repayment/paymentResult";

                //还款的金额
                String money = repaymentVO.getRepayment_money()+"";

                //当前时间
                long currentTimeMillis = System.currentTimeMillis();

                //1
                Map<String,String> map = new HashMap<>();
                map.put("key",key);
                map.put("orderId",orderId);
                map.put("notice_url",notice_url);
                map.put("money",money);
                map.put("secret_key","dskfjwerqe24");

                //获取当前日期
                map.put("timeMillis",currentTimeMillis+"");

                //2.将所有的key存入list中
                List<String> keys = new ArrayList<>();
                for (String keyAll : map.keySet()) {
                    keys.add(keyAll);
                }


                //3.降序排序加密
                Collections.sort(keys,Collections.reverseOrder());
                StringBuffer sb = new StringBuffer();
                for (String s : keys) {
                    //拼接值
                    sb.append(map.get(s));
                }

                String md5 = MD5Util.getMd5Code(sb.toString());

                Content content =  Request.Get("http://192.168.3.117:9000/prePay?key="+key+"&orderId="+orderId+"&notice_url="+notice_url+"&money="+money+"&timeMillis="+currentTimeMillis+"&md5="+md5)
                .execute().returnContent();

                System.out.println(content.toString());
            }
        }
    }

    @RequestMapping("/paymentResult")
    public Message getResult(@RequestParam String result){

        System.out.println("-------------"+result);

        //将json 字符串 转 json
        JSONObject jsonObject = JSONObject.parseObject(result);

        //获取json 某个值
        String orderId = jsonObject.getString("orderId");

        //初始化还款实例
        RepaymentVO repaymentVO = new RepaymentVO();
        repaymentVO.setId(Integer.valueOf(orderId));


        log.info(orderId);

        return null;
    }
}
