package com.shitouren.core.service;


import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.bean.param.user.NewUserLoginParam;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;

import java.util.List;
import java.util.Map;


public interface UserService {

    void distribution();

    /**
     * 账户名和密码登录
     *
     * @param param
     * @return
     */
    Users userLogin(UserLoginParam param);

    /**
     * 切换账号
     *
     * @return
     */
    Users userqhLogin(int UserId);

    /**
     * 用户注册
     *
     * @param param
     */
    void userRegister(UserRegisterParam param);

    void regh5(UserRegisterParam param);

    /**
     * 忘记密码
     *
     * @param param
     */
    void userRestPwd(UserRestPwdParam param);

    void updateAvatar(Integer userId, String avatar);

    void updateTradePassWord(SysUserParam sysUserParam, String userAccount, String phone, String code, String newTradePassWord, String newTradePassWord2);

    void updateNickname(Integer userId, String nickname);

    String createUserShareImg(String registerCode);

    void createimg();

    String TestPay() throws Exception;

    void aaaa() throws Exception;

    Users passworduserLogin(NewUserLoginParam param);
}
