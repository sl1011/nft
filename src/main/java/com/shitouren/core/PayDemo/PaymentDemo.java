package com.shitouren.core.PayDemo;

import com.alibaba.fastjson.JSON;
import com.huifu.adapay.core.exception.BaseAdaPayException;
import com.huifu.adapay.model.Checkout;
import com.huifu.adapay.model.Payment;
import com.huifu.adapay.model.PaymentConfirm;
import com.huifu.adapay.model.PaymentReverse;

import java.util.HashMap;
import java.util.Map;


/**
 * @author jane.zhao
 */
public class PaymentDemo {
    /**
     * 运行支付类接口
     *
     * @return paymentId
     * @throws Exception 异常
     */
    public static String executePaymentTest(String merchantKey, String app_id) throws Exception {
        PaymentDemo demo = new PaymentDemo();
        //支付接口
        Map<String, Object> payment = demo.executePayment(merchantKey, app_id);
        payment = demo.executeDelayPayment(merchantKey, app_id);

        // payment = demo.createReverse(merchantKey, "002112019102420545010033426145952493568",app_id);
        // payment = demo.queryReverse(merchantKey,"002112019102420545010033426145952493568", app_id);
        // payment = demo.queryReverseList(merchantKey,"002112019102420545010033426145952493568", app_id);
        // payment = demo.createConfirm(merchantKey,"002112019102420545010033426145952493568", app_id);
        // payment = demo.queryConfirm(merchantKey,"002112019102420545010033426145952493568", app_id);
        // payment = demo.queryConfirmList(merchantKey, "002112019102420545010033426145952493568",app_id);
        //payment = demo.executePagePayment(merchantKey, app_id);
        payment = demo.executeCheckOutPayment(merchantKey, app_id);
        System.out.println("钱包支付地址:" + payment.get("pay_url"));
        payment = demo.queryList(merchantKey, app_id);

        //支付查询接口
        demo.queryPayment(merchantKey, (String) payment.get("id"));
        // //关单接口
        demo.closePayment(merchantKey, (String) payment.get("id"));

        return (String) payment.get("id");
    }

    /**
     * 运行支付类接口
     *
     * @return paymentId
     * @throws Exception 异常
     */
    public static String executePaymentTest(String app_id) throws Exception {
        PaymentDemo demo = new PaymentDemo();
        //支付接口
        Map<String, Object> payment = demo.executePayment(app_id);
        // payment = demo.executeDelayPayment( app_id);

//        payment = demo.createReverse("002112019102420545010033426145952493568", app_id);
//        payment = demo.queryReverse("002112019102420545010033426145952493568", app_id);
//        payment = demo.queryReverseList("002112019102420545010033426145952493568", app_id);
//        payment = demo.createConfirm("002112019102420545010033426145952493568", app_id);
//        payment = demo.queryConfirm("002112019102420545010033426145952493568", app_id);
//        payment = demo.queryConfirmList("002112019102420545010033426145952493568", app_id);
//
//        payment = demo.executePagePayment(app_id);
//        payment = demo.executeCheckOutPayment(app_id);
//        payment = demo.queryCheckoutList("002112019102420545010033426145952493568",app_id);
//
//        System.out.println("钱包支付地址:" + payment.get("pay_url"));
//
//        //支付查询接口
//        demo.queryPayment((String) payment.get("id"));
//        // //关单接口
//        demo.closePayment((String) payment.get("id"));


        return (String) payment.get("id");
    }

