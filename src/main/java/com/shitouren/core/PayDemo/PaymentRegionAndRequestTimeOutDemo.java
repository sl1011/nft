package com.shitouren.core.PayDemo;

import com.alibaba.fastjson.JSON;
import com.huifu.adapay.core.exception.BaseAdaPayException;
import com.huifu.adapay.model.Payment;
import com.shitouren.core.utils.DateUtils;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author chunying.jia
 */
public class PaymentRegionAndRequestTimeOutDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("order_no", "xingchen_" + System.currentTimeMillis());//no
        paymentParams.put("app_id", "app_13265d93-269c-4b51-890d-6f5ed2bfe4a7");//控制台 主页面应用的app_id
        paymentParams.put("pay_channel", "alipay_wap");//支付类型
        paymentParams.put("pay_amt", "0.01");//金额
        paymentParams.put("goods_title", "商品标题");
        paymentParams.put("goods_desc", "商品描述");
        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("支付交易，请求参数：" + JSON.toJSONString(paymentParams));
            payment = Payment.create(paymentParams);

        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("支付交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute payment end=======");
        System.out.println("return=" + payment);
    }
//
//    public static String executePaymentTest(String app_id) throws Exception {
//        PaymentRegionAndRequestTimeOutDemo demo = new PaymentRegionAndRequestTimeOutDemo();
//        //支付接口
//        Map<String, Object> payment = demo.executePayment(app_id);
//        return (String) payment.get("id");
//    }

    public static Map executePaymentTest1(String app_id, BigDecimal pay_amt, String name, String cs) throws Exception {
        PaymentRegionAndRequestTimeOutDemo demo = new PaymentRegionAndRequestTimeOutDemo();
        //支付接口
        Map<String, Object> payment = demo.executePayment(app_id, pay_amt, name, cs);
        return payment;
    }

    /**
     * 执行一个支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executePayment(String app_id, BigDecimal pay_amt, String name, String cs) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);
        // 设置超时时间 单位毫秒 类型 int
        // adapay_connection_request_timeout 类型 int, 单位：毫秒 ms
        // 非必须, 默认 10000 指从连接池获取连接的 timeout
        paymentParams.put("adapay_connection_request_timeout", 1500);
        // adapay_connect_timeout 单位：毫秒 ms
        // 非必须, 默认 30000 指客户端和服务器建立连接的timeout
        paymentParams.put("adapay_connect_timeout", 1500);
        // adapay_socket_timeout 单位：毫秒 ms
        // 非必须, 默认 30000 指客户端从服务器读取数据的timeout，超出后会抛出SocketTimeOutException
        paymentParams.put("adapay_socket_timeout", 1500);
        // 设置网络区域
        // 非必须, 默认 shanghai, 如果要使用其他区域请提交工单备注服务器公网出口IP地址申请开通（如：beijing）
        paymentParams.put("adapay_region", "shanghai");
        paymentParams.put("app_id", app_id);//控制台 主页面应用的app_id
        paymentParams.put("order_no", DateUtils.getSecondTimestampTwo(new Date()) + System.currentTimeMillis());//no
        paymentParams.put("pay_channel", "alipay_wap");//支付类型
        paymentParams.put("pay_amt", pay_amt);//金额
        paymentParams.put("goods_title", name);
        paymentParams.put("goods_desc", "购买商品");
        paymentParams.put("description", cs);
        paymentParams.put("notify_url", "http://47.110.66.149:8200/callback");
        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("支付交易，请求参数：" + JSON.toJSONString(paymentParams));
            payment = Payment.create(paymentParams);

        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("支付交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute payment end=======");
        return payment;
    }

//        paymentParams.put("notify_url", "回调地址");

    /**
     * 关闭一个支付交易
     *
     * @param paymentId 要关闭的支付id
     * @return 关闭的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> closePayment(String paymentId) throws Exception {
        System.out.println("=======close payment begin=======");
        //关闭支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        //调用sdk方法，关闭支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            Map<String, Object> paymentParams = new HashMap<>(10);

            paymentParams.put("payment_id", paymentId);

            // 设置超时时间 单位毫秒 类型 int
            // adapay_connection_request_timeout 类型 int, 单位：毫秒 ms
            // 非必须, 默认 10000 指从连接池获取连接的 timeout
            paymentParams.put("adapay_connection_request_timeout", 1500);
            // adapay_connect_timeout 单位：毫秒 ms
            // 非必须, 默认 30000 指客户端和服务器建立连接的timeout
            paymentParams.put("adapay_connect_timeout", 1500);
            // adapay_socket_timeout 单位：毫秒 ms
            // 非必须, 默认 30000 指客户端从服务器读取数据的timeout，超出后会抛出SocketTimeOutException
            paymentParams.put("adapay_socket_timeout", 1500);

            // 设置网络区域
            // 非必须, 默认 shanghai, 如果要使用其他区域请提交工单备注服务器公网出口IP地址申请开通（如：beijing）
            paymentParams.put("adapay_region", "beijing");


            paymentParams.put("reason", "reason");
            paymentParams.put("expend", "expend");
            paymentParams.put("notify_url", "notify_url");
            System.out.println("关单请求参数：" + JSON.toJSONString(paymentId));
            payment = Payment.close(paymentParams);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("关单返回参数：" + JSON.toJSONString(payment));

        System.out.println("=======close payment end=======");
        return payment;
    }

    /**
     * 查询一个支付交易
     *
     * @param paymentId 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryPayment(String paymentId) throws Exception {
        System.out.println("=======query payment begin=======");
        //查询支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        //调用sdk方法，查询支付交易，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("支付查询请求参数：" + JSON.toJSONString(paymentId));
            payment = Payment.query(paymentId);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("支付查询返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======query payment end=======");
        return payment;
    }

}
