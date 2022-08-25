package com.shitouren.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.shitouren.core.annotation.Access;
import com.shitouren.core.autogenerate.dao.UsersDao;
import com.shitouren.core.bean.param.system.VerificationCodeParam;
import com.shitouren.core.config.redis.CloudRedisTemplate;

import com.shitouren.core.service.UserService;
import com.shitouren.core.utils.SMSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "系统管理", tags = "系统管理")
@Log4j2
public class SystemController {
    private static Map<String, String> tempMap = new HashMap<>();

    static {
        tempMap.put("reg_code_", "【弘基数藏】您的验证码为%s，在5分钟内有效。");
        tempMap.put("forget_pwd_code_", "【弘基数藏】您的验证码为%s，在5分钟内有效。");
        tempMap.put("update_trade_pwd_", "【弘基数藏】您的验证码为%s，在5分钟内有效。");
        tempMap.put("check_code_", "【弘基数藏】您的验证码为%s，在5分钟内有效。");
    }

    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;
    @Autowired
    UserService userService;

   /* @PostMapping(value = "/system/get/verification/code")
    @ApiOperation(value = "获取手机验证码")
    @Access(value = false)*/
    /*public String getPhoneCode(@Validated VerificationCodeParam param) {
        String code = RandomStringUtils.randomNumeric(6);
//        String temp = tempMap.get(param.getType());
        *//**
         * 这里需要加发送短信的相关逻辑
         *//*
//        SMSUtils.send(param.getPhone(), String.format(temp, code));
        log.info("手机验证码为：{}，类型为：{}", code, param.getType());
        String path = "http://yzm.api.bdymkt.com/sms";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials("daf88d5bb94e4327a40252d3c4b834d9", "7e3fdd3fa1744a4b9897a19e2cbba869");
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
        request.addQueryParameter("mobile", param.getPhone());
        request.addQueryParameter("content", "【希艾宇宙】您的验证码为" + code + "，在5分钟内有效。");
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String key = param.getType() + param.getPhone();
        System.out.println("key=" + key);
        cloudRedisTemplate.set(key, code, 30 * 60);
        return "你打个438试试";
    }*/

    /*public String getPhoneCode(@Validated VerificationCodeParam param) {
        String code = RandomStringUtils.randomNumeric(6);
        String temp = tempMap.get(param.getType());
        *//**
         * 这里需要加发送短信的相关逻辑
         *//*
        SMSUtils.send(param.getPhone(), String.format(temp, code));
        log.info("手机验证码为：{}，类型为：{}", code, param.getType());
        String key = param.getType() + param.getPhone();
        cloudRedisTemplate.set(key, code, 30 * 60);
        return code;
    }*/

    @SneakyThrows
    @PostMapping(value = "/system/get/verification/code")
    @ApiOperation(value = "获取手机验证码")
    @Access(value = false)
    public String getPhoneCode(@Validated VerificationCodeParam param) {
        System.out.println("11111111111");
        String code = RandomStringUtils.randomNumeric(6);
        String temp = tempMap.get(param.getType());
        /**
         * 这里需要加发送短信的相关逻辑
         */
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("code", code);
        System.out.println(jsonObj);
        String json_to_string = JSONObject.toJSONString(jsonObj);

        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5tJHJdXQnbv6ztc7LraE", "mWrf1NEk30bBf779P7sMeBGCfQFq1Y");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(param.getPhone())
                .setTemplateCode("SMS_243775038")
                .setSignName("shulian")
                .setTemplateParam(json_to_string);
        client.sendSms(sendSmsRequest);


        //SMSUtils.send(param.getPhone(), String.format(temp, code));
        System.out.println("param.getType() :"+param.getType());
        log.info("手机验证码为：{}，类型为：{}", code, param.getType());
        String key = param.getType() + param.getPhone();
        cloudRedisTemplate.set(key, code, 30 * 60);
        return code;
    }


    public static void main(String[] args) {
        String code = RandomStringUtils.randomNumeric(6);
        String path = "http://yzm.api.bdymkt.com/sms";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials("LTAI5t9vgtTyJZtKPfkitqKu", "yDoFp6Bh4Sdb46FWwFJk5AHXP0i01n");
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
        request.addQueryParameter("mobile", "17734698612");
        request.addQueryParameter("content", "【希艾宇宙】您的验证码为xacs，在5分钟内有效。");
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @PostMapping(value = "/system/code")
//    @ApiOperation(value = "添加收款信息获取验证码")
//    @Access
//    @GetLoginUser
//    public String getPhoneCode(SysUserParam sysUserParam) {
//        Users users = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
//        String code = RandomStringUtils.randomNumeric(6);
//        String temp = tempMap.get("add_code");
//        /**
//         * 这里需要加发送短信的相关逻辑
//         */
//        SMSUtils.send(users.getPhoneNumber(), String.format(temp, code));
//        log.info("手机验证码为：{}，类型为：{}", code, "add_code");
//        String key = "add_code" + users.getPhoneNumber();
//        cloudRedisTemplate.set(key, code, 30 * 60);
//        return code;
//    }
//
//    @PostMapping("/public/checkTradeaaa")
//    @Access(value = false)
//    @ApiOperation(value = "checkTradeaaa")
//    public void checkTradeaaa() throws ParseException {
//        userService.distribution();
//    }
    @PostMapping("/public/TestPay")
    @Access(value = false)
    @ApiOperation(value = "TestPay")
    public String TestPay() throws Exception {
        return userService.TestPay();
    }

    @PostMapping("/public/aaaa")
    @Access(value = false)
    @ApiOperation(value = "aaaa")
    public void aaaa() throws Exception {
         userService.aaaa();
    }
}