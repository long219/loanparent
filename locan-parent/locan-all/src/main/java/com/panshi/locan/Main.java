package com.panshi.locan;



import com.panshi.locan.util.MD5Util;
import com.panshi.locan.util.TimeUtil;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) {

        /*Date date = new Date();
        long time = date.getTime();
        System.out.println(time);*/

        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        System.out.println(year+" 年 "+month+" 月 "+day+" 日   "+hour+"时 "+minute+"分 "+second+"秒");




        /*Calendar calendar = Calendar.getInstance();
        //Integer repaymentPeriods = loanApplication.getRepaymentPeriods();
        for(int i= 0; i < 3; i++){
            // 每次跳一月
            calendar.roll(Calendar.MONTH,true);
            // 跳了一月，月为1
            if(calendar.get(Calendar.MONTH) == 0){
                // 年跳一年
                calendar.roll(Calendar.YEAR,true);
            }
            //map.put(i,calendar.getTime());
            //System.out.println(calendar.getTime());
            String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime());

            System.out.println(format);
        }*/

        /*Map<Integer, String> repaymentTime = getRepaymentTime(3);
        System.out.println(repaymentTime.get("2019-10-02 11:12:56"));*/



        //get();

        bigdecimal();


    }


    public static  void  get(){
        String key = "long"; //商家指定Id
        String orderId = 6+""; //还款id
        String notice_url="http://192.168.3.99:9999/repayment/paymentResult"; //通知的url
        String money = 565+""; //还款的金额
        long currentTimeMillis = System.currentTimeMillis(); //当前时间

        //1
        Map<String,String> map = new HashMap<>();

        map.put("key",key);
        map.put("orderId",orderId);
        map.put("notice_url",notice_url);
        map.put("money",money);
        map.put("secret_key","dskfjwerqe24");

        //获取当前日期
        //Calendar calendar = Calendar.getInstance();
        //String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime());
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
            sb.append(map.get(s)); //拼接值
        }

        String md5 = MD5Util.getMd5Code(sb.toString());

        System.out.println(md5);

    }


    public static Map<Integer,String> getRepaymentTime(int length){

        Map<Integer, String> map = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        for(int i= 0; i < length; i++){
            // 每次跳一月
            calendar.roll(Calendar.MONTH,true);
            // 跳了一月，月为1
            if(calendar.get(Calendar.MONTH) == 0){
                // 年跳一年
                calendar.roll(Calendar.YEAR,true);
            }
            //map.put(i,calendar.getTime());
            //System.out.println(calendar.getTime());
            String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime());

            map.put(i,format);
            System.out.println(format);
        }

        return  map;
    }


    public static  void  bigdecimal(){
        //BigDecimal money = BigDecimal.valueOf(20).multiply(BigDecimal.valueOf(5));
        BigDecimal money = BigDecimal.valueOf(20).add(BigDecimal.valueOf(5));
        System.out.println(money);
    }

}
