package com.shitouren.core.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.google.gson.Gson;
import com.huifu.adapay.Adapay;
import com.huifu.adapay.model.MerConfig;
import com.shitouren.core.PayDemo.PaymentRegionAndRequestTimeOutDemo;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.controller.DDCSdkClient;
import com.shitouren.core.controller.SignEventTest;
import com.shitouren.core.utils.AliPayConfig;
import com.shitouren.core.utils.DateUtils;
import com.shitouren.core.utils.DeShanUtil;
import com.shitouren.core.utils.StringUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    BannerDao bannerDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    AlbumDao albumDao;
    @Autowired(required = false)
    IssueDao issueDao;
    @Autowired(required = false)
    ImglistDao imglistDao;
    @Autowired
    private WXAppPayService wxAppPayService;
    @Autowired
    MarketService marketService;

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


    @Override
    public List show(int userid,int collectionstype,int albumid,String search, int type) {
        int issale=Integer.parseInt(dictService.getValue("issale"));
        if(issale==0){
            throw new CloudException(ExceptionConstant.暂未开放);
        }

        List list = new ArrayList();
        CollectionExample collectionExample=new CollectionExample();
        CollectionExample.Criteria criteria11=collectionExample.createCriteria();
        criteria11.andTypeEqualTo(collectionstype);
        if(albumid!=0){
            criteria11.andAlbumidEqualTo(albumid);
        }
        if(search!=""){
            criteria11.andNameLike("%" + search + "%");
        }

        List<Collection> collections = collectionDao.selectByExample(collectionExample);
        if (collections.size() == 0) {
            return null;
        }
        List<Integer> integers1 = new ArrayList<>();
        for (Collection collection : collections) {
            integers1.add(collection.getId());
        }
        List<Integer> integers = new ArrayList<>();
        integers.add(7);
        integers.add(8);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        UserGrantExample.Criteria criteria=userGrantExample1.createCriteria();
        criteria.andTypeIn(integers).andCollidIn(integers1);


        if (type == 1) {
            userGrantExample1.setOrderByClause("sjtime desc");
        }
        if (type == 2) {
            userGrantExample1.setOrderByClause("sellprice asc");
        }


        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        System.out.println("----------------------------------");
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant) {
            Map map = new HashMap();
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            if (collection != null) {
                map.put("id", grant.getId());//id
                /*map.put("no", grant.getNumberno());//编号
                if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                    map.put("no", grant.getTruenumber());//编号
                }*/
                map.put("no", grant.getTruenumber()+"/"+collection.getLimits());//编号

                //map.put("state",grant.getType());
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("price", grant.getSellprice());//图片
                map.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());
                map.put("creatorname",collection.getPublisher());
                System.out.println("grant.getSellprice()=" + grant.getSellprice());
                map.put("type", grant.getType());//图片
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public List search(int userid, String search, int type, int row) {
        List list = new ArrayList();
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andNameLike("%" + search + "%");
        List<Collection> collections = collectionDao.selectByExample(collectionExample);
        if (collections.size() == 0) {
            return null;
        }
        List<Integer> integers1 = new ArrayList<>();
        for (Collection collection : collections) {
            integers1.add(collection.getId());
        }
        List<Integer> integers = new ArrayList<>();
        integers.add(7);
        integers.add(8);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andCollidIn(integers1).andTypeIn(integers);
        userGrantExample1.setPageNo(row);
        userGrantExample1.setPageSize(10);
        if (type == 1) {
            userGrantExample1.setOrderByClause("sjtime desc");
        }
        if (type == 2) {
            userGrantExample1.setOrderByClause("sjtime asc");
        }
        if (type == 3) {
            userGrantExample1.setOrderByClause("sellprice desc");
        }
        if (type == 4) {
            userGrantExample1.setOrderByClause("sellprice asc");
        }
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getNumberno());//编号
            if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                map.put("no", grant.getTruenumber());//编号
            }
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());

            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("price", grant.getSellprice());//图片
            map.put("type", grant.getType());//图片
            list.add(map);
        }
        return list;
    }

    @Override
    public Map details(int uerid, int id) {
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        IssueExample issueExample=new IssueExample();
        issueExample.createCriteria().andCollidEqualTo(userGrant.getCollid());
        List<Issue>issueList=issueDao.selectByExample(issueExample);
        if(issueList.size()>0){
            map.put("limits", issueList.get(0).getPresale());//限量
        }else{
            map.put("limits", collection.getLimits());//限量
        }

        map.put("id", userGrant.getId());//id
        map.put("name", collection.getName());//名称
        //map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
        map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
        map.put("price", userGrant.getSellprice());//价格
        map.put("details", collection.getDetails());//详情
        map.put("publisher", collection.getPublisher());//发行者
        map.put("creator", collection.getCreator());//创作者s
        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        if(chaintype==0){
            map.put("contractAddress",collection.getContractaddress());
        }else{
            map.put("contractAddress",collection.getThcontractaddress());
        }
        //map.put("contractAddress",collection.getContractaddress());
        map.put("biaoshi","ERC721");
        map.put("publisher", collection.getPublisher());//发行者
        //map.put("creator", collection.getCreator());//创作者
        map.put("colltype", collection.getType());//创作者
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//发行者
        map.put("minname",collection.getAlbumname());
        map.put("collid", collection.getId());//名称


        List list=new ArrayList();
        ImglistExample hideRecordExample=new ImglistExample();
        hideRecordExample.createCriteria().andAddressEqualTo(userGrant.getHashs());
        List<Imglist>hideRecordExampleList=imglistDao.selectByExample(hideRecordExample);
        if(hideRecordExampleList.size()>0){
            for (Imglist hideRecord : hideRecordExampleList) {
                Map map1=new HashMap();
                //map1.put("img",ImgEnum.img.getUrl()+hideRecord.getImg());
                Users users=userDao.selectByPrimaryKey(hideRecord.getUid());
                map1.put("address",users.getAddress());
                map1.put("name",users.getNickName());
                map1.put("img",ImgEnum.img.getUrl()+users.getHeadPrtraits());
                map1.put("time",DateUtils.getDateToStr(hideRecord.getStarttime(), DateUtils.TIME_FORMAT1));
                map1.put("price",hideRecord.getPrice());
                list.add(map1);
            }
        }
        map.put("list",list);
        return map;
    }

    @Override
    public int Confirmorder(int userid, int id) {
        Users users = userDao.selectByPrimaryKey(userid);
        if (users.getRealnametype() != 2) {
            throw new CloudException(ExceptionConstant.请先进行实名认证);
        }
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection=collectionDao.selectByPrimaryKey(userGrant.getCollid());
        if (userid == userGrant.getUserid()) {
            throw new CloudException(ExceptionConstant.该藏品是你的无法购买);
        }
        if (userGrant.getType() != 7) {
            throw new CloudException(ExceptionConstant.藏品已被交易);
        }
        if(userGrant.getType()==8){
            throw new CloudException(ExceptionConstant.藏品已被锁仓);
        }
        UserGrant userGrants = new UserGrant();
        userGrants.setId(id);
        userGrants.setType(8);
        userGrants.setOppositeuser(userid);
        //userGrants.setType(2);
        userGrants.setBuytime(new Date());
       // userGrants.setOppositeuser(userid);
        userGrantDao.updateByPrimaryKeySelective(userGrants);
        //购买者加一个订单
        String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";

        HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(userid);

        hideRecord.setImg(collection.getImg());
        hideRecord.setName(collection.getName());
        hideRecord.setPrice(userGrant.getSellprice());
        hideRecord.setNo(orderno);
        hideRecord.setUsergrantid(userGrant.getId());
        hideRecord.setCreatetime(new Date());
        hideRecord.setMs("未付款");
        hideRecord.setType(0);//0.黄的1.绿2.红
        hideRecordDao.insertSelective(hideRecord);
        IssueExample issueExample=new IssueExample();
        issueExample.createCriteria().andCollidEqualTo(collection.getId());
        List<Issue>issueList=issueDao.selectByExample(issueExample);


        UserGrant userGrant11 = new UserGrant();
        userGrant11.setUserid(userid);
        userGrant11.setCollid(userGrant.getCollid());
        userGrant11.setNumberno(orderno);
        userGrant11.setBuyprice(userGrant.getSellprice());
        userGrant11.setCreatetime(new Date());
        userGrant11.setHashs(userGrant.getHashs());
        userGrant11.setSendtype(3);
        userGrant11.setTruenumber(userGrant.getTruenumber());
        //userGrant.setBuytime(new Date());
        userGrant11.setGranttype(2);
        userGrant11.setCotype(2);
        if(issueList!=null){
            userGrant11.setIssueid(issueList.get(0).getId());
        }
        userGrant11.setType(0);
        userGrant11.setAlbumid(collection.getAlbumid());
        userGrant11.setAlbumname(collection.getAlbumname());
        userGrantDao.insertSelective(userGrant11);


        return userGrant11.getId();
    }

    @Override
    public Map Confirmorderdetails(int userid, int id) {
        Map map = new HashMap();
        String a = dictService.getValue("djs");
        int djs = Integer.valueOf(a);
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());
        map.put("name", collection.getName());
        map.put("img", ImgEnum.img.getUrl() + collection.getImg());
        map.put("price", userGrant.getBuyprice());
        map.put("orderno", userGrant.getNumberno());

      /*  if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
            map.put("orderno", userGrant.getTruenumber());//编号
        }
*/

        map.put("createtime", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.DATETIME_FORMAT));
        long hm = DateUtils.dateDiff(new Date(), DateUtils.getFrontMinute(userGrant.getCreatetime(), djs));
        if (hm < 5000) {
            throw new CloudException(ExceptionConstant.订单已过期);
        }
        map.put("surplustime", hm / 1000);
        switch (userGrant.getPaytype()) {//1.平台 2.微信 3.支付宝 4.其他
            case 1:
                map.put("paytype", "平台");
                break;
            case 2:
                map.put("paytype", "微信");
                break;
            case 3:
                map.put("paytype", "支付宝");
                break;
            case 4:
                map.put("paytype", "其他");
                break;
        }
        return map;
    }

    @Override
    public synchronized String Payorder(int userid, int id, int type, String paytype,String ip) throws Exception {
        Users users = userDao.selectByPrimaryKey(userid);

        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        //Users selluser=userDao.selectByPrimaryKey(userGrant.getUserid());
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.createCriteria().andNoEqualTo(userGrant.getNumberno());
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        HideRecord hideRecordnew=hideRecordList.get(0);
        String ailay = "";
        //1.平台2.微信3.支付宝4.其他
        if (type == 1) {
            if (users.getBalance().compareTo(userGrant.getSellprice()) == -1) {
                throw new CloudException(ExceptionConstant.资产不足);
            }
            int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
            if(chaintype==0){
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
                //当前订单
                userGrant.setType(3);
                userGrant.setBuytime(new Date());
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
                imglist.setAddress(userGrant.getHashs());
                imglist.setUid(userGrant.getUserid());
                imglist.setPrice(userGrant.getBuyprice());
                imglist.setStarttime(new Date());
                imglistDao.insertSelective(imglist);


            }else{

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



        } else if (type == 2) {
            String hrefUrl="";
            String notifyUrl = dictService.getValue("yqlj")+":8200/pay/alipay/notify1";

            hrefUrl = dictService.getValue("yqlj")+"/#/pages/my/my";


            ailay = DeShanUtil.pay(userGrant.getNumberno(), collection.getPrice(), collection.getName(), ip,  notifyUrl,  hrefUrl, Integer.toString(userid), users.getRealname(), users.getRealno());



            System.out.println("ailay=" + ailay);
            return ailay;
        } else if (type == 3) {//支付宝
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            String PARTNER = dictService.getValue("PARTNER");
//            if (paytype == 1) {
            //实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(leix + "支付");
            model.setSubject(leix + "支付");
            model.setOutTradeNo(userGrant.getNumberno()); //商家订单编号(我使用(订单号+时间戳:流水号))
            model.setTimeoutExpress("60m"); //超时关闭该订单时间
            model.setTotalAmount(userGrant.getSellprice().toString());  //订单总金额
            model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
            model.setSellerId(PARTNER); // 商家id
            request.setBizModel(model);
            request.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");  //回调地址
            String orderStr = "";
            try {
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request); //这里和普通的接口调用不同，使用的是sdkExecute
                orderStr = response.getBody();
                System.out.println("=======>>>>>这是orderStr:" + orderStr); // 就是orderString 可以直接给客户端请求，无需再做处理。
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            //请求
            ailay = orderStr;
//            userGrant.setType(2);
            userGrant.setType(-1);
            userGrant.setPaytype(type);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            System.out.println("ailay=" + ailay);
            return ailay;
        } else if (type == 4) {
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            //实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            String aaa = "QUICK_WAP_WAY";
            //该笔订单允许的最晚付款时间
            String timeout = "30m";
            //设置请求参数
            String content = "{\"out_trade_no\":\"" + userGrant.getNumberno() + "\"," + "\"total_amount\":\"" + userGrant.getSellprice() + "\"," + "\"subject\":\"" + leix + "支付" + "\"," + "\"timeout_express\":\"" + timeout + "\"," + "\"body\":\"" + leix + "支付" + "\"," + "\"product_code\":\"" + aaa + "\"}";
            System.out.println("content" + content);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
            alipayRequest.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");
            alipayRequest.setBizContent(content);
            System.out.println("alipayRequest" + alipayRequest);
            //请求
            ailay = alipayClient.pageExecute(alipayRequest).getBody();

//            userGrant.setType(2);
            userGrant.setType(-1);
            userGrant.setPaytype(type);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            System.out.println("ailay=" + ailay);
            return ailay;
        } else if (type == 5) {
            //debug 模式，开启后有详细的日志
            Adapay.debug = true;
            //prodMode 模式，默认为生产模式，false可以使用mock模式
            Adapay.prodMode = true;
//             * 初始化商户配置，服务器启动前，必须通过该方式初始化商户配置完成
//             * apiKey为prod模式的API KEY
//             * mockApiKey为mock模式的API KEY
//             * rsaPrivateKey为商户发起请求时，用于请求参数加签所需要的RSA私钥

            String apiKey = "api_live_868e9791-b72e-4375-8f43-c7ab2f2ab7f3";
            String mockApiKey = "api_test_c63cd78b-44fb-400c-9f3e-e73d9bc2fbfa";
            String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItaGPyXckA1us32lI2eaqcfbuKmTxWq1xPK6nKMyBxeXzvTljyYDMDJPnrJdo57sJbzW/c3NqmGPA4EZuB0OhihDers0J+4UfBkzK3TV2dXqWuPaQeKNITT3HY7/JY0B4tjkx27CLpyDrcizZ22JsPsvDQY24T/Pgl0sgqbVbNZAgMBAAECgYBU7u0bxxKToCvjLM8OIefPRJFJAyRgiGfeYGbUuomdAMf6ptOCywm61u2F2RSxcyIGXv1GiBiA6pff0z8AvtZJFDYGCCn6BO+Z0Yl4Wdw2lgPn+ZXbKf330w0M528GgRPo3QYV84ChZj9zQeuuwyQPLFzqT/SL5RSGQEMI5vDAgQJBANiyfSZQy972Q+8rTELNwV35SKb+PIJgIAuzvH/GXQNsI1Kmmj3jyyQFXP4FolzDX8912Sm4DAcDhgBXOkzWuBUCQQCkoF/+sXHTCpHXlvX5nZcXnWI+nnHRk61TLUn8OZ7cdGBRbjZQjALLmH5IEB3p9OKzoPYAA6jLuNNoZnHnEfs1AkAhIzpsL5Ldp78/xdlFpf1aB5MKpnpOXbDI2VWMHKOAsHq+WuffawRXn9JWrjnkAaSXLyhkbta4A9vsFWq+8V75AkAEKY3jyabAGD9RvR5g/kwIq9EFFkp4awxx0u5Q80ACAOtCg8/Zv4B+l09yEP5AoRFuSz6NB4qoA/dMZ3KvzfOVAkAxHQ19ANyH0aIeQMu3FOQL/YOkI5E3EaS2lTmy5NQTv15/vPLLrOKwgU3KeS0/s+regQ3lOQxoYx+e/Qjdc6in";
//                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALM7qAYPHL74b9LbbFwrxFW2yRVU+fLGF0FCusPEBZbjY2g22DAFFPcU+wWj+ww/l4YXyAey89HCn/mBHeugfBKJWIK7qNe/Cx2wT8sMVox+ASuruZQMVDuFIKylHiDODLCxqcOtnxFzvLfAmo+g006pzl0sZzK6uEeVleB4gp43AgMBAAECgYEAqAD4a82InX2UnCqvtOoVnF68uDEyJZ4HOFYLaOt6bchxasNV/BUXZM+WyN+/t+h36OqrxoO4xltPxvl/iVmiUcyF0nR4MOwtLlf409JowPzb6oJVma3/M3HyMLXzovH+kQWG9MiQeHycXOC+2pgoCphldV8Bbqrf39/hM7A4euECQQD1Sk09dEGrU5aRK3cS6VIfoqufQP0gX/p0XyKhZq78M0FFNo6gQaPtp1+Iku8BVJc6u2JVxh6jKnNwSipkLhWxAkEAuw8CWMbCm2UJ351/I1OEtudBlZhIGFxyXi5udsx02dLJ0vCsoDohepyKolaRFl46v0PL2bSagZnCujzyyVskZwJATqDSwbBSnKjeywoz0UwZlyp9+T7L5WTe1A7q+vH/hbioY5dpgNWJxtSwOwdetf53u8v1aaloiMVC2+vqStK7AQJAIqxhjvsoz40nC2AyVOYCT7UGHyifFITuXi7VIwg7ELVV29fo57pewk+KExVgx3ioAxQaETy89xD9W3A4wMI4OQJBANcaWpIzg/paevDumZpcTofrugr/YS0acnS6WKPdFfRCUONl/Tr/v8y1ZlvjJFmCaKkukj7wEj+pZJH1hybmqbY=";
            MerConfig merConfig = new MerConfig();
            merConfig.setApiKey(apiKey);
            merConfig.setApiMockKey(mockApiKey);
            merConfig.setRSAPrivateKey(rsaPrivateKey);
            Adapay.initWithMerConfig(merConfig);
//             * 初始化完成，可以开始交易
            String appId = "app_5073f892-cecc-4922-8e5b-f8dfb8bda0ca";
            Map mapss = PaymentRegionAndRequestTimeOutDemo.executePaymentTest1(appId, userGrant.getSellprice().setScale(2, BigDecimal.ROUND_HALF_UP), "测试", userGrant.getNumberno());
            Map mapsss = (Map) mapss.get("expend");
            ailay = mapsss.get("pay_info").toString();
//            userGrant.setType(2);
            userGrant.setType(-1);
            userGrant.setPaytype(type);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            System.out.println("ailay=" + ailay);
            return ailay;
        } else {
            throw new CloudException(ExceptionConstant.暂未开放);
        }
        return Integer.toString(id);
    }

    //all album
    @Override
    public List allalbum(Integer logUserPid) {
        return albumDao.selectByExample(new AlbumExample());
       // return null;
    }

    @Override
    public Map noregdetails(int id) {
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());//id
        map.put("name", collection.getName());//名称
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
        map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//图片
        map.put("price", userGrant.getSellprice());//价格
        map.put("details", collection.getDetails());//详情
        map.put("publisher", collection.getPublisher());//发行者
        map.put("creator", collection.getCreator());//创作者s
        map.put("contractAddress",collection.getContractaddress());
        map.put("biaoshi","ERC721");
        map.put("publisher", collection.getPublisher());//发行者
        map.put("creator", collection.getCreator());//创作者
        map.put("colltype", collection.getType());//创作者
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//发行者
        map.put("minname",collection.getAlbumname());
        map.put("collid", collection.getId());//名称


        List list=new ArrayList();
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.createCriteria().andAddressEqualTo(userGrant.getHashs());
        List<HideRecord>hideRecordExampleList=hideRecordDao.selectByExample(hideRecordExample);
        if(hideRecordExampleList.size()>0){
            for (HideRecord hideRecord : hideRecordExampleList) {
                Map map1=new HashMap();
                //map1.put("img",ImgEnum.img.getUrl()+hideRecord.getImg());
                map1.put("name",hideRecord.getName());
                map1.put("time",DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.TIME_FORMAT1));
                map1.put("price",hideRecord.getPrice());
                list.add(map1);
            }
        }
        map.put("list",list);
        return map;
    }

}
