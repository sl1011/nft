package com.shitouren.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.pingplusplus.model.Recharge;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.EumUser;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.controller.DDCSdkClient;
import com.shitouren.core.controller.HttpUtils;
import com.shitouren.core.controller.SignEventTest;
import com.shitouren.core.utils.*;
import fosun.sumpay.merchant.integration.core.request.outer.*;
import fosun.sumpay.merchant.integration.core.service.SumpayService;
import fosun.sumpay.merchant.integration.core.service.SumpayServiceImpl;
import fosun.sumpay.merchant.integration.core.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.CharSet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


//import fosun.sumpay.merchant.integration.core.request.Request;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@Service
@Transactional(rollbackFor = Exception.class)
public class MineServiceImpl implements MineService {

    @Autowired(required = false)
    UsersDao usersDao;
    @Autowired(required = false)
    MessageDao messageDao;
    @Autowired(required = false)
    WithdrawalDao withdrawalDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    IssueDao issueDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    FragmentDao fragmentDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    CancelRecordDao cancelRecordDao;
    @Autowired(required = false)
    ChatDao chatDao;
    @Autowired(required = false)
    RealnameDao realnameDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    PromptDao promptDao;
    @Autowired(required = false)
    BankDao bankDao;
    @Autowired
    MineService mineService;
    @Autowired(required = false)
    RechargelistDao rechargelistDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    DrawDao drawDao;
    @Autowired(required = false)
    DrawrecordDao drawrecordDao;
    @Autowired(required = false)
    TicketDao ticketDao;
    @Autowired(required = false)
    MessagerecordDao messagerecordDao;
    @Autowired(required = false)
    GiveUserDao giveUserDao;
    @Autowired(required = false)
    BlindboxtrueDao blindboxtrueDao;
    @Autowired
    HomeService homeService;

    private static final String TEST_URL = "https://entrance.sumpay.cn/gateway.htm";
    private static final String MER_ID = "200102158670";
    private static final String SUB_MERID = "https://test.sumpay.cn/entrance/gateway.htm";


    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;


    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("800000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();

    DDC1155Service ddc1155Service = client.getDDC1155Service();

    /**
     * 我的信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getMineInfo(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("avatar", ImgEnum.img.getUrl() + user.getHeadPrtraits());//头像
        map.put("nickname", user.getNickName());//昵称
        map.put("id",user.getUserId());
        map.put("address",user.getAddress());
        map.put("realnametype",user.getRealnametype());
        map.put("phone", phoneMask(user.getPhoneNumber()));//电话
        map.put("phone1", user.getPhoneNumber());//电话
        map.put("freezemoney", user.getFreezemoney());//电话
        map.put("alipay", user.getAlipay());//电话
        map.put("alipayname", user.getAlipayname());//电话
        map.put("money", user.getMoney());//电话

        TicketExample ticketExample=new TicketExample();
        ticketExample.createCriteria().andUseridEqualTo(userId).andStateEqualTo(1);
        List<Ticket>ticketList=ticketDao.selectByExample(ticketExample);
        map.put("number", ticketList.size());//电话

        //查询message总数
        int notread=0;
        List<Message>messageList=messageDao.selectByExample(new MessageExample());
        if(messageList.size()>0){
            for (Message message : messageList) {
                MessagerecordExample messagerecordExample=new MessagerecordExample();
                messagerecordExample.createCriteria().andUseridEqualTo(userId).andMessageidEqualTo(message.getId());
                List<Messagerecord>messagerecordList=messagerecordDao.selectByExample(messagerecordExample);
                if(messagerecordList.size()==0){
                    notread=notread+1;
                }
            }
        }
       // int total=messageList.size();
        //已读总数


        //int read=messagerecordList.size();

        map.put("numbertwo", notread);//电话
        int a = 0;
        if (user.getPrivatekey() != null) {
            a = 1;
        }
        map.put("key", a);
        return map;
    }

    @Override
    public Map<String, Object> Acsecurity(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("phone", phoneMask(user.getPhoneNumber()));//电话
        map.put("phone1", user.getPhoneNumber());//电话
        if (StringUtil.getLength(user.getAlipay()) > 0) {
            map.put("alipay", phoneMask(user.getAlipay()));//头像
        } else {
            map.put("alipay", "");//头像
        }
        if (user.getRealnametype() == 0) {
            map.put("isRealName", 0);//昵称
        } else {
            map.put("isRealName", 1);//昵称
        }
        return map;
    }

    @Override
    public void usedPhone(Integer userId, String code) {
        Users user = usersDao.selectByPrimaryKey(userId);
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + user.getPhoneNumber();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
    }

    @Override
    public void updPhone(Integer userId, String phone, String code) {
        Users user = usersDao.selectByPrimaryKey(userId);
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + phone;
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        Users users = new Users();
        users.setUserId(userId);
        users.setPhoneNumber(phone);
        usersDao.updateByPrimaryKeySelective(users);
    }

    @Override
    public void aplPhone(Integer userId, String phone, String name,String code) {
        Users users = usersDao.selectByPrimaryKey(userId);
//        if (StringUtil.isEmpty(users.getTradePassWord())) {
//            throw new CloudException(ExceptionConstant.请先设置操作密码);
//        }
//        if (!users.getTradePassWord().equals(MD5Util.MD5Encode(password))) {
//            throw new CloudException(ExceptionConstant.操作密码错误);
//        }
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + users.getPhoneNumber();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }

        if (StringUtil.isEmpty(users.getAlipay())) {
            users.setAlipay(phone);
            users.setAlipayname(name);
            usersDao.updateByPrimaryKeySelective(users);
        } else {

            users.setAlipay(phone);
            users.setAlipayname(name);
            users.setSztime(new Date());
            usersDao.updateByPrimaryKeySelective(users);
        }
    }

    @Override
    public void addtradepassword(SysUserParam sysUserParam, String TradePassWord) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        user.setTradePassWord(MD5Util.MD5Encode(TradePassWord));
        usersDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改交易密码
     *
     * @param sysUserParam
     * @param phone
     * @param code
     * @param newTradePassWord
     * @param newTradePassWord2
     */
    public void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(phone);
        List<Users> userList = usersDao.selectByExample(query);
        if (!ListUtil.isValidateList(userList)) {
            throw new CloudException(ExceptionConstant.账户名不存在);
        }
        if (!newTradePassWord.equals(newTradePassWord2)) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        if (!phone.equals(user.getPhoneNumber())) {
            throw new CloudException(ExceptionConstant.手机号与账号绑定手机号不一致);
        }
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.交易密码.getValue() + phone;
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        user.setPasswd(MD5Util.MD5Encode(newTradePassWord));
        usersDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public String setup(Integer userId) {
        String a = "0";
        Users user = usersDao.selectByPrimaryKey(userId);
        if (!StringUtil.isEmpty(user.getTradePassWord())) {
            a = "1";
        }
        return a;
    }

    /**
     * 用户身份证号码的打码隐藏加星号加*
     *
     * @return 处理完成的身份证
     */
    public static String idCardMask(String idCardNum) {
        String res = "";
        if (!StringUtil.isEmpty(idCardNum)) {
            StringBuilder stringBuilder = new StringBuilder(idCardNum);
            res = stringBuilder.replace(6, 14, "********").toString();
        }
        return res;
    }

    /**
     * 用户电话号码的打码隐藏加星号加*
     *
     * @return 处理完成的身份证
     */
    public static String phoneMask(String phone) {
        String res = phone;
        if (!StringUtil.isEmpty(phone)) {
            if (StringUtil.getLength(phone) > 10) {
                StringBuilder stringBuilder = new StringBuilder(phone);
                res = stringBuilder.replace(3, 7, "****").toString();
            }
        }
        return res;
    }

    @Override
    public Map personal(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("avatar", ImgEnum.img.getUrl() + user.getHeadPrtraits());//头像
        map.put("nickname", user.getNickName());//昵称
        map.put("autograph", user.getAutograph());//个性签名
        return map;
    }

    @Override
    public void updateUserInfo(Integer userId, String avatar, String nickname, String autograph) {
        Users user = usersDao.selectByPrimaryKey(userId);
        if (!StringUtil.isEmpty(avatar)) {
            user.setHeadPrtraits(avatar);
        }
        if (!StringUtil.isEmpty(nickname)) {
            user.setNickName(nickname);
        }
        if (!StringUtil.isEmpty(autograph)) {
            user.setAutograph(autograph);
        }
        usersDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public List message(int userid) {
        List list = new ArrayList();
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("createtime desc");
        List<Message> messages = messageDao.selectByExample(messageExample);
        for (Message message : messages) {


            Map map = new HashMap();
            //查询有没有读过
            MessagerecordExample messagerecordExample=new MessagerecordExample();
            messagerecordExample.createCriteria().andUseridEqualTo(userid).andMessageidEqualTo(message.getId());
            List<Messagerecord>messagerecordList=messagerecordDao.selectByExample(messagerecordExample);
            if(messagerecordList.size()>0){
                map.put("type",1);
            }else{
                map.put("type", 0);
            }

            map.put("id", message.getId());
            map.put("title", message.getTitle());
            map.put("createtime", DateUtils.getDateToStr(message.getCreatetime(), DateUtils.TIME_FORMAT1));
            map.put("introduce", message.getIntroduce());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> messagedetails(int userid,Integer id) {
        Message message = messageDao.selectByPrimaryKey(id);


        MessagerecordExample messagerecordExample=new MessagerecordExample();
        messagerecordExample.createCriteria().andUseridEqualTo(userid).andMessageidEqualTo(message.getId());
        List<Messagerecord>messagerecordList=messagerecordDao.selectByExample(messagerecordExample);
        if(messagerecordList.size()==0){
            Messagerecord messagerecord=new Messagerecord();
            messagerecord.setMessageid(id);
            messagerecord.setUserid(userid);
            messagerecord.setCreattime(new Date());
            messagerecordDao.insertSelective(messagerecord);
        }


        Map map = new HashMap();
        map.put("id", message.getId());
        map.put("title", message.getTitle());
        map.put("createtime", DateUtils.getDateToStr(message.getCreatetime(), DateUtils.TIME_FORMAT1));
        map.put("notice", message.getNotice());
        return map;
    }

    @Override
    public BigDecimal Myassets(Integer userId) {
        Users user = usersDao.selectByPrimaryKey(userId);
        return user.getBalance();
    }

    @Override
    public Map realname(int userid) {
        Users user = usersDao.selectByPrimaryKey(userid);
        Map map = new HashMap();
        map.put("name", replaceNameX(user.getRealname()));
        map.put("no", idEncrypt(user.getRealno()));
        return map;
    }

    //身份证前三后四脱敏
    public static String idEncrypt(String idcard) {
        if (StringUtil.isEmpty(idcard) || (idcard.length() < 8)) {
            return idcard;
        }
        return idcard.replaceAll("(?<=\\w{1})\\w(?=\\w{1})", "*");
    }

    public static String replaceNameX(String str) {
        // 获取姓名长度
        String custName = str;
        int length = custName.length();

        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == length) continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Override
    public void withdrawal(int userId, BigDecimal count ) {
       // System.out.println("pass=" + pass);
        Users user = usersDao.selectByPrimaryKey(userId);
        if (count.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.数量有误);
        }
        if (user.getBalance().compareTo(count) == -1) {
            throw new CloudException(ExceptionConstant.资产不足);
        }
       /* if (StringUtil.isEmpty(user.getTradePassWord())) {
            throw new CloudException(ExceptionConstant.请先设置操作密码);
        }*/
        /*System.out.println("user.getTradePassWord()=" + user.getTradePassWord());
        System.out.println("MD5Util.MD5Encode(pass)=" + MD5Util.MD5Encode(pass));
        if (!user.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
            throw new CloudException(ExceptionConstant.操作密码错误);
        }*/
        if (StringUtil.isEmpty(user.getAlipay()) || StringUtil.isEmpty(user.getAlipayname())) {
            throw new CloudException(ExceptionConstant.请先设置支付宝);
        }
        BigDecimal balance = user.getBalance().subtract(count);
        if (balance.compareTo(new BigDecimal(0)) == -1) {
            balance = new BigDecimal(0);
        }
        user.setBalance(balance);
        usersDao.updateByPrimaryKeySelective(user);
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setUserid(userId);
        balanceRecord.setName("提现");
        balanceRecord.setCount(count.negate());
        balanceRecord.setCreatetime(new Date());
        balanceRecordDao.insertSelective(balanceRecord);
        //余额扣除记录
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setUserid(userId);
        withdrawal.setCount(count);
        withdrawal.setCreatetime(new Date());
        withdrawal.setAlipay(user.getAlipay());
        withdrawal.setAlipayname(user.getAlipayname());
        withdrawalDao.insertSelective(withdrawal);
    }

    @Override
    public List withdrawalrecode(int userId) {
        List list = new ArrayList();
        WithdrawalExample withdrawalExample = new WithdrawalExample();
        withdrawalExample.createCriteria().andUseridEqualTo(userId);
        withdrawalExample.setOrderByClause("createtime desc");
        List<Withdrawal> withdrawals = withdrawalDao.selectByExample(withdrawalExample);
        for (Withdrawal withdrawal : withdrawals) {
            Map map = new HashMap();
            map.put("name", "提现");
            map.put("count", withdrawal.getCount());
            map.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
            list.add(map);
        }
        return list;
    }

    @Override
    public Map balancerecords(int userId) {
        Map totalmap=new HashMap();
        List list = new ArrayList();
        List list1 = new ArrayList();//支出
        List list2 = new ArrayList();//收入
        List list3 = new ArrayList();//充值
        List list4 = new ArrayList();//提现
        BalanceRecordExample withdrawalExample = new BalanceRecordExample();
        withdrawalExample.createCriteria().andUseridEqualTo(userId);
        withdrawalExample.setOrderByClause("createtime desc");
        List<BalanceRecord> withdrawals = balanceRecordDao.selectByExample(withdrawalExample);
        if(withdrawals.size()>0) {
            for (BalanceRecord withdrawal : withdrawals) {
                Map map = new HashMap();
                map.put("name", withdrawal.getName());
                map.put("count", withdrawal.getCount());
                map.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
                list.add(map);
                if (withdrawal.getCount().compareTo(new BigDecimal(0)) == -1) {
                    //小于0
                    Map map1 = new HashMap();
                    map1.put("name", withdrawal.getName());
                    map1.put("count", withdrawal.getCount());
                    map1.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
                    list1.add(map1);
                } else {
                    Map map2 = new HashMap();
                    map2.put("name", withdrawal.getName());
                    map2.put("count", withdrawal.getCount());
                    map2.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
                    list2.add(map2);
                }
                if (withdrawal.getName().equals("提现")) {
                    Map map3 = new HashMap();
                    map3.put("name", withdrawal.getName());
                    map3.put("count", withdrawal.getCount());
                    map3.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
                    list3.add(map3);
                }
                if (withdrawal.getName().equals("充值")) {
                    Map map4 = new HashMap();
                    map4.put("name", withdrawal.getName());
                    map4.put("count", withdrawal.getCount());
                    map4.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(), DateUtils.DATETIME_FORMAT));
                    list4.add(map4);
                }

            }
        }
        totalmap.put("list",list);
        totalmap.put("list1",list1);
        totalmap.put("list2",list2);
        totalmap.put("list3",list4);
        totalmap.put("list4",list3);
        BigDecimal balance=new BigDecimal(0);
        BigDecimal frozen=new BigDecimal(0);
        Users users=usersDao.selectByPrimaryKey(userId);
        if(users!=null){
            balance=users.getBalance();
            frozen=users.getFreezemoney();
        }
        totalmap.put("balance",balance);
        totalmap.put("frozen",frozen);
        return totalmap;
    }

    @Override
    public List Collectionrecords(int userId) {
        List list = new ArrayList();
        HideRecordExample hideRecordExample = new HideRecordExample();
        hideRecordExample.createCriteria().andUseridEqualTo(userId);
        hideRecordExample.setOrderByClause("createtime desc");
        List<HideRecord> withdrawals = hideRecordDao.selectByExample(hideRecordExample);
        for (HideRecord hideRecord : withdrawals) {
            Map map = new HashMap();
            map.put("name", hideRecord.getName());
            map.put("img", ImgEnum.img.getUrl() + hideRecord.getImg());
//            map.put("no", hideRecord.getNo());
//            map.put("no", DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.DATETIME_FORMAT));
            map.put("type", hideRecord.getType());
            map.put("ms", hideRecord.getMs());
            map.put("describe", DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.DATETIME_FORMAT));
//            switch (hideRecord.getType()) {//0.黄的1.绿2.红
//                case 0:
//                    map.put("describe", DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.DATETIME_FORMAT));
//                    break;
//                case 1:
//                    map.put("describe", hideRecord.getPrice());
//                    break;
//                case 2:
//                    map.put("describe", hideRecord.getPrice());
//                    break;
//            }
            list.add(map);
        }
        return list;
    }

