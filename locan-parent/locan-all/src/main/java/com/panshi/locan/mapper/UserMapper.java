package com.panshi.locan.mapper;

import com.panshi.locan.domain.AssessResult;
import com.panshi.locan.domain.UserVO;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 查询用户所有信息
     * @return
     */
    List<UserVO> userAll();

    /**
     * 客户增加
     */
    void userAdd(UserVO userVO);

    /**
     * 根据客户id 删除
     */
    void userDelete(UserVO userVO);

    /**
     * 修改客户信息
     */
    void userUpdate(UserVO userVO);

    /**
     * 根据手机号查询
     * @return
     */
    List<UserVO> queryByIphone(UserVO userVO);

    /**
     * 根据id查询
     * @param userVO
     * @return
     */
    List<UserVO> queryById(UserVO userVO);

    /**
     * 根据用户id 修改 银行卡和身份证图片
     * @param userVO
     * @return
     */
    void updateByIdBankCard(UserVO userVO);

    /**
     *  根据id修改客户额度
     */
    void updateByIdMoney(AssessResult assessResult);

    void updateState(UserVO userVO);


    /**
     * 修改个人额度
     */
    void  updateByIdQuota(BigDecimal borrowMoney , Integer id);


    /**
     * 查询总条记录
     * @return
     */
    int total();
}
