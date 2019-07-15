package com.panshi.locan.controller;


import com.alibaba.fastjson.JSONObject;
import com.panshi.locan.domain.AssessResult;
import com.panshi.locan.domain.Message;
import com.panshi.locan.domain.UserVO;
import com.panshi.locan.service.impl.QnUploadServiceImpl;
import com.panshi.locan.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {



    @Autowired
    private QnUploadServiceImpl qnUploadServiceImpl;


    @Value("${filePath.name}")
    private String filePath;  //存储图片的路径

    @Value("${filePath.imgPath}")
    private String imgPath; //获取图片url路径

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/queryAll")
    public Message queryAll(){
        List<UserVO> userVOS = userService.queryAll();

        Message msg = getMessage(userVOS, "查询");

        return msg;
    }

    /**
     * 根据用户id查询
     */
    @PostMapping("/queryById")
    public Message queryById(@RequestBody UserVO userVO){

        List<UserVO> userVO1 = userService.queryById(userVO);

        for (UserVO vo : userVO1) {
            vo.setBank_img(imgPath+"/img/"+vo.getBank_img());
            //aodi.jpg,binli.jpg

            /**
             * 1.获取身份证
             * 2.按 ， 拆分
             *
             * 3.拼接
             */
            String[] split = vo.getIdentity_card_img().split(",");

            String identity_card_img = "";
            for (int i = 0; i < split.length; i++) {
                identity_card_img+=imgPath+"/img/"+split[i]+",";
            }

            //设置身份证
            vo.setIdentity_card_img(identity_card_img.substring(0,identity_card_img.length()-1));
        }


        log.info("-----------"+userVO.getId());

        Message msg = getMessage(userVO1,"查询");
        return msg;
    }

    /**
     * 根据用户id修改信息
     */
    @PostMapping("/update")
    public Message updateUser(@RequestBody UserVO userVO){

        String s = userService.userUpdate(userVO);

        Message msg = getMessage(s,"修改成功");

        return msg;
    }


    /**
     * 上传身份证 ,银行卡
     *
     */
    @PostMapping("/upload")
    public Message upload(@RequestParam Integer id,@RequestParam("img1") Part img1,@RequestParam("img2") Part img2,@RequestParam("img3") Part img3) throws IOException {


        List<Part> isList = new ArrayList<>();

        //身份证正面
        isList.add(img1);

        //身份证反面
        isList.add(img2);

        //银行卡
        isList.add(img3);


        InputStream inputStream=null;
        FileOutputStream fos=null;

        UserVO userVO = new UserVO();

        String iDCard = "";

        for (int i = 0; i < isList.size(); i++) {

            Part part = isList.get(i);

            //读流
            inputStream  = part.getInputStream();

            File file = new File(filePath+"/"+id);

            //如果文件夹不存在就创建
            if (!file.exists()){
                file.mkdir();
            }

            byte[] by = new byte[ inputStream.available()];

            inputStream.read(by);

            log.info("文件路径: "+file.getAbsoluteFile()+"/"+part.getSubmittedFileName());

            //将信息写入文件中
            fos = new FileOutputStream(file.getAbsoluteFile()+"/"+part.getSubmittedFileName());
            fos.write(by);

            //关流
            inputStream.close();
            fos.close();

            //添加银行卡图片
            if (i==isList.size()-1){
                userVO.setBank_img(part.getSubmittedFileName());
            }else {
                //添加身份证正反面
                iDCard+=part.getSubmittedFileName()+",";
            }

        }

        iDCard = iDCard.substring(0,iDCard.length()-1);

        userVO.setIdentity_card_img(iDCard);
        userVO.setId(id);

        log.info("身份证: "+userVO.getIdentity_card_img()+"------"+" 银行卡:"+userVO.getBank_img()+"-----"+id);

        /*String s = userService.updateByIdBankCard(userVO);

        Message message = getMessage("上传成功",s);*/

        return  null;
    }



    @PostMapping("/uploadYun")
    public Message uploadYun(@RequestParam("img1") Part img1) throws IOException {

        System.out.println("--------上传文件-------");

        InputStream inputStream=null;

        //读流
        inputStream  = img1.getInputStream();


       String s = qnUploadServiceImpl.uploadFile(inputStream, img1.getName());

        System.out.println("-----------"+s);

        /*List<Part> isList = new ArrayList<>();

        //身份证正面
        isList.add(img1);

        //身份证反面
        isList.add(img2);

        //银行卡
        isList.add(img3);


        InputStream inputStream=null;
        FileOutputStream fos=null;

        UserVO userVO = new UserVO();

        String iDCard = "";

        for (int i = 0; i < isList.size(); i++) {

            Part part = isList.get(i);

            //读流
            inputStream  = part.getInputStream();

            File file = new File(filePath+"/"+id);

            //如果文件夹不存在就创建
            if (!file.exists()){
                file.mkdir();
            }

            byte[] by = new byte[ inputStream.available()];

            inputStream.read(by);

            log.info("文件路径: "+file.getAbsoluteFile()+"/"+part.getSubmittedFileName());

            //将信息写入文件中
            fos = new FileOutputStream(file.getAbsoluteFile()+"/"+part.getSubmittedFileName());
            fos.write(by);

            //关流
            inputStream.close();
            fos.close();

            //添加银行卡图片
            if (i==isList.size()-1){
                userVO.setBank_img(part.getSubmittedFileName());
            }else {
                //添加身份证正反面
                iDCard+=part.getSubmittedFileName()+",";
            }

        }

        iDCard = iDCard.substring(0,iDCard.length()-1);

        userVO.setIdentity_card_img(iDCard);
        userVO.setId(id);

        log.info("身份证: "+userVO.getIdentity_card_img()+"------"+" 银行卡:"+userVO.getBank_img()+"-----"+id);
*/
        /*String s = userService.updateByIdBankCard(userVO);

        Message message = getMessage("上传成功",s);*/


        Message message = new Message();
        message.setData(s);

        return  message;
    }



    /**
     * 评估
     */
    @PostMapping("/valuation")
    public Message audit(@RequestBody UserVO userVO) throws IOException {

        Message message = new Message();


        //根据id查询
        List<UserVO> userVOList = userService.queryById(userVO);

        UserVO userVO1 = userVOList.get(0);

        if (userVO1.getName()==null || userVO1.getBank_card()==null){
            message.setCode("002");
            message.setMessage("未完善个人信息");

            return  message;
        }

        int i = Integer.parseInt(userVO1.getAudit_status());

        if (i==1){
            System.out.println("---dafdafa--");


            String key = String.valueOf(userVO1.getId());
            String idcard = userVO1.getIdentity_card();

            //调用第三方接口
            Request.Get("http://192.168.3.117:9000/valuation?key="+"99"+"&idcard="+"928416415185244"+"&notice_url=http://192.168.3.99:9999/user/assessResult/"+userVO1.getId())
                    .execute().returnContent();

        }else {

            System.out.println("-----"+userVO1);
            message.setCode("002");
            message.setMessage("身份证正在审核中...");
        }

        return message;


    }

    /**
     *  评估的结果
     *
     * @param data 评估的结果信息
     * @param idcard 客户的身份证号
     * @param id  客户的Id
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/assessResult/{id}")
    public void assessResult(@RequestParam String data ,@RequestParam String idcard,@PathVariable Integer id) throws UnsupportedEncodingException {

        //Message message = new Message();

        if (data!=null){

            String result = URLDecoder.decode(data, "UTF-8");


            System.out.println("------------"+result);

            //将json 字符串转为java 对象
            /*JSONObject josn =JSONObject.parseObject(data);
            String money = josn.getString("money");*/


            JSONObject jsonObject = JSONObject.parseObject(result);

            AssessResult assessResult = JSONObject.toJavaObject(jsonObject, AssessResult.class);
            assessResult.setId(id);


            //修改客户额度
            userService.updateByIdMoney(assessResult);

            log.info("结果: "+result+"---------"+idcard);

            log.info("金额:"+assessResult.getMoney()+"-----Id:"+id);

        }else {
            log.info("评估失败!");
        }



    }


    /**
     * 获取响应信息
     *  0  成功
     *  002  失败
     * @return
     */
    public Message getMessage(Object object,String state){

        Message message = new Message();

        if ("查询".equals(state)){
            if (object!=null){
                message.setMessage("查询成功");
                message.setCode("0");
                message.setData(object);
            }else {
                message.setMessage("查询失败");
                message.setCode("002");
            }
        }else{
            if (state.equals(object)){
                message.setMessage(state);
                message.setCode("0");
            }else{
                message.setMessage(object.toString());
                message.setCode("002");
            }
        }

        return  message;
    }

}