   /* public static void main(String[] args) {
        String host = "https://zidv2.market.alicloudapi.com";
        String path = "/idcheck/Post";
        String method = "POST";
        String appcode = "你自己的AppCode";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cardNo", "330329199001021122");
        bodys.put("realName", "李四");


        try {
            *//**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             *//*
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


        public static void main(String[] args) throws IOException {
            String url = "https://eid.shumaidata.com/eid/check";
            String appCode = "你的AppCode";

            Map<String, String> params = new HashMap<>();
            params.put("idcard", "42112******82416");
            params.put("name", "张*");
            String result = postForm(appCode, url, params);
            System.out.println(result);
        }

        /**
         * 用到的HTTP工具包：okhttp 3.13.1
         * <dependency>
         * <groupId>com.squareup.okhttp3</groupId>
         * <artifactId>okhttp</artifactId>
         * <version>3.13.1</version>
         * </dependency>
         */
        public static String postForm(String appCode, String url, Map<String, String> params) throws IOException {
            OkHttpClient client = new OkHttpClient.Builder().build();
            FormBody.Builder formbuilder = new FormBody.Builder();
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                formbuilder.add(key, params.get(key));
            }
            FormBody body = formbuilder.build();
            Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
            Response response = client.newCall(request).execute();
            System.out.println("返回状态码" + response.code() + ",message:" + response.message());
            String result = response.body().string();
            return result;
        }



    @SneakyThrows
    public Map addRealName(RealNameParam RealNameParam, Integer userId)  {
        Users users2323=usersDao.selectByPrimaryKey(userId);
        if(users2323.getRealnametype()==2){
            throw new CloudException(ExceptionConstant.该账号已实名);
        }

        UsersExample usersExample1111 = new UsersExample();
        usersExample1111.createCriteria().andRealnameEqualTo(RealNameParam.getName()).andRealnoEqualTo(RealNameParam.getIdCardCode()).andRealnametypeEqualTo(2);
        List<Users> usesrs2222 = usersDao.selectByExample(usersExample1111);
        if (usesrs2222.size() > 0) {
            throw new CloudException(ExceptionConstant.该实名信息已达认证上限);
        }


        Map map=new HashMap();

        String url = "https://eid.shumaidata.com/eid/check";
        String appCode = "775104535da44b70936ee048575ea14f";

        Map<String, String> params = new HashMap<>();
        params.put("idcard",RealNameParam.getIdCardCode());
        params.put("name", RealNameParam.getName());
        String code = postForm(appCode, url, params);
        System.out.println(code);
        int code111 =Integer.parseInt(JSONObject.parseObject(code).getString("code"));
        System.out.println("code11 :"+code111);
        if(code111==0){


                    map.put("type",1);
                    map.put("msg","实名成功");
                    Users users=usersDao.selectByPrimaryKey(userId);
                    users.setMoney(users.getMoney().add(new BigDecimal(5)));
                    users.setRealnametype(2);
                    users.setRealno(RealNameParam.getIdCardCode());
                    users.setRealname(RealNameParam.getName());
                    usersDao.updateByPrimaryKeySelective(users);

                    //奖励鲸币
                    Moneyrecord moneyrecord=new Moneyrecord();
                    moneyrecord.setPrice(new BigDecimal(5));
                    moneyrecord.setName("实名赠送");
                    moneyrecord.setTime(new Date());
                    moneyrecord.setUserid(userId);
                    moneyrecordDao.insertSelective(moneyrecord);

                    Task task=new Task();
                    task.setUserid(userId);
                    task.setMoney(new BigDecimal(5));
                    task.setCreattime(new Date());
                    task.setState(1);
                    taskDao.insertSelective(task);

                    //查询上架
                    if(users.getInvitationId()!=null){
                        Users users1=usersDao.selectByPrimaryKey(users.getInvitationId());
                        //查询邀请的总数
                        TaskExample taskExample=new TaskExample();
                        taskExample.createCriteria().andUseridEqualTo(users1.getUserId()).andStateEqualTo(3);
                        List<Task>taskList=taskDao.selectByExample(taskExample);
                        if(taskList.size()<100){
                            //可以有奖励
                            users1.setMoney(users1.getMoney().add(new BigDecimal(10)));

                            //奖励鲸币
                            Moneyrecord moneyrecord11=new Moneyrecord();
                            moneyrecord11.setPrice(new BigDecimal(10));
                            moneyrecord11.setName("邀请赠送");
                            moneyrecord11.setTime(new Date());
                            moneyrecord11.setUserid(users1.getUserId());
                            moneyrecordDao.insertSelective(moneyrecord11);

                            Task task11=new Task();
                            task11.setUserid(users1.getUserId());
                            task11.setMoney(new BigDecimal(10));
                            task11.setCreattime(new Date());
                            task11.setState(2);
                            taskDao.insertSelective(task11);

                        }
                        users1.setRealnamenum(users1.getRealnamenum()+1);
                        usersDao.updateByPrimaryKeySelective(users1);


                        //查询有没有开启活动
                        int sfzs=Integer.parseInt(dictService.getValue("sfzs"));
                        if(sfzs==1){
                            //查询有没有实名
                            if(users1.getRealnametype()==2){
                                //获得开启
                                Date nowdate=new Date();
                                GiveUserExample giveUserExample=new GiveUserExample();
                                giveUserExample.createCriteria().andCreattimeLessThan(nowdate).andEndtimeGreaterThan(nowdate);
                                List<GiveUser>giveUserList=giveUserDao.selectByExample(giveUserExample);
                                if(giveUserList.size()>0){
                                    GiveUser giveUser=giveUserList.get(0);
                                    if(giveUser.getMoney().compareTo(new BigDecimal(0))==1){
                                        Users users2=usersDao.selectByPrimaryKey(users1.getUserId());
                                        //查询有没有超过次数
                                        MoneyrecordExample moneyrecordExample=new MoneyrecordExample();
                                        moneyrecordExample.createCriteria().andUseridEqualTo(users2.getUserId()).andTypeEqualTo(giveUser.getId());
                                        List<Moneyrecord>moneyrecordList=moneyrecordDao.selectByExample(moneyrecordExample);
                                        if(moneyrecordList.size()<giveUser.getNumber()){
                                            //奖励鲸币
                                            Moneyrecord moneyrecord11=new Moneyrecord();
                                            moneyrecord11.setPrice(giveUser.getMoney());
                                            moneyrecord11.setName("活动奖励");
                                            moneyrecord11.setTime(new Date());
                                            moneyrecord11.setType(giveUser.getId());
                                            moneyrecord11.setUserid(users2.getUserId());
                                            moneyrecordDao.insertSelective(moneyrecord11);
                                            users2.setMoney(users2.getMoney().add(giveUser.getMoney()));
                                            usersDao.updateByPrimaryKeySelective(users2);



                                        }
                                    }
                                    Date starttime=giveUser.getCreattime();
                                    Date endtime=giveUser.getEndtime();
                                    //最大赠送数量
                                    int number=giveUser.getNumber();
                                    int limitinfo=giveUser.getLimitinfo();
                                    //查询注册实名人数
                                    UsersExample usersExample=new UsersExample();
                                    usersExample.createCriteria().andInvitationIdEqualTo(users1.getUserId()).andRealnametypeEqualTo(2).andCreateTimeGreaterThan(starttime).andCreateTimeLessThan(endtime);
                                    List<Users>usersList=usersDao.selectByExample(usersExample);
                                    int nowsize=usersList.size();
                                    if(nowsize<=limitinfo){
                                        //是否能整除
                                        if(nowsize%number==0){
                                            String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                                            //奖励藏品
                                            Collection collection=collectionDao.selectByPrimaryKey(giveUser.getCollid());
                                            if(collection!=null){
                                                UserGrant userGrant=new UserGrant();
                                                userGrant.setUserid(users1.getUserId());
                                                userGrant.setCollid(collection.getId());
                                                userGrant.setNumberno(orderno);
                                                userGrant.setBuytime(new Date());
                                                userGrant.setBuyprice(collection.getPrice());
                                                userGrant.setType(1);
                                                userGrant.setGranttype(1);
                                                userGrant.setCotype(1);
                                                userGrant.setAlbumname(collection.getAlbumname());
                                                userGrant.setAlbumid(collection.getAlbumid());
                                                userGrant.setCollectiontype(collection.getType());
                                                userGrantDao.insertSelective(userGrant);

                                            }
                                            //赠送优惠券
                                            Ticket ticket=new Ticket();
                                            ticket.setState(1);
                                            ticket.setDrawid(giveUser.getDrawid());
                                            ticket.setCreattime(new Date());
                                            ticket.setUserid(users1.getUserId());
                                            Draw draw=drawDao.selectByPrimaryKey(giveUser.getDrawid());
                                            if(draw!=null){
                                                Issue issue=issueDao.selectByPrimaryKey(draw.getIssueid());
                                                ticket.setColid(issue.getCollid());
                                            }
                                            ticketDao.insertSelective(ticket);
                                        }
                                    }


                                }
                            }

                        }



                    }



        }else{
            ///String msg=codeobj.get("msg").toString();
            map.put("type",0);
            map.put("msg","实名信息有误");
        }



        return map;
    }

    private void processSignSendMsg(Map map, fosun.sumpay.merchant.integration.core.request.Request request2) {
        SendMessageForSignRequest req = new SendMessageForSignRequest();
        String sub_merid = MER_ID;

        req.setMer_no(sub_merid);

        req.setUser_id(map.get("uid").toString());
        req.setOrder_no(map.get("order_no").toString());
        req.setCard_no(map.get("accountNo").toString());
        req.setMobile_no(map.get("bankPreMobile").toString());
        String realname = map.get("name").toString();
        /*try {
            // TODO 编码转换可能不需要，要看环境
            req.setRealname(new String(realname.getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        req.setRealname(map.get("name").toString());
        req.setId_no(map.get("idCardCode").toString());
        req.setId_type("1");
        req.setApp_id(MER_ID);
        req.setFormat("JSON");
        req.setService("fosun.sumpay.api.quickpay.send.message.for.sign");
        //req.setTerminal_type(request.getParameter("terminal_type").trim());
        req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        req.setVersion("1.0");

        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("Guolanhongji3007"); //
        request2.setPrivateKeyPath("./ttfsiyao.pfx");
        request2.setPublicKeyPath("./TTFPublicKey.cer");
        request2.setUrl(TEST_URL);
        //request2.setDomain(request.getParameter("domain"));
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }

    private void processSignValidMsg(Map map, fosun.sumpay.merchant.integration.core.request.Request request2) {
        ValidMessageForSignRequest req = new ValidMessageForSignRequest();
        String sub_merid = MER_ID;

        req.setUser_id(map.get("user_id").toString());
        req.setOrder_no(map.get("order_no").toString());
        req.setCard_no(map.get("accountNo").toString());
        req.setMobile_no(map.get("bankPreMobile").toString());

        req.setMer_no(sub_merid);


        String realname = map.get("name").toString();

        /*try {
            // TODO 编码转换可能不需要，要看环境
            req.setRealname(new String(realname.getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
      req.setId_no(map.get("idCardCode").toString());
        req.setId_type("1");
        /*  String cvv = request.getParameter("cvv").trim();
        if(StringUtils.isNotBlank(cvv)) {
            req.setCvv(cvv);
            req.setValid_month(request.getParameter("valid_month").trim());
            req.setValid_year(request.getParameter("valid_year").trim());
        }*/
        req.setRealname(map.get("name").toString());
        req.setVerify_code(map.get("verify_code").toString());

        req.setApp_id(MER_ID);
        req.setFormat("JSON");
        req.setService("fosun.sumpay.api.quickpay.valid.message.for.sign");
        //req.setTerminal_type(request.getParameter("terminal_type").trim());
        req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        req.setVersion("1.0");
        System.out.println("req :"+req);
        System.out.println(JSON.toJSONString(req));
        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("Guolanhongji3007"); //
        request2.setPrivateKeyPath("./ttfsiyao.pfx");
        request2.setPublicKeyPath("./TTFPublicKey.cer");
        request2.setUrl(TEST_URL);
        //request2.setDomain(request.getParameter("domain"));
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }



    public static String get(String appCode, String url, Map<String, String> params) throws IOException {
        url = url + buildRequestUrl(params);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        String result = response.body().string();
        return result;
    }

    public static String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }


   /* public void addRealName(RealNameParam RealNameParam, Integer userId) {
        System.out.println("RealNameParam.getAccountNo()=" + RealNameParam.getAccountNo());
        System.out.println("RealNameParam.getName()=" + RealNameParam.getName());
        System.out.println("RealNameParam.getIdCardCode()=" + RealNameParam.getIdCardCode());
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andRealnameEqualTo(RealNameParam.getName()).andRealnoEqualTo(RealNameParam.getIdCardCode());
        List<Users> usesrs = usersDao.selectByExample(usersExample);
        if (usesrs.size() > 0) {
            throw new CloudException(ExceptionConstant.该实名信息已达认证上限);
        }
        Users user = usersDao.selectByPrimaryKey(userId);
        String path = "https://bccheckv2.api.bdymkt.com/v1/bankcheck";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
//        request.setCredentials("您的 access key", "您的 secret key");
        request.setCredentials("c4ec0b330e9247f3a9f0ecad85827886", "0a6ecd580b7542f0b2b5f6afccf41bef");
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
        request.addQueryParameter("accountNo", RealNameParam.getAccountNo());
        request.addQueryParameter("name", RealNameParam.getName());
//        request.addQueryParameter("idCardCode", "");
        request.addQueryParameter("idCardCode", RealNameParam.getIdCardCode());
        request.addQueryParameter("bankPreMobile", "");
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        String response = client.sendRequest(request).getResult();
        System.out.println("response=" + response);
        // 返回结果格式为Json字符串
        String result = JSONObject.parseObject(response).getString("result");
        String results = JSONObject.parseObject(result).getString("result");
        System.out.println("result=" + result);
        System.out.println("results=" + results);
        //T 表示有效的，F 表示无效的，N 表示无法认证的，P表示网络连接超时
        if ("T".equals(results)) {  //user 实名状态
            System.out.println("进入实名");
            Users users = new Users();
            users.setUserId(user.getUserId());
            users.setRealnametype(2);
            users.setRealname(RealNameParam.getName());
            users.setRealno(RealNameParam.getIdCardCode());
            usersDao.updateByPrimaryKeySelective(users);
        } else {
            throw new CloudException(ExceptionConstant.认证失败);
        }
    }*/

