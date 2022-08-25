package com.shitouren.core.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.utils.AliPayConfig;
import com.shitouren.core.utils.DateUtils;
import com.shitouren.core.utils.HttpRequest;
import com.shitouren.core.utils.StringUtil;
import lombok.SneakyThrows;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AliPayServiceImpl implements AliPayService {

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
    @Autowired(required = false)
    RechargelistDao rechargelistDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    ImglistDao imglistDao;


    @SneakyThrows
    @Override
    public String aliPay(int userId, String orderno1) {
//        int tradeid = Integer.parseInt(orderno1);
//        String leix = "";
//        String orderno = DateUtils.getSecondTimestampTwo(new Date()) + ((int) (Math.random() * 1000)) + "";
//        Payment payment = new Payment();
//        payment.setUserid(userId);
//        payment.setTradeno(orderno);
//        double bigDecimal1 = Double.parseDouble(dictService.getValue("hnzfje"));
//        double bigDecimal2 = Double.parseDouble(dictService.getValue("gmztzfje"));
//        double bigDecimal = 0;
//        if (tradeid == 0) {
//            bigDecimal = bigDecimal1;
//            payment.setMoney(new BigDecimal(bigDecimal1));
//            payment.setType(1);
//            payment.setTradeid(tradeid);
//        } else {
//            bigDecimal = bigDecimal2;
//            payment.setTradeid(tradeid);
//            payment.setMoney(new BigDecimal(bigDecimal2));
//            payment.setType(0);
//        }
//        //payment.setType(type);
//        payment.setCreattime(new Date());
//        //添加支付订单
//        paymentDao.insertSelective(payment);
//        String APPID = dictService.getValue("APPID");
//        String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
//        String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
//        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//        String aaa = "QUICK_WAP_WAY";
//        System.out.println(bigDecimal);
//        //该笔订单允许的最晚付款时间
//        String timeout = "30m";
//        //设置请求参数
//        String content = "{\"out_trade_no\":\"" + orderno + "\","
//                + "\"total_amount\":\"" + bigDecimal + "\","
//                + "\"subject\":\"" + leix + "支付" + "\","
//                + "\"timeout_express\":\"" + timeout + "\","
//                + "\"body\":\"" + leix + "支付" + "\","
//                + "\"product_code\":\"" + aaa + "\"}";
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//        alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
//        alipayRequest.setNotifyUrl(AliPayConfig.NOTIFY_URL);
//        alipayRequest.setBizContent(content);
//        //请求
//        return alipayClient.pageExecute(alipayRequest).getBody();
        return null;
    }


    @Override
    public void updPay(String orderNo, BigDecimal money1) {//

        System.out.println("这里实现你的订单更新操作-service");
            UserGrantExample userGrantExample=new UserGrantExample();
            userGrantExample.createCriteria().andNumbernoEqualTo(orderNo).andTypeEqualTo(0);
            List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
            if(userGrantList.size()>0){

                UserGrant userGrant =userGrantList.get(0);
                    int userid=userGrant.getUserid();
                Users users=userDao.selectByPrimaryKey(userid);
                Issue issue = issueDao.selectByPrimaryKey(userGrant.getIssueid());

                Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());

                BalanceRecord balanceRecord = new BalanceRecord();
                balanceRecord.setUserid(userid);
                balanceRecord.setName("购买" + collection.getName());
                balanceRecord.setCount(collection.getPrice().negate());
                balanceRecord.setCreatetime(new Date());
                balanceRecord.setState(1);
                balanceRecordDao.insertSelective(balanceRecord);
                System.out.println("支付");
                userGrant.setCotype(1);

                BigDecimal over = users.getBalance().subtract(userGrant.getBuyprice());
                //Users users1 = new Users();
                //users1.setUserId(userid);
                users.setBalance(over);
                users.setMoney(users.getMoney().add(new BigDecimal(5)));
                userDao.updateByPrimaryKeySelective(users);

                //奖励鲸币
                Moneyrecord moneyrecord11=new Moneyrecord();
                moneyrecord11.setPrice(new BigDecimal(5));
                moneyrecord11.setName("购买藏品");
                moneyrecord11.setTime(new Date());
                moneyrecord11.setUserid(users.getUserId());
                moneyrecordDao.insertSelective(moneyrecord11);

                Task task11=new Task();
                task11.setUserid(users.getUserId());
                task11.setMoney(new BigDecimal(5));
                task11.setCreattime(new Date());
                task11.setState(3);
                taskDao.insertSelective(task11);

                if(collection.getType()==1){
                    int a = issue.getSold() + 1;
                    System.out.println();
                    System.out.println("a :"+a);
                    issue.setSold(a);
                    issueDao.updateByPrimaryKeySelective(issue);

            /*if(issue.getSendwhite()==1){
                Users users1=userDao.selectByPrimaryKey(userid);
                users1.setWhitelist(1);
                userDao.updateByPrimaryKeySelective(users1);
            }*/
                    int b=collection.getSold()+1;
                    collection.setSold(b);
                    collectionDao.updateByPrimaryKeySelective(collection);
                    userGrant.setTruenumber(b+"");
                    userGrant.setType(3);
                    userGrant.setGranttype(2);
                    userGrant.setCotype(2);

                }else{
                    userGrant.setType(1);
                    userGrant.setGranttype(1);
                    userGrant.setCotype(1);
                }
                userGrant.setBuytime(new Date());

                userGrant.setPaytype(2);
                userGrantDao.updateByPrimaryKeySelective(userGrant);

                HideRecordExample hideRecordExample=new HideRecordExample();
                hideRecordExample.createCriteria().andUseridEqualTo(userid).andNoEqualTo(userGrant.getNumberno());
                List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);

                if(hideRecordList.size()>0){
                    HideRecord hideRecord=hideRecordList.get(0);
                    if(collection.getType()==1){
                        int bee=collection.getSold()+1;
                        String thetruenumber=bee+"/"+collection.getLimits();//编号
                        hideRecord.setThenum(thetruenumber);
                    }

                    hideRecord.setMs("完成付款");
                    hideRecord.setType(1);//0.黄的1.绿2.红
                    hideRecordDao.updateByPrimaryKeySelective(hideRecord);
                }

            }


    }

    @Override
    public void updPay1(String orderNo, BigDecimal money) {
        UserGrantExample userGrantExample=new UserGrantExample();
        userGrantExample.createCriteria().andNumbernoEqualTo(orderNo).andTypeEqualTo(0);
        List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
        if(userGrantList.size()>0){

            UserGrant userGrant =userGrantList.get(0);
            int userid=userGrant.getUserid();
            Users users=userDao.selectByPrimaryKey(userid);
            Issue issue = issueDao.selectByPrimaryKey(userGrant.getIssueid());

            Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());

            HideRecordExample hideRecordExample=new HideRecordExample();
            hideRecordExample.createCriteria().andNoEqualTo(userGrant.getNumberno());
            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
            HideRecord hideRecordnew=hideRecordList.get(0);


            BalanceRecord balanceRecord = new BalanceRecord();
            balanceRecord.setUserid(userid);
            balanceRecord.setName("购买" + collection.getName());
            balanceRecord.setCount(userGrant.getBuyprice().negate());
            balanceRecord.setCreatetime(new Date());
            balanceRecord.setState(1);
            balanceRecordDao.insertSelective(balanceRecord);



            BigDecimal over = users.getBalance().subtract(userGrant.getBuyprice());
            Users users1 = new Users();
            users1.setUserId(userid);
            users1.setBalance(over);
            userDao.updateByPrimaryKeySelective(users1);

            //原先订单
            UserGrant userGrant11=userGrantDao.selectByPrimaryKey(hideRecordnew.getUsergrantid());
            userGrant11.setType(9);
            userGrantDao.updateByPrimaryKeySelective(userGrant11);
            //出售的用户
            Users senduser=userDao.selectByPrimaryKey(userGrant11.getUserid());
            String thuserId=senduser.getPhoneNumber();
            String  thuserKey=senduser.getUserkey();
            String useraddress=senduser.getThaddress();


            Users buyuser=userDao.selectByPrimaryKey(userGrant.getUserid());


            String tianheappid=dictService.getValue("tianheappid");
            String tianheappkey=dictService.getValue("tianheappkey");

            com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject();
            //            Map<String, String> ingredients = new HashMap<String, String>();
            jsonObj.put("appId",tianheappid);
            jsonObj.put("appKey", tianheappkey);
            jsonObj.put("userId", thuserId);
            jsonObj.put("userKey", thuserKey);
            jsonObj.put("contractAddress", collection.getThcontractaddress());
            jsonObj.put("tokenId", userGrant11.getTokenid());
            jsonObj.put("from", useraddress);
            jsonObj.put("to", buyuser.getThaddress());
            //System.out.println(jsonObj);
            String url="https://api.tichain.tianhecloud.com/api/v2/nfr/transfer";

            String json_to_string = jsonObj.toJSONString(jsonObj);
            System.out.println("json_to_string :"+json_to_string);



            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            StringEntity postingString = null;// json传递
            try {
                postingString = new StringEntity(json_to_string);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = null;
            try {
                response = httpClient.execute(post);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String content = null;
            try {
                content = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String endata="";
            com.alibaba.fastjson.JSONObject codeobj = com.alibaba.fastjson.JSONObject.parseObject(content);
            int sendcode=Integer.parseInt(codeobj.get("code").toString());
            if(sendcode==0){
                String data11=codeobj.get("data").toString();
                com.alibaba.fastjson.JSONObject jsondate = com.alibaba.fastjson.JSONObject.parseObject(data11);
                endata=jsondate.get("transactionHash").toString();

            }


            //当前订单
            userGrant.setType(3);
            userGrant.setBuytime(new Date());
            userGrant.setTokenid(userGrant11.getTokenid());
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            //修改订单状态
            hideRecordnew.setMs("完成付款");
            hideRecordnew.setType(1);//0.黄的1.绿2.红
            hideRecordDao.updateByPrimaryKeySelective(hideRecordnew);

            //给对方加资产
            //总钱数
            BigDecimal zong = userGrant.getBuyprice();
            //服务费手续费
            BigDecimal fwfsxf = new BigDecimal(dictService.getValue("fwfsxf")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);

            BigDecimal fwxsfkc = zong.multiply(fwfsxf).setScale(2, BigDecimal.ROUND_CEILING);

            BigDecimal overb = zong.subtract(fwxsfkc);
            Users users2 = userDao.selectByPrimaryKey(userGrant11.getUserid());
            BigDecimal bigDecimal = users2.getBalance().add(overb);
            users2.setBalance(bigDecimal);
            userDao.updateByPrimaryKeySelective(users2);
            BalanceRecord balanceRecord1 = new BalanceRecord();
            balanceRecord1.setUserid(userGrant11.getUserid());
            balanceRecord1.setName("出售获得");
            balanceRecord1.setCount(overb);
            balanceRecord1.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord1);


            Imglist imglist=new Imglist();
            imglist.setAddress(endata);
            imglist.setUid(userGrant.getUserid());
            imglist.setPrice(userGrant.getBuyprice());
            imglist.setStarttime(new Date());
            imglistDao.insertSelective(imglist);

        }

    }

    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(DateUtils.getDateToStr(date, DateUtils.DATETIME_FORMAT));
//        date = DateUtils.getFrontMinute(date, -43200);
//        System.out.println(DateUtils.getDateToStr(DateUtils.getFrontMinute(new Date(), -43200), DateUtils.DATETIME_FORMAT));
//        date.setTime(date.getTime() + 30 * 24 * 60 * 60 * 1000);
//        System.out.println(DateUtils.getDateToStr(date, DateUtils.DATETIME_FORMAT));
        BigDecimal money = new BigDecimal(10);
        BigDecimal getBuyprice = new BigDecimal(11);
        System.out.println(money.compareTo(getBuyprice));

    }

    @Override
    public void rechargeupdPay(String orderNo, BigDecimal money) {//
        System.out.println("这里实现你的订单更新操作-service");
        RechargelistExample rechargelistExample=new RechargelistExample();
        rechargelistExample.createCriteria().andOrdernoEqualTo(orderNo).andStateEqualTo(0);
        List<Rechargelist>rechargelists=rechargelistDao.selectByExample(rechargelistExample);
        if(rechargelists.size()>0){
            Rechargelist rechargelist=rechargelists.get(0);
            rechargelist.setState(1);
            rechargelistDao.updateByPrimaryKeySelective(rechargelist);
            Users users=userDao.selectByPrimaryKey(rechargelist.getUserid());
            BigDecimal usermoney=users.getBalance().add(rechargelist.getMoney());
            users.setBalance(usermoney);
            userDao.updateByPrimaryKeySelective(users);
            BalanceRecord balanceRecord=new BalanceRecord();
            balanceRecord.setName("充值");
            balanceRecord.setCount(rechargelist.getMoney());
            balanceRecord.setUserid(rechargelist.getUserid());
            balanceRecord.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord);

        }

    }
}