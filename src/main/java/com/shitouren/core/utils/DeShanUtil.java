package com.shitouren.core.utils;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class DeShanUtil {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String createTime = sdf.format(calendar.getTime());
        calendar.add(Calendar.HOUR, 1);
        String endTime = sdf.format(calendar.getTime());
        String version = "10";
        //商户号
        String mer_no = "6888802045820";
        //商户key1
        String mer_key = "0CmLd4RtPoxpEtJThuZfwaMGOWX+VDDknbQvHr/ZWRRrqOX97FGGdex01MYGl1cMWw9k5BtNfe4=";
        //订单号
        UUID uuid = UUID.randomUUID();
        String mer_order_no = uuid.toString().replaceAll("-", "");
        //回调地址
        String notify_url = "https://www.baidu.com";
        String return_url = "https://www.baidu.com";
        //金额
        String order_amt = "1";
        //商品名称
        String goods_name = "测试";
        //支付扩展域
        //"userId":"用户在商户下唯一标识 1-10位",
        // "userName":"证件姓名",
        // "idCard":"18位身份证号码"
        String pay_extra = "{\"userId\":\"2\",\"userName\":\"陈立\",\"idCard\":\"530325199701180592\"}";
        //支付扩展域
        //云函数所需参数，cardNo："付款卡号，最大长度19位，填写卡号则直接跳转到银联H5支付页面，且卡号不允许修改；不填写卡号则跳转至杉德H5支付页面，卡号由用户手工输入，提交后再跳转到银联H5支付页面，且卡号允许修改；"
//        String pay_extra = "{\"cardNo\":\"666666\"}";
        //md5key
        String key = "bOmCNk+QWpuKz/1YRQxRxrp29SoRZfL+zsuUOngiL+x/CcZ+S7bbB/wq9B1yhqahstI0OXPG0PKYQfCOxGLo0UX1JQ875bDitcLXi7gGKa4KJEnnNFiGZ7Yqeaidyd5+arG3/R6ci9lixuz+UKFTiw==";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("accsplit_flag", "NO");
        map.put("create_ip", "113_200_86_142");
        map.put("create_time", createTime);
        map.put("mer_key", mer_key);
        map.put("mer_no", mer_no);
        map.put("mer_order_no", mer_order_no);
        map.put("notify_url", notify_url);
        map.put("order_amt", order_amt);
        map.put("pay_extra", pay_extra);       //H5云函数小程序不需要此参数
        map.put("return_url", return_url); //支付宝h5支付完成显示页面
        map.put("sign_type", "MD5");
        map.put("store_id", "000000");
        map.put("version", version);
        map.put("key", key);
        //map.put("expire_time",endTime);
        //map.put("goods_name",goods_name);
        //map.put("product_code","02010006");
        //map.put("clear_cycle","0");
        String signature = "";
        for (String s : map.keySet()) {
            if (!(map.get(s) == null || map.get(s).equals(""))) {
                signature += s + "=";
                signature += map.get(s) + "&";
            }
        }
        signature = signature.substring(0, signature.length() - 1);
        System.out.println("参与签名字符串：\n" + signature);
        String sign = MD5Utils.encode(signature).toUpperCase();
        System.out.println("签名串：\n" + sign);
        //拼接url
//        String url = "https://sandcash.mixienet.com.cn/pay/h5/unionpayh5?" +
        String url = "https://sandcash.mixienet.com.cn/pay/h5/quicktopup?" +
                //     云函数h5： applet  ；支付宝H5：alipay  ； 微信公众号H5：wechatpay   ；
                // 一键快捷：fastpayment   ；H5快捷 ：unionpayh5    ；支付宝扫码：alipaycode ;快捷充值:quicktopup
                //电子钱包【云账户】：cloud
                "version=" + version + "" +
                "&mer_no=" + mer_no + "" +
                "&mer_key=" + URLEncoder.encode(mer_key) + "" +
                "&mer_order_no=" + mer_order_no + "" +
                "&create_time=" + createTime + "" +
                "&expire_time=" + endTime + "" +  //endTime
                "&order_amt=1" +
                "&notify_url=" + URLEncoder.encode(notify_url) + "" +
                "&return_url=" + URLEncoder.encode(return_url) + "" +
                "&create_ip=113_200_86_142" +
                "&goods_name=" + URLEncoder.encode(goods_name) + "" +
                "&store_id=000000" +
                // 产品编码: 云函数h5：  02010006  ；支付宝H5：  02020002  ；微信公众号H5：02010002   ；
                //一键快捷：  05030001  ；H5快捷：  06030001   ；支付宝扫码：  02020005 ；快捷充值：  06030003
                //电子钱包【云账户】：开通账户并支付product_code应为：04010001；消费（C2C）product_code 为：04010003 ; 我的账户页面 product_code 为：00000001
                "&product_code=06030003" + "" +
                "&clear_cycle=3" +
                "&pay_extra=" + URLEncoder.encode(pay_extra) + "" +
                "&meta_option=%5B%7B%22s%22%3A%22Android%22,%22n%22%3A%22wxDemo%22,%22id%22%3A%22com.pay.paytypetest%22,%22sc%22%3A%22com.pay.paytypetest%22%7D%5D" +
                "&accsplit_flag=NO" +
                "&jump_scheme=" +
                "&sign_type=MD5" +
                "&sign=" + sign + "";
        System.out.println("最终链接：\n\n" + url);
    }

    public static String pay(String orderno, BigDecimal price, String name, String ip, String notifyurl, String returnurl, String userid, String username, String idcard) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String createTime = sdf.format(calendar.getTime());
        calendar.add(Calendar.HOUR, 1);
        String endTime = sdf.format(calendar.getTime());
        String version = "10";
        //商户号
        String mer_no = "6888802045820";
        //商户key1
        String mer_key = "0CmLd4RtPoxpEtJThuZfwaMGOWX+VDDknbQvHr/ZWRRrqOX97FGGdex01MYGl1cMWw9k5BtNfe4=";
        //订单号
        UUID uuid = UUID.randomUUID();
        String mer_order_no = orderno;
        //回调地址
        String notify_url = notifyurl;
        String return_url = returnurl;
        //金额
        String order_amt = price.toString();
        //商品名称
        String goods_name = name;

        //支付扩展域
        //"userId":"用户在商户下唯一标识 1-10位",
        // "userName":"证件姓名",
        // "idCard":"18位身份证号码"
        String pay_extra = "{\"userId\":\"" + userid + "\",\"userName\":\"" + username + "\",\"idCard\":\"" + idcard + "\"}";
        //支付扩展域
        //云函数所需参数，cardNo："付款卡号，最大长度19位，填写卡号则直接跳转到银联H5支付页面，且卡号不允许修改；不填写卡号则跳转至杉德H5支付页面，卡号由用户手工输入，提交后再跳转到银联H5支付页面，且卡号允许修改；"