    /**
     * 执行一个支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeDelayPayment(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", app_id);
        paymentParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        paymentParams.put("pay_channel", "alipay");
        paymentParams.put("pay_amt", "0.01");

        paymentParams.put("goods_title", "your goods title");
        paymentParams.put("goods_desc", "your goods desc");

        paymentParams.put("div_members", "");
        paymentParams.put("pay_mode", "delay");


        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            payment = Payment.create(paymentParams, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        String error_code = (String) payment.get("error_code");
        if (null != error_code) {
            String error_msg = (String) payment.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }


        return payment;
    }

    /**
     * 执行一个支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executePayment(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", app_id);
        paymentParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        paymentParams.put("pay_channel", "alipay");
        paymentParams.put("pay_amt", "0.01");

        paymentParams.put("goods_title", "your goods title");
        paymentParams.put("goods_desc", "your goods desc");


        paymentParams.put("div_members", "");

        Map<String, Object> deviceInfo = new HashMap<>(2);

        deviceInfo.put("device_ip", "127.0.0.1");
        deviceInfo.put("device_mac", "交易设备 MAC");
        deviceInfo.put("device_type", "1");
        deviceInfo.put("device_imei", "交易设备 IMEI");
        deviceInfo.put("device_imsi", "交易设备 IMSI");
        deviceInfo.put("device_iccId", "ICCID");
        deviceInfo.put("device_wifi_mac", "WIFIMAC");

        paymentParams.put("device_info", deviceInfo);

        Map<String, Object> expendParams = new HashMap<>(2);
        String openId = "";//微信授权获取
        expendParams.put("open_id", openId);
        expendParams.put("is_raw", "1");
        expendParams.put("callback_url", "绝对路径");
        expendParams.put("limit_pay", "1");

        paymentParams.put("expend", expendParams);

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {

            payment = Payment.create(paymentParams, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        String error_code = (String) payment.get("error_code");
        if (null != error_code) {
            System.out.println("创建支付返回参数：" + JSON.toJSONString(payment));

            String error_msg = (String) payment.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }

        return payment;
    }

    /**
     * 关闭一个支付交易
     *
     * @param paymentId 要关闭的支付id
     * @return 关闭的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> closePayment(String merchantKey, String paymentId) throws Exception {
        System.out.println("=======close payment begin=======");
        //关闭支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        //调用sdk方法，关闭支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("payment_id", paymentId);
        try {
            paymentParams.put("payment_id", paymentId);
            paymentParams.put("reason", "reason");
            paymentParams.put("expend", "expend");
            paymentParams.put("notify_url", "notify_url");
            System.out.println("关单请求参数：" + JSON.toJSONString(paymentId));
            payment = Payment.close(paymentParams, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }


        String error_code = (String) payment.get("error_code");
        if (null != error_code) {
            System.out.println("关单返回参数：" + JSON.toJSONString(payment));
            String error_msg = (String) payment.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }


        return payment;
    }


    /**
     * 查询一个支付交易
     *
     * @param paymentId 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryPayment(String merchantKey, String paymentId) throws Exception {
        System.out.println("=======query payment begin=======");
        //查询支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        //调用sdk方法，查询支付交易，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("支付查询请求参数：" + JSON.toJSONString(paymentId));
            payment = Payment.query(paymentId, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("支付查询返回参数：" + JSON.toJSONString(payment));

        String error_code = (String) payment.get("error_code");
        if (null == error_code) {
            String error_msg = (String) payment.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("=======query payment end=======");
        return payment;
    }


    /**
     * 创建撤销对象
     *
     * @param paymentId 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> createReverse(String merchantKey, String paymentId, String app_id) throws Exception {
        System.out.println("=======create Reverse  begin=======");

        Map<String, Object> reverse = new HashMap<>();

        reverse.put("payment_id", paymentId);
        reverse.put("app_id", app_id);
        reverse.put("order_no", "jsdk_reverse_" + System.currentTimeMillis());
        reverse.put("app_id", app_id);
        reverse.put("notify_url", "");
        reverse.put("reverse_amt", "0.01");
        reverse.put("reason", "reason");
        reverse.put("expand", "expend");
        reverse.put("device_info", "device_info");


        try {
            System.out.println("创建撤销对象" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.create(reverse, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("创建撤销对象返回参数：" + JSON.toJSONString(reverse));
        String error_code = (String) reverse.get("error_code");
        if (null == error_code) {
            String error_msg = (String) reverse.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("=======create Reverse  end=======");
        return reverse;
    }

    /**
     * 查询撤销对象
     *
     * @param reverse_id 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryReverse(String merchantKey, String reverse_id, String app_id) throws Exception {
        System.out.println("=======query Reverse  begin=======");

        Map<String, Object> reverse = new HashMap<>();

        reverse.put("reverse_id", reverse_id);

        try {
            System.out.println("查询撤销对象请求参数：" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.query(reverse, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        String error_code = (String) reverse.get("error_code");
        if (null == error_code) {
            String error_msg = (String) reverse.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("查询撤销对象返回参数：" + JSON.toJSONString(reverse));
        System.out.println("=======query Reverse  end=======");
        return reverse;
    }

    /**
     * 查询撤销对象列表
     *
     * @param
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryReverseList(String merchantKey, String payment_id, String app_id) throws Exception {
        System.out.println("=======query Reverse list  begin=======");

        Map<String, Object> reverse = new HashMap<>();
        reverse.put("payment_id", payment_id);
        reverse.put("app_id", app_id);
        reverse.put("page_index", "1");
        reverse.put("page_size", "20");
        reverse.put("created_gte", "1571913923");
        reverse.put("created_lte", "1571913924");


        try {
            System.out.println("查询撤销对象列表请求参数：" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.queryList(reverse, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        String error_code = (String) reverse.get("error_code");
        if (null == error_code) {
            String error_msg = (String) reverse.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("查询撤销对象列表返回参数：" + JSON.toJSONString(reverse));
        System.out.println("=======query Reverse list  end=======");
        return reverse;
    }


    /**
     * 创建确认对象
     *
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> createConfirm(String merchantKey, String paymentId, String app_id) throws Exception {
        System.out.println("=======create confirm  begin=======");

        Map<String, Object> confirm = new HashMap<>();

        confirm.put("payment_id", paymentId);
        confirm.put("order_no", "jsdk_confirm_" + System.currentTimeMillis());
        confirm.put("confirm_amt", "0.01");
        confirm.put("description", "description");
        confirm.put("div_members", "");

        try {
            System.out.println("创建确认对象" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.create(confirm, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        String error_code = (String) confirm.get("error_code");
        if (null == error_code) {
            String error_msg = (String) confirm.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("创建确认对象返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======create confirm  end=======");
        return confirm;
    }

    /**
     * 查询撤销对象
     *
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryConfirm(String merchantKey, String payment_confirm_id, String app_id) throws Exception {
        System.out.println("=======query confirm  begin=======");

        Map<String, Object> confirm = new HashMap<>();

        confirm.put("payment_confirm_id", payment_confirm_id);

        try {
            System.out.println("查询确认对象请求参数：" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.query(confirm, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        String error_code = (String) confirm.get("error_code");
        if (null == error_code) {
            String error_msg = (String) confirm.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("查询撤销对象返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======query confirm  end=======");
        return confirm;
    }

    /**
     * 查询确认对象列表
     *
     * @param
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryConfirmList(String merchantKey, String payment_id, String app_id) throws Exception {
        System.out.println("=======query confirm list  begin=======");

        Map<String, Object> confirm = new HashMap<>();
        confirm.put("payment_id", payment_id);
        confirm.put("app_id", app_id);
        confirm.put("page_index", "1");
        confirm.put("page_size", "20");
        confirm.put("created_gte", "1571913867");
        confirm.put("created_lte", "1571913923");


        try {
            System.out.println("查询撤销对象列表请求参数：" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.queryList(confirm, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        String error_code = (String) confirm.get("error_code");
        if (null == error_code) {
            String error_msg = (String) confirm.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }
        System.out.println("查询撤销对象列表返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======query Reverse list  end=======");
        return confirm;
    }

    /**
     * 执行一个支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeDelayPayment(String app_id) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);
        paymentParams.put("app_id", app_id);
        paymentParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        paymentParams.put("pay_channel", "alipay");
        paymentParams.put("pay_amt", "0.10");

        paymentParams.put("goods_title", "your goods title");
        paymentParams.put("goods_desc", "your goods desc");

        paymentParams.put("div_members", "");
        paymentParams.put("pay_mode", "delay");


        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("支付交易，请求参数：" + JSON.toJSONString(paymentParams));
            payment = Payment.create(paymentParams);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("支付交易，返回参数：" + JSON.toJSONString(payment));
        String error_code = (String) payment.get("error_code");
        if (null == error_code) {
            String error_msg = (String) payment.get("error_msg");
            System.out.println("error_code:" + error_code + "............." + error_msg);
        }

        System.out.println("=======execute payment end=======");
        return payment;
    }

    /**
     * 执行一个支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executePayment(String app_id) throws Exception {
        System.out.println("=======execute payment begin=======");
        //创建支付对象的参数，全部参数请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> paymentParams = new HashMap<>(10);

        paymentParams.put("adapay_connection_request_timeout", 500);
        paymentParams.put("adapay_connect_timeout", 500);
        paymentParams.put("adapay_socket_timeout", 500);
        paymentParams.put("adapay_region", "beijing");


        paymentParams.put("app_id", app_id);
        paymentParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        paymentParams.put("pay_channel", "alipay");
        paymentParams.put("pay_amt", "0.01");

        paymentParams.put("goods_title", "your goods title");
        paymentParams.put("goods_desc", "your goods desc");

        paymentParams.put("div_members", "");


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


    /**
     * 执行一个包支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executePagePayment(String app_id) throws Exception {
        System.out.println("=======execute pagePayment begin=======");
        //请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> params = new HashMap<>();
        params.put("order_no", System.currentTimeMillis());
        params.put("pay_amt", "0.01");
        params.put("currency", "cny");
        params.put("goods_title", "goods");
        params.put("goods_desc", "goodsdesc");
        params.put("app_id", app_id);
//        params.put("app_id", "app_f8b14a77-dc24-433b-864f-98a62209d6c4");
        params.put("callback_url", "https://www.baidu.com/");

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("钱包支付交易，请求参数：" + JSON.toJSONString(params));
            payment = Payment.createPage(params);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("钱包交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute pagePayment end=======");
        return payment;
    }

    /**
     * 执行一个包支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executePagePayment(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute pagePayment begin=======");
        //请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> params = new HashMap<>();
        params.put("order_no", System.currentTimeMillis());
        params.put("pay_amt", "0.01");
        params.put("currency", "cny");
        params.put("goods_title", "goods");
        params.put("goods_desc", "goodsdesc");
        params.put("app_id", "app_f8b14a77-dc24-433b-864f-98a62209d6c4");
        params.put("callback_url", "https://www.baidu.com/");

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("钱包支付交易，请求参数：" + JSON.toJSONString(params));
            payment = Payment.createPage(params, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("钱包交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute pagePayment end=======");
        return payment;
    }

    /**
     * 执行一个收银台支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCheckOutPayment(String app_id) throws Exception {
        System.out.println("=======execute quickPayment begin=======");
        //请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> params = new HashMap<>();
        params.put("order_no", "jdskjdd_200000014");
        params.put("member_id", "user_00013");
        params.put("pay_amt", "0.01");
        params.put("currency", "cny");
        params.put("goods_title", "商品标题");
        params.put("goods_desc", "商品描述");
        params.put("app_id", "app_7d87c043-aae3-4357-9b2c-269349a980d6");
        params.put("callback_url", "https://www.cnblogs.com/wanlibingfeng/p/7080581.html");

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("收银台支付交易，请求参数：" + JSON.toJSONString(params));
            payment = Checkout.create(params);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("收银台交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute quickPayment end=======");
        return payment;
    }

    /**
     * 执行一个收银台支付交易
     *
     * @return 创建的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCheckOutPayment(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute quickPayment begin=======");
        //请参考 https://docs.adapay.tech/api/index.html
        Map<String, Object> params = new HashMap<>();
        params.put("order_no", "jdskjdd_200000013");
        params.put("member_id", "user_00013");
        params.put("pay_amt", "0.01");
        params.put("currency", "cny");
        params.put("goods_title", "商品标题");
        params.put("goods_desc", "商品描述");
        params.put("app_id", "app_7d87c043-aae3-4357-9b2c-269349a980d6");
        params.put("callback_url", "https://www.cnblogs.com/wanlibingfeng/p/7080581.html");

        //调用sdk方法，创建支付，得到支付对象
        Map<String, Object> payment = new HashMap<>();
        try {
            System.out.println("收银台支付交易，请求参数：" + JSON.toJSONString(params));
            payment = Checkout.create(params, merchantKey);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }
        System.out.println("收银台交易，返回参数：" + JSON.toJSONString(payment));
        System.out.println("=======execute quickPayment end=======");
        return payment;
    }

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


    /**
     * 创建撤销对象
     *
     * @param paymentId 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> createReverse(String paymentId, String app_id) throws Exception {
        System.out.println("=======create Reverse  begin=======");

        Map<String, Object> reverse = new HashMap<>();

        reverse.put("payment_id", paymentId);
        reverse.put("app_id", app_id);
        reverse.put("order_no", "jsdk_reverse_" + System.currentTimeMillis());
        reverse.put("app_id", app_id);
        reverse.put("notify_url", app_id);
        reverse.put("reverse_amt", "0.01");
        reverse.put("reason", app_id);
        reverse.put("expand", app_id);
        reverse.put("device_info", app_id);


        try {
            System.out.println("创建撤销对象" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.create(reverse);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("创建撤销对象返回参数：" + JSON.toJSONString(reverse));
        System.out.println("=======create Reverse  end=======");
        return reverse;
    }

    /**
     * 查询撤销对象
     *
     * @param reverse_id 要查询的支付id
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryReverse(String reverse_id, String app_id) throws Exception {
        System.out.println("=======query Reverse  begin=======");

        Map<String, Object> reverse = new HashMap<>();

        reverse.put("reverse_id", reverse_id);

        try {
            System.out.println("查询撤销对象请求参数：" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.query(reverse);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询撤销对象返回参数：" + JSON.toJSONString(reverse));
        System.out.println("=======query Reverse  end=======");
        return reverse;
    }

    /**
     * 查询撤销对象列表
     *
     * @param
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryReverseList(String payment_id, String app_id) throws Exception {
        System.out.println("=======query Reverse list  begin=======");

        Map<String, Object> reverse = new HashMap<>();
        reverse.put("payment_id", payment_id);
        reverse.put("app_id", app_id);
        reverse.put("page_index", "1");
        reverse.put("page_size", "20");
        reverse.put("created_gte", "");
        reverse.put("created_lte", "");


        try {
            System.out.println("查询撤销对象列表请求参数：" + JSON.toJSONString(reverse));
            reverse = PaymentReverse.queryList(reverse);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询撤销对象列表返回参数：" + JSON.toJSONString(reverse));
        System.out.println("=======query Reverse list  end=======");
        return reverse;
    }


    /**
     * 创建确认对象
     *
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> createConfirm(String paymentId, String app_id) throws Exception {
        System.out.println("=======create confirm  begin=======");

        Map<String, Object> confirm = new HashMap<>();

        confirm.put("payment_id", paymentId);
        confirm.put("order_no", "jsdk_confirm_" + System.currentTimeMillis());
        confirm.put("confirm_amt", "0.01");
        confirm.put("description", "description");
        confirm.put("div_members", "");


        try {
            System.out.println("创建确认对象" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.create(confirm);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("创建确认对象返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======create confirm  end=======");
        return confirm;
    }

    /**
     * 查询撤销对象
     *
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryConfirm(String payment_confirm_id, String app_id) throws Exception {
        System.out.println("=======query confirm  begin=======");

        Map<String, Object> confirm = new HashMap<>();

        confirm.put("payment_confirm_id", payment_confirm_id);

        try {
            System.out.println("查询确认对象请求参数：" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.query(confirm);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询撤销对象返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======query confirm  end=======");
        return confirm;
    }

    /**
     * 查询确认对象列表
     *
     * @param
     * @return 查询的支付对象
     * @throws Exception 异常
     */
    public Map<String, Object> queryConfirmList(String payment_id, String app_id) throws Exception {
        System.out.println("=======query confirm list  begin=======");

        Map<String, Object> confirm = new HashMap<>();
        confirm.put("payment_id", payment_id);
        confirm.put("app_id", app_id);
        confirm.put("page_index", "1");
        confirm.put("page_size", "20");
        confirm.put("created_gte", "");
        confirm.put("created_lte", "");


        try {
            System.out.println("查询撤销对象列表请求参数：" + JSON.toJSONString(confirm));
            confirm = PaymentConfirm.queryList(confirm);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询撤销对象列表返回参数：" + JSON.toJSONString(confirm));
        System.out.println("=======query Reverse list  end=======");
        return confirm;
    }
    //add beg

    /**
     * 查询确认对象列表
     *
     * @param
     * @return 查询的支付对象列表
     * @throws Exception 异常
     */
    public Map<String, Object> queryList(String payment_id, String app_id) throws Exception {
        System.out.println("=======query list begin=======");

        Map<String, Object> querylist = new HashMap<>();
        querylist.put("payment_id", payment_id);
        querylist.put("app_id", app_id);
        querylist.put("page_index", "1");
        querylist.put("page_size", "20");
        querylist.put("created_gte", "");
        querylist.put("created_lte", "");


        try {
            System.out.println("查询支付对象列表请求参数：" + JSON.toJSONString(querylist));
            querylist = Payment.queryList(querylist);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询支付对象列表返回参数：" + JSON.toJSONString(querylist));
        System.out.println("=======query list  end=======");
        return querylist;
    }

    /**
     * 查询收银台对象列表
     *
     * @param
     * @return 查询的收银台对象列表
     * @throws Exception 异常
     */
    public Map<String, Object> queryCheckoutList(String payment_id, String app_id) throws Exception {
        System.out.println("=======query list begin=======");

        Map<String, Object> checklist = new HashMap<>();
        checklist.put("payment_id", payment_id);
        checklist.put("app_id", app_id);
        checklist.put("page_index", "1");
        checklist.put("page_size", "20");
        checklist.put("created_gte", "");
        checklist.put("created_lte", "");


        try {
            System.out.println("查询收银台对象列表请求参数：" + JSON.toJSONString(checklist));
            checklist = Checkout.queryList(checklist);
        } catch (BaseAdaPayException e) {
            e.printStackTrace();
        }

        System.out.println("查询收银台对象列表返回参数：" + JSON.toJSONString(checklist));
        System.out.println("=======query check list  end=======");
        return checklist;
    }
    //add end
}
