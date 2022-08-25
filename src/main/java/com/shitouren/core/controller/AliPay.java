package com.shitouren.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.huifu.adapay.core.AdapayCore;
import com.huifu.adapay.core.util.AdapaySign;
import com.shitouren.core.annotation.Access;
import com.shitouren.core.annotation.GetLoginUser;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.AliPayService;
import com.shitouren.core.service.DictService;
import com.shitouren.core.utils.AliPayConfig;
import com.shitouren.core.utils.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator.
 * Created on 2018/3/27.
 * Description: 支付宝支付
 */
@RestController
public class AliPay {

    private static final Logger logger = LoggerFactory.getLogger(AliPay.class);
    @Autowired(required = false)
    AliPayService aliPayService;
    @Autowired(required = false)
    DictService dictService;

//    @GetLoginUser
//    @Access
//    @ApiOperation("支付")
//    @PostMapping("/mine/smrzPay")
//    @ApiImplicitParam(name = "orderno", value = "订单id")
//    public String aliPay(SysUserParam param, String orderno) {
//        System.out.println("tradeid"+orderno);
//        return aliPayService.aliPay(param.getLogUserPid(), orderno);
//    }


//    @ResponseBody
//    @RequestMapping(value = "/pay/alipay/notify", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
//    public String aliPay_notify(HttpServletRequest request) {

    /**
     * 支付宝支付成功后.异步请求该接口
     *
     * @param request
     * @return
     * @throws IOException
     */
    @Access(value = false)
    @RequestMapping(value = "/pay/alipay/notify", method = RequestMethod.POST)
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getParameter("data");
        logger.info("接收到后台通知数据：" + data);
        Map maps = (Map) JSON.parse(data);
        Map<String, String> params = new HashMap<String, String>();
        for (Object map : maps.entrySet()) {
            params.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        Map maps1 = (Map) JSON.parse(maps.get("body").toString());
        Map<String, String> params1 = new HashMap<String, String>();
        for (Object map : maps1.entrySet()) {
            params1.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        String out_trade_no = params1.get("orderCode"); // 商户订单号
        logger.info("out_trade_no===>>>" + out_trade_no);
        aliPayService.updPay(out_trade_no, new BigDecimal(0));
        return "respCode=000000";
    }


    /**
     * 支付宝支付成功后.异步请求该接口
     *
     * @param request
     * @return
     * @throws IOException
     */
    @Access(value = false)
    @RequestMapping(value = "/pay/alipay/notify1", method = RequestMethod.POST)
    @ResponseBody
    public String notify1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getParameter("data");
        logger.info("接收到后台通知数据：" + data);
        Map maps = (Map) JSON.parse(data);
        Map<String, String> params = new HashMap<String, String>();
        for (Object map : maps.entrySet()) {
            params.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        Map maps1 = (Map) JSON.parse(maps.get("body").toString());
        Map<String, String> params1 = new HashMap<String, String>();
        for (Object map : maps1.entrySet()) {
            params1.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        String out_trade_no = params1.get("orderCode"); // 商户订单号
        logger.info("out_trade_no===>>>" + out_trade_no);
        aliPayService.updPay1(out_trade_no, new BigDecimal(0));
        return "respCode=000000";
    }

    /**
     * 支付宝支付成功后.异步请求该接口
     *
     * @param request
     * @return
     * @throws IOException
     */
    @Access(value = false)
    @RequestMapping(value = "/pay/alipay/rechargeupdPay", method = RequestMethod.POST)
    @ResponseBody
    public String rechargeupdPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getParameter("data");
        logger.info("接收到后台通知数据：" + data);
        Map maps = (Map) JSON.parse(data);
        Map<String, String> params = new HashMap<String, String>();
        for (Object map : maps.entrySet()) {
            params.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        Map maps1 = (Map) JSON.parse(maps.get("body").toString());
        Map<String, String> params1 = new HashMap<String, String>();
        for (Object map : maps1.entrySet()) {
            params1.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue().toString());
        }
        String out_trade_no = params1.get("orderCode"); // 商户订单号
        logger.info("out_trade_no===>>>" + out_trade_no);
        aliPayService.rechargeupdPay(out_trade_no, new BigDecimal(0));
        return "respCode=000000";
    }


    @Access(value = false)
    @PostMapping("/callback")
    @ResponseBody
    public String callback(HttpServletRequest request) {
        System.out.println("回调");
        try {
            //验签请参data
            String data = request.getParameter("data");
            //验签请参sign
            String sign = request.getParameter("sign");
            //验签标记
            boolean checkSign;
            //验签请参publicKey
            String publicKey = AdapayCore.PUBLIC_KEY;
            logger.info("验签请参：data={}sign={}");
            //验签
            checkSign = AdapaySign.verifySign(data, sign, publicKey);
            if (checkSign) {
                //验签成功逻辑
                System.out.println("成功返回数据data:" + data);
                //取出数据
                Map<String, String> requestParams = JSONObject.parseObject(data, Map.class);
                logger.info("out_trade_no===>>>" + requestParams.get("description"));
                logger.info("money===>>>" + requestParams.get("pay_amt"));
                String out_trade_no = requestParams.get("description");
                BigDecimal money = new BigDecimal(requestParams.get("pay_amt"));//支付金额
                /**
                 这里实现你的订单更新操作
                 */
                //根据订单号 更新 订单 支付状态
                System.out.println("这里实现你的订单更新操作");
                aliPayService.updPay(out_trade_no, money);

            } else {
                //验签失败逻辑
            }
        } catch (Exception e) {
            logger.info("异步回调开始，参数，request={}");
        }
        return "200";
    }






}