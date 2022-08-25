// This file is auto-generated, don't edit it. Thanks.
package com.shitouren.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;

import java.util.HashMap;
import java.util.Map;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("code", "2222");
        System.out.println(jsonObj);
        String json_to_string = JSONObject.toJSONString(jsonObj);
        System.out.println("json_to_string :"+json_to_string);
        Map map=new HashMap();
        map.put("code","654");
        JSONObject object = new JSONObject(map);
        System.out.println("object :"+object);
        String json = object.toString();
        System.out.println("json :"+json);
        System.out.println(String.valueOf(map));
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5t9vgtTyJZtKPfkitqKu", "yDoFp6Bh4Sdb46FWwFJk5AHXP0i01n");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers("18192721125")
                .setTemplateCode("SMS_246810620")
                .setSignName("mojing")
                .setTemplateParam(json_to_string);
        client.sendSms(sendSmsRequest);

    }
}
