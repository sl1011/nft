package com.shitouren.core.controller;


import com.shitouren.core.annotation.Access;
import com.shitouren.core.service.WXAppPayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
@RequestMapping("apppay")
@Api("App支付")
public class PayAppController {


    @Autowired
    private WXAppPayService wxAppPayService;


    /**
     *
     * App支付统一下单
     * @param out_trade_no  订单号
     * @param total_fee      支付金额
     * @param type          0:预约订单1:专家保证金2：即时咨询订单
     * @return
     * @throws Exception
     */
    @PostMapping("order")
    public Map<String, String> order(@RequestParam(value = "type") String  type,@RequestParam(value = "out_trade_no") String out_trade_no,@RequestParam(value = "total_fee") String total_fee)throws Exception{
        return wxAppPayService.dounifiedOrder(type,out_trade_no,total_fee);
    }


    /**
     *   微信支付异步结果通知
     */
    @Access(value = false)
    @RequestMapping(value = "wxPayNotify", method = {RequestMethod.GET, RequestMethod.POST})
    public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.print("微信回调开始"+"\n");
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxAppPayService.payBack(resXml);
            System.out.print("微信回调结束"+"\n");
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }

}