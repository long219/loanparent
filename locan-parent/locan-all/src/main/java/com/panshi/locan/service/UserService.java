package com.panshi.locan.service;

import com.panshi.locan.domain.AssessResult;
import com.panshi.locan.domain.UserVO;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<UserVO> queryAll();

    String  userAdd(UserVO userVO);

    String  userDelete(UserVO userVO);

    String  userUpdate(UserVO userVO);

    List<UserVO> queryByIphone(UserVO userVO);

    List<UserVO> queryById(UserVO userVO);

    String updateByIdBankCard(UserVO userVO);

    void updateByIdMoney(AssessResult assessResult);

    String updateState(UserVO userVO);

    String updateByIdQuota(BigDecimal borrowMoney , Integer id);

    int total();
}