//    public static void main(String[] args) {
//        BigDecimal bigDecimal = new BigDecimal(-1);
//        System.out.println(bigDecimal.negate());

       /* String result = "T";
        System.out.println("T".equals(result));
        if ("T".equals(result)) {
            System.out.println("aaa");
        }*/
//        String path = "https://bccheckv2.api.bdymkt.com/v1/bankcheck";
//        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
////        request.setCredentials("您的 access key", "您的 secret key");
//        request.setCredentials("db68c7d8f01a4ae1b2a63587d18d11db", "37acd96bb795498cbf11403ee05fd3b7");
//        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
//        request.addQueryParameter("accountNo", "6214832948387258");
//        request.addQueryParameter("name", "孟何坤");
//        request.addQueryParameter("idCardCode", "");
//        request.addQueryParameter("bankPreMobile", "");
//        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
//        try {
//            ApiExplorerResponse response = client.sendRequest(request);
//            // 返回结果格式为Json字符串
//            System.out.println(response.getResult());
//            String a = response.getResult();
//            String result = JSONObject.parseObject(a).getString("reason");
//            System.out.println(result);
//            if ("成功".equals(result)) {
//                System.out.println("成功结束");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////     String stri=   {"error_code":0,"reason":"成功","result":{"accountNo":"621483**********","name":"孟**","idCardCore":"610582************","bankPreMobile":"","result":"T","message":"银行卡鉴权成功","messagetype":0}}
//
//        db68c7d8f01a4ae1b2a63587d18d11db
//        37acd96bb795498cbf11403ee05fd3b7
    //}


