package com.shitouren.core.controller;

import com.shitouren.core.annotation.Access;

import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.param.user.NewUserLoginParam;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.service.UserService;
import com.shitouren.core.utils.StringUtil;
import com.shitouren.core.utils.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/1 15:50
 */
@RestController
@Api(value = "用户管理", tags = "用户管理")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 商城账号密码登录
     *
     * @param param
     * @return
     */
    @PostMapping("/user/login")
    @Access(value = false)
    @ApiOperation(value = "用户-验证码登录")
    public Map<String, Object> mallLogin(@Valid UserLoginParam param) {
        Users user = userService.userLogin(param);
        String token = TokenUtil.getToken(new UserLoginWx(user));
        if (!StringUtil.isValidStr(token)) {
            throw new CloudException(ExceptionConstant.获取token失败.getCode());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @PostMapping("/user/passwordlogin")
    @Access(value = false)
    @ApiOperation(value = "用户-密码登录")
    public Map<String, Object> passwordlogin(@Valid NewUserLoginParam param) {
        Users user = userService.passworduserLogin(param);
        String token = TokenUtil.getToken(new UserLoginWx(user));
        if (!StringUtil.isValidStr(token)) {
            throw new CloudException(ExceptionConstant.获取token失败.getCode());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    /**
     * 用户注册
     *
     * @param param
     */
    @PostMapping("/user/reg")
    @Access(value = false)
    @ApiOperation(value = "用户-注册")
    public void userRegister(@Valid UserRegisterParam param) {
        userService.userRegister(param);
    }

    /**
     * 用户注册
     *
     * @param param
     */
    @PostMapping("/user/regh5")
    @Access(value = false)
    @ApiOperation(value = "用户-注册H5没用到")
    public void regh5(@Valid UserRegisterParam param) {
        userService.regh5(param);
    }

    /**
     * 忘记密码
     *
     * @param param
     */
    @PostMapping("/user/reset/pwd")
    @ApiOperation(value = "用户-忘记密码")
    @Access(value = false)
    public void userRestPwd(@Valid UserRestPwdParam param) {
        userService.userRestPwd(param);
    }


}
