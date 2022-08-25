package com.shitouren.core.service;


import com.alibaba.fastjson.JSON;
import com.huifu.adapay.Adapay;
import com.huifu.adapay.model.MerConfig;
import com.shitouren.core.PayDemo.MainDemo;
import com.shitouren.core.PayDemo.PaymentRegionAndRequestTimeOutDemo;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.EumUser;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.bean.param.user.NewUserLoginParam;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.utils.*;
import lombok.SneakyThrows;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/1 21:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UsersDao usersDao;
    @Autowired(required = false)
    GiveUserDao giveUserDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    WithdrawalDao withdrawalDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired
    HomeService homeService;
    @Autowired
    DictService dictService;
    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;

    @Override
    public void distribution() {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andSzcountEqualTo(null);
        List<Users> users = usersDao.selectByExample(usersExample);
        for (Users user : users) {
            Users users1 = new Users();
            users1.setUserId(user.getUserId());
            users1.setSzcount(0);
            usersDao.updateByPrimaryKeySelective(users1);
        }
    }

    @Override
    public Users passworduserLogin(NewUserLoginParam param) {
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getUserAccount());
        List<Users> userList = usersDao.selectByExample(query);
        if (!ListUtil.isValidateList(userList)) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        Users user = userList.get(0);


        if (!user.getPasswd().equals(MD5Util.MD5Encode(param.getUserPassword()))) {
            throw new CloudException(ExceptionConstant.账号或密码错误);
        }
        if (EumUser.UserStatus.禁用.getValue() == Integer.parseInt(user.getStatusId())) {
            throw new CloudException(ExceptionConstant.账号已被禁用);
        }
        return user;
    }
    /**
     * 创建邀请码
     */
    public String createCode() {
        String filename = "";
        for (int i = 0; i < 100000; i++) {
            //随机字符串
            filename = RandomStringUtils.randomAlphanumeric(6);
            UsersExample usersExample = new UsersExample();
            usersExample.createCriteria().andRegisterCodeEqualTo(filename);
            List<Users> usersList = usersDao.selectByExample(usersExample);
            if (usersList.size() < 1) {
                break;
            }
        }
        return filename;
    }

    @SneakyThrows
    @Override
    public Users userLogin(UserLoginParam param) {

        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getUserAccount());
        List<Users> userList = usersDao.selectByExample(query);
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + param.getUserAccount();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);

        if (!ListUtil.isValidateList(userList)) {
            //throw new CloudException(ExceptionConstant.账号不存在);

            if (StringUtil.getLength(param.getUserAccount()) < 11) {
                throw new CloudException(ExceptionConstant.手机号有误);
            }

            Map map=homeService.hqaddress(param.getUserAccount(),param.getCode());

            Users user = new Users();
            if (param.getUid().length() > 0) {
                String uid = param.getUid();
                UsersExample usersExample1=new UsersExample();
                usersExample1.createCriteria().andRegisterCodeEqualTo(uid);
                List<Users>usersList=usersDao.selectByExample(usersExample1);
                if(usersList.size()>0){
                    Users users =usersList.get(0);
                    if (users != null) {
                        int a = users.getInvitationcount() + 1;

                        users.setInvitationcount(a);
                        usersDao.updateByPrimaryKeySelective(users);
                        user.setInvitationId(users.getUserId());
                    }
                }

            }

            String filename = RandomStringUtils.randomAlphanumeric(6);
            user.setNickName("藏家" + filename);//昵称
            user.setPhoneNumber(param.getUserAccount());//手机号
            user.setCreateTime(new Date());//创建时间


            user.setPrivatekey(map.get("privateKey").toString());//密码
            user.setAddress(map.get("address").toString());//创建时间
            user.setThaddress(map.get("thaddress").toString());
            user.setThprivatekey(map.get("thprivateKey").toString());
            user.setUserkey(param.getCode());
            user.setRegisterCode(createCode());//邀请码
            usersDao.insertSelective(user);//创建用户




            return user;
        }else{

            Users user = userList.get(0);
            System.out.println("user: "+user.getStatusId());
            int userstate =Integer.parseInt(user.getStatusId());
            if(userstate==1){
                throw new CloudException(ExceptionConstant.账号已被禁用);
            }

            return user;
        }

    }

    @Override
    public Users userqhLogin(int UserId) {
        UsersExample query = new UsersExample();
        query.createCriteria().andUserIdEqualTo(UserId);
        List<Users> userList = usersDao.selectByExample(query);
        if (userList.size() < 1) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        Users user = userList.get(0);
        if (Integer.parseInt(user.getStatusId()) == 1) {
            throw new CloudException(ExceptionConstant.账号已被禁用);
        }
        return user;
    }

    @Override
    public void userRegister(UserRegisterParam param) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andPhoneNumberEqualTo(param.getPhone());
        if (StringUtil.getLength(param.getPhone()) < 11) {
            throw new CloudException(ExceptionConstant.手机号有误);
        }
        //校验密码
        if (!param.getPassword().equals(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + param.getPhone();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getPhone());
        if (usersDao.countByExample(query) != 0) {
            List<Users> userList = usersDao.selectByExample(query);
            if (ListUtil.isValidateList(userList)) {
                throw new CloudException(ExceptionConstant.账号已存在);
            }
        }
        /**
         * 注册用户
         */
        Users user = new Users();
        if (param.getUid().length() > 0) {
            String uid = param.getUid();
            UsersExample usersExample1=new UsersExample();
            usersExample1.createCriteria().andRegisterCodeEqualTo(uid);
            List<Users>usersList=usersDao.selectByExample(usersExample1);
            if(usersList.size()>0){
                Users users =usersList.get(0);
                if (users != null) {
                    int a = users.getInvitationcount() + 1;

                    users.setInvitationcount(a);
                    usersDao.updateByPrimaryKeySelective(users);
                    user.setInvitationId(users.getUserId());
                }
            }

        }
        Map map=homeService.hqaddress(param.getPhone(),param.getCode());

        String filename = RandomStringUtils.randomAlphanumeric(6);
        user.setNickName("藏家" + filename);//昵称
        user.setPhoneNumber(param.getPhone());//手机号
        user.setPasswd(MD5Util.MD5Encode(param.getPassword()));//密码
        user.setCreateTime(new Date());//创建时间
        user.setPrivatekey(map.get("privateKey").toString());//密码
        user.setAddress(map.get("address").toString());//创建时间
        user.setThaddress(map.get("thaddress").toString());
        user.setThprivatekey(map.get("thprivateKey").toString());
        user.setRegisterCode(createCode());//邀请码
        user.setUserkey(param.getCode());
        usersDao.insertSelective(user);//创建用户

       /* //奖励鲸币
        Moneyrecord moneyrecord=new Moneyrecord();
        moneyrecord.setPrice(new BigDecimal(2));
        moneyrecord.setName("注册赠送");
        moneyrecord.setTime(new Date());
        moneyrecord.setUserid(user.getUserId());
        moneyrecordDao.insertSelective(moneyrecord);

        Task task=new Task();
        task.setUserid(user.getUserId());
        task.setMoney(new BigDecimal(2));
        task.setCreattime(new Date());
        task.setState(0);
        taskDao.insertSelective(task);*/

    }

    @Override
    public void regh5(UserRegisterParam param) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andPhoneNumberEqualTo(param.getPhone());
        if (StringUtil.getLength(param.getPhone()) < 11) {
            throw new CloudException(ExceptionConstant.手机号有误);
        }
        //校验密码
        if (!param.getPassword().equals(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + param.getPhone();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getPhone());
        if (usersDao.countByExample(query) != 0) {
            List<Users> userList = usersDao.selectByExample(query);
            if (ListUtil.isValidateList(userList)) {
                throw new CloudException(ExceptionConstant.账号已存在);
            }
        }
        /**
         * 注册用户
         */
        Users user = new Users();
        if (param.getUid().length() > 0) {
            int uid = Integer.valueOf(param.getUid());
            Users users = usersDao.selectByPrimaryKey(uid);
            if (users != null) {
                int a = users.getInvitationcount() + 1;

                users.setInvitationcount(a);
                usersDao.updateByPrimaryKeySelective(users);
                user.setInvitationId(uid);
            }
        }
        String filename = RandomStringUtils.randomAlphanumeric(6);
        user.setNickName("藏家" + filename);//昵称
        user.setPhoneNumber(param.getPhone());//手机号
        user.setPasswd(MD5Util.MD5Encode(param.getPassword()));//密码
        user.setCreateTime(new Date());//创建时间
        user.setPrivatekey(param.getPrivateKey());//密码
        user.setAddress(param.getAddress());//创建时间
        user.setRegisterCode(createCode());//邀请码
        usersDao.insertSelective(user);//创建用户
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public void userRestPwd(UserRestPwdParam param) {
        //校验密码
        if (!param.getPassword().equals(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.忘记密码.getValue() + param.getPhone();
        System.out.println("loginCodekey=" + loginCodekey);
        String str = cloudRedisTemplate.get(loginCodekey);
        System.out.println("phnoe=" + param.getPhone());
        System.out.println("str=" + str);
        System.out.println("getCode=" + param.getCode());
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getPhone());
        List<Users> userList = usersDao.selectByExample(query);
        if (!ListUtil.isValidateList(userList)) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        Users user = userList.get(0);
        user.setPasswd(MD5Util.MD5Encode(param.getPassword()));
        usersDao.updateByPrimaryKeySelective(user);
    }

    public void updateAvatar(Integer userId, String avatar) {
        Users user = usersDao.selectByPrimaryKey(userId);
        user.setHeadPrtraits(avatar);
        usersDao.updateByPrimaryKeySelective(user);
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
        String url = "http:///#/?registerCode=" + registerCode;
        boolean falg = QRcode.CreatQRCode(QrCodeFile, 200, 200, url);
        if (falg) {
            return ImgEnum.QrCode.getUrl() + fileName;
        } else {
            return null;
        }
    }

    @Override
    public void createimg() {
        for (int i = 10000001; i < 10000121; i++) {
            String fileName = "InvQRCode" + i + ".jpg";
            File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//生成图片位置
            String url = "http:///#/?registerCode=" + i;
            boolean falg = QRcode.CreatQRCode(QrCodeFile, 200, 200, url);
        }
    }

    @Override
    public String TestPay() throws Exception {
        UserGrant userGrant = new UserGrant();
        userGrant.setUserid(1);
        userGrant.setCollid(1);
        //产生随机字符串
        String randnum = DateUtilss.dateTimeNow() + getRandomString(6);
        userGrant.setNumberno(randnum);
        userGrant.setBuyprice(new BigDecimal(0.01));
        userGrant.setCreatetime(new Date());
        userGrant.setBuytime(new Date());
        userGrant.setGranttype(1);
        userGrant.setCotype(1);
        userGrant.setPaytype(1);
        userGrantDao.insertSelective(userGrant);
        //debug 模式，开启后有详细的日志
        Adapay.debug = true;
        //prodMode 模式，默认为生产模式，false可以使用mock模式
        Adapay.prodMode = true;
        /**
         * 初始化商户配置，服务器启动前，必须通过该方式初始化商户配置完成
         * apiKey为prod模式的API KEY
         * mockApiKey为mock模式的API KEY
         * rsaPrivateKey为商户发起请求时，用于请求参数加签所需要的RSA私钥
         */
        String apiKey = "api_live_8735f1de-a316-4579-9f42-3739b6edce7d";
        String mockApiKey = "api_test_79ae822c-c149-4527-a0b0-8b6803574430";
        String rsaPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJH74VwaH6D7CcY2XljzQZcACdEl6EEtY/76J2YpEZ35V7fZCGYrSsaMmB7pHsoe58dp/9jn4O40FAqTJCiAIAMJ+qBcCinMKKDW25z/ui4bjnLYRe00fIAGx0F+9RlAjFVxo7CwBZYKZVROlJHghKZ/yNTdSTM15UCkHWlxp3kxAgMBAAECgYBGK7rh2LTHXqQOIOgs/ZDTknMlk4EWg0kDsR7rHmaXdtlRSAOfiMPCuKYOhaQFJ+HlmvAeRSd7mJDhnGTPCvdXhLxKKZgylV4wLuesuj3N1L6Zgw8GdCW31RWZrhvpebGu46smvqycXWaa5PG/CEJNKE8wS16jFuYzj9f4xuCzIQJBAMUQeMV98Da/I8eaiHvr6Hm0XeM/nwQ2y9EH9F1PZtbi+xfmOWXT+jot99vHTLbBUEZ3Z2WKmY0EAFZf4+j6ot0CQQC9pJ4GL63kp7VZ0ueLLiXBegI4Y4D0BXCj1UC3cAh5qUh+bDKr3Of5eX3u40beoZFlWC8VnjRTjsewtU8QOphlAkAPdJ9EPgBdCtivivsxXOs6wF6suAyss++AUXNk7hlOuTagRTozNlDlxz0tS5BEtRyqqiq92AT6/llYbOTfHOkxAkEAmm1NfS4gRX1H0QBMKmsGtblby31DifJQGilSjM2/KQrvjHsg/BBkGiOTqA8NK8eXdVZM6Sat54jyFXRfEJEWfQJAAzo4oYrUTuya2XkmjjQjRpqu0NUFty8XpGrU1ahM4H3QDlLoS6G3ma6hEobevsCEclz2eb0rt45sPRAMr5w/jQ==";
        MerConfig merConfig = new MerConfig();
        merConfig.setApiKey(apiKey);
        merConfig.setApiMockKey(mockApiKey);
        merConfig.setRSAPrivateKey(rsaPrivateKey);
        Adapay.initWithMerConfig(merConfig);
        /**
         * 初始化完成，可以开始交易
         */
        String appId = "app_8638436d-9235-495e-bfed-99a5f14de1d9";
        Map mapss = PaymentRegionAndRequestTimeOutDemo.executePaymentTest1(appId, userGrant.getBuyprice().setScale(2, BigDecimal.ROUND_HALF_UP), "测试", userGrant.getNumberno());
        Map mapsss = (Map) mapss.get("expend");
        return mapsss.get("pay_info").toString();
    }

    @Override
    public void aaaa() throws Exception {
        WithdrawalExample withdrawalExample = new WithdrawalExample();
        withdrawalExample.createCriteria().andCountLessThan(new BigDecimal(0));
        List<Withdrawal> withdrawals = withdrawalDao.selectByExample(withdrawalExample);
        for (Withdrawal withdrawal : withdrawals) {
            BigDecimal bigDecimal = withdrawal.getCount().negate();
            withdrawal.setCount(bigDecimal);
            withdrawalDao.updateByPrimaryKeySelective(withdrawal);
        }

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
    public void updateTradePassWord(SysUserParam sysUserParam, String userAccount, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(userAccount);
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
        user.setTradePassWord(MD5Util.MD5Encode(newTradePassWord));
        usersDao.updateByPrimaryKeySelective(user);
    }

    public void updateNickname(Integer userId, String nickname) {
        Users user = usersDao.selectByPrimaryKey(userId);
        user.setNickName(nickname);
        usersDao.updateByPrimaryKeySelective(user);
    }

}