//    public static void main(String[] args) {
//        /*分割*/
//        String a = "12,13,15,16";
//        String[] split = a.split(",");
//        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
//        }
//    }

    @Override
    public Map blindboxs(Integer userid,int boxid) {
       /* List list = new ArrayList();
        List<Blindbox> blindboxes = blindboxDao.selectByExample(new BlindboxExample());
        for (Blindbox blindbox : blindboxes) {*/

            Blindbox    blindbox=blindboxDao.selectByPrimaryKey(boxid);
        //List<Integer> idl = new ArrayList();
        JSONArray jsonArray=JSONArray.parseArray(blindbox.getFragment());
        System.out.println("jsonArray :"+jsonArray);
        List list1 = new ArrayList();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object= (JSONObject) jsonArray.get(i);
            //labelstring=labelstring+object.get("label").toString()+",";
            int isnum=Integer.parseInt(object.get("label").toString());
            int neednum=Integer.parseInt(object.get("number").toString());

            Map map1 = new HashMap();
            Collection collection = collectionDao.selectByPrimaryKey(isnum);
            UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(isnum).andTypeEqualTo(3);
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            if (userGrants.size() > 0) {
                   /* List list2 = new ArrayList();
                    list2.add(collection.getName() + "x" + userGrants.size());*/
                map1.put("name", collection.getName());
                map1.put("number", neednum);
                map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                if(userGrants.size()>=neednum){
                    map1.put("type", 1);
                }else{
                    map1.put("type", 0);
                }

            } else {
                    /*List list2 = new ArrayList();
                    list2.add(collection.getName() + "x0");*/
                map1.put("number", neednum);
                map1.put("name", collection.getName());
                map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                map1.put("type", 0);
            }
            list1.add(map1);

            //idl.add(isnum);

        }
            Map map = new HashMap();

            Collection collectionss=collectionDao.selectByPrimaryKey(blindbox.getSynthesis());


            map.put("name", collectionss.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collectionss.getImg());//图片
            map.put("limits", collectionss.getLimits());//限量
            map.put("surplus",collectionss.getLimits()-collectionss.getSold());
            map.put("price", collectionss.getPrice());//价格
            map.put("publisher", collectionss.getPublisher());//发行者
            map.put("cimg", ImgEnum.img.getUrl() + collectionss.getCreatorimg());//发行者
            map.put("begintime", DateUtils.getDateToStr(blindbox.getBegintime(), DateUtils.DATE_FORMAT));
            map.put("endtime",DateUtils.getDateToStr(blindbox.getEndtime(), DateUtils.DATE_FORMAT));



            map.put("id", blindbox.getId());
            //map.put("name", blindbox.getName());
           // map.put("count", "(" + idl.size() + "个)");
            /*List list1 = new ArrayList();
            for (Integer integer : idl) {
                Map map1 = new HashMap();
                Collection collection = collectionDao.selectByPrimaryKey(integer);
                UserGrantExample userGrantExample = new UserGrantExample();
                userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(integer).andTypeEqualTo(3);
                List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
                if (userGrants.size() > 0) {
                   *//* List list2 = new ArrayList();
                    list2.add(collection.getName() + "x" + userGrants.size());*//*
                    map1.put("name", collection.getName());
                    map1.put("number", userGrants.size());
                    map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                    map1.put("type", 1);
                } else {
                    *//*List list2 = new ArrayList();
                    list2.add(collection.getName() + "x0");*//*
                    map1.put("number", 0);
                    map1.put("name", collection.getName());
                    map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                    map1.put("type", 0);
                }
                list1.add(map1);
            }*/
            map.put("fragment", list1);
            //list.add(map);
        //}
        return map;
    }

    @Override
    public List myblindboxs(Integer id) {
        List list = new ArrayList();
        MyboxExample myboxExample = new MyboxExample();
        myboxExample.setOrderByClause("id desc");
        myboxExample.createCriteria().andUseridEqualTo(id);
        List<Mybox> myboxes = myboxDao.selectByExample(myboxExample);
        for (Mybox mybox : myboxes) {
            Map map = new HashMap();
            map.put("id", mybox.getId());
            map.put("type", mybox.getType());
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(mybox.getBoxid());
            Collection collection = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
            map.put("name", blindbox.getName());
            map.put("price", collection.getPrice());
            if (mybox.getType() == 1) {
                Collection collections = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
                map.put("img", ImgEnum.img.getUrl() + collections.getImg());
            }
            list.add(map);
        }
        return list;
    }

    public Blindboxtrue lotteryStart(int id) {
        BlindboxtrueExample blindboxtrueExample = new BlindboxtrueExample();
        blindboxtrueExample.createCriteria().andColidEqualTo(id).andNumberGreaterThan(0);
        List<Blindboxtrue> blindboxtrues = blindboxtrueDao.selectByExample(blindboxtrueExample);
        if(blindboxtrues.size()==0){
            throw new CloudException(ExceptionConstant.库存不足);
        }
        Integer allProbability = 0;
        for (Blindboxtrue blindboxtrue : blindboxtrues) {
            allProbability = allProbability + blindboxtrue.getNumber();
        }
        if(allProbability==0){
            throw new CloudException(ExceptionConstant.库存不足);
        }
        Double random = Math.random();
        Double luckyNum = random * allProbability;
        Blindboxtrue lottery = new Blindboxtrue();
        for (Blindboxtrue blindboxtrue : blindboxtrues) {
            luckyNum = luckyNum - blindboxtrue.getNumber();
            if (luckyNum < 0) {
                lottery = blindboxtrue;
                break;
            }
        }

        return lottery;
    }

    @Override
    public Map openbox(int userid, int id) {
        Map map = new HashMap();
        UserGrant userGrant=userGrantDao.selectByPrimaryKey(id);
        Collection collection111=collectionDao.selectByPrimaryKey(userGrant.getCollid());
       // Mybox mybox = myboxDao.selectByPrimaryKey(id);

        Blindboxtrue blindboxtrue = lotteryStart(collection111.getId());

        if (DateUtils.compareTo(new Date(), blindboxtrue.getBegintime(), DateUtils.DATETIME_FORMAT) == 1) {
            throw new CloudException(ExceptionConstant.盲盒未到开启时间);
        }
        if (DateUtils.compareTo(new Date(), blindboxtrue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
            throw new CloudException(ExceptionConstant.盲盒开启时间已结束);
        }

        int nownumber=blindboxtrue.getNumber()-1;
        if(nownumber<0){
            nownumber=0;
        }
        blindboxtrue.setNumber(nownumber);
        blindboxtrueDao.updateByPrimaryKeySelective(blindboxtrue);

        int a=blindboxtrue.getOpenid();
        //查询有没有发行
        IssueExample issueExample=new IssueExample();
        issueExample.createCriteria().andCollidEqualTo(a);
        List<Issue>issueList=issueDao.selectByExample(issueExample);


        Collection collections = collectionDao.selectByPrimaryKey(a);

       String no= System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
        String address = dictService.getValue("address");
        String privateKey = dictService.getValue("privateKey");

        UserGrant userGrantnew = new UserGrant();

        if(issueList.size()>0){
            Issue issue=issueList.get(0);
            userGrantnew.setIssueid(issue.getId());
        }
        userGrantnew.setUserid(userid);
        userGrantnew.setCollid(a);
        userGrantnew.setNumberno(no);
        userGrantnew.setCreatetime(new Date());
        userGrantnew.setBuyprice(collections.getPrice());
        userGrantnew.setGranttype(1);
        userGrantnew.setCotype(1);
        userGrantnew.setBuytime(new Date());
        userGrantnew.setType(1);
        userGrantnew.setAlbumid(collections.getAlbumid());
        userGrantnew.setAlbumname(collections.getAlbumname());
        userGrantDao.insertSelective(userGrantnew);

        Mybox mybox=new Mybox();
        mybox.setUsergrantid(userGrantnew.getId());
        mybox.setUserid(userid);
        mybox.setType(1);
        mybox.setBoxid(collection111.getId());
        mybox.setIssuid(userGrantnew.getIssueid());
        mybox.setCreattime(new Date());
        mybox.setSpid(a);
        mybox.setColid(collections.getId());
        myboxDao.insertSelective(mybox);


        userGrantDao.deleteByPrimaryKey(id);

        //map.put("collectionid", collection.getId());
        map.put("user_grantid", userGrantnew.getId());
        map.put("img",ImgEnum.img.getUrl()+collections.getImg());
        map.put("name",collections.getName());
        //map.put("type", type);
        return map;
    }

    @Override
    public Map synthesis(int userid, int id) {
        Map map = new HashMap();
        Blindbox blindbox = blindboxDao.selectByPrimaryKey(id);
        //比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        if (DateUtils.compareTo(new Date(), blindbox.getBegintime(), DateUtils.DATETIME_FORMAT) == 1) {
            map.put("describe", "敬请期待 " + DateUtils.getDateToStr(blindbox.getBegintime(), DateUtils.TIME_FORMAT1) + " 开售");
        } else {
            if (DateUtils.compareTo(new Date(), blindbox.getEndtime(), DateUtils.DATETIME_FORMAT) == 1) {
                map.put("describe", "合成将于" + DateUtils.getDateToStr(blindbox.getEndtime(), DateUtils.TIME_FORMAT1) + " 结束");
            }
            if (DateUtils.compareTo(new Date(), blindbox.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("describe", "本轮合成已结束 - 敬请期待下次开放");
            }
        }
        Collection collections = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
        map.put("name", collections.getName());
        map.put("img", ImgEnum.img.getUrl() + collections.getImg());
        List<Integer> idl = new ArrayList();
        String[] split = blindbox.getFragment().split(",");
        for (int i = 0; i < split.length; i++) {
            idl.add(Integer.parseInt(split[i]));
        }
        List list1 = new ArrayList();
        for (Integer integer : idl) {
            Map map1 = new HashMap();
            Collection collection = collectionDao.selectByPrimaryKey(integer);
            map1.put("name", collection.getName());
            map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
            list1.add(map1);
        }
        map.put("fragment", list1);
        return map;
    }

    @Override
    public Map synthesisprize(int userid, int id) {
        Blindbox blindbox = blindboxDao.selectByPrimaryKey(id);
        //比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        if (DateUtils.compareTo(new Date(), blindbox.getBegintime(), DateUtils.DATETIME_FORMAT) == 1) {
            throw new CloudException(ExceptionConstant.合成暂未开始);
        }
        if (DateUtils.compareTo(new Date(), blindbox.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
            throw new CloudException(ExceptionConstant.本轮合成已结束);
        }
        Collection collection=collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
        if(collection.getLimits()<=collection.getSold()){
            throw new CloudException(ExceptionConstant.已售光);
        }

       /* List<Integer> idl = new ArrayList();
        String[] split = blindbox.getFragment().split(",");
        for (int i = 0; i < split.length; i++) {
            idl.add(Integer.parseInt(split[i]));
        }*/
        List<Map> list=new ArrayList();
        JSONArray jsonArray=JSONArray.parseArray(blindbox.getFragment());
        System.out.println("jsonArray :"+jsonArray);
        for(int i=0;i<jsonArray.size();i++){
            Map map=new HashMap();
            JSONObject object= (JSONObject) jsonArray.get(i);
            //labelstring=labelstring+object.get("label").toString()+",";
            int label=Integer.parseInt(object.get("label").toString());
            int number=Integer.parseInt(object.get("number").toString());

            map.put("label",label);
            map.put("number",number);
            list.add(map);
        }

        int success = 1;
        for (Map map : list) {
            int label=Integer.parseInt(map.get("label").toString());
            int number=Integer.parseInt(map.get("number").toString());
            //System.out.println("label :"+label);
            //System.out.println("label :"+label);
            UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(label).andTypeEqualTo(3);
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            System.out.println("userGrants=" + userGrants.size());
            if (userGrants.size() < number) {
                success = 0;
            }
        }

        if (success == 0) {
            throw new CloudException(ExceptionConstant.碎片未集齐);
        }

        for (Map map : list) {
            int label=Integer.parseInt(map.get("label").toString());
            int number=Integer.parseInt(map.get("number").toString());


            UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(label).andTypeEqualTo(3);
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            for(int i=0;i<number;i++){
                UserGrant userGrant = userGrants.get(i);
                String hash = userGrant.getHashs();
                Fragment fragment = new Fragment();
                fragment.setCollid(userGrant.getCollid());
                fragment.setHashs(hash);
                fragmentDao.insertSelective(fragment);
               /* userGrant.setHashs(null);
                userGrant.setType(4);
                userGrantDao.updateByPrimaryKeySelective(userGrant);*/

                Users senduser=usersDao.selectByPrimaryKey(userGrant.getUserid());
                String thuserId=senduser.getPhoneNumber();
                String  thuserKey=senduser.getUserkey();
                String useraddress=senduser.getThaddress();




                String tianheappid=dictService.getValue("tianheappid");
                String tianheappkey=dictService.getValue("tianheappkey");

                com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject();
                //            Map<String, String> ingredients = new HashMap<String, String>();
                jsonObj.put("appId",tianheappid);
                jsonObj.put("appKey", tianheappkey);
                jsonObj.put("userId", thuserId);
                jsonObj.put("userKey", thuserKey);
                jsonObj.put("contractAddress", collection.getThcontractaddress());
                jsonObj.put("tokenId", userGrant.getTokenid());

                //System.out.println(jsonObj);
                String url="https://api.tichain.tianhecloud.com/api/v2/nfr/burn";

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




                userGrantDao.deleteByPrimaryKey(userGrant.getId());
            }
        }

        Collection collections = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
        Map map = new HashMap();
        map.put("name", collections.getName());
        map.put("img", ImgEnum.img.getUrl() + collections.getImg());


        String no= System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
        String address = dictService.getValue("address");
        String privateKey = dictService.getValue("privateKey");
/*
        String contractAddress = collections.getContractaddress();
        String stringdata = "address=" + address + "&privateKey=" + privateKey + "&contractAddress=" + contractAddress;
        System.out.println("stringdata :" + stringdata);
        String data = HttpRequest.sendGet("http://123.56.186.63:7200/api/mintnft", stringdata);
        int b = collections.getSold() + 1;
        collections.setSold(b);
        collectionDao.updateByPrimaryKeySelective(collections);

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
        System.out.println("222222");
        System.out.println(jsonObject);
        String blockhash = jsonObject.get("blockhash").toString();
        String stringtokenId = jsonObject.get("tokenId").toString();//a
        int tokenId = Integer.valueOf(stringtokenId).intValue();
        userGrant.setTokenid(tokenId);
        int aa = tokenId + 1;*/
        UserGrant userGrant = new UserGrant();
        //合成创世的赠送
       /* if(collections.getDrawid()!=0){
            Ticket ticket=new Ticket();
            ticket.setState(1);
            ticket.setUserid(userid);
            ticket.setColid(collections.getDrawid());
            ticket.setCreattime(new Date());
            ticketDao.insertSelective(ticket);
        }*/

        userGrant.setUserid(userid);
        userGrant.setCollid(blindbox.getSynthesis());
        userGrant.setNumberno(no);
        userGrant.setAlbumname(collections.getAlbumname());
        userGrant.setAlbumid(collections.getAlbumid());
        userGrant.setCreatetime(new Date());
        userGrant.setBuyprice(collections.getPrice());
        userGrant.setGranttype(1);
        userGrant.setBuytime(new Date());
        userGrant.setCotype(1);
        userGrant.setType(1);
        userGrantDao.insertSelective(userGrant);

        HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(userid);
        hideRecord.setImg(collections.getImg());
        hideRecord.setName(collections.getName());
        hideRecord.setPrice(collections.getPrice());
        hideRecord.setNo(userGrant.getNumberno());

      /*  if (userGrant.getTruenumber() != null) {
            if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
                hideRecord.setNo(userGrant.getTruenumber());
            }
        }*/
        hideRecord.setMs("碎片合成");
        hideRecord.setCreatetime(new Date());
        hideRecord.setType(9);//0.黄的1.绿2.红
        hideRecordDao.insertSelective(hideRecord);

        map.put("collectionid", userGrant.getCollid());
        map.put("user_grantid", userGrant.getId());
        return map;
    }

    @Override
    public List myorder(int userid, int type) {
        List list = new ArrayList();
        if(type==0){
            List listin=new ArrayList();
            listin.add("未付款");
            listin.add("完成付款");
            HideRecordExample hideRecordExample=new HideRecordExample();
            hideRecordExample.setOrderByClause("id desc");
            hideRecordExample.createCriteria().andMsIn(listin).andUseridEqualTo(userid);
            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
            if(hideRecordList.size()>0){
                for (HideRecord hideRecord : hideRecordList) {
                    Map map = new HashMap();

                    UserGrantExample userGrantExample=new UserGrantExample();
                    userGrantExample.createCriteria().andNumbernoEqualTo(hideRecord.getNo());
                    List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
                    if(userGrantList.size()>0){
                        map.put("id", userGrantList.get(0).getId());

                        Collection collections = collectionDao.selectByPrimaryKey(userGrantList.get(0).getCollid());
                        map.put("coltype",collections.getType());
                        if(userGrantList.get(0).getSendtype()==3){
                            map.put("sendtype",1);
                        }else{
                            map.put("sendtype",0);
                        }

                    }

                    map.put("numberno", hideRecord.getNo());
                    map.put("buyprice", hideRecord.getPrice());
                    map.put("createtime", hideRecord.getCreatetime());




                    if(hideRecord.getMs().equals("未付款")){
                        map.put("type",0);
                    }else{
                        map.put("type",1);
                    }

                    map.put("name", hideRecord.getName());
                    map.put("img", ImgEnum.img.getUrl() + hideRecord.getImg());

                    list.add(map);
                }
            }



          /*  UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andUseridEqualTo(userid);
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            for (UserGrant userGrant : userGrants) {
                Map map = new HashMap();
                map.put("id", userGrant.getId());
                map.put("collectionid", userGrant.getCollid());
                map.put("numberno", userGrant.getNumberno());
                map.put("buyprice", userGrant.getBuyprice());
                map.put("createtime", userGrant.getCreatetime());
                map.put("type",userGrant.getCotype());


                Collection collections = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                map.put("coltype",collections.getType());
                map.put("name", collections.getName());
                map.put("img", ImgEnum.img.getUrl() + collections.getImg());
                map.put("creator", collections.getPublisher());//发行者
                map.put("creatorimg",ImgEnum.img.getUrl()+ collections.getCreatorimg());//发行者
                list.add(map);
            }*/
        }else if(type==1){
            List listin=new ArrayList();
            listin.add("未付款");
            //listin.add("完成付款");
            HideRecordExample hideRecordExample=new HideRecordExample();
            hideRecordExample.setOrderByClause("id desc");
            hideRecordExample.createCriteria().andMsIn(listin).andUseridEqualTo(userid);
            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
            if(hideRecordList.size()>0){
                for (HideRecord hideRecord : hideRecordList) {
                    Map map = new HashMap();
                    UserGrantExample userGrantExample=new UserGrantExample();
                    userGrantExample.createCriteria().andNumbernoEqualTo(hideRecord.getNo());
                    List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
                    if(userGrantList.size()>0){
                        map.put("id", userGrantList.get(0).getId());
                        Collection collections = collectionDao.selectByPrimaryKey(userGrantList.get(0).getCollid());
                        map.put("coltype",collections.getType());
                    }

                    map.put("numberno", hideRecord.getNo());
                    map.put("buyprice", hideRecord.getPrice());
                    map.put("createtime", hideRecord.getCreatetime());

                    map.put("type",0);
                    map.put("name", hideRecord.getName());
                    map.put("img", ImgEnum.img.getUrl() + hideRecord.getImg());

                    list.add(map);
                }
            }

       /*     UserGrantExample userGrantExample = new UserGrantExample();
            List newlist=new ArrayList();

            userGrantExample.createCriteria().andUseridEqualTo(userid).andCotypeEqualTo(0);
            //userGrantExample.or().andOppositeuserEqualTo(userid);

            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            for (UserGrant userGrant : userGrants) {
                Map map = new HashMap();
                map.put("id", userGrant.getId());
                map.put("collectionid", userGrant.getCollid());
                map.put("numberno", userGrant.getNumberno());
                map.put("buyprice", userGrant.getBuyprice());
                map.put("createtime", userGrant.getCreatetime());
                map.put("type",0);
                Collection collections = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                map.put("coltype",collections.getType());
                map.put("name", collections.getName());
                map.put("img", ImgEnum.img.getUrl() + collections.getImg());
                map.put("creator", collections.getPublisher());//发行者
                map.put("creatorimg",ImgEnum.img.getUrl()+ collections.getCreatorimg());//发行者
                list.add(map);
            }*/
        }else if(type==2){
            List listin=new ArrayList();
            //listin.add("未付款");
            listin.add("完成付款");
            HideRecordExample hideRecordExample=new HideRecordExample();
            hideRecordExample.setOrderByClause("id desc");
            hideRecordExample.createCriteria().andMsIn(listin).andUseridEqualTo(userid);
            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
            if(hideRecordList.size()>0){
                for (HideRecord hideRecord : hideRecordList) {
                    Map map = new HashMap();
                    UserGrantExample userGrantExample=new UserGrantExample();
                    userGrantExample.createCriteria().andNumbernoEqualTo(hideRecord.getNo());
                    List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
                    if(userGrantList.size()>0){
                        map.put("id", userGrantList.get(0).getId());
                        Collection collections = collectionDao.selectByPrimaryKey(userGrantList.get(0).getCollid());
                        map.put("coltype",collections.getType());
                    }

                    map.put("numberno", hideRecord.getNo());
                    map.put("buyprice", hideRecord.getPrice());
                    map.put("createtime", hideRecord.getCreatetime());

                    map.put("type",1);
                    map.put("name", hideRecord.getName());
                    map.put("img", ImgEnum.img.getUrl() + hideRecord.getImg());

                    list.add(map);
                }
            }

            //type=2
         /*   UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andTypeEqualTo(3).andUseridEqualTo(userid);
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            for (UserGrant userGrant : userGrants) {
                Map map = new HashMap();
                map.put("id", userGrant.getId());
                map.put("collectionid", userGrant.getCollid());
                map.put("numberno", userGrant.getNumberno());
                map.put("buyprice", userGrant.getBuyprice());
                map.put("createtime", userGrant.getCreatetime());
                map.put("type",2);
                Collection collections = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                map.put("coltype",collections.getType());
                map.put("name", collections.getName());
                map.put("img", ImgEnum.img.getUrl() + collections.getImg());
                map.put("creator", collections.getPublisher());//发行者
                map.put("creatorimg",ImgEnum.img.getUrl()+ collections.getCreatorimg());//发行者
                list.add(map);
            }*/

        }

        return list;
    }

    @Override
    public void beoverdue() {
        //获取当前时间
        int a = Integer.parseInt(dictService.getValue("djs"));
        Date date = new Date();
        date.setTime(date.getTime() - a * 60 * 1000);
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andTypeEqualTo(2).andBuytimeLessThan(date);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        for (UserGrant userGrant : userGrants) {
            userGrant.setType(1);
            userGrant.setOppositeuser(0);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
        }
    }

    @Override
    public void checkTradeStatus() {
        System.out.println("checkTradeStatus1");
        //获取当前时间
        int a = Integer.parseInt(dictService.getValue("djs"));
        Date date = new Date();
        date.setTime(date.getTime() - a * 60 * 1000);
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andTypeEqualTo(2).andBuytimeLessThan(date);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        for (UserGrant userGrant : userGrants) {
            userGrant.setType(1);
            userGrant.setOppositeuser(0);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
        }
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andCotypeEqualTo(0).andBuytimeLessThan(date);
        List<UserGrant> userGrants1 = userGrantDao.selectByExample(userGrantExample1);
        for (UserGrant userGrant : userGrants1) {
            userGrantDao.deleteByPrimaryKey(userGrant.getId());
        }

    }

    @Override
    public void cancelorder(int userid, int id) {
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        String no=userGrant.getNumberno();
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.createCriteria().andNoEqualTo(no).andUseridEqualTo(userid);
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        if(hideRecordList.size()>0){
            for (HideRecord hideRecord : hideRecordList) {
                if(hideRecord.getUsergrantid()!=0){
                    //说明是二级市场的订单
                    UserGrant userGrant1=userGrantDao.selectByPrimaryKey(hideRecord.getUsergrantid());
                    if(userGrant1!=null){
                        if(userGrant1.getType()==8){
                            userGrant1.setType(7);
                            userGrant1.setSellprice(new BigDecimal(0));
                            userGrant1.setOppositeuser(null);
                            userGrantDao.updateByPrimaryKeySelective(userGrant1);

                        }
                    }
                }

                hideRecordDao.deleteByPrimaryKey(hideRecord.getId());
            }
        }

        userGrantDao.deleteByPrimaryKey(userGrant.getId());
        /*if (userGrant.getGranttype() == 2) {
            if (userGrant.getType() == 2) {
                Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                userGrant.setType(1);
                userGrant.setOppositeuser(null);
                userGrantDao.updateByPrimaryKeySelective(userGrant);
                CancelRecord cancelRecord = new CancelRecord();
                cancelRecord.setUserid(userid);
                cancelRecord.setImg(collection.getImg());
                cancelRecord.setName(collection.getName());
                cancelRecord.setNo(userGrant.getNumberno());
                cancelRecord.setPrice(userGrant.getSellprice());
                cancelRecordDao.insertSelective(cancelRecord);
            }
        } else {
            //藏品还未发放 删除 退次数
            Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
//            int a = 0;
//            if (collection.getSold() != 0) {
//                a = collection.getSold() - 1;
//                collection.setSold(a);
//                collectionDao.updateByPrimaryKeySelective(collection);
//            }
            IssueExample issueExample = new IssueExample();
            issueExample.createCriteria().andCollidEqualTo(userGrant.getCollid()).andReleasetimeBetween(DateUtils.getDayBegin(), DateUtils.getDayEnd());
            List<Issue> issues = issueDao.selectByExample(issueExample);
            if (issues.size() > 0) {
                Issue issue = issues.get(0);
                int b = 0;
                if (issue.getSold() != 0) {
                    b = issue.getSold() - 1;
                }
                issue.setSold(b);
                issueDao.updateByPrimaryKeySelective(issue);
            }
            CancelRecord cancelRecord = new CancelRecord();
            cancelRecord.setUserid(userid);
            cancelRecord.setImg(collection.getImg());
            cancelRecord.setName(collection.getName());
            cancelRecord.setNo(userGrant.getNumberno());
            cancelRecord.setPrice(userGrant.getSellprice());
            cancelRecordDao.insertSelective(cancelRecord);
//            userGrant.setType(1);
//            userGrant.setSellprice(new BigDecimal(0));
            userGrantDao.deleteByPrimaryKey(userGrant.getId());

        }*/
    }


    public Map<String, Object> getInviteInfo(SysUserParam param) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(param.getLogUserPid());
        String img = createUserShareImg(user.getUserId().toString());
        //图片展示地址 生成存储地址 生成链接路径
        String dicturl=dictService.getValue("url");
        map.put("QrCode", img);
        map.put("url", dicturl+ "/#/pages/login/register?uid=" + user.getRegisterCode());
        map.put("isagent",user.getIsagent());

        //map.put("url", "http://nftxcsc.com:8100/#/?uid=" + user.getUserId());

        UsersExample usersExample=new UsersExample();
        usersExample.setOrderByClause("user_id desc");
        usersExample.createCriteria().andInvitationIdEqualTo(param.getLogUserPid());
        List <Users>usersList=usersDao.selectByExample(usersExample);
        List list=new ArrayList();
        BigDecimal totalmoney=new BigDecimal(0);
        if(usersList.size()>0) {
            for (Users users : usersList) {
                Map map1 = new HashMap();
                BigDecimal userbalance=new BigDecimal(0);
                //查询用户的消费记录
                BalanceRecordExample balanceRecordExample=new BalanceRecordExample();
                balanceRecordExample.createCriteria().andStateEqualTo(1).andUseridEqualTo(users.getUserId());
                List<BalanceRecord>balanceRecordList=balanceRecordDao.selectByExample(balanceRecordExample);
                if(balanceRecordList.size()>0){
                    for (BalanceRecord balanceRecord : balanceRecordList) {
                        userbalance= userbalance.add(balanceRecord.getCount());
                        totalmoney=totalmoney.add(balanceRecord.getCount());
                    }
                }

                map1.put("price",userbalance.negate());
                map1.put("name",users.getNickName());
                map1.put("time",DateUtils.getDateToStr(users.getCreateTime(), DateUtils.DATETIME_FORMAT));
                if(users.getRealnametype()==2){
                    map1.put("type","已实名");
                }else{
                    map1.put("type","未实名");
                }
                list.add(map1);
            }
        }
        map.put("totalmoney",totalmoney.negate());
        map.put("total",usersList.size());
        map.put("code",user.getRegisterCode());
        map.put("money",user.getBalance());
        map.put("freezemoney",user.getFreezemoney());
        map.put("list",list);
        return map;
    }

    /**
     * 生成邀请二维码
     *
     * @param registerCode
     * @return
     */
    public String createUserShareImg(String registerCode) {
        String fileName = "InvQRCode" + registerCode + ".jpg";
        File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//生成图片位置
        String dicturl=dictService.getValue("url");
        //String url = dicturl+"http://nftxcsc.com:8100/#/?uid=" + registerCode;
        //String url = dicturl+":8200/#/?uid=" + registerCode;
        String url = dicturl+ "/#/pages/login/register?uid=" + registerCode;
        boolean falg = QRcode.CreatQRCode(QrCodeFile, 200, 200, url);
        if (falg) {
            return ImgEnum.QrCode.getUrl() + fileName;
        } else {
            return null;
        }
    }

    @Override
    public String inviteInfoimgs(SysUserParam param) {
        Users user = usersDao.selectByPrimaryKey(param.getLogUserPid());
        String a = ImgEnum.img.getPath() + createUserShareImg1(user.getRegisterCode().toString());
        System.out.println("a :"+a);

        String imgurl=ImgEnum.img.getPath() + chatDao.selectByPrimaryKey(2).getImg();
        System.out.println("imgurl :"+imgurl);
        return composePic(imgurl, a, "InvQRCode1" + user.getRegisterCode() + ".jpg", user.getRegisterCode().toString());
    }

    @Override
    public Map chat() {
        Map map = new HashMap();
        map.put("img",ImgEnum.img.getUrl() + chatDao.selectByPrimaryKey(1).getImg());
        map.put("content",chatDao.selectByPrimaryKey(1).getContent());
        return map;
    }

    @SneakyThrows
    @Override
    public String recharge(int userId, BigDecimal count,String ip)  {
        Map map=new HashMap();

        Users user = usersDao.selectByPrimaryKey(userId);
        if (user.getRealnametype() == 0) {
            throw new CloudException(ExceptionConstant.请先进行实名认证);
        }
        if (count.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.数量有误);
        }
        String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
        Rechargelist rechargelist = new Rechargelist();
        rechargelist.setUserid(userId);
        rechargelist.setMoney(count);
        rechargelist.setOrderno(orderno);
        rechargelistDao.insertSelective(rechargelist);

        String notifyUrl = dictService.getValue("yqlj")+":8200/pay/alipay/rechargeupdPay";

       String hrefUrl = dictService.getValue("yqlj")+"/#/pages/my/wallet";


        //请求
        String orderStr = DeShanUtil.pay(orderno, count, "充值", ip, notifyUrl, hrefUrl, Integer.toString(user.getUserId()), user.getRealname(), user.getRealno());
        return orderStr;


    }

    private void processSendMsg(Map map, fosun.sumpay.merchant.integration.core.request.Request request2) throws UnsupportedEncodingException {
        // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
        String sub_merid = MER_ID;
        // String trade_code =request.getParameter("tradeCode").trim();
        String mer_id = MER_ID;
        String user_id = map.get("userid").toString();
        String order_no =  DateUtils.getSecondTimestampTwo(new Date()) + ((int) (Math.random() * 1000)) + "".trim();
        String order_amt = map.get("count").toString();
        System.out.println("order_no :"+order_no);
        // String cur_type =request.getParameter("curType").trim();

        String terminal_type = "wap";

        //String domain = request.getParameter("domain").trim();
        String bind_card_id =map.get("bind_card_id").toString();
        String realname =map.get("realname").toString();
        System.out.println("start_realname :"+realname);

        String id_card_no = map.get("id_card_no").toString();

        NewQuickPayOrderApplyRequest req = new NewQuickPayOrderApplyRequest();
        //if (!"".endsWith(bind_card_id)) {
            req.setBind_card_id(bind_card_id);
        /*} else {
            //req.setCard_no("6226220636974130");
            req.setCard_no("6212261202005412546");
            //req.setBank_code(request.getParameter("bank_code").trim());
            String cardType = "0";
            req.setCard_type(cardType);
            if ("1".equals(cardType)) {
                req.setCvv(request.getParameter("cvv").trim());
                req.setValid_year(request.getParameter("valid_year").trim());
                req.setValid_month(request.getParameter("valid_month").trim());
            }
            req.setMobile_no("18192721125");
        }*/
        req.setMer_no(mer_id);
        req.setApp_id(mer_id);
        req.setTrade_code("T0002");
        //req.setSub_mer_no(sub_merid);
        req.setUser_id(user_id);
        req.setOrder_no(order_no);
        req.setOrder_amount(order_amt);
        req.setTerminal_type(terminal_type);
        req.setFormat("JSON");
        req.setService("fosun.sumpay.api.quickpay.new.order.apply");
        req.setVersion("1.0");
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        req.setTimestamp(timestamp);
        req.setOrder_time(timestamp);
        req.setNeed_notify("1");
        //回调地址

        req.setNotify_url(dictService.getValue("url") + ":8200/pay/alipay/rechargenotify");
        req.setCurrency("CNY");
        req.setGoods_name("jiangtest");
        req.setGoods_num("1");
        req.setGoods_type("2");
        req.setId_type("1");

        System.out.println("req1 :"+req.getOrder_no());
        req.setRealname(realname);
        /*try {

           // req.setRealname(new String(realname.getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        req.setId_no(id_card_no);
        req.setUser_ip_addr("127.0.0.1");
        req.setFree_mark("0");// 0-普通模式；1-仅支付免短信模式

        System.out.println("end_realname :"+req.getRealname());
//		req.setShare_benefit_flag("1");//非分润商户不需要此参数
//		req.setShare_benefit_exp("{\"share_type\":\"1\",\"prior\":\"1\",\"benefit_bean_list\":[{\"mer_no\":\"s100000040\",\"share_type\":\"1\",\"prior\":\"2\",\"amount\":\"1.25\"}]}");

        // 建立请求
        System.out.println("JSON.toJSONString(req) :"+JSON.toJSONString(req));
        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("Guolanhongji3007"); //
        request2.setPrivateKeyPath("./ttfsiyao.pfx");
        request2.setPublicKeyPath("./TTFPublicKey.cer");
        request2.setUrl(TEST_URL);
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }

    private void processTradeQuery(HttpServletRequest request, fosun.sumpay.merchant.integration.core.request.Request request2) {

        String mer_id =MER_ID;
        OrderQueryRequest req = new OrderQueryRequest();
        req.setApp_id(mer_id);
        req.setFormat("JSON");
        req.setMer_no(mer_id);
        req.setOrder_no(request.getParameter("order_no"));
        req.setService("fosun.sumpay.api.trade.order.search.merchant");
        req.setSub_mer_no(request.getParameter("sub_mer_no"));
        req.setTerminal_type(request.getParameter("terminal_type"));
        req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        req.setVersion("1.0");

        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("sumpay"); //
        request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
        request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
        request2.setUrl(TEST_URL);
        request2.setDomain(request.getParameter("domain"));
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }


    private void processAvaliBanks(HttpServletRequest request, fosun.sumpay.merchant.integration.core.request.Request request2) {
        GetAvaliableBanksAndBindedCardsRequest req = new GetAvaliableBanksAndBindedCardsRequest();
        req.setApp_id(request.getParameter("mer_no"));
        req.setFormat("JSON");
        req.setBusiness_code(request.getParameter("business_code"));
        req.setMer_no(request.getParameter("mer_no"));
        req.setService("fosun.sumpay.api.quickpay.avaliable.bank");
        req.setSub_mer_no(request.getParameter("sub_mer_no"));
        req.setTerminal_type(request.getParameter("terminal_type"));
        req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        req.setUser_id(request.getParameter("user_id"));
        req.setVersion("1.0");

        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("sumpay"); //
        request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
        request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
        request2.setUrl(TEST_URL);
        request2.setDomain(request.getParameter("domain"));
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }

    private void processPay(Map map, fosun.sumpay.merchant.integration.core.request.Request request2) {

        //String sub_merid = request.getParameter("subMerId").trim();
        // String trade_code =request.getParameter("tradeCode").trim();

        String order_no = map.get("order_no").toString();
        //String terminal_type = request.getParameter("terminal_type").trim();
        //String domain = request.getParameter("domain").trim();
        //String token = request.getParameter("token").trim();
        NewQuickPayVerifyCodeRequest req = new NewQuickPayVerifyCodeRequest();
        String mer_id =MER_ID;


        req.setService("fosun.sumpay.api.quickpay.submit.pay");
        req.setVersion("1.0");
        req.setFormat("JSON");
        req.setApp_id(mer_id);
        req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        req.setTerminal_type("wap");

        req.setMer_no(mer_id);

        //req.setSub_mer_no(sub_merid);
        //req.setToken(token);//申请支付时返回 result_type为1时必填，为外部签约同步回调返回
        req.setOrder_no(order_no);
        req.setVerify_code(map.get("verify_code").toString());//申请支付时返回 result_type为0时必填

        //req.setTerminal_type(terminal_type);


        System.out.println("reqgetOrder_no :"+req.getOrder_no());
        System.out.println("getVerify_code :"+req.getVerify_code());
        // 建立请求
        request2.setCharset("UTF-8");// 取jsp的请求编码
        request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
        request2.setPassword("Guolanhongji3007"); //
        request2.setPrivateKeyPath("./ttfsiyao.pfx");
        request2.setPublicKeyPath("./TTFPublicKey.cer");
        request2.setUrl(TEST_URL);
        //request2.setDomain(domain);
        request2.setAesEncodedWords(req.getAesEncodedWords());
        request2.setBase64EncodedWords(req.getBase64EncodedWords());
        request2.setCharsetChangeWords(req.getCharsetChangeWords());
    }






    @SneakyThrows
    @Override
    public void settle() {
        System.out.println("settle1");
        Date date = new Date();

        DrawExample drawExample=new DrawExample();
        drawExample.createCriteria().andTypeEqualTo(0).andDrawtimeLessThan(date);
        List<Draw>drawList=drawDao.selectByExample(drawExample);
        if(drawList.size()>0){
            for (Draw draw : drawList) {
              int drawid=draw.getId();
              int total=draw.getNumber();
                DrawrecordExample drawrecordExample=new DrawrecordExample();
                drawrecordExample.createCriteria().andDrawidEqualTo(drawid).andStateEqualTo(0);
                List<Drawrecord>drawrecordList=drawrecordDao.selectByExample(drawrecordExample);
                if(drawrecordList.size()>0){
                    //报名人数不够
                    if(draw.getNumber()>=draw.getDrawnumber()){
                        for (Drawrecord drawrecord : drawrecordList) {
                                    Collection collection=collectionDao.selectByPrimaryKey(draw.getIssueid());
                                   //Issue issue=issueDao.selectByPrimaryKey(draw.getIssueid());
                                   Ticket ticket=new Ticket();
                                   ticket.setColid(collection.getId());
                                   ticket.setCreattime(new Date());
                                   ticket.setUserid(drawrecord.getUserid());
                                   ticket.setState(1);
                                   ticket.setDrawid(drawid);
                                   ticketDao.insertSelective(ticket);

                                    Prompt prompt =new Prompt();
                                    prompt.setUserid(drawrecord.getUserid());
                                    prompt.setState(1);
                                    prompt.setContent(collection.getName());
                                    promptDao.insertSelective(prompt);

                                 /*   drawrecord.setState(1);
                                    drawrecordDao.updateByPrimaryKeySelective(drawrecord);*/
                                    //添加用户白名单
                        }
                    }else{
                        //报名人数超出预售人数
                        List<Drawrecord> listRandom = new ArrayList<Drawrecord>();
                        //随机取出n条不重复的数据,这里我设置随机取3条数据
                        for (int i = total; i >=1; i--) {
                            Random random = new Random();
                            Math.random();
                            //在数组大小之间产生一个随机数 j
                            int j = random.nextInt(drawrecordList.size());
                            //取得list 中下标为j 的数据存储到 listRandom 中
                            listRandom.add(drawrecordList.get(j));
                            //把已取到的数据移除,避免下次再次取到出现重复
                            drawrecordList.remove(j);
                        }
                        System.out.println("listRandom :"+listRandom);
                        for (Drawrecord drawrecord : listRandom) {
                            System.out.println("drawrecordid :"+drawrecord.getId());
                            /*  drawrecord.setState(1);
                            drawrecordDao.updateByPrimaryKeySelective(drawrecord);*/
                            //Issue issue=issueDao.selectByPrimaryKey(draw.getIssueid());
                            Collection collection=collectionDao.selectByPrimaryKey(draw.getIssueid());
                            Ticket ticket=new Ticket();
                            ticket.setColid(collection.getId());
                            ticket.setCreattime(new Date());
                            ticket.setUserid(drawrecord.getUserid());
                            ticket.setState(1);
                            ticket.setDrawid(drawid);
                            ticketDao.insertSelective(ticket);

                            Prompt prompt =new Prompt();
                            prompt.setUserid(drawrecord.getUserid());
                            prompt.setState(1);
                            prompt.setContent(collection.getName());
                            promptDao.insertSelective(prompt);


                        }
                        System.out.println("drawrecordList :"+drawrecordList);
                        for (Drawrecord drawrecord : drawrecordList) {
                            System.out.println("drawrecordid :"+drawrecord.getId());
                         /*   drawrecord.setState(2);
                            drawrecordDao.updateByPrimaryKeySelective(drawrecord);*/
                            Collection collection=collectionDao.selectByPrimaryKey(draw.getIssueid());

                            //退一半的鲸币
                            BigDecimal money=draw.getPrice().divide(new BigDecimal(2).setScale(2, BigDecimal.ROUND_CEILING)).setScale(2, BigDecimal.ROUND_CEILING);
                            Users users=usersDao.selectByPrimaryKey(drawrecord.getUserid());
                            users.setMoney(users.getMoney().add(money));
                            usersDao.updateByPrimaryKeySelective(users);

                            Moneyrecord moneyrecord=new Moneyrecord();
                            moneyrecord.setUserid(users.getUserId());
                            moneyrecord.setPrice(money);
                            moneyrecord.setTime(new Date());
                            moneyrecord.setName("未中签返还");
                            moneyrecordDao.insertSelective(moneyrecord);

                            Prompt prompt =new Prompt();
                            prompt.setUserid(drawrecord.getUserid());
                            prompt.setState(0);
                            prompt.setContent(collection.getName());
                            promptDao.insertSelective(prompt);

                        }



                    }

                }

                //更改状态
                draw.setType(1);
                drawDao.updateByPrimaryKeySelective(draw);

            }
        }
    }

  /*  @SneakyThrows
    public static void main(String[] args) {
        String url="https://openapi-ddc.bsnbase.com/ddcoai/sys/v1/opb/account/create/save";
        String acctoken="eyJ0eXAiOiJKV1QiLCJ0eXBlIjoiT1BFTi1BUEktSldUIiwiYWxnIjoiSFMyNTYifQ.eyJsb2dpbk5hbWUiOiJndW9sYW5ob25namk4OCIsImV4cCI6NDgwOTgyNzY5OX0._0BRfrHOre-vdiaIMgAVGHQCEdfgFHT3SRgLkqReRF4";
       // HttpUtil.post(url,acctoken,param);
        //发起请求
//内容
        String profff="{\"claim\":{\"entName\":\"北京国澜弘基科技有限公司\",\"domain\":\"https://guolanhongji.cn\",\"loginName\":\"guolanhongji88\",\"did\":\"did:bsn:27EhqsmcEBuphgnPyyZe66bN122X\"},\"context\":\"https://www.w3.org/2018/credentials/v1\",\"cptId\":\"882201251518080013\",\"created\":\"2022-06-02 07:14:59\",\"expirationDate\":\"2042-06-02\",\"id\":\"1532259467033448448\",\"issuerDid\":\"did:bsn:2unHZEGYWU5TrcmVnFQ8YGKnLV2h\",\"proof\":{\"creator\":\"did:bsn:2unHZEGYWU5TrcmVnFQ8YGKnLV2h\",\"type\":\"Secp256k1\",\"signatureValue\":\"xaL8MKUKS4uRMg6k9vZbeRmtZxtmm70P7HRQX7teElsoh+XCBqJUjUJDBFHs5YXfIgh9EFs5L9DxtBzjf7yEywE=\"},\"shortDesc\":\"DDC业务凭证模板\",\"type\":\"Proof\",\"userDid\":\"did:bsn:27EhqsmcEBuphgnPyyZe66bN122X\"}";
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("opbChainClientName","666");
        jsonObject.put("opbChainClientType","2");
        jsonObject.put("opbChainId","2");
        jsonObject.put("opbClientAddress","0xfb7f65142deebf282e3031ce168c7443c963e600");
        jsonObject.put("openDdc","5");
        jsonObject.put("opbKeyType","3");
        jsonObject.put("proof",profff);

   //设置请求头
        System.out.println("jsonObject :"+jsonObject);
        Map header = new HashMap();
        header.put("apitoken",acctoken);
        String ans = HttpPostJson.send(url,header, jsonObject);
        Map<String, Object> map = (Map<String, Object>) JSON.parse(ans);
        int code=Integer.parseInt(map.get("code").toString());
        if(code==0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        System.out.println(map.get("code"));

    }*/
   /* public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("日本");
        list.add("中国");
        list.add("美国");
        list.add("德国");

        //把随机取得的数据存储在 listRandom 中
        List<String> listRandom = new ArrayList<String>();

        //随机取出n条不重复的数据,这里我设置随机取3条数据
        for (int i = 3; i >=1; i--) {
            Random random = new Random();
            Math.random();
            //在数组大小之间产生一个随机数 j
            int j = random.nextInt(list.size());
            //取得list 中下标为j 的数据存储到 listRandom 中
            listRandom.add(list.get(j));
            //把已取到的数据移除,避免下次再次取到出现重复
            list.remove(j);
        }
        //循环取出 listRandom 中的数据
        System.out.println("list +"+list);
        System.out.println("listRandom +"+listRandom);
        for(String l:listRandom) {
            System.out.println(l);
        }
    }*/


    @Override
    public void changetime() {
        System.out.println("changetime");
        Date date=new Date();
        IssueExample issueExample=new IssueExample();
        issueExample.createCriteria().andStarttimeLessThanOrEqualTo(date).andStateEqualTo(0);
        List<Issue>issueList=issueDao.selectByExample(issueExample);
        if(issueList.size()>0){
            for (Issue issue : issueList) {
                issue.setState(1);
                issueDao.updateByPrimaryKeySelective(issue);
            }
        }
    }

    @SneakyThrows
    @Override
    public void compound(Integer logUserPid,String ids) {
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andTypeEqualTo(0).andIsdeployEqualTo(1);
        List<Collection>collectionList=collectionDao.selectByExample(collectionExample);
        System.out.println(" 111");
        System.out.println(ids);
        if(collectionList.size()>0){
            System.out.println("collectionList.size() "+collectionList.size());
            Random r = new Random();
            int preNumber = r.nextInt(collectionList.size());
            System.out.println("preNumber "+preNumber);
            Collection collection111=collectionList.get(preNumber);
            if(collection111!=null){
                String[] as = ids.split(",");
                List<Integer> inte=new ArrayList<>();
                for (int i = 0; i < as.length; i++) {
                    //System.out.println(as[i]);
                    inte.add(Integer.parseInt(as[i]));
                }
                System.out.println("logUserPid :"+logUserPid);
                System.out.println("inte :"+inte);
                UserGrantExample userGrantExample=new UserGrantExample();
                userGrantExample.createCriteria().andUseridEqualTo(logUserPid).andIdIn(inte);
                List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
                if(userGrantList.size()>0){
                    for (UserGrant userGrant : userGrantList) {
                        userGrantDao.deleteByPrimaryKey(userGrant.getId());
                    }
                }


                String sender= collection111.getContractaddress();
                String from= collection111.getContractaddress();
                BigInteger ddcid= new BigInteger(collection111.getDdcid().toString());
                String to= usersDao.selectByPrimaryKey(logUserPid).getAddress();
                String projectid=dictService.getValue("projectid");


                client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/"+projectid+"/evmrpc");
                //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
                //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
                byte[] data = {10, 10, 10};
                //System.out.println(ddc1155Service.safeTransferFrom(sender,from, to,ddcid,new BigInteger("1"),data));
                String blockhash=ddc1155Service.safeTransferFrom(sender,from, to,ddcid,new BigInteger("1"),data);
                System.out.println("blockhash "+blockhash);


                String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                UserGrant newusergrant=new UserGrant();
                newusergrant.setUserid(logUserPid);
                newusergrant.setCollid(collection111.getId());
                newusergrant.setNumberno(orderno);

                newusergrant.setHashs(blockhash);
                newusergrant.setCreatetime(new Date());
                newusergrant.setGranttype(2);
                newusergrant.setCotype(2);
                newusergrant.setCreatetime(new Date());
                newusergrant.setType(0);
                newusergrant.setAlbumid(collection111.getAlbumid());
                newusergrant.setAlbumname(collection111.getAlbumname());
                newusergrant.setCollectiontype(0);
                userGrantDao.insertSelective(newusergrant);


            }


        }


    }

    @Override
    public Map bindbanone(Integer userId,RealNameParam realNameParam, HttpServletResponse response) {
        fosun.sumpay.merchant.integration.core.request.Request request2 = new fosun.sumpay.merchant.integration.core.request.Request();

        String order_no =  DateUtils.getSecondTimestampTwo(new Date()) + ((int) (Math.random() * 1000)) + "".trim();

        Map newmap=new HashMap();
       // newmap.put("accountNo",realNameParam.getAccountNo().trim());
        newmap.put("name",realNameParam.getName().trim());
        newmap.put("idCardCode",realNameParam.getIdCardCode().trim());
        /*newmap.put("bankPreMobile",realNameParam.getBankPreMobile().trim());
        newmap.put("bankname",realNameParam.getBankname().trim());*/
        newmap.put("uid",userId);
        newmap.put("order_no",order_no);


        Map map=new HashMap();
        map.put("order_no",order_no);
        this.processSignSendMsg(newmap,request2);

        SumpayService ss22 = new SumpayServiceImpl();
        Map<String, String> res22 = ss22.execute(request2);
        System.out.println("res22  :"+res22);
        if ("000000".equals(res22.get("resp_code"))) {

            map.put("type",1);

        } else {
          /*  response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);*/
            map.put("type",0);
        }

        return map;
    }

    @SneakyThrows
    @Override
    public Map bindbantwo(Integer userId, HttpServletRequest request, HttpServletResponse response) {
        fosun.sumpay.merchant.integration.core.request.Request request2 = new fosun.sumpay.merchant.integration.core.request.Request();

        Map newmap=new HashMap();
        newmap.put("accountNo",request.getParameter("accountNo").trim());
        newmap.put("name",request.getParameter("name").trim());
        newmap.put("idCardCode",request.getParameter("idCardCode").trim());
        newmap.put("bankPreMobile",request.getParameter("bankPreMobile").trim());
        newmap.put("bankname",request.getParameter("bankname").trim());

        newmap.put("user_id",userId);
        newmap.put("order_no",request.getParameter("order_no").trim());
        newmap.put("verify_code",request.getParameter("code").trim());

        Map map=new HashMap();

        this.processSignValidMsg(newmap,request2);

        SumpayService ss22 = new SumpayServiceImpl();
        Map<String, String> res22 = ss22.execute(request2);
        System.out.println("res22  :"+res22);
        if ("000000".equals(res22.get("resp_code"))) {


            String url = "https://slycard3.market.alicloudapi.com/bankcard3check";
            String appCode = "fe5bb6e4425f4135b9668b24ce5f507c";

            Map<String, String> params = new HashMap<>();
            params.put("idcard", request.getParameter("idCardCode").trim());
            params.put("name", request.getParameter("name").trim());
            params.put("bankcard", request.getParameter("accountNo").trim());
            System.out.println("params :"+params);

            String result =get(appCode, url, params);
            System.out.println("result "+result);
            com.alibaba.fastjson.JSONObject codeobj = com.alibaba.fastjson.JSONObject.parseObject(result);
            int code=Integer.parseInt(codeobj.get("code").toString());
            com.alibaba.fastjson.JSONObject data=com.alibaba.fastjson.JSONObject.parseObject(codeobj.get("data").toString());
            int end=Integer.parseInt(data.get("result").toString());
            String msg=data.get("msg").toString();
            if(code==200){
                if(end==0){
                    map.put("type",1);
                    map.put("msg","实名成功");
                    Users users=usersDao.selectByPrimaryKey(userId);
                    users.setRealnametype(2);
                    users.setRealname(request.getParameter("name").trim());
                    users.setRealno(request.getParameter("idCardCode").trim());
                    users.setBindCardId(res22.get("bind_card_id"));
                    usersDao.updateByPrimaryKeySelective(users);

                    Bank bank=new Bank();
                    bank.setBankname(newmap.get("bankname").toString());
                    bank.setAccountno(newmap.get("accountNo").toString());
                    bank.setName(newmap.get("name").toString());
                    bank.setIdcardcode(newmap.get("idCardCode").toString());
                    bank.setBankpremobile(newmap.get("bankPreMobile").toString());
                    bank.setUid(userId);
                    bank.setCardid("-----------------------");


                }else{
                    map.put("type",0);
                    map.put("msg",msg);
                }
            }else{
                map.put("type",0);
                map.put("msg",msg);
            }


           // map.put("type",1);

        } else {
          /*  response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);*/
            map.put("type",0);
            map.put("msg","失败");
        }

        return map;
    }


    @SneakyThrows
    @Override
    public Map getrechargeone(Integer logUserPid, BigDecimal count,String code,HttpServletResponse response) {
        Users users11=usersDao.selectByPrimaryKey(logUserPid);
        if(users11.getRealnametype()!=2){
            throw new CloudException(ExceptionConstant.请先实名);
        }
        if(count.compareTo(new BigDecimal(0))<1){
            throw new CloudException(ExceptionConstant.金额错误);
        }
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + users11.getPhoneNumber();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }

        Map endmap=new HashMap();
        Map map=new HashMap();
        fosun.sumpay.merchant.integration.core.request.Request request2 = new fosun.sumpay.merchant.integration.core.request.Request();
        //request.setAttribute("merId","111");
        Users users=usersDao.selectByPrimaryKey(logUserPid);


        map.put("userid",logUserPid);
        map.put("bind_card_id",users.getBindCardId());
        map.put("id_card_no",users.getRealno());
        map.put("realname",users.getRealname());
        map.put("count",count.setScale(2, BigDecimal.ROUND_CEILING));
        System.out.println("map :"+map);
        this.processSendMsg(map, request2);

        SumpayService ss = new SumpayServiceImpl();
        Map<String, String> res = ss.execute(request2);
        System.out.println("res :"+res);
        if ("000000".equals(res.get("resp_code"))) {
            String sumpay_order_apply_response=res.get("sumpay_order_apply_response");
            JSONObject object = JSONObject.parseObject(sumpay_order_apply_response);
            System.out.println("object :"+object);
            String orderno=object.getString("order_no");

            Rechargelist rechargelist=new Rechargelist();
            rechargelist.setUserid(logUserPid);
            rechargelist.setMoney(count.setScale(2, BigDecimal.ROUND_CEILING));
            rechargelist.setOrderno(orderno);
            rechargelistDao.insertSelective(rechargelist);

            endmap.put("order_no",orderno);
            endmap.put("type",1);
        } else {
            endmap.put("type",0);
            endmap.put("order_no",0);
        }
        return endmap;

    }

    @SneakyThrows
    @Override
    public void getrechargetwo(Integer logUserPid , String order_no,String code, HttpServletResponse response) {
        fosun.sumpay.merchant.integration.core.request.Request request2 = new fosun.sumpay.merchant.integration.core.request.Request();

        HashMap newmap=new HashMap();
        newmap.put("order_no",order_no);
        newmap.put("verify_code",code);
        Map map=new HashMap();


        this.processPay(newmap,request2);

        SumpayService ss22 = new SumpayServiceImpl();
        Map<String, String> res22 = ss22.execute(request2);
        System.out.println("res22  :"+res22);
        if ("000000".equals(res22.get("resp_code"))) {
            response.sendRedirect(res22.get("redirect_url"));
            //sumpay_order_apply_response
            String sumpay_order_apply_response=res22.get("sumpay_order_apply_response");
            JSONObject object = JSONObject.parseObject(sumpay_order_apply_response);
            System.out.println("object :"+object);
            String orderno=object.getString("order_no");
            BigDecimal success_amount=new BigDecimal(object.getString("success_amount"));
            System.out.println("orderno :"+orderno);
            System.out.println("success_amount :"+success_amount);




          /*  OrderQueryRequest req = new OrderQueryRequest();
            req.setApp_id(MER_ID);
            req.setFormat("JSON");
            req.setMer_no(MER_ID);
            req.setOrder_no(orderno);
            req.setService("fosun.sumpay.api.trade.order.search.merchant");

            //req.setTerminal_type(request.getParameter("terminal_type"));
            req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            req.setVersion("1.0");

            request2.setCharset("UTF-8");// 取jsp的请求编码
            request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
            request2.setPassword("Guolanhongji3007"); //
            request2.setPrivateKeyPath("./ttfsiyao.pfx");
            request2.setPublicKeyPath("./TTFPublicKey.cer");
            request2.setUrl(TEST_URL);
            //request2.setDomain(request.getParameter("domain"));
            request2.setAesEncodedWords(req.getAesEncodedWords());
            request2.setBase64EncodedWords(req.getBase64EncodedWords());
            request2.setCharsetChangeWords(req.getCharsetChangeWords());
*/

           /* SumpayService ss = new SumpayServiceImpl();
            Map<String, String> res = ss.execute(request2);
            System.out.println("res  :"+res);
            if ("000000".equals(res.get("resp_code"))) {
                Rechargelist rechargelist=new Rechargelist();
                rechargelist.setUserid(logUserPid);
                rechargelist.setMoney(success_amount);
                rechargelist.setOrderno(order_no);
                rechargelistDao.insertSelective(rechargelist);

              *//*  Users users=usersDao.selectByPrimaryKey(logUserPid);
                BigDecimal usermoney=users.getBalance().add(success_amount);
                users.setBalance(usermoney);
                usersDao.updateByPrimaryKeySelective(users);
                BalanceRecord balanceRecord=new BalanceRecord();
                balanceRecord.setName("充值");
                balanceRecord.setCount(success_amount);
                balanceRecord.setUserid(logUserPid);
                balanceRecord.setCreatetime(new Date());
                balanceRecordDao.insertSelective(balanceRecord);*//*
                map.put("type",1);
                map.put("msg","充值成功");
                //response.sendRedirect(res.get("redirect_url"));
            } else {
                map.put("type",0);
                map.put("msg","充值失败");
            }*/

            Rechargelist rechargelist=new Rechargelist();
            rechargelist.setUserid(logUserPid);
            rechargelist.setMoney(success_amount);
            rechargelist.setOrderno(order_no);
            rechargelistDao.insertSelective(rechargelist);
            map.put("type",1);
            map.put("msg","充值成功");



        } else {

            map.put("type",0);
            map.put("msg","充值失败");
        }
        // return map;
    }

    @Override
    public Map money(int logUserPid) {
        Users users=usersDao.selectByPrimaryKey(logUserPid);
        BigDecimal money=users.getMoney();
        Map map=new HashMap();
        MoneyrecordExample moneyrecordExample=new MoneyrecordExample();
        moneyrecordExample.createCriteria().andUseridEqualTo(logUserPid);
        moneyrecordExample.setOrderByClause("id desc");
        List<Moneyrecord>moneyrecordList=moneyrecordDao.selectByExample(moneyrecordExample);
        List list=new ArrayList();
        List list1=new ArrayList();
        List list2=new ArrayList();
        if(moneyrecordList.size()>0){
            for (Moneyrecord moneyrecord : moneyrecordList) {
                Map newmap=new HashMap();
                newmap.put("name",moneyrecord.getName());
                newmap.put("price",moneyrecord.getPrice());
                newmap.put("time",DateUtils.getDateToStr(moneyrecord.getTime(), DateUtils.DATETIME_FORMAT));
                if(moneyrecord.getPrice().compareTo(new BigDecimal(0))==-1){
                    list1.add(newmap);
                }
                if(moneyrecord.getPrice().compareTo(new BigDecimal(0))==1){
                    list2.add(newmap);
                }
                list.add(newmap);
            }
        }
        map.put("list",list);
        map.put("list1",list2);
        map.put("list2",list1);
        map.put("money",money);
        return map;
    }

    @Override
    public Map moneytask(int logUserPid) {
        Map map=new HashMap();
        Date today=DateUtils.getDayBegin();
        //查询是否有做注册任务
        TaskExample taskExample=new TaskExample();
        taskExample.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(0).andCreattimeGreaterThanOrEqualTo(today);
        List<Task>taskList=taskDao.selectByExample(taskExample);
        if(taskList.size()>0){
            map.put("regist",1);
        }else{
            map.put("regist",0);
        }
        //是否实名
        TaskExample taskExample11=new TaskExample();
        taskExample11.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(1);
        List<Task>taskList11=taskDao.selectByExample(taskExample11);
        if(taskList11.size()>0){
            map.put("realname",1);
        }else{
            map.put("realname",0);
        }
        //是否邀请
        TaskExample taskExample22=new TaskExample();
        taskExample22.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(2);
        List<Task>taskList22=taskDao.selectByExample(taskExample22);
        if(taskList22.size()>0){
            map.put("invite",1);
            map.put("number1",taskList22.size());
        }else{
            map.put("invite",0);
            map.put("number1",0);
        }

        //是否邀请
        TaskExample taskExample33=new TaskExample();
        taskExample33.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(3);
        List<Task>taskList33=taskDao.selectByExample(taskExample33);
        if(taskList33.size()>0){
            map.put("buysize",1);
            map.put("number2",taskList33.size());
        }else{
            map.put("buysize",0);
            map.put("number2",0);
        }


        return map;
    }

    @Override
    public Map homemessage() {
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("createtime desc");
        List<Message> messages = messageDao.selectByExample(messageExample);
        Map map=new HashMap();
        if(messages.size()>0){
            Message message=messages.get(0);
            map.put("name",message.getTitle());
            map.put("id",message.getId());
        }

        return map;
    }

    @Override
    public void dailytask(int logUserPid) {
        Users users=usersDao.selectByPrimaryKey(logUserPid);
        Date today=DateUtils.getDayBegin();
        //查询是否有做注册任务
        TaskExample taskExample=new TaskExample();
        taskExample.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(0).andCreattimeGreaterThanOrEqualTo(today);
        List<Task>taskList=taskDao.selectByExample(taskExample);
        if(taskList.size()>0){
            throw new CloudException(ExceptionConstant.今日已签到);
        }else{
            Task task=new Task();
            task.setState(0);
            task.setCreattime(new Date());
            task.setMoney(new BigDecimal(2));
            task.setUserid(logUserPid);
            taskDao.insertSelective(task);

            Moneyrecord moneyrecord=new Moneyrecord();
            moneyrecord.setName("签到");
            moneyrecord.setPrice(new BigDecimal(2));
            moneyrecord.setTime(new Date());
            moneyrecord.setUserid(logUserPid);
            moneyrecordDao.insertSelective(moneyrecord);


            users.setMoney(users.getMoney().add(new BigDecimal(2)));
            usersDao.updateByPrimaryKeySelective(users);
        }
    }

    @Override
    public List myticket(int logUserPid) {
        List list=new ArrayList();
        TicketExample ticketExample=new TicketExample();
        ticketExample.createCriteria().andUseridEqualTo(logUserPid);
        ticketExample.setOrderByClause("id desc");
        List<Ticket>ticketList=ticketDao.selectByExample(ticketExample);
        if(ticketList.size()>0){
            for (Ticket ticket : ticketList) {
                Collection collection=collectionDao.selectByPrimaryKey(ticket.getColid());
                IssueExample issueExample=new IssueExample();
                issueExample.setOrderByClause("id desc");
                issueExample.createCriteria().andCollidEqualTo(ticket.getColid());
                List<Issue>issueList=issueDao.selectByExample(issueExample);
                Issue issue=issueList.get(0);
              /*  Draw draw=drawDao.selectByPrimaryKey(ticket.getDrawid());
                Issue issue=issueDao.selectByPrimaryKey(draw.getIssueid());*/

                Map map=new HashMap();
                map.put("id",ticket.getId());
                map.put("state",ticket.getState());
                map.put("name",collection.getName());
                map.put("img",ImgEnum.img.getUrl()+collection.getDrawimg());
                map.put("time",DateUtils.getDateToStr(issue.getEndtime(), DateUtils.TIME_FORMAT1));
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public void changeendtime() {
        System.out.println("changeendtime");
        Date date=new Date();
        IssueExample issueExample=new IssueExample();
        issueExample.createCriteria().andEndtimeLessThanOrEqualTo(date).andStateEqualTo(1);
        List<Issue>issueList=issueDao.selectByExample(issueExample);
        if(issueList.size()>0){
            for (Issue issue : issueList) {
                issue.setState(2);
                issueDao.updateByPrimaryKeySelective(issue);
                //查询所有的draw
                DrawExample drawExample=new DrawExample();
                drawExample.createCriteria().andIssueidEqualTo(issue.getId());
                List<Draw>drawList=drawDao.selectByExample(drawExample);
                if(drawList.size()>0){
                    for (Draw draw : drawList) {
                        TicketExample ticketExample=new TicketExample();
                        ticketExample.createCriteria().andDrawidEqualTo(draw.getId()).andStateEqualTo(1);
                        List<Ticket>ticketList=ticketDao.selectByExample(ticketExample);
                        if(ticketList.size()>0){
                            for (Ticket ticket : ticketList) {
                                    ticket.setState(3);
                                    ticketDao.updateByPrimaryKeySelective(ticket);
                            }
                        }
                    }
                }
               // Draw draw=drawDao.selectByPrimaryKey(i)


            }
        }
    }

    @Override
    public Map newinvite() {
        Map map = new HashMap();
        map.put("img",ImgEnum.img.getUrl() + chatDao.selectByPrimaryKey(3).getImg());
        map.put("content",chatDao.selectByPrimaryKey(1).getContent());
        return map;

    }

    @Override
    public void sendhash() { System.out.println("sendhash :");
        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));

            //查询所有已支付未发放的藏品发放
            UserGrantExample userGrantExample=new UserGrantExample();
            userGrantExample.createCriteria().andTypeEqualTo(1).andCotypeEqualTo(1).andGranttypeEqualTo(1);
            List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
            if(userGrantList.size()>0){
                for (UserGrant userGrant : userGrantList) {
                    if(chaintype==0){
                        homeService.mintnft(userGrant.getCollid(),userGrant.getId());
                    }else{
                        homeService.thmintnft(userGrant.getCollid(),userGrant.getId());
                    }


                }
            }



        
    }

    @Override
    public Map myorderdetails(int logUserPid, int id) {

        Map map = new HashMap();


    UserGrant userGrant=userGrantDao.selectByPrimaryKey(id);
    if(userGrant!=null){
        Collection collections = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("coltype",collections.getType());
        map.put("numberno", userGrant.getNumberno());
        map.put("buyprice", collections.getPrice());
        map.put("createtime", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.TIME_FORMAT1));

        if(userGrant.getSendtype()==3){
            map.put("sendtype",1);
        }else{
            map.put("sendtype",0);
        }
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.createCriteria().andNoEqualTo(userGrant.getNumberno());
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        if(hideRecordList.size()>0){
            HideRecord hideRecord=hideRecordList.get(0);
            if(hideRecord.getMs().equals("未付款")){
                map.put("type",0);
            }else{
                map.put("type",1);
            }
        }



        map.put("name", collections.getName());
        map.put("img", ImgEnum.img.getUrl() + collections.getImg());
    }

        return map;


    }

   /* @Override
    public void changetickttime() {
        //查询所有ticket

        TicketExample ticketExample=new TicketExample();
        ticketExample.createCriteria().andStateEqualTo(1);
        List <Ticket>ticketList=ticketDao.selectByExample(ticketExample);

    }*/


    /**
     * 签名处理

     */
    public String signMsg(String plaintext, String privateKeyPath, String passWord, String charset) {
        try {
            InputStream keyFile = new FileInputStream(new File(privateKeyPath));
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try {
                ks.load(keyFile, passWord.toCharArray());
            } catch (Throwable ex) {
                if (keyFile != null)
                    keyFile.close();
                throw new RuntimeException("加载私钥失败",ex);
            }
            Enumeration<String> myEnum = ks.aliases();
            String keyAlias = null;
            RSAPrivateCrtKey prikey = null;
            //* IBM JDK必须使用While循环取最后一个别名，才能得到个人私钥别名 *//*
            while (myEnum.hasMoreElements()) {
                keyAlias = myEnum.nextElement();
                if (ks.isKeyEntry(keyAlias)) {
                    prikey = (RSAPrivateCrtKey) ks.getKey(keyAlias, passWord.toCharArray());
                    break;
                }
            }
            if (prikey == null) {
                throw new RuntimeException("没有找到匹配私钥");
            } else {
                Signature sign = Signature.getInstance("SHA256withRSA");
                sign.initSign(prikey);
                sign.update(plaintext.getBytes(charset));
                return Base64.encodeBase64String(sign.sign());
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException("签名失败",e);
        }
    }

    public static String composePic(String templatePath, String seedPath, String name, String code) {
        try {
            String picName = name;
            //合成文件路径
            String path = ImgEnum.img.getPath() + File.separator + picName;
            //---------------------------------合成图片步骤-----------------------------
            //背景
            File templateFlie = new File(templatePath);
            BufferedImage bg = ImageIO.read(templateFlie);//读取背景图片
            int height = bg.getHeight();//背景图片的高
            int width = bg.getWidth();  //背景图片的宽
            BufferedImage qcCode = ImageIO.read(new File(seedPath));  //读取图片
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//创建画布
            Graphics g = img.getGraphics();//生成画笔 开启画图
            // 绘制背景图片
            g.drawImage(bg.getScaledInstance(width, height, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图
            //绘制二维码图片  定位到背景图的右下角
            g.drawImage(qcCode.getScaledInstance(200, 200, Image.SCALE_DEFAULT), 580, 1150, null); // 绘制缩小后的图
            //最后一个参数用来设置字体的大小
//            Font f = new Font("新宋体", Font.BOLD, 55);
//            Color mycolor = Color.black;//new Color(0, 0, 255);
//            g.setColor(mycolor);
//            g.setFont(f);
//            //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//            g.drawString("邀请码 : " + code, 350, 1000);
//            g.drawString(, 400, 1400);

            //关掉画笔
            g.dispose();
            ImageIO.write(img, "jpg", new File(path));
            //返回合成图片的路径
            return ImgEnum.img.getUrl() + picName;
        } catch (Exception E) {
            throw new CloudException(ExceptionConstant.生成失败);
        }
    }


    /**
     * 生成邀请二维码
     *
     * @param registerCode
     * @return
     */
    public String createUserShareImg1(String registerCode) {
        String dicturl=dictService.getValue("url");
        System.out.println("生成二维码");
        String fileName = "InvQRCode" + registerCode + ".jpg";
        File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//生成图片位置
        //String url = ImgEnum.img.getUrl()+ "http://nftxcsc.com:8100/#/?uid=" + registerCode;
        String url = dicturl+ "/#/pages/login/register?uid=" + registerCode;
        boolean falg = QRcode.CreatQRCode(QrCodeFile, 100, 100, url);
        if (falg) {
            return fileName;
        } else {
            return null;
        }
    }
}