package com.shitouren.core.service;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.controller.WxCfg;
import com.shitouren.core.utils.StringUtil;
import com.shitouren.core.utils.WXConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.*;

@Service
public class WXAppPayServiceImpl implements WXAppPayService {

    private static final Logger logger = LoggerFactory.getLogger("WXAppPayServiceImpl");
    @Autowired
    private WxCfg wxCfg;
    @Autowired
    DictService dictService;

    @Autowired(required = false)
    AliPayService aliPayService;
    @Autowired(required = false)
    DictDao dictDao;
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    IssueDao issueDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    ApliaymhDao apliaymhDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;

    /**
     * 调用官方SDK 获取预支付订单等参数
     * @param type
     * @param out_trade_no
     * @param money
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> dounifiedOrder(String type,String out_trade_no,String money) throws Exception {
        Map<String, String> returnMap = new HashMap<>();
        InetAddress addr = InetAddress.getLocalHost();
        //支付参数

        WXConfigUtil config = new WXConfigUtil();
        WXPay wxpay = new WXPay(config);
        int themoney=new BigDecimal(money).multiply(new BigDecimal(100)).intValue();
        System.out.println("themoney+++"+themoney);
        //请求参数封装

        Map<String, String> data = new HashMap<>();
        data.put("appid", dictService.getValue("wxappid"));
        data.put("mch_id", dictService.getValue("wxmch_id"));
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("body", "订单支付");
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", themoney+"");
        data.put("spbill_create_ip", addr.getHostAddress()); //自己的服务器IP地址
        //data.put("notify_url", dictService.getValue("yqlj") + "/apppay/wxPayNotify");//异步通知地址（请注意必须是外网）
        data.put("notify_url", dictService.getValue("yqlj") + ":8200/apppay/wxPayNotify");//异步通知地址（请注意必须是外网）
        data.put("trade_type", "APP");//交易类型
        data.put("attach", type);//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
        String s = WXPayUtil.generateSignature(data, dictService.getValue("wxkey"));  //签名
        data.put("sign", s);//签名

        System.out.println(data);
        //try {
            //使用官方API请求预付订单
        System.out.println("111111111");
        String xml = "<xml>"
                + "<appid>" + data.get("appid") + "</appid>"
                + "<attach>" + data.get("attach") + "</attach>"
                + "<body><![CDATA[" + data.get("body") + "]]></body>"
                + "<mch_id>" + data.get("mch_id") + "</mch_id>"
                + "<nonce_str>" + data.get("nonce_str") + "</nonce_str>"
                + "<notify_url>" +data.get("notify_url") + "</notify_url>"
                + "<out_trade_no>" + data.get("out_trade_no") + "</out_trade_no>"
                + "<spbill_create_ip>" + data.get("spbill_create_ip") + "</spbill_create_ip>"
                + "<total_fee>" + data.get("total_fee") + "</total_fee>"
                + "<trade_type>" + data.get("trade_type") + "</trade_type>"
                + "<sign>" + data.get("sign") + "</sign>"
                + "</xml>";
        System.out.println(xml);
        //调用统一下单接口，并接收返回的结果
        String result = httpRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", xml);

        Map<String, String> response  =wxpay.processResponseXml(result);
        System.out.println(response);
           // Map<String, String> response = wxpay.unifiedOrder(data);
        System.out.println("3333");
            System.out.println(response);
            String returnCode = response.get("return_code");    //获取返回码
            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
            if (returnCode.equals("SUCCESS")) {
                //主要返回以下5个参数(必须按照顺序，否则APP报错：-1)
                String resultCode = response.get("result_code");
                returnMap.put("appid", response.get("appid"));
                returnMap.put("noncestr", response.get("nonce_str"));
                if ("SUCCESS".equals(resultCode)) {//resultCode 为SUCCESS，才会返回prepay_id和trade_type
                    returnMap.put("package","Sign=WXPay");
                    returnMap.put("partnerid", response.get("mch_id"));
                    returnMap.put("prepayid", response.get("prepay_id"));
                    returnMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//单位为秒
                    System.out.println("222222222");
                    String sign = WXPayUtil.generateSignature(returnMap, dictService.getValue("wxkey"));// 二次签名
                    returnMap.put("sign",sign); //签名
                    returnMap.put("trade_type", response.get("trade_type"));//获取预支付交易回话标志
                    return returnMap;
                } else {
                    //此时返回没有预付订单的数据
                    return returnMap;
                }
            } else {
                return returnMap;
            }
      /*  } catch (Exception e) {
            System.out.println(e);
            //系统等其他错误的时候
        }*/
       // return returnMap;

    }
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

   /* public static String sign(String text, String key, String input_charset) {
        text = text + "&key=" + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }*/

    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        // 创建SSLContext
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }





    /**
     *
     * @param notifyData 异步通知后的XML数据
     * @return
     */
    @Override
    public String payBack(String notifyData) {
        WXConfigUtil config = null;
        try {
            config = new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(notifyData);         // 调用官方SDK转换成map类型数据
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {//验证签名是否有效，有效则进一步处理

                String return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//商户订单号
                String orderNo=out_trade_no;

                if (return_code.equals("SUCCESS")) {
                    if (out_trade_no != null) {
                        // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户的订单状态从退款改成支付成功
                        // 注意特殊情况：微信服务端同样的通知可能会多次发送给商户系统，所以数据持久化之前需要检查是否已经处理过了，处理了直接返回成功标志
                        //业务数据持久化
                        System.err.println("支付成功"+"\n");
                        String attach = notifyMap.get("attach");//附加数据，用于区分是那张表订单
                        System.out.print("附加数据类型为：{}"+attach+"\n");
                        if(StringUtils.isEmpty(attach)){
                            logger.info("附加数据类型为：{}", attach);
                        }else{
                            //@TODO 预支付下单后回调的逻辑

                            if (orderNo.length() > 8) {
                                UserGrantExample userGrantExample = new UserGrantExample();
                                userGrantExample.createCriteria().andNumbernoEqualTo(orderNo);
                                List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
                                if (userGrants.size() > 0) {
                                    UserGrant userGrant = userGrants.get(0);
                                    //if (money.compareTo(userGrant.getBuyprice()) > -1) {
                                        if (userGrant.getGranttype() == 1 && userGrant.getCotype() == -1) {
                                            userGrant.setCotype(1);
                                            userGrantDao.updateByPrimaryKeySelective(userGrant);
                                        } else {
                                            if (userGrant.getType() == 2) {
                                                //二级市场
                                                userGrant.setType(3);
                                                userGrantDao.updateByPrimaryKeySelective(userGrant);
                                                com.shitouren.core.autogenerate.bean.Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                                                UserGrant newUg = new UserGrant();
                                                newUg.setUserid(userGrant.getOppositeuser());
                                                newUg.setCollid(userGrant.getCollid());
                                                newUg.setNumberno(userGrant.getNumberno());
                                                newUg.setBuyprice(userGrant.getSellprice());
                                                newUg.setHashs(userGrant.getHashs());
                                                newUg.setTokenid(userGrant.getTokenid());
                                                newUg.setType(0);
                                                newUg.setCreatetime(new Date());
                                                newUg.setGranttype(2);
                                                newUg.setCotype(2);
                                                userGrantDao.insertSelective(newUg);
                                                HideRecord hideRecord = new HideRecord();
                                                hideRecord.setUserid(userGrant.getOppositeuser());
                                                hideRecord.setImg(collection.getImg());
                                                hideRecord.setName(collection.getName());
                                                hideRecord.setPrice(userGrant.getSellprice());
                                                hideRecord.setNo(userGrant.getNumberno());
                                                if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
                                                    hideRecord.setNo(userGrant.getTruenumber());
                                                }
                                                hideRecord.setCreatetime(new Date());
                                                hideRecord.setMs("购买成功");
                                                hideRecord.setType(2);//0.黄的1.绿2.红
                                                hideRecordDao.insertSelective(hideRecord);

                                                //给对方加资产
                                                //总钱数
                                                BigDecimal zong = userGrant.getSellprice();
                                                //服务费手续费
                                                BigDecimal fwfsxf = new BigDecimal(dictService.getValue("fwfsxf")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);
                                                //服务费版税
                                                BigDecimal fwfbs = new BigDecimal(dictService.getValue("fwfbs")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);
                                                BigDecimal fwxsfkc = zong.multiply(fwfsxf).setScale(2, BigDecimal.ROUND_CEILING);
                                                BigDecimal fwfbskc = zong.multiply(fwfbs).setScale(2, BigDecimal.ROUND_CEILING);
                                                BigDecimal overb = zong.subtract(fwxsfkc.add(fwfbskc));
                                                Users users2 = userDao.selectByPrimaryKey(userGrant.getUserid());
                                                BigDecimal bigDecimal = users2.getBalance().add(overb);
                                                users2.setBalance(bigDecimal);
                                                userDao.updateByPrimaryKeySelective(users2);
                                                BalanceRecord balanceRecord1 = new BalanceRecord();
                                                balanceRecord1.setUserid(userGrant.getUserid());
                                                balanceRecord1.setName("出售获得");
                                                balanceRecord1.setCount(overb);
                                                balanceRecord1.setCreatetime(new Date());
                                                balanceRecordDao.insertSelective(balanceRecord1);
                                            }
                                        }
                                    //}
                                }
                            } else {//盲盒支付
                                ApliaymhExample apliaymhExample = new ApliaymhExample();
                                apliaymhExample.createCriteria().andIdEqualTo(Integer.valueOf(orderNo));
                                List<Apliaymh> apliaymhs = apliaymhDao.selectByExample(apliaymhExample);
                                if (apliaymhs.size() > 0) {
                                    Apliaymh apliaymh = apliaymhs.get(0);
                                    Blindbox blindbox = blindboxDao.selectByPrimaryKey(apliaymh.getBoxid());
                                    Collection collection = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
                                    //if (money.compareTo(collection.getPrice()) > -1) {
                                        if(apliaymh.getType()==0) {
                                            apliaymh.setType(1);
                                            apliaymhDao.updateByPrimaryKeySelective(apliaymh);
                                            Mybox mybox = new Mybox();
                                            mybox.setUserid(apliaymh.getUserid());
                                            mybox.setBoxid(apliaymh.getBoxid());
                                            myboxDao.insertSelective(mybox);
                                        }
                                    //}
                                }
                            }

                        }
                        logger.info("微信手机支付回调成功订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        logger.info("微信手机支付回调失败订单号:{}", out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }
                return xmlBack;
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                //失败的数据要不要存储？
                logger.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            logger.error("手机支付回调通知失败", e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }
}