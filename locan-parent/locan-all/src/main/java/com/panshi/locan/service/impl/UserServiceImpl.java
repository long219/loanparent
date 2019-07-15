package com.panshi.locan.service.impl;

import com.panshi.locan.domain.AssessResult;
import com.panshi.locan.domain.UserVO;
import com.panshi.locan.mapper.UserMapper;
import com.panshi.locan.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> queryAll() {

        return  userMapper.userAll();
    }

    @Override
    public String userAdd(UserVO userVO) {

        if (userVO.getIphone()!=null && userVO.getPassword()!=null){

            int check = check(userVO);
            if (check==-1){
                userMapper.userAdd(userVO);
                return "添加成功";
            }
        }


        throw new RuntimeException();

        //return;
       // return "添加失败";
    }

    @Override
    public String userDelete(UserVO userVO) {
        int check = check(userVO);
        if (check!=-1){
            userMapper.userDelete(userVO);
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String userUpdate(UserVO userVO) {
        int check = check(userVO);
        if (check!=-1){
            userMapper.userUpdate(userVO);
            return "修改成功";
        }
        return "修改失败";
    }


    @Override
    public List<UserVO> queryByIphone(UserVO userVO) {

        return userMapper.queryByIphone(userVO);
    }

    @Override
    public List<UserVO> queryById(UserVO userVO) {

        return userMapper.queryById(userVO);
    }

    @Override
    public String updateByIdBankCard(UserVO userVO) {

        int check = checkId(userVO);
        if (check!=-1){
            userMapper.updateByIdBankCard(userVO);
            return "上传成功";
        }
        return "上传失败!";
    }

    @Override
    public void updateByIdMoney(AssessResult assessResult) {

         userMapper.updateByIdMoney(assessResult);

    }

    @Override
    public String updateState(UserVO userVO) {

        int check = check(userVO);
        if (check!=-1){

            //银行卡号
            if (userVO.getBank_card()==null){
                return "请完善个人信息";
            }

            //校验身份证
            if (userVO.getIdentity_card_img()==null){
                return  "请完善个人信息";
            }

            //银行卡
            if (userVO.getBank_img()==null){
                return "请完善个人信息";
            }

            userMapper.updateState(userVO);

            return "修改成功";
        }
        return "修改失败";
    }

    @Override
    public String updateByIdQuota(BigDecimal borrowMoney, Integer id) {

        userMapper.updateByIdQuota(borrowMoney,id);

        return "修改成功";
    }

    @Override
    public int total() {
        return userMapper.total();
    }


    /**
     * 校验用户ID信息
     * @param userVO
     * @return
     */
    public int checkId(UserVO userVO){
        List<UserVO> userVOS = userMapper.userAll();
        for (int i = 0; i < userVOS.size(); i++) {
            UserVO vo = userVOS.get(i);
            if (userVO.getId()!=null && userVO.getId()==vo.getId()){
                return  i;
            }
        }
        return -1;
    }


    /**
     * 校验用户手机号
     * @param userVO
     * @return
     */
    public int check(UserVO userVO){
        List<UserVO> userVOS = userMapper.userAll();
        for (int i = 0; i < userVOS.size(); i++) {
            UserVO vo = userVOS.get(i);
            if (userVO.getIphone()!=null && userVO.getIphone().equals(vo.getIphone())){
                return  i;
            }
        }
        return -1;
    }
}
