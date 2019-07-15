package com.panshi.locan.service.impl;

import com.github.pagehelper.PageHelper;
import com.panshi.locan.domain.NoteVO;
import com.panshi.locan.domain.RepaymentVO;
import com.panshi.locan.mapper.NoteMapper;
import com.panshi.locan.mapper.RepaymentMapper;
import com.panshi.locan.service.RepaymentService;
import com.panshi.locan.util.MD5Util;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    //获取日志对象
    public Logger logger = LoggerFactory.getLogger(RepaymentServiceImpl.class);


    @Autowired
    private RepaymentMapper repaymentMapper;

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public List<RepaymentVO> queryAll() {
        return repaymentMapper.queryAll();
    }

    @Override
    public List<RepaymentVO> queryById(RepaymentVO repaymentVO) {

        return repaymentMapper.queryByIdAndUserId(repaymentVO);
    }

    @Override
    public List<RepaymentVO> queryByBorrowId(Integer borrowId) {

        return repaymentMapper.queryByBorrowId(borrowId);
    }

    @Override
    public List<RepaymentVO> queryAllRepayType() {

        return repaymentMapper.queryAllRepayType();
    }

    @Override
    public String add(RepaymentVO repaymentVO) {

        repaymentMapper.add(repaymentVO);
        return "0";
    }


    @Override
    public String updateRepay(RepaymentVO repaymentVO) {

        repaymentMapper.updateRepay(repaymentVO);

        return "修改成功";
    }

    @Override
    public List<RepaymentVO> queryByUserid(Integer userId) {
        return repaymentMapper.queryByUserid(userId);
    }


    @Override
    public String dept(Map<String, Object> map) {

        String key = "long";
        //还款id
        String orderId = map.get("orderId")+"";
        //还款金额
        String money = map.get("money")+"";
        //用户手机号
        String phone = map.get("phone")+"";
        //用户身份证号
        String idcard = map.get("idcard")+"";
        //设置安全key
        String secret_key ="gwewerer1er3g";

        map.put("key",key);
        map.put("secret_key",secret_key);

        //2.将所有的key存入list中
        List<String> keys = new ArrayList<>();
        for (String keyAll : map.keySet()) {
            keys.add(keyAll);
        }


        //3.降序排序加密
        Collections.sort(keys,Collections.reverseOrder());
        StringBuffer sb = new StringBuffer();
        for (String s : keys) {
            sb.append(map.get(s)); //拼接值
        }

        //MD5加密
        String md5 = MD5Util.getMd5Code(sb.toString());

        try {
            Content content = Request.Get("http://192.168.3.117:9000/debt?key=" + key + "&orderId=" + orderId + "&idcard=" + idcard + "&money=" + money + "&phone=" + phone + "&md5=" + md5)
                    .execute().returnContent();

            System.out.println(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "已催款";
    }


    //定时任务  判断已逾期
    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(cron = "0 0 12 * * *")
    public void setOverdue(){

        List<RepaymentVO> repaymentVOS = repaymentMapper.queryAll();

        for (RepaymentVO repaymentVO : repaymentVOS) {

            int i = repaymentMapper.timeDifference(repaymentVO.getRepayment_time());

            if (i>1){

                BigDecimal money = repaymentVO.getRepayment_money();
                repaymentVO.setRepayment_type("4");

                //计算逾期总收利（没天收利 5 元   i: 逾期的天数 ）
                BigDecimal overdueMoney = BigDecimal.valueOf(5).multiply(BigDecimal.valueOf(i));

                repaymentVO.setRepayment_money(overdueMoney.add(money));
                repaymentMapper.udpateRepayType(repaymentVO);
                System.out.println("已逾期...");
            }

            System.out.println("没逾期..."+i);

        }

    }


    @Scheduled(cron = "0 0 12 * * *")
    public void billingNotice(){

        List<RepaymentVO> repaymentVOS = repaymentMapper.queryAll();

        for (RepaymentVO repaymentVO : repaymentVOS) {

            int i = repaymentMapper.timeDifference(repaymentVO.getRepayment_time());

            if (i>=-5 || i>=-3 || i==0){

                NoteVO noteVO = new NoteVO();

                noteVO.setIphone(repaymentVO.getBorrowVO().getUserVO().getIphone());
                noteVO.setType("6");
                noteVO.setShort_message("您的还款日期快到了");

                noteMapper.addNote(noteVO);
            }
        }

    }


}
