
package com.panshi.locan;

import com.panshi.locan.domain.UserVO;
import com.panshi.locan.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    private  UserServiceImpl userService;



    /**
     * 测试手机号不能为空
     */


    @Test(expected = RuntimeException.class)
    public  void  testAdd(){

        UserVO userVO=new UserVO();

        userVO.setIphone("19868321695");

        userService.userAdd(userVO);

    }

    //iphone,password,regist_time


    /**
     * 测试密码不能为空
     */


    @Test(expected = RuntimeException.class)
    public  void  testAdd2(){

        UserVO userVO=new UserVO();

        userVO.setPassword("666666");

        userService.userAdd(userVO);

    }


    @Test(expected = RuntimeException.class)
    public void  testAdd3(){

        UserVO userVO=new UserVO();

        userVO.setPassword("666666");
        userVO.setIphone("19868321695");

        String s = userService.userAdd(userVO);

        Assert.assertEquals("添加成功",s);

    }



}