//        String pay_extra = "{\"cardNo\":\"666666\"}";
        //md5key
        String key = "bOmCNk+QWpuKz/1YRQxRxrp29SoRZfL+zsuUOngiL+x/CcZ+S7bbB/wq9B1yhqahstI0OXPG0PKYQfCOxGLo0UX1JQ875bDitcLXi7gGKa4KJEnnNFiGZ7Yqeaidyd5+arG3/R6ci9lixuz+UKFTiw==";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("accsplit_flag", "NO");
        map.put("create_ip", ip);
        map.put("create_time", createTime);
        map.put("mer_key", mer_key);
        map.put("mer_no", mer_no);
        map.put("mer_order_no", mer_order_no);
        map.put("notify_url", notify_url);
        map.put("order_amt", order_amt);
        map.put("pay_extra", pay_extra);       //H5云函数小程序不需要此参数
        map.put("return_url", return_url); //支付宝h5支付完成显示页面
        map.put("sign_type", "MD5");
        map.put("store_id", "000000");
        map.put("version", version);
        map.put("key", key);
        //map.put("expire_time",endTime);
        //map.put("goods_name",goods_name);
        //map.put("product_code","02010006");
        //map.put("clear_cycle","0");
        String signature = "";
        for (String s : map.keySet()) {
            if (!(map.get(s) == null || map.get(s).equals(""))) {
                signature += s + "=";
                signature += map.get(s) + "&";
            }
        }
        signature = signature.substring(0, signature.length() - 1);
        System.out.println("参与签名字符串：\n" + signature);
        String sign = MD5Utils.encode(signature).toUpperCase();
        System.out.println("签名串：\n" + sign);
        //拼接url
//        String url = "https://sandcash.mixienet.com.cn/pay/h5/unionpayh5?" +
        String url = "https://sandcash.mixienet.com.cn/pay/h5/quicktopup?" +
                //     云函数h5： applet  ；支付宝H5：alipay  ； 微信公众号H5：wechatpay   ；
                // 一键快捷：fastpayment   ；H5快捷 ：unionpayh5    ；支付宝扫码：alipaycode ;快捷充值:quicktopup
                //电子钱包【云账户】：cloud
                "version=" + version + "" +
                "&mer_no=" + mer_no + "" +
                "&mer_key=" + URLEncoder.encode(mer_key) + "" +
                "&mer_order_no=" + mer_order_no + "" +
                "&create_time=" + createTime + "" +
                "&expire_time=" + endTime + "" +  //endTime
                "&order_amt=" + price +
                "&notify_url=" + URLEncoder.encode(notify_url) + "" +
                "&return_url=" + URLEncoder.encode(return_url) + "" +
                "&create_ip=" + ip +
                "&goods_name=" + URLEncoder.encode(goods_name) + "" +
                "&store_id=000000" +
                // 产品编码: 云函数h5：  02010006  ；支付宝H5：  02020002  ；微信公众号H5：02010002   ；
                //一键快捷：  05030001  ；H5快捷：  06030001   ；支付宝扫码：  02020005 ；快捷充值：  06030003
                //电子钱包【云账户】：开通账户并支付product_code应为：04010001；消费（C2C）product_code 为：04010003 ; 我的账户页面 product_code 为：00000001
                "&product_code=06030003" + "" +
                "&clear_cycle=3" +
                "&pay_extra=" + URLEncoder.encode(pay_extra) + "" +
                "&meta_option=%5B%7B%22s%22%3A%22Android%22,%22n%22%3A%22wxDemo%22,%22id%22%3A%22com.pay.paytypetest%22,%22sc%22%3A%22com.pay.paytypetest%22%7D%5D" +
                "&accsplit_flag=NO" +
                "&jump_scheme=" +
                "&sign_type=MD5" +
                "&sign=" + sign + "";
        System.out.println("最终链接：\n\n" + url);
        return url;
    }

}
