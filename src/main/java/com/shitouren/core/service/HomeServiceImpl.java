package com.shitouren.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
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
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.controller.DDCSdkClient;
import com.shitouren.core.controller.SignEventTest;
import com.shitouren.core.dto.Account;
import com.shitouren.core.utils.*;
import lombok.SneakyThrows;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    BannerDao bannerDao;
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
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    AgreementsDao agreementsDao;
    @Autowired(required = false)
    ApliaymhDao apliaymhDao;
    @Autowired(required = false)
    PromptDao promptDao;
    @Autowired(required = false)
    DrawDao drawDao;
    @Autowired(required = false)
    DrawrecordDao drawrecordDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    TicketDao ticketDao;
    @Autowired(required = false)
    ImglistDao imglistDao;
    @Autowired(required = false)
    TransforlistDao transforlistDao;

    @Autowired
    private WXAppPayService wxAppPayService;


    public static Integer START_TRIAL = 1;

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
    BaseService baseService = client.getChargeService();


    @Override
    public Map show() {
        System.out.println("START_TRIAL :"+START_TRIAL);
        if(START_TRIAL==0){
            throw new CloudException(ExceptionConstant.????????????????????????);
        }

        Map maps = new HashMap();
        BannerExample bannerExample=new BannerExample();
        bannerExample.createCriteria().andTypeEqualTo(0);
        List<Banner> banners = bannerDao.selectByExample(bannerExample);
        List list = new ArrayList();
        for (Banner banner : banners) {
            Map map = new HashMap();
            map.put("id", banner.getId());
            map.put("title",banner.getTitle());
            map.put("content",banner.getContent());
            map.put("title1",banner.getTitle1());
            map.put("state",banner.getState());
            map.put("link",banner.getLink());
            map.put("img", ImgEnum.img.getUrl() + banner.getImg());
            list.add(map);
        }
        maps.put("banners", list);
        List list1 = new ArrayList();
//?????????
        IssueExample collectionExample22 = new IssueExample();
        collectionExample22.createCriteria().andTypeEqualTo(0);
         collectionExample22.setOrderByClause("releasetime desc");
        List<Issue> collections = issueDao.selectByExample(collectionExample22);
        for (Issue issue : collections) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
//            map.put("whitelist", users.getWhitelist());//id
//            map.put("bmdtq", dictService.getValue("bmdtq"));//id
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", issue.getPresale());//??????
            map.put("price", collection.getPrice());//??????
            map.put("publisher", collection.getPublisher());//?????????
            map.put("coltype", collection.getType());//?????????
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
            List label = new ArrayList();
           /* JSONArray jsonObject = JSONArray.fromObject(collection.getLabel());
            if (jsonObject.size() > 1) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    JSONObject job = jsonObject.getJSONObject(i);
                    Map map1 = new HashMap();
                    map1.put("label", job.get("label"));//??????
                    label.add(map1);
                }
            }*/
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
            long hms1 = DateUtils.dateDiff(new Date(), issue.getReleasetime());
            long hms = DateUtils.dateDiff(new Date(), issue.getStarttime());
            if (DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) == 1) {
                map.put("second", hms1 / 1000);//1.?????????
                map.put("type", 0);//
                map.put("time", "??????????????????");
            } else if(DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) ==1){
                //if

                map.put("second", hms / 1000);//1.?????????
                map.put("type", 1);//
                map.put("time", "????????????");



            }else if(DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) ==1){
                map.put("second", hms / 1000);//1.?????????
                map.put("type", 2);//
                map.put("time", "????????????");
            }else {
                map.put("second", 0);//1.?????????
                map.put("type", 3);//
                map.put("time", "?????????");
            }


            list1.add(map);
        }

        maps.put("szcp", list1);


        List list2 = new ArrayList();
        Date date=new Date();
        IssueExample collectionExample1 = new IssueExample();
        collectionExample1.createCriteria().andTypeEqualTo(0);
        collectionExample1.setOrderByClause("releasetime desc");
        List<Issue> collections2 = issueDao.selectByExample(collectionExample1);
        for (Issue issue : collections2) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", issue.getPresale());//??????
            map.put("price", collection.getPrice());//??????
            map.put("publisher", collection.getPublisher());//?????????
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
            map.put("date", DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT3));//??????
            map.put("date1", DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT4));//??????
            list2.add(map);
        }
        maps.put("fsrl", list2);//????????????
        return maps;
    }

    @Override
    public String bannerdetails(int id) {
        return bannerDao.selectByPrimaryKey(id).getContent();
    }

    @Override
    public Map shows(int userid) {
        System.out.println("START_TRIAL :"+START_TRIAL);
        if(START_TRIAL==0){
            throw new CloudException(ExceptionConstant.????????????????????????);
        }

        Users users = userDao.selectByPrimaryKey(userid);
        Map maps = new HashMap();
        List<Banner> banners = bannerDao.selectByExample(new BannerExample());
        List list = new ArrayList();
        for (Banner banner : banners) {
            Map map = new HashMap();
            map.put("id", banner.getId());
            map.put("img", ImgEnum.img.getUrl() + banner.getImg());
            map.put("title",banner.getTitle());
            map.put("content",banner.getContent());
            list.add(map);
        }
        maps.put("banners", list);
        List list1 = new ArrayList();
        //?????????
        IssueExample collectionExample22 = new IssueExample();
        collectionExample22.createCriteria().andReleasetimeLessThanOrEqualTo(new Date()).andEndtimeGreaterThanOrEqualTo(new Date());
        collectionExample22.setOrderByClause("releasetime desc");
        List<Issue> collections = issueDao.selectByExample(collectionExample22);
        for (Issue issue : collections) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
            map.put("whitelist", users.getWhitelist());//id
            map.put("bmdtq", dictService.getValue("bmdtq"));//id
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", collection.getLimits());//??????
            map.put("price", collection.getPrice());//??????
            map.put("publisher", collection.getPublisher());//?????????
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
            List label = new ArrayList();
            JSONArray jsonObject = JSONArray.fromObject(collection.getLabel());
            if (jsonObject.size() > 1) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    JSONObject job = jsonObject.getJSONObject(i);
                    Map map1 = new HashMap();
                    map1.put("label", job.get("label"));//??????
                    label.add(map1);
                }
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
            map.put("type", 0);//1.?????????
            map.put("time", "?????????");
            if (a == 1) {
                map.put("type", 1);//1.?????????
                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                System.out.println("hm=" + hm);
                map.put("second", hm / 1000);//1.?????????
                map.put("time", "???????????? " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " ??????");
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            System.out.println("????????????1");
            System.out.println(DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT));
            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);//2.?????????
                map.put("time", "?????????");
                if (issue.getSold() >= issue.getPresale()) {
                    map.put("type", 2);//2.?????????
                    map.put("time", "?????????");
                }
            } else {
                System.out.println("????????????");
                System.out.println("issue.getSold()=" + issue.getSold());
                System.out.println("issue.getPresale()=" + issue.getPresale());
                if (issue.getSold() >= issue.getPresale()) {
                    map.put("type", 2);//2.?????????
                    map.put("time", "?????????");
                }
            }
            list1.add(map);
        }
        //????????????
        IssueExample collectionExample = new IssueExample();
        collectionExample.createCriteria().andReleasetimeBetween(new Date(), DateUtils.getDayEnd());
        collectionExample.setOrderByClause("releasetime asc");
        List<Issue> collections1 = issueDao.selectByExample(collectionExample);

        for (Issue issue : collections1) {
            System.out.println("issue=" + issue.getId());
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
            map.put("whitelist", users.getWhitelist());//id
            map.put("bmdtq", dictService.getValue("bmdtq"));//id
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", collection.getLimits());//??????
            map.put("price", collection.getPrice());//??????
            map.put("publisher", collection.getPublisher());//?????????
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
            List label = new ArrayList();
            JSONArray jsonObject = JSONArray.fromObject(collection.getLabel());
            if (jsonObject.size() > 1) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    JSONObject job = jsonObject.getJSONObject(i);
                    Map map1 = new HashMap();
                    map1.put("label", job.get("label"));//??????
                    label.add(map1);
                }
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
            map.put("type", 0);//1.?????????
            if (a == 1) {
                map.put("type", 1);//1.?????????
                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                System.out.println("hm=" + hm);
                System.out.println(hm + "-------");
                map.put("second", hm / 1000);//1.?????????
                map.put("time", "???????????? " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " ??????");
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            System.out.println("????????????2");
            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);//2.?????????
                map.put("time", "?????????");
            } else {
                if (issue.getSold() >= issue.getPresale()) {
                    map.put("type", 2);//2.?????????
                    map.put("time", "?????????");
                }
            }
            list1.add(map);
        }
//???????????????
        IssueExample collectionExampleg = new IssueExample();
        collectionExampleg.createCriteria().andEndtimeBetween(DateUtils.getDayBegin(), new Date());
        List<Issue> collections1s = issueDao.selectByExample(collectionExampleg);
        for (Issue issue : collections1s) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
            map.put("whitelist", users.getWhitelist());//id
            map.put("bmdtq", dictService.getValue("bmdtq"));//id
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", collection.getLimits());//??????
            map.put("price", collection.getPrice());//??????
            map.put("publisher", collection.getPublisher());//?????????
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
            List label = new ArrayList();
            JSONArray jsonObject = JSONArray.fromObject(collection.getLabel());
            if (jsonObject.size() > 1) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    JSONObject job = jsonObject.getJSONObject(i);
                    Map map1 = new HashMap();
                    map1.put("label", job.get("label"));//??????
                    label.add(map1);
                }
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
            map.put("type", 0);//1.?????????
            if (a == 1) {
                map.put("type", 1);//1.?????????
                map.put("time", "???????????? " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " ??????");
            }
            //?????????????????????????????? = -1??? ?????? =0???????????? = 1
            System.out.println("????????????3");
            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);//2.?????????
                map.put("time", "?????????");
            } else {
                if (issue.getSold() >= issue.getPresale()) {
                    map.put("type", 2);//2.?????????
                    map.put("time", "?????????");
                }
            }
            list1.add(map);
        }
        maps.put("szcp", list1);//????????????
        List list2 = new ArrayList();
        IssueExample collectionExample1 = new IssueExample();
        collectionExample1.createCriteria().andReleasetimeGreaterThan(DateUtils.getDayEnd());
        List<Issue> collections2 = issueDao.selectByExample(collectionExample1);
        for (Issue issue : collections2) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            Map map = new HashMap();
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//??????
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
            map.put("limits", collection.getLimits());//??????
            map.put("price", collection.getPrice());//??????
            map.put("date", DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT3));//??????
            map.put("date1", DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT4));//??????
            list2.add(map);
        }
        maps.put("fsrl", list2);//????????????
        return maps;
    }

    @Override
    public Map details(int uerid, int id) {
        Users users = userDao.selectByPrimaryKey(uerid);
        Map map = new HashMap();
        Issue issue = issueDao.selectByPrimaryKey(id);

        long hms1 = DateUtils.dateDiff(new Date(), issue.getReleasetime());
        long hms = DateUtils.dateDiff(new Date(), issue.getStarttime());
        if (DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) == 1) {
            map.put("second", hms1 / 1000);//1.?????????
            map.put("type", 0);//
            map.put("time", "??????????????????");
        } else if(DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) ==1){
            //if


            UserGrantExample userGrantExample1111 = new UserGrantExample();
            userGrantExample1111.createCriteria().andIssueidEqualTo(id);
            List<UserGrant> userGrants1111 = userGrantDao.selectByExample(userGrantExample1111);
            if (issue.getPresale() >userGrants1111.size() ) {
                map.put("second", hms / 1000);//1.?????????
                map.put("type", 1);//
                map.put("time", "????????????");
            }else{
                map.put("second", 0);//1.?????????
                map.put("type", 3);//
                map.put("time", "?????????");
            }

        }else if(DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) ==1){

            UserGrantExample userGrantExample1111 = new UserGrantExample();
            userGrantExample1111.createCriteria().andIssueidEqualTo(id);
            List<UserGrant> userGrants1111 = userGrantDao.selectByExample(userGrantExample1111);
            if (issue.getPresale() >issue.getSold() ) {
                map.put("second", hms / 1000);//1.?????????
                map.put("type", 2);//
                map.put("time", "????????????");
            }else{
                map.put("second", 0);//1.?????????
                map.put("type", 3);//
                map.put("time", "?????????");
            }

        }else {
            map.put("second", 0);//1.?????????
            map.put("type", 3);//
            map.put("time", "?????????");
        }

        Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
        List list1 = new ArrayList();
     /*   if(collection.getType()==1){
            Blindbox blindbox=blindboxDao.selectByPrimaryKey(collection.getBoxid());

            List<Integer> idl = new ArrayList();
            String[] split = blindbox.getFragment().split(",");
            for (int i = 0; i < split.length; i++) {
                idl.add(Integer.parseInt(split[i]));
            }


            for (Integer integer : idl) {
                Map map1 = new HashMap();
                Collection collectionnew = collectionDao.selectByPrimaryKey(integer);
                map1.put("name", collectionnew.getName());
                map1.put("img", ImgEnum.img.getUrl() + collectionnew.getImg());


                list1.add(map1);
            }
        }
        map.put("fragment", list1);*/

        map.put("id", issue.getId());//id
        map.put("name", collection.getName());//??????
        map.put("collid", collection.getId());//??????
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//??????
        map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//??????
        map.put("limits", issue.getPresale());//??????
        map.put("shengyu", issue.getPresale()-issue.getSold());
        map.put("price", collection.getPrice());//??????
        System.out.println("details=" + collection.getDetails());
        map.put("details", collection.getDetails());//??????
        map.put("minname",collection.getAlbumname());
        map.put("newtime",DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.DATETIME_FORMAT));

        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        if(chaintype==0){
            map.put("contractAddress",collection.getContractaddress());
        }else{
            map.put("contractAddress",collection.getThcontractaddress());
        }


        map.put("biaoshi","ERC721");
        map.put("publisher", collection.getPublisher());//?????????
        //map.put("creator", collection.getCreator());//?????????
        map.put("colltype", collection.getType());//?????????
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
        List list=new ArrayList();

        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andUseridEqualTo(uerid).andIssueidEqualTo(id).andTypeEqualTo(6) ;
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if(userGrants.size()>0){
            map.put("isgrant",1);
        }else {
            map.put("isgrant",0);
        }


        return map;
    }

    @Override
    public int detailss(int userid, int id) {
        return collectionDao.selectByPrimaryKey(id).getType();
    }

    @Override
    public synchronized int Confirmorder(int userid, int id) {
        Users users = userDao.selectByPrimaryKey(userid);
        int userstate =Integer.parseInt(users.getStatusId());
        if(userstate==1){
            throw new CloudException(ExceptionConstant.??????????????????);
        }
        if (users.getRealnametype() != 2) {
            throw new CloudException(ExceptionConstant.????????????????????????);
        }
        Issue issue = issueDao.selectByPrimaryKey(id);
            int drawid=issue.getDrawid();
            int colid=issue.getCollid();
        long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
        if (DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) == 1) {
            throw new CloudException(ExceptionConstant.??????????????????);

        } else if(DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) ==1){
            //if
            //????????????????????????????????????????????????
            TicketExample ticketExample=new TicketExample();
            ticketExample.createCriteria().andStateEqualTo(1).andUseridEqualTo(userid).andColidEqualTo(colid);
            List<Ticket>ticketList=ticketDao.selectByExample(ticketExample);
            if(ticketList.size()==0){
                throw new CloudException(ExceptionConstant.?????????????????????);
            }


          /*  if(users.getWhitelist()==0){
                throw new CloudException(ExceptionConstant.?????????????????????);
            }*/

        }else if(DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) <=0){
            throw new CloudException(ExceptionConstant.?????????);
        }

        /*UserGrantExample userGrantExample1111 = new UserGrantExample();
        userGrantExample1111.createCriteria().andUseridEqualTo(userid).andIssueidEqualTo(id);
        List<UserGrant> userGrants1111 = userGrantDao.selectByExample(userGrantExample1111);*/



        if (issue.getSold() >= issue.getPresale()) {
            throw new CloudException(ExceptionConstant.?????????);
        }
        Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());

        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(collection.getId()).andTypeEqualTo(0);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if (userGrants.size() > 0) {
            throw new CloudException(ExceptionConstant.???????????????????????????);
        }

        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.createCriteria().andUseridEqualTo(userid).andIssueidEqualTo(id);
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        if (hideRecordList.size() >= issue.getLimitcount()) {
            throw new CloudException(ExceptionConstant.????????????????????????);
        }

        int bmd = Integer.valueOf(dictService.getValue("bmdtq"));
        Date date = issue.getReleasetime();
        date.setTime(date.getTime() - bmd * 60 * 1000);
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(collection.getId()).andCotypeIn(integers).andBuytimeGreaterThan(DateUtils.getDayBegin());
        List<UserGrant> userGrants1 = userGrantDao.selectByExample(userGrantExample1);


        String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";

        HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(userid);

        hideRecord.setImg(collection.getImg());
        hideRecord.setName(collection.getName());
        hideRecord.setPrice(collection.getPrice());
        hideRecord.setNo(orderno);
        hideRecord.setIssueid(id);
        hideRecord.setCreatetime(new Date());
        hideRecord.setMs("?????????");
        hideRecord.setType(0);//0.??????1.???2.???
        hideRecordDao.insertSelective(hideRecord);



        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        UserGrant userGrant = new UserGrant();
        if(chaintype==0){

            userGrant.setUserid(userid);
            userGrant.setCollid(issue.getCollid());
            userGrant.setNumberno(orderno);
            userGrant.setBuyprice(collection.getPrice());
            userGrant.setCreatetime(new Date());
            //userGrant.setBuytime(new Date());
            userGrant.setGranttype(1);
            userGrant.setCotype(0);
            userGrant.setIssueid(issue.getId());
            userGrant.setAlbumid(collection.getAlbumid());
            userGrant.setAlbumname(collection.getAlbumname());
            userGrantDao.insertSelective(userGrant);
        }else{
            userGrant.setUserid(userid);
            userGrant.setCollid(issue.getCollid());
            userGrant.setNumberno(orderno);
            userGrant.setBuyprice(collection.getPrice());
            userGrant.setCreatetime(new Date());
            //userGrant.setBuytime(new Date());
            userGrant.setGranttype(1);
            userGrant.setCotype(0);
            userGrant.setIssueid(issue.getId());
            userGrant.setAlbumid(collection.getAlbumid());
            userGrant.setAlbumname(collection.getAlbumname());
            userGrantDao.insertSelective(userGrant);
        }

        return userGrant.getId();
    }

    @Override
    public int ordertype(int id) {
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        return userGrant.getType();
    }

    @Override
    public Map Confirmorderdetails(int userid, int id) {
        Map map = new HashMap();
        int djs = Integer.parseInt(dictService.getValue("djs"));
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());
        map.put("name", collection.getName());
        map.put("img", ImgEnum.img.getUrl() + collection.getImg());
        map.put("price", userGrant.getBuyprice());

        map.put("orderno", userGrant.getNumberno());
        map.put("publisher", collection.getPublisher());//?????????
        map.put("createtime", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.DATETIME_FORMAT));
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
        map.put("ordertype", userGrant.getCotype());//?????????
        long hm = DateUtils.dateDiff(new Date(), DateUtils.getFrontMinute(userGrant.getCreatetime(), djs));
        if (hm < 5000) {
            throw new CloudException(ExceptionConstant.???????????????);
        }
        map.put("surplustime", hm / 1000);
        switch (userGrant.getPaytype()) {//1.?????? 2.?????? 3.????????? 4.??????
            case 1:
                map.put("paytype", "??????");
                break;
            case 2:
                map.put("paytype", "??????");
                break;
            case 3:
                map.put("paytype", "?????????");
                break;
            case 4:
                map.put("paytype", "??????");
                break;
        }
        return map;
    }

    @Override
    public synchronized Map Payorder(int userid, int id, int type , int paytype, String ip) throws Exception {
        Users users = userDao.selectByPrimaryKey(userid);
        int userstate =Integer.parseInt(users.getStatusId());
        if(userstate==1){
            throw new CloudException(ExceptionConstant.??????????????????);
        }

        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);

        Issue issue = issueDao.selectByPrimaryKey(userGrant.getIssueid());
        int drawid=issue.getDrawid();
        int colid=issue.getCollid();

        if (issue.getSold() >= issue.getPresale()) {
            throw new CloudException(ExceptionConstant.?????????);
        }

        long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
        if (DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) == 1) {
            throw new CloudException(ExceptionConstant.??????????????????);

        } else if(DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) ==1){
            //if
        /*    if(users.getWhitelist()==0){
                throw new CloudException(ExceptionConstant.?????????????????????);
            }*/

            TicketExample ticketExample=new TicketExample();
            ticketExample.createCriteria().andStateEqualTo(1).andUseridEqualTo(userid).andColidEqualTo(colid);
            List<Ticket>ticketList=ticketDao.selectByExample(ticketExample);
            if(ticketList.size()==0){
                throw new CloudException(ExceptionConstant.?????????????????????);
            }
            Ticket ticket=ticketList.get(0);
            ticket.setState(2);
            ticketDao.updateByPrimaryKeySelective(ticket);


        }else if(DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) <=0){
            throw new CloudException(ExceptionConstant.?????????);
        }

        if (issue.getSold() >= issue.getPresale()) {
            throw new CloudException(ExceptionConstant.?????????);
        }


        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        String ailay = "";
        //1.??????2.??????3.?????????4.??????
        if (type == 1) {
            if (users.getBalance().compareTo(userGrant.getBuyprice()) == -1) {
                throw new CloudException(ExceptionConstant.????????????);
            }

                BalanceRecord balanceRecord = new BalanceRecord();
                balanceRecord.setUserid(userid);
                balanceRecord.setName("??????" + collection.getName());
                balanceRecord.setCount(collection.getPrice().negate());
                balanceRecord.setCreatetime(new Date());
                balanceRecord.setState(1);
                balanceRecordDao.insertSelective(balanceRecord);
                System.out.println("??????");
                userGrant.setCotype(1);
                map.put("collectionid", collection.getId());
                map.put("user_grantid", userGrant.getId());
                BigDecimal over = users.getBalance().subtract(userGrant.getBuyprice());
                //Users users1 = new Users();
                //users1.setUserId(userid);
                users.setBalance(over);
                users.setMoney(users.getMoney().add(new BigDecimal(5)));
                userDao.updateByPrimaryKeySelective(users);

                //????????????
                Moneyrecord moneyrecord11=new Moneyrecord();
                moneyrecord11.setPrice(new BigDecimal(5));
                moneyrecord11.setName("????????????");
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

            userGrant.setPaytype(type);
            userGrantDao.updateByPrimaryKeySelective(userGrant);

            HideRecordExample hideRecordExample=new HideRecordExample();
            hideRecordExample.createCriteria().andUseridEqualTo(userid).andNoEqualTo(userGrant.getNumberno());
            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);

            if(hideRecordList.size()>0){
                HideRecord hideRecord=hideRecordList.get(0);
                if(collection.getType()==1){
                    int bee=collection.getSold()+1;
                    String thetruenumber=bee+"/"+collection.getLimits();//??????
                    hideRecord.setThenum(thetruenumber);
                }

                hideRecord.setMs("????????????");
                hideRecord.setType(1);//0.??????1.???2.???
                hideRecordDao.updateByPrimaryKeySelective(hideRecord);
            }






            /*??????*/
        } else if (type == 2) {
            String hrefUrl="";
            String notifyUrl = dictService.getValue("yqlj")+":8200/pay/alipay/notify";
            if(collection.getType()==0){
                hrefUrl  = dictService.getValue("yqlj")+"/#/pages/pay/paySuccess?collectionid="+collection.getId()+"&user_grantid="+userGrant.getId();

            }else{
                hrefUrl = dictService.getValue("yqlj")+"/#/pages/my/my";

            }
            ailay = DeShanUtil.pay(userGrant.getNumberno(), collection.getPrice(), collection.getName(), ip,  notifyUrl,  hrefUrl, Integer.toString(userid), users.getRealname(), users.getRealno());

          //  Map<String, String> returnMap = wxAppPayService.dounifiedOrder("??????", userGrant.getNumberno(), collection.getPrice().toString());
            //Gson gson = new Gson();
            //ailay = gson.toJson(returnMap);
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else if (type == 3) {//?????????
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            String PARTNER = dictService.getValue("PARTNER");
            // ??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(leix + "??????");
            model.setSubject(leix + "??????");
            model.setOutTradeNo(userGrant.getNumberno()); //??????????????????(?????????(?????????+?????????:?????????))
            model.setTimeoutExpress("60m"); //???????????????????????????
            model.setTotalAmount(collection.getPrice().toString());  //???????????????
            model.setProductCode("QUICK_MSECURITY_PAY"); //?????????????????????????????????????????????????????????????????????QUICK_MSECURITY_PAY
            model.setSellerId(PARTNER); // ??????id
            request.setBizModel(model);
            request.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");  //????????????
            String orderStr = "";
            try {
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request); //???????????????????????????????????????????????????sdkExecute
                orderStr = response.getBody();
                System.out.println("=======>>>>>??????orderStr:" + orderStr); // ??????orderString ??????????????????????????????????????????????????????
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            ailay = orderStr;
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else if (type == 4) {
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            //??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            String aaa = "QUICK_WAP_WAY";
            //???????????????????????????????????????
            String timeout = "30m";
            //??????????????????
            String content = "{\"out_trade_no\":\"" + userGrant.getNumberno() + "\"," + "\"total_amount\":\"" + collection.getPrice() + "\"," + "\"subject\":\"" + leix + "??????" + "\"," + "\"timeout_express\":\"" + timeout + "\"," + "\"body\":\"" + leix + "??????" + "\"," + "\"product_code\":\"" + aaa + "\"}";
            System.out.println("content" + content);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
            alipayRequest.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");
            alipayRequest.setBizContent(content);
            System.out.println("alipayRequest" + alipayRequest);
            //??????
            ailay = alipayClient.pageExecute(alipayRequest).getBody();
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else if (type == 5) {
            //debug ????????????????????????????????????
            Adapay.debug = true;
            //prodMode ?????????????????????????????????false????????????mock??????
            Adapay.prodMode = true;
//             * ?????????????????????????????????????????????????????????????????????????????????????????????
//             * apiKey???prod?????????API KEY
//             * mockApiKey???mock?????????API KEY
//             * rsaPrivateKey???????????????????????????????????????????????????????????????RSA??????
            String apiKey = "api_live_868e9791-b72e-4375-8f43-c7ab2f2ab7f3";
            String mockApiKey = "api_test_c63cd78b-44fb-400c-9f3e-e73d9bc2fbfa";
            String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItaGPyXckA1us32lI2eaqcfbuKmTxWq1xPK6nKMyBxeXzvTljyYDMDJPnrJdo57sJbzW/c3NqmGPA4EZuB0OhihDers0J+4UfBkzK3TV2dXqWuPaQeKNITT3HY7/JY0B4tjkx27CLpyDrcizZ22JsPsvDQY24T/Pgl0sgqbVbNZAgMBAAECgYBU7u0bxxKToCvjLM8OIefPRJFJAyRgiGfeYGbUuomdAMf6ptOCywm61u2F2RSxcyIGXv1GiBiA6pff0z8AvtZJFDYGCCn6BO+Z0Yl4Wdw2lgPn+ZXbKf330w0M528GgRPo3QYV84ChZj9zQeuuwyQPLFzqT/SL5RSGQEMI5vDAgQJBANiyfSZQy972Q+8rTELNwV35SKb+PIJgIAuzvH/GXQNsI1Kmmj3jyyQFXP4FolzDX8912Sm4DAcDhgBXOkzWuBUCQQCkoF/+sXHTCpHXlvX5nZcXnWI+nnHRk61TLUn8OZ7cdGBRbjZQjALLmH5IEB3p9OKzoPYAA6jLuNNoZnHnEfs1AkAhIzpsL5Ldp78/xdlFpf1aB5MKpnpOXbDI2VWMHKOAsHq+WuffawRXn9JWrjnkAaSXLyhkbta4A9vsFWq+8V75AkAEKY3jyabAGD9RvR5g/kwIq9EFFkp4awxx0u5Q80ACAOtCg8/Zv4B+l09yEP5AoRFuSz6NB4qoA/dMZ3KvzfOVAkAxHQ19ANyH0aIeQMu3FOQL/YOkI5E3EaS2lTmy5NQTv15/vPLLrOKwgU3KeS0/s+regQ3lOQxoYx+e/Qjdc6in";
//                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALM7qAYPHL74b9LbbFwrxFW2yRVU+fLGF0FCusPEBZbjY2g22DAFFPcU+wWj+ww/l4YXyAey89HCn/mBHeugfBKJWIK7qNe/Cx2wT8sMVox+ASuruZQMVDuFIKylHiDODLCxqcOtnxFzvLfAmo+g006pzl0sZzK6uEeVleB4gp43AgMBAAECgYEAqAD4a82InX2UnCqvtOoVnF68uDEyJZ4HOFYLaOt6bchxasNV/BUXZM+WyN+/t+h36OqrxoO4xltPxvl/iVmiUcyF0nR4MOwtLlf409JowPzb6oJVma3/M3HyMLXzovH+kQWG9MiQeHycXOC+2pgoCphldV8Bbqrf39/hM7A4euECQQD1Sk09dEGrU5aRK3cS6VIfoqufQP0gX/p0XyKhZq78M0FFNo6gQaPtp1+Iku8BVJc6u2JVxh6jKnNwSipkLhWxAkEAuw8CWMbCm2UJ351/I1OEtudBlZhIGFxyXi5udsx02dLJ0vCsoDohepyKolaRFl46v0PL2bSagZnCujzyyVskZwJATqDSwbBSnKjeywoz0UwZlyp9+T7L5WTe1A7q+vH/hbioY5dpgNWJxtSwOwdetf53u8v1aaloiMVC2+vqStK7AQJAIqxhjvsoz40nC2AyVOYCT7UGHyifFITuXi7VIwg7ELVV29fo57pewk+KExVgx3ioAxQaETy89xD9W3A4wMI4OQJBANcaWpIzg/paevDumZpcTofrugr/YS0acnS6WKPdFfRCUONl/Tr/v8y1ZlvjJFmCaKkukj7wEj+pZJH1hybmqbY=";
            MerConfig merConfig = new MerConfig();
            merConfig.setApiKey(apiKey);
            merConfig.setApiMockKey(mockApiKey);
            merConfig.setRSAPrivateKey(rsaPrivateKey);
            Adapay.initWithMerConfig(merConfig);
//             * ????????????????????????????????????
            String appId = "app_5073f892-cecc-4922-8e5b-f8dfb8bda0ca";
            Map mapss = PaymentRegionAndRequestTimeOutDemo.executePaymentTest1(appId, userGrant.getBuyprice().setScale(2, BigDecimal.ROUND_HALF_UP), "??????", userGrant.getNumberno());
            Map mapsss = (Map) mapss.get("expend");
            ailay = mapsss.get("pay_info").toString();
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else {
            throw new CloudException(ExceptionConstant.????????????);
        }





        return map;
    }

    @Override
    public Map mhPayorder(int userid, int id, int type, String password, int paytype) throws Exception {
        Users users = userDao.selectByPrimaryKey(userid);
/*        if (StringUtil.isEmpty(users.getTradePassWord())) {
            throw new CloudException(ExceptionConstant.????????????????????????);
        }
        if (!users.getTradePassWord().equals(MD5Util.MD5Encode(password))) {
            throw new CloudException(ExceptionConstant.??????????????????);
        }*/
        Map map = new HashMap();
        Issue issue = issueDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
        String ailay = "";
        //1.??????2.??????3.?????????4.??????
        if (type == 1) {
            if (users.getBalance().compareTo(collection.getPrice()) == -1) {
                throw new CloudException(ExceptionConstant.????????????);
            }  //????????????adas
            Mybox mybox = new Mybox();
            mybox.setUserid(userid);
            mybox.setBoxid(collection.getBoxid());
            myboxDao.insertSelective(mybox);
            BigDecimal over = users.getBalance().subtract(collection.getPrice());
            Users users1 = new Users();
            users1.setUserId(userid);
            users.setBalance(over);
            userDao.updateByPrimaryKeySelective(users);
            BalanceRecord balanceRecord = new BalanceRecord();
            balanceRecord.setUserid(userid);
            balanceRecord.setName("??????" + collection.getName());
            balanceRecord.setCount(collection.getPrice().negate());
            balanceRecord.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord);
        } else if (type == 2) {
            Apliaymh apliaymh = new Apliaymh();
            apliaymh.setUserid(userid);
            apliaymh.setBoxid(collection.getBoxid());
            apliaymhDao.insertSelective(apliaymh);
            Map<String, String> returnMap = wxAppPayService.dounifiedOrder("??????", apliaymh.getId().toString(), collection.getPrice().toString());

            Gson gson = new Gson();

            ailay = gson.toJson(returnMap);

            map.put("ailay", ailay);
        } else if (type == 3) {//?????????
            Apliaymh apliaymh = new Apliaymh();
            apliaymh.setUserid(userid);
            apliaymh.setBoxid(collection.getBoxid());
            apliaymhDao.insertSelective(apliaymh);
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            String PARTNER = dictService.getValue("PARTNER");
            // if (paytype == 1) {  //??????????????????
            //??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(leix + "??????");
            model.setSubject(leix + "??????");
            model.setOutTradeNo(apliaymh.getId().toString()); //??????????????????(?????????(?????????+?????????:?????????))
            model.setTimeoutExpress("60m"); //???????????????????????????
            model.setTotalAmount(collection.getPrice().toString());  //???????????????
            model.setProductCode("QUICK_MSECURITY_PAY"); //?????????????????????????????????????????????????????????????????????QUICK_MSECURITY_PAY
            model.setSellerId(PARTNER); // ??????id
            request.setBizModel(model);
            request.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");  //????????????
            String orderStr = "";
            try {
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request); //???????????????????????????????????????????????????sdkExecute
                orderStr = response.getBody();
                System.out.println("=======>>>>>??????orderStr:" + orderStr); // ??????orderString ??????????????????????????????????????????????????????
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            //??????
            ailay = orderStr;

            map.put("ailay", ailay);
        } else if (type == 4) {
            Apliaymh apliaymh = new Apliaymh();
            apliaymh.setUserid(userid);
            apliaymh.setBoxid(collection.getBoxid());
            apliaymhDao.insertSelective(apliaymh);

            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            //??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            String aaa = "QUICK_WAP_WAY";
            //???????????????????????????????????????
            String timeout = "30m";
            //??????????????????
            String content = "{\"out_trade_no\":\"" + apliaymh.getId().toString() + "\"," + "\"total_amount\":\"" + collection.getPrice() + "\"," + "\"subject\":\"" + leix + "??????" + "\"," + "\"timeout_express\":\"" + timeout + "\"," + "\"body\":\"" + leix + "??????" + "\"," + "\"product_code\":\"" + aaa + "\"}";
            System.out.println("content" + content);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
            alipayRequest.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");
            alipayRequest.setBizContent(content);
            System.out.println("alipayRequest" + alipayRequest);
            //??????
            ailay = alipayClient.pageExecute(alipayRequest).getBody();
            map.put("ailay", ailay);

        } else if (type == 5) {
            Apliaymh apliaymh = new Apliaymh();
            apliaymh.setUserid(userid);
            apliaymh.setBoxid(collection.getBoxid());
            apliaymhDao.insertSelective(apliaymh);
            //debug ????????????????????????????????????
            Adapay.debug = true;
            //prodMode ?????????????????????????????????false????????????mock??????
            Adapay.prodMode = true;
//             * ?????????????????????????????????????????????????????????????????????????????????????????????
//             * apiKey???prod?????????API KEY
//             * mockApiKey???mock?????????API KEY
//             * rsaPrivateKey???????????????????????????????????????????????????????????????RSA??????
            String apiKey = "api_live_868e9791-b72e-4375-8f43-c7ab2f2ab7f3";
            String mockApiKey = "api_test_c63cd78b-44fb-400c-9f3e-e73d9bc2fbfa";
            String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItaGPyXckA1us32lI2eaqcfbuKmTxWq1xPK6nKMyBxeXzvTljyYDMDJPnrJdo57sJbzW/c3NqmGPA4EZuB0OhihDers0J+4UfBkzK3TV2dXqWuPaQeKNITT3HY7/JY0B4tjkx27CLpyDrcizZ22JsPsvDQY24T/Pgl0sgqbVbNZAgMBAAECgYBU7u0bxxKToCvjLM8OIefPRJFJAyRgiGfeYGbUuomdAMf6ptOCywm61u2F2RSxcyIGXv1GiBiA6pff0z8AvtZJFDYGCCn6BO+Z0Yl4Wdw2lgPn+ZXbKf330w0M528GgRPo3QYV84ChZj9zQeuuwyQPLFzqT/SL5RSGQEMI5vDAgQJBANiyfSZQy972Q+8rTELNwV35SKb+PIJgIAuzvH/GXQNsI1Kmmj3jyyQFXP4FolzDX8912Sm4DAcDhgBXOkzWuBUCQQCkoF/+sXHTCpHXlvX5nZcXnWI+nnHRk61TLUn8OZ7cdGBRbjZQjALLmH5IEB3p9OKzoPYAA6jLuNNoZnHnEfs1AkAhIzpsL5Ldp78/xdlFpf1aB5MKpnpOXbDI2VWMHKOAsHq+WuffawRXn9JWrjnkAaSXLyhkbta4A9vsFWq+8V75AkAEKY3jyabAGD9RvR5g/kwIq9EFFkp4awxx0u5Q80ACAOtCg8/Zv4B+l09yEP5AoRFuSz6NB4qoA/dMZ3KvzfOVAkAxHQ19ANyH0aIeQMu3FOQL/YOkI5E3EaS2lTmy5NQTv15/vPLLrOKwgU3KeS0/s+regQ3lOQxoYx+e/Qjdc6in";
//                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALM7qAYPHL74b9LbbFwrxFW2yRVU+fLGF0FCusPEBZbjY2g22DAFFPcU+wWj+ww/l4YXyAey89HCn/mBHeugfBKJWIK7qNe/Cx2wT8sMVox+ASuruZQMVDuFIKylHiDODLCxqcOtnxFzvLfAmo+g006pzl0sZzK6uEeVleB4gp43AgMBAAECgYEAqAD4a82InX2UnCqvtOoVnF68uDEyJZ4HOFYLaOt6bchxasNV/BUXZM+WyN+/t+h36OqrxoO4xltPxvl/iVmiUcyF0nR4MOwtLlf409JowPzb6oJVma3/M3HyMLXzovH+kQWG9MiQeHycXOC+2pgoCphldV8Bbqrf39/hM7A4euECQQD1Sk09dEGrU5aRK3cS6VIfoqufQP0gX/p0XyKhZq78M0FFNo6gQaPtp1+Iku8BVJc6u2JVxh6jKnNwSipkLhWxAkEAuw8CWMbCm2UJ351/I1OEtudBlZhIGFxyXi5udsx02dLJ0vCsoDohepyKolaRFl46v0PL2bSagZnCujzyyVskZwJATqDSwbBSnKjeywoz0UwZlyp9+T7L5WTe1A7q+vH/hbioY5dpgNWJxtSwOwdetf53u8v1aaloiMVC2+vqStK7AQJAIqxhjvsoz40nC2AyVOYCT7UGHyifFITuXi7VIwg7ELVV29fo57pewk+KExVgx3ioAxQaETy89xD9W3A4wMI4OQJBANcaWpIzg/paevDumZpcTofrugr/YS0acnS6WKPdFfRCUONl/Tr/v8y1ZlvjJFmCaKkukj7wEj+pZJH1hybmqbY=";
            MerConfig merConfig = new MerConfig();
            merConfig.setApiKey(apiKey);
            merConfig.setApiMockKey(mockApiKey);
            merConfig.setRSAPrivateKey(rsaPrivateKey);
            Adapay.initWithMerConfig(merConfig);
//             * ????????????????????????????????????
            String appId = "app_5073f892-cecc-4922-8e5b-f8dfb8bda0ca";
            Map mapss = PaymentRegionAndRequestTimeOutDemo.executePaymentTest1(appId, collection.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP), "??????", apliaymh.getId().toString());
            Map mapsss = (Map) mapss.get("expend");
            ailay = mapsss.get("pay_info").toString();
            map.put("ailay", ailay);
        } else {
            throw new CloudException(ExceptionConstant.????????????);
        }
        return map;
    }


    @Override
    public void mintnft(int collectionid, int user_grantid) {
        String address = dictService.getValue("address");
        String privateKey = dictService.getValue("privateKey");
        Collection collection = collectionDao.selectByPrimaryKey(collectionid);
        if (collection != null) {
            UserGrant userGrant = userGrantDao.selectByPrimaryKey(user_grantid);
            if (userGrant.getType() == 1) {
                                            String contractAddress = collection.getContractaddress();
                                            String stringdata = "address=" + address + "&privateKey=" + privateKey + "&contractAddress=" + contractAddress;
                                            System.out.println("stringdata :" + stringdata);
                                            String data = HttpRequest.sendGet("http://123.56.186.63:7200/api/mintnft", stringdata);
                                         /*   int b = collection.getSold() + 1;
                                            collection.setSold(b);
                                            collectionDao.updateByPrimaryKeySelective(collection);*/

                                            JSONObject jsonObject = JSONObject.fromObject(data);
                                            System.out.println("222222");
                                            System.out.println(jsonObject);
                                            String blockhash = jsonObject.get("blockhash").toString();
                                            String stringtokenId = jsonObject.get("tokenId").toString();//a
                                            int tokenId = Integer.valueOf(stringtokenId).intValue();
                                            userGrant.setTokenid(tokenId);
                                            int a = tokenId + 1;

                                            int bbb=collection.getSold()+1;

                                            userGrant.setTruenumber(bbb+"");
                                            userGrant.setHashs(blockhash);
                                            userGrant.setType(3);
                                            userGrant.setGranttype(2);
                                            userGrant.setCotype(2);
                                            userGrantDao.updateByPrimaryKeySelective(userGrant);

                                            HideRecordExample hideRecordExample=new HideRecordExample();
                                            hideRecordExample.createCriteria().andNoEqualTo(userGrant.getNumberno());
                                            List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
                                            String thenum=bbb+"/"+collection.getLimits();
                                            if(hideRecordList.size()>0){
                                                HideRecord hideRecord=hideRecordList.get(0);

                                                hideRecord.setThenum(thenum);
                                                hideRecordDao.updateByPrimaryKeySelective(hideRecord);
                                            }
                                            //??????????????????????????????mybox
                                            MyboxExample myboxExample=new MyboxExample();
                                            myboxExample.createCriteria().andUsergrantidEqualTo(userGrant.getId());
                                            List<Mybox>myboxList=myboxDao.selectByExample(myboxExample);
                                            if(myboxList.size()>0){
                                                Mybox mybox=myboxList.get(0);
                                                mybox.setTruenumber(thenum);
                                                mybox.setHash(blockhash);
                                                myboxDao.updateByPrimaryKeySelective(mybox);
                                            }

                                            if(userGrant.getIssueid()!=null){
                                                Issue issue=issueDao.selectByPrimaryKey(userGrant.getIssueid());
                                                if(issue!=null){
                                                    int aaa = issue.getSold() + 1;
                                                    issue.setSold(aaa);
                                                    issueDao.updateByPrimaryKeySelective(issue);
                                                }


                                            }

                                            //collection

                                            collection.setSold(bbb);
                                            collectionDao.updateByPrimaryKeySelective(collection);
                                            //??????????????????
                                            Imglist imglist=new Imglist();
                                            imglist.setAddress(blockhash);
                                            imglist.setUid(userGrant.getUserid());
                                            imglist.setPrice(userGrant.getBuyprice());
                                            imglist.setStarttime(new Date());
                                            imglistDao.insertSelective(imglist);

            }
        }

    }


    @Override
    public void thmintnft(int collectionid, int user_grantid) {
        Collection collection = collectionDao.selectByPrimaryKey(collectionid);
        if (collection != null) {
            UserGrant userGrant = userGrantDao.selectByPrimaryKey(user_grantid);
            Users users=userDao.selectByPrimaryKey(userGrant.getUserid());
            if (userGrant.getType() == 1) {
                //?????????????????????
                TransforlistExample transforlistExample=new TransforlistExample();
                transforlistExample.createCriteria().andColidEqualTo(collection.getId()).andStateEqualTo(0);
                List<Transforlist>transforlistList=transforlistDao.selectByExample(transforlistExample);
                if(transforlistList.size()>0){
                    Transforlist transforlist=transforlistList.get(0);

                    String tianheappid=dictService.getValue("tianheappid");
                    String tianheappkey=dictService.getValue("tianheappkey");
                    String thuserId=dictService.getValue("userId");
                    String thuserKey=dictService.getValue("userKey");
                    String mainpublickey=dictService.getValue("mainpublickey");
                    com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject();
                    //            Map<String, String> ingredients = new HashMap<String, String>();
                    jsonObj.put("appId",tianheappid);
                    jsonObj.put("appKey", tianheappkey);
                    jsonObj.put("userId", thuserId);
                    jsonObj.put("userKey", thuserKey);
                    jsonObj.put("contractAddress", collection.getThcontractaddress());
                    jsonObj.put("tokenId", transforlist.getThtokenid());
                    jsonObj.put("from", mainpublickey);
                    jsonObj.put("to", users.getThaddress());
                    System.out.println(jsonObj);
                    String url="https://api.tichain.tianhecloud.com/api/v2/nfr/transfer";

                    String json_to_string = jsonObj.toJSONString(jsonObj);
                    System.out.println("json_to_string :"+json_to_string);



                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    StringEntity postingString = null;// json??????
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

                    userGrant.setTokenid(transforlist.getThtokenid());
                    int a = transforlist.getThtokenid();

                    int bbb=collection.getSold()+1;

                    userGrant.setTruenumber(bbb+"");
                    userGrant.setHashs(transforlist.getTransactionhash());
                    userGrant.setType(3);
                    userGrant.setGranttype(2);
                    userGrant.setCotype(2);
                    userGrantDao.updateByPrimaryKeySelective(userGrant);

                    HideRecordExample hideRecordExample=new HideRecordExample();
                    hideRecordExample.createCriteria().andNoEqualTo(userGrant.getNumberno());
                    List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
                    String thenum=bbb+"/"+collection.getLimits();
                    if(hideRecordList.size()>0){
                        HideRecord hideRecord=hideRecordList.get(0);

                        hideRecord.setThenum(thenum);
                        hideRecordDao.updateByPrimaryKeySelective(hideRecord);
                    }
                    //??????????????????????????????mybox
                    MyboxExample myboxExample=new MyboxExample();
                    myboxExample.createCriteria().andUsergrantidEqualTo(userGrant.getId());
                    List<Mybox>myboxList=myboxDao.selectByExample(myboxExample);
                    if(myboxList.size()>0){
                        Mybox mybox=myboxList.get(0);
                        mybox.setTruenumber(thenum);
                        mybox.setHash(transforlist.getTransactionhash());
                        myboxDao.updateByPrimaryKeySelective(mybox);
                    }

                    if(userGrant.getIssueid()!=null){
                        Issue issue=issueDao.selectByPrimaryKey(userGrant.getIssueid());
                        if(issue!=null){
                            int aaa = issue.getSold() + 1;
                            issue.setSold(aaa);
                            issueDao.updateByPrimaryKeySelective(issue);
                        }


                    }

                    //collection

                    collection.setSold(bbb);
                    collectionDao.updateByPrimaryKeySelective(collection);
                    //??????????????????
                    Imglist imglist=new Imglist();
                    imglist.setAddress(endata);
                    imglist.setUid(userGrant.getUserid());
                    imglist.setPrice(userGrant.getBuyprice());
                    imglist.setStarttime(new Date());
                    imglistDao.insertSelective(imglist);

                    transforlist.setState(1);
                    transforlistDao.updateByPrimaryKeySelective(transforlist);
                }



            }
        }

    }

    @Override
    public void transfercoin(String privateKey, String fromaddress, String toaddress, String tokenId, String contractAddress) {
       /* String stringdata = "privateKey=" + privateKey + "&fromaddress=" + fromaddress + "&toaddress=" + toaddress + "&tokenId=" + tokenId + "&contractAddress=" + contractAddress;
        System.out.println("stringdata :" + stringdata);
        String data = HttpRequest.sendGet("http://123.56.186.63:7100/api/transfercoin", stringdata);
        JSONObject jsonObject = JSONObject.fromObject(data);
        System.out.println("222222");
        System.out.println(jsonObject);*/
    }


    @Override
    public void adduser(int uid, String address, String privateKey) {
        Users users = userDao.selectByPrimaryKey(uid);
        if (users != null) {
            users.setAddress(address);
            users.setPrivatekey(privateKey);
            userDao.updateByPrimaryKeySelective(users);
        }
    }

    @Override
    public Map houtaidetails(int id) {
        Map map = new HashMap();
        Collection collection = collectionDao.selectByPrimaryKey(id);
        map.put("id", collection.getId());//id
        map.put("name", collection.getName());//??????
        map.put("minname", collection.getAlbumname());
        map.put("limits", collection.getLimits());//??????
        map.put("isdeploy", collection.getIsdeploy());
        return map;
    }

    @Override
    public void updateaddress(int id, String address) {
        Collection collection = collectionDao.selectByPrimaryKey(id);
        collection.setContractaddress(address);
        collectionDao.updateByPrimaryKeySelective(collection);
    }

    @Override
    public String agreementss(int id) {
        return agreementsDao.selectByPrimaryKey(id).getContent();
    }

    @Override
    public synchronized Map hqaddress(String count,String code) {
        Map map = new HashMap();
       /* String projectid=dictService.getValue("projectid");
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/"+projectid+"/evmrpc");

        BaseService baseService = new BaseService();
        baseService.setFuncGasLimit("100000");
        Account acc = baseService.createAccountHex();
        System.out.println("================================" + acc.getAddress());
        System.out.println("================================" + acc.getPrivateKey());
        String addHex = baseService.accountHexToBech32(acc.getAddress());*/
        String address="";
        String privateKey="";
        String thaddress="";
        String thprivateKey="";
        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        //if(chaintype==0){
            /*String data = HttpRequest.sendGet("http://123.56.186.63:7100/api/creatwallet", "");
            //{"address":"0x2B7c12CAb75Af6352fFA55B8902a741bF7B1711E","privateKey":"0x4910725c57db0ae24b59cea6ea988057a12697c45fa9b0c404549996b374fff0"}
            JSONObject jsonObject = JSONObject.fromObject(data);
             address = jsonObject.get("address").toString();
             privateKey = jsonObject.get("privateKey").toString();*///a
        //}else{
            String param="appId=tichain20220701000002&appKey=3e3cf2fd9125a555f647f5256fac4d2f0e444ce2&userId="+count+"&userKey="+code;

            String tianheappid=dictService.getValue("tianheappid");
            String tianheappkey=dictService.getValue("tianheappkey");
            com.alibaba.fastjson.JSONObject jsonObj = new com.alibaba.fastjson.JSONObject();
//            Map<String, String> ingredients = new HashMap<String, String>();
            jsonObj.put("appId",tianheappid);
            jsonObj.put("appKey", tianheappkey);
            jsonObj.put("userId", count);
            jsonObj.put("userKey", code);
            System.out.println(jsonObj);
            String url="https://api.tichain.tianhecloud.com/api/v2/user";

            String json_to_string = jsonObj.toJSONString(jsonObj);
            System.out.println("json_to_string :"+json_to_string);



            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            StringEntity postingString = null;// json??????
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
            // Log.i("test",content);
            System.out.println(content);

            com.alibaba.fastjson.JSONObject codeobj = com.alibaba.fastjson.JSONObject.parseObject(content);
            int sendcode=Integer.parseInt(codeobj.get("code").toString());
            if(sendcode==0){
                String data11=codeobj.get("data").toString();
                com.alibaba.fastjson.JSONObject jsondate = com.alibaba.fastjson.JSONObject.parseObject(data11);
                String pubKey=jsondate.get("pubKey").toString();
                com.alibaba.fastjson.JSONObject pubKeysjon = com.alibaba.fastjson.JSONObject.parseObject(pubKey);
                thaddress = pubKeysjon.get("address").toString();
                thprivateKey = pubKeysjon.get("publicKey").toString();//a
            }
            //this.getRequest()?????????????????????HttpServletRequest?????????????????????




            /*  String data = HttpRequest.sendPost("https://api.tichain.tianhecloud.com/api/v2/user", param);
            System.out.println("data :"+data);*/
            /*try {
                String data = HttpUtil.postGeneralUrl("https://api.tichain.tianhecloud.com/api/v2/user","application-json",param,"utf-8");
                System.out.println("data :"+data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }*/
        //}


        map.put("address", address);
        map.put("privateKey", privateKey);
        map.put("thaddress", thaddress);
        map.put("thprivateKey", thprivateKey);
        return map;
    }






    @Override
    public void tui() {
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andCollidEqualTo(1);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        for (UserGrant userGrant : userGrants) {
            Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
            Users users = userDao.selectByPrimaryKey(userGrant.getUserid());
            BigDecimal a = users.getBalance().add(new BigDecimal(1));
            users.setBalance(a);
            userDao.updateByPrimaryKeySelective(users);
            BalanceRecord balanceRecord = new BalanceRecord();
            balanceRecord.setUserid(users.getUserId());
            balanceRecord.setName(collection.getName() + "??????");
            balanceRecord.setCount(new BigDecimal(1));
            balanceRecord.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord);
        }
    }

    @Override
    public Map persondetails(Integer logUserPid, int id) {
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);

        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", collection.getId());//id
        map.put("name", collection.getName());//??????
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//??????
        map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//??????
        map.put("limits", collection.getLimits());//??????
        map.put("price", collection.getPrice());//??????
        System.out.println("details=" + collection.getDetails());
        map.put("details", collection.getDetails());//??????
        map.put("publisher", collection.getPublisher());//?????????
        map.put("creator", collection.getCreator());//?????????
        map.put("colltype", collection.getType());//?????????
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
        return map;

    }

    @Override
    public int personConfirmorder(Integer userid, int id, String orderno) {
        Users users = userDao.selectByPrimaryKey(userid);
        if (users.getRealnametype() != 2) {
            throw new CloudException(ExceptionConstant.????????????????????????);
        }
        UserGrant userGrant11 = userGrantDao.selectByPrimaryKey(id);
        userGrant11.setOppositeuser(userid);
        userGrant11.setNumberno(orderno);
       /* UserGrant userGrant = new UserGrant();
        userGrant.setUserid(userid);
        userGrant.setCollid(userGrant11.getCollid());
        userGrant.setNumberno(orderno);
        userGrant.setBuyprice(userGrant11.getSellprice());
        userGrant.setCreatetime(new Date());
        userGrant.setBuytime(new Date());
        userGrant.setGranttype(1);
        userGrant.setCotype(0);
        userGrantDao.insertSelective(userGrant);*/
        userGrantDao.updateByPrimaryKeySelective(userGrant11);
        return userGrant11.getId();


    }

    @Override
    public Map personPayorder(Integer userid, int id, int type , int paytype) throws Exception {

        Users users = userDao.selectByPrimaryKey(userid);

        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        String ailay = "";
        //1.??????2.??????3.?????????4.??????
        if (type == 1) {
            if (users.getBalance().compareTo(userGrant.getBuyprice()) == -1) {
                throw new CloudException(ExceptionConstant.????????????);
            }
            BalanceRecord balanceRecord = new BalanceRecord();
            balanceRecord.setUserid(userid);
            balanceRecord.setName("??????" + collection.getName());
            balanceRecord.setCount(collection.getPrice().negate());
            balanceRecord.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord);
            userGrant.setType(3);

            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            BigDecimal over = users.getBalance().subtract(userGrant.getSellprice());
            Users users1 = new Users();
            users1.setUserId(userid);
            users.setBalance(over);
            userDao.updateByPrimaryKeySelective(users);


            BalanceRecord balanceRecord111 = new BalanceRecord();
            balanceRecord111.setUserid(userGrant.getUserid());
            balanceRecord111.setName("??????" + collection.getName());
            balanceRecord111.setCount(collection.getPrice().negate());
            balanceRecord111.setCreatetime(new Date());
            balanceRecordDao.insertSelective(balanceRecord111);

            Users users2 = userDao.selectByPrimaryKey(userGrant.getUserid());
            BigDecimal money = users2.getBalance().add(userGrant.getSellprice());

            users2.setBalance(money);
            userDao.updateByPrimaryKeySelective(users2);
            HideRecord hideRecord = new HideRecord();
            hideRecord.setUserid(userid);
            hideRecord.setImg(collection.getImg());
            hideRecord.setName(collection.getName());
            hideRecord.setPrice(collection.getPrice());
            hideRecord.setNo(userGrant.getNumberno());
            if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
                hideRecord.setNo(userGrant.getTruenumber());
            }
            hideRecord.setCreatetime(new Date());
            hideRecord.setMs("????????????");
            hideRecord.setType(2);//0.??????1.???2.???
            hideRecordDao.insertSelective(hideRecord);
            /*??????*/


        } else if (type == 2) {
            Map<String, String> returnMap = wxAppPayService.dounifiedOrder("??????", userGrant.getNumberno(), collection.getPrice().toString());

            Gson gson = new Gson();

            ailay = gson.toJson(returnMap);

            userGrant.setCotype(-1);
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);

        } else if (type == 3) {//?????????
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            String PARTNER = dictService.getValue("PARTNER");
//            if (paytype == 1) {  //??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(leix + "??????");
            model.setSubject(leix + "??????");
            model.setOutTradeNo(userGrant.getNumberno()); //??????????????????(?????????(?????????+?????????:?????????))
            model.setTimeoutExpress("60m"); //???????????????????????????
            model.setTotalAmount(collection.getPrice().toString());  //???????????????
            model.setProductCode("QUICK_MSECURITY_PAY"); //?????????????????????????????????????????????????????????????????????QUICK_MSECURITY_PAY
            model.setSellerId(PARTNER); // ??????id
            request.setBizModel(model);
            request.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");  //????????????
            String orderStr = "";
            try {
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request); //???????????????????????????????????????????????????sdkExecute
                orderStr = response.getBody();
                System.out.println("=======>>>>>??????orderStr:" + orderStr); // ??????orderString ??????????????????????????????????????????????????????
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            ailay = orderStr;


            userGrant.setCotype(-1);
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else if (type == 4) {
            String leix = "";
            String APPID = dictService.getValue("APPID");
            String PRIVATE_KEY = dictService.getValue("PRIVATE_KEY");
            String ALI_PUBLIC_KEY = dictService.getValue("ALI_PUBLIC_KEY");
            //??????????????????
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.SERVICE_URL, APPID, PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.INPUT_CHARSET, ALI_PUBLIC_KEY, AliPayConfig.SIGN_TYPE);
            //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
            String aaa = "QUICK_WAP_WAY";
            //???????????????????????????????????????
            String timeout = "30m";
            //??????????????????
            String content = "{\"out_trade_no\":\"" + userGrant.getNumberno() + "\"," + "\"total_amount\":\"" + collection.getPrice() + "\"," + "\"subject\":\"" + leix + "??????" + "\"," + "\"timeout_express\":\"" + timeout + "\"," + "\"body\":\"" + leix + "??????" + "\"," + "\"product_code\":\"" + aaa + "\"}";
            System.out.println("content" + content);
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
            alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
            alipayRequest.setNotifyUrl(dictService.getValue("yqlj") + ":8200/pay/alipay/notify");
            alipayRequest.setBizContent(content);
            System.out.println("alipayRequest" + alipayRequest);
            //??????
            ailay = alipayClient.pageExecute(alipayRequest).getBody();

            userGrant.setCotype(-1);
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);


        } else if (type == 5) {
            //debug ????????????????????????????????????
            Adapay.debug = true;
            //prodMode ?????????????????????????????????false????????????mock??????
            Adapay.prodMode = true;
//             * ?????????????????????????????????????????????????????????????????????????????????????????????
//             * apiKey???prod?????????API KEY
//             * mockApiKey???mock?????????API KEY
//             * rsaPrivateKey???????????????????????????????????????????????????????????????RSA??????
            String apiKey = "api_live_868e9791-b72e-4375-8f43-c7ab2f2ab7f3";
            String mockApiKey = "api_test_c63cd78b-44fb-400c-9f3e-e73d9bc2fbfa";
            String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItaGPyXckA1us32lI2eaqcfbuKmTxWq1xPK6nKMyBxeXzvTljyYDMDJPnrJdo57sJbzW/c3NqmGPA4EZuB0OhihDers0J+4UfBkzK3TV2dXqWuPaQeKNITT3HY7/JY0B4tjkx27CLpyDrcizZ22JsPsvDQY24T/Pgl0sgqbVbNZAgMBAAECgYBU7u0bxxKToCvjLM8OIefPRJFJAyRgiGfeYGbUuomdAMf6ptOCywm61u2F2RSxcyIGXv1GiBiA6pff0z8AvtZJFDYGCCn6BO+Z0Yl4Wdw2lgPn+ZXbKf330w0M528GgRPo3QYV84ChZj9zQeuuwyQPLFzqT/SL5RSGQEMI5vDAgQJBANiyfSZQy972Q+8rTELNwV35SKb+PIJgIAuzvH/GXQNsI1Kmmj3jyyQFXP4FolzDX8912Sm4DAcDhgBXOkzWuBUCQQCkoF/+sXHTCpHXlvX5nZcXnWI+nnHRk61TLUn8OZ7cdGBRbjZQjALLmH5IEB3p9OKzoPYAA6jLuNNoZnHnEfs1AkAhIzpsL5Ldp78/xdlFpf1aB5MKpnpOXbDI2VWMHKOAsHq+WuffawRXn9JWrjnkAaSXLyhkbta4A9vsFWq+8V75AkAEKY3jyabAGD9RvR5g/kwIq9EFFkp4awxx0u5Q80ACAOtCg8/Zv4B+l09yEP5AoRFuSz6NB4qoA/dMZ3KvzfOVAkAxHQ19ANyH0aIeQMu3FOQL/YOkI5E3EaS2lTmy5NQTv15/vPLLrOKwgU3KeS0/s+regQ3lOQxoYx+e/Qjdc6in";
//                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALM7qAYPHL74b9LbbFwrxFW2yRVU+fLGF0FCusPEBZbjY2g22DAFFPcU+wWj+ww/l4YXyAey89HCn/mBHeugfBKJWIK7qNe/Cx2wT8sMVox+ASuruZQMVDuFIKylHiDODLCxqcOtnxFzvLfAmo+g006pzl0sZzK6uEeVleB4gp43AgMBAAECgYEAqAD4a82InX2UnCqvtOoVnF68uDEyJZ4HOFYLaOt6bchxasNV/BUXZM+WyN+/t+h36OqrxoO4xltPxvl/iVmiUcyF0nR4MOwtLlf409JowPzb6oJVma3/M3HyMLXzovH+kQWG9MiQeHycXOC+2pgoCphldV8Bbqrf39/hM7A4euECQQD1Sk09dEGrU5aRK3cS6VIfoqufQP0gX/p0XyKhZq78M0FFNo6gQaPtp1+Iku8BVJc6u2JVxh6jKnNwSipkLhWxAkEAuw8CWMbCm2UJ351/I1OEtudBlZhIGFxyXi5udsx02dLJ0vCsoDohepyKolaRFl46v0PL2bSagZnCujzyyVskZwJATqDSwbBSnKjeywoz0UwZlyp9+T7L5WTe1A7q+vH/hbioY5dpgNWJxtSwOwdetf53u8v1aaloiMVC2+vqStK7AQJAIqxhjvsoz40nC2AyVOYCT7UGHyifFITuXi7VIwg7ELVV29fo57pewk+KExVgx3ioAxQaETy89xD9W3A4wMI4OQJBANcaWpIzg/paevDumZpcTofrugr/YS0acnS6WKPdFfRCUONl/Tr/v8y1ZlvjJFmCaKkukj7wEj+pZJH1hybmqbY=";
            MerConfig merConfig = new MerConfig();
            merConfig.setApiKey(apiKey);
            merConfig.setApiMockKey(mockApiKey);
            merConfig.setRSAPrivateKey(rsaPrivateKey);
            Adapay.initWithMerConfig(merConfig);
//             * ????????????????????????????????????
            String appId = "app_5073f892-cecc-4922-8e5b-f8dfb8bda0ca";
            Map mapss = PaymentRegionAndRequestTimeOutDemo.executePaymentTest1(appId, userGrant.getBuyprice().setScale(2, BigDecimal.ROUND_HALF_UP), "??????", userGrant.getNumberno());
            Map mapsss = (Map) mapss.get("expend");
            ailay = mapsss.get("pay_info").toString();

            userGrant.setCotype(-1);
            map.put("collectionid", collection.getId());
            map.put("user_grantid", userGrant.getId());
            map.put("ailay", ailay);
        } else {
            throw new CloudException(ExceptionConstant.????????????);
        }
        userGrant.setPaytype(type);
        userGrantDao.updateByPrimaryKeySelective(userGrant);

        return map;

    }

    @Override
    public List searchcommodity(String content,int type) {
        List list1 = new ArrayList();
        //?????????
        IssueExample collectionExample22 = new IssueExample();
        collectionExample22.createCriteria().andReleasetimeLessThanOrEqualTo(new Date()).andEndtimeGreaterThanOrEqualTo(new Date());
        collectionExample22.setOrderByClause("releasetime desc");
        List<Issue> collections = issueDao.selectByExample(collectionExample22);
        for (Issue issue : collections) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            if(collection!=null){
                if(collection.getName().contains(content)){
                    Map map = new HashMap();
                    // map.put("whitelist", users.getWhitelist());//id
                    map.put("bmdtq", dictService.getValue("bmdtq"));//id
                    map.put("id", issue.getId());//id
                    map.put("name", collection.getName());//??????
                    map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
                    map.put("limits", collection.getLimits());//??????
                    map.put("price", collection.getPrice());//??????
                    map.put("publisher", collection.getPublisher());//?????????
                    map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
                    List label = new ArrayList();
                    JSONArray jsonObject = JSONArray.fromObject(collection.getLabel());
                    if (jsonObject.size() > 1) {
                        for (int i = 0; i < jsonObject.size(); i++) {
                            JSONObject job = jsonObject.getJSONObject(i);
                            Map map1 = new HashMap();
                            map1.put("label", job.get("label"));//??????
                            label.add(map1);
                        }
                    }
                    //?????????????????????????????? = -1??? ?????? =0???????????? = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    map.put("type", 0);//1.?????????
                    map.put("time", "?????????");
                    if (a == 1) {
                        map.put("type", 1);//1.?????????
                        long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                        System.out.println("hm=" + hm);
                        map.put("second", hm / 1000);//1.?????????
                        map.put("time", "???????????? " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " ??????");
                    }
                    //?????????????????????????????? = -1??? ?????? =0???????????? = 1
                    System.out.println("????????????1");
                    System.out.println(DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT));
                    if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                        map.put("type", 2);//2.?????????
                        map.put("time", "?????????");
                        if (issue.getSold() >= issue.getPresale()) {
                            map.put("type", 2);//2.?????????
                            map.put("time", "?????????");
                        }
                    } else {
                        System.out.println("????????????");
                        System.out.println("issue.getSold()=" + issue.getSold());
                        System.out.println("issue.getPresale()=" + issue.getPresale());
                        if (issue.getSold() >= issue.getPresale()) {
                            map.put("type", 2);//2.?????????
                            map.put("time", "?????????");
                        }
                    }
                    list1.add(map);
                }
            }
        }
        if(type==2){
            Collections.sort(list1, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    Integer name1 = Integer.valueOf(o1.get("price").toString()) ;

                    Integer name2 = Integer.valueOf(o2.get("price").toString()) ;

                    return name1.compareTo(name2);

                }

            });


        }else if(type==3){
            Collections.sort(list1, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    Integer name1 = Integer.valueOf(o1.get("price").toString()) ;

                    Integer name2 = Integer.valueOf(o2.get("price").toString()) ;

                    return name2.compareTo(name1);

                }

            });


        }


        return list1;
    }

    @Override
    public Map appointmentinfo(int uid, int id) {
        System.out.println("id  "+id);
        Users users = userDao.selectByPrimaryKey(uid);
        if(users.getRealnametype()==0){
            throw new CloudException(ExceptionConstant.????????????);
        }
        if(users.getWhitelist()==0){
            throw new CloudException(ExceptionConstant.??????????????????);
        }

        Issue issue = issueDao.selectByPrimaryKey(id);

        if(issue.getPresale()<issue.getSold()+1){
            throw new CloudException(ExceptionConstant.????????????);
        }

        Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());

        BigDecimal balance = users.getBalance();
        BigDecimal price = collection.getPrice();
        if (balance.compareTo(price) == -1) {
            throw new CloudException(ExceptionConstant.????????????);
            //return null;
        }

        //????????????????????????
       UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andUseridEqualTo(uid).andIssueidEqualTo(id).andTypeEqualTo(6) ;
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if(userGrants.size()>0){
            throw new CloudException(ExceptionConstant.?????????);
        }

        users.setFreezemoney(users.getFreezemoney().add(price));
        users.setBalance(balance.subtract(price));
        userDao.updateByPrimaryKeySelective(users);

        //balancerecord
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setUserid(uid);
        balanceRecord.setCount(price.negate());
        balanceRecord.setCreatetime(new Date());
        balanceRecord.setName("????????????");
        balanceRecordDao.insertSelective(balanceRecord);

        String orderno = System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
        UserGrant userGrant = new UserGrant();
        userGrant.setUserid(uid);
        userGrant.setIssueid(id);

        userGrant.setCollid(issue.getCollid());
        userGrant.setNumberno(orderno);
        userGrant.setBuyprice(collection.getPrice());
        userGrant.setCreatetime(new Date());
        userGrant.setBuytime(new Date());
        userGrant.setGranttype(1);
        userGrant.setCotype(1);
        userGrant.setCollectiontype(collection.getType());
        userGrant.setAlbumid(collection.getAlbumid());
        userGrant.setType(6);
        userGrant.setAlbumname(collection.getAlbumname());
        userGrantDao.insertSelective(userGrant);

        //appointment
        int sold = issue.getSold()+1;
        issue.setSold(sold);
        issueDao.updateByPrimaryKeySelective(issue);


        Map map = new HashMap();


        map.put("id", userGrant.getId());


        return map;


    }

    @Override
    public Map prompt(Integer logUserPid) {
        Map map=new HashMap();

        PromptExample promptExample=new PromptExample();
        promptExample.createCriteria().andUseridEqualTo(logUserPid).andTypeEqualTo(0);
        List<Prompt>promptList=promptDao.selectByExample(promptExample);
        if(promptList.size()>0){
            Prompt prompt=promptList.get(0);
            map.put("type",prompt.getType());
            map.put("state",prompt.getState());
            map.put("content",prompt.getContent());
            map.put("id",prompt.getId());
        }else{
            throw new CloudException(ExceptionConstant.?????????);
        }
        return map;
    }

    @Override
    public void readprompt(Integer logUserPid, int id) {
        Prompt prompt=promptDao.selectByPrimaryKey(id);
        prompt.setType(1);
        promptDao.updateByPrimaryKeySelective(prompt);

    }

    @Override
    public String detailsimg(Integer logUserPid, int id,int ids,String type) {
        //??????iss
        Collection collection=collectionDao.selectByPrimaryKey(id);
        IssueExample issueExample=new IssueExample();
        issueExample.setOrderByClause("id desc");
        issueExample.createCriteria().andCollidEqualTo(id);
        List<Issue>issueList=issueDao.selectByExample(issueExample);
        int total=collection.getLimits();
        if(issueList.size()>0){
            Issue issue=issueList.get(0);
            total=issue.getPresale();
        }
        String strtotal=total+"???";
        String name=collection.getName();
        String album=collection.getAlbumname();
        String img=ImgEnum.img.getPath()+collection.getImg1();
        Users user = userDao.selectByPrimaryKey(logUserPid);
        String a = ImgEnum.img.getPath() + colcreateUserShareImg1(ids,type);
        QRcode qRcode=new QRcode();
        String dicturl=dictService.getValue("url");
        String url = dicturl+ "/#/pages/index/shopDetails?id=" + ids+"&type="+type;
        //qRcode.CreatQRCode(ImgEnum.img.getPath() +id+ user.getUserId() + ".jpg",new File(ImgEnum.img.getPath() + "details.png"), 50, 50, url, name,album, 10, 20, 365, 120, 380, 120, 400,img,20,370,40,40);
        System.out.println("url :"+url);
        System.out.println("details :"+ImgEnum.img.getPath() + "details.png");
        System.out.println("jpg :"+id + user.getUserId() + ".jpg");
        System.out.println("name :"+name);
        System.out.println("album :"+album);
        System.out.println("img :"+img);

        String onedetails= composePic(ImgEnum.img.getPath() + "details.png", a, id + user.getUserId() + ".jpg", name,strtotal,img);
      return onedetails;
    }

    @Override
    public Map noregdetails(int id) {

        Map map = new HashMap();
        Issue issue = issueDao.selectByPrimaryKey(id);
        //?????????????????????????????? = -1??? ?????? =0???????????? = 1

        long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == 1) {
            map.put("second", hms / 1000);//1.?????????
            map.put("type", 0);//
            map.put("time", "??????????????????");
        } else if(DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) ==1){
            map.put("second", hms / 1000);//1.?????????
            map.put("type", 1);//
            map.put("time", "????????????");
        }else {
            map.put("second", 0);//1.?????????
            map.put("type", 2);//
            map.put("time", "?????????");
        }

        Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());



        List list1 = new ArrayList();
        if(collection.getType()==1){
            Blindbox blindbox=blindboxDao.selectByPrimaryKey(collection.getBoxid());

            List<Integer> idl = new ArrayList();
            String[] split = blindbox.getFragment().split(",");
            for (int i = 0; i < split.length; i++) {
                idl.add(Integer.parseInt(split[i]));
            }


            for (Integer integer : idl) {
                Map map1 = new HashMap();
                Collection collectionnew = collectionDao.selectByPrimaryKey(integer);
                map1.put("name", collectionnew.getName());
                map1.put("img", ImgEnum.img.getUrl() + collectionnew.getImg());


                list1.add(map1);
            }
        }
        map.put("fragment", list1);






        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        if(chaintype==0){
            map.put("contractAddress",collection.getContractaddress());
        }else{
            map.put("contractAddress",collection.getThcontractaddress());
        }


        map.put("id", issue.getId());//id
        map.put("name", collection.getName());//??????
        map.put("collid", collection.getId());//??????
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//??????
        map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//??????
        map.put("limits", issue.getPresale());//??????
        map.put("price", collection.getPrice());//??????
        System.out.println("details=" + collection.getDetails());
        map.put("details", collection.getDetails());//??????
        map.put("minname",collection.getAlbumname());


        map.put("biaoshi","ERC721");
        map.put("publisher", collection.getPublisher());//?????????
        //map.put("creator", collection.getCreator());//?????????
        map.put("colltype", collection.getType());//?????????
        map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
        map.put("newtime",DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.DATETIME_FORMAT));

        List list=new ArrayList();

      /*  UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andUseridEqualTo(uerid).andIssueidEqualTo(id).andTypeEqualTo(6) ;
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if(userGrants.size()>0){
            map.put("isgrant",1);
        }else {
            map.put("isgrant",0);
        }*/
        map.put("isgrant",2);

        return map;


    }

    @Override
    public void trial(int type) {
        System.out.println("START_TRIAL :"+START_TRIAL);
        START_TRIAL=type;
        System.out.println("START_TRIAL :"+START_TRIAL);
    }

    @Override
    public Map collections(int type) {
        Map maps=new HashMap();
        List list1=new ArrayList();
        //?????????
        if(type==2){
            List <Blindbox> blindboxes=blindboxDao.selectByExample(new BlindboxExample());
            if(blindboxes.size()>0){
                for (Blindbox blindbox : blindboxes) {
                    Map map = new HashMap();
                    //?????????????????????issue


                    Collection collectionss = collectionDao.selectByPrimaryKey(blindbox.getSynthesis());
                    if(collectionss!=null){
                        map.put("id", blindbox.getId());//id
                        map.put("name", collectionss.getName());//??????
                        map.put("img", ImgEnum.img.getUrl() + collectionss.getImg());//??????
                        map.put("limits", collectionss.getLimits());//??????
                        map.put("price", collectionss.getPrice());//??????
                        map.put("publisher", collectionss.getPublisher());//?????????
                        map.put("cimg", ImgEnum.img.getUrl() + collectionss.getCreatorimg());//?????????
                        map.put("begintime", DateUtils.getDateToStr(blindbox.getBegintime(), DateUtils.DATE_FORMAT));
                        map.put("endtime",DateUtils.getDateToStr(blindbox.getEndtime(), DateUtils.DATE_FORMAT));

                    }
                    if(!map.isEmpty()){
                        list1.add(map);
                    }

                }
            }
        }else{
            IssueExample collectionExample22 = new IssueExample();
            collectionExample22.createCriteria().andTypeEqualTo(0);
            collectionExample22.setOrderByClause("releasetime desc");
            List<Issue> collections = issueDao.selectByExample(collectionExample22);
            for (Issue issue : collections) {
                Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
                if(collection.getType()==type){
                    Map map = new HashMap();
                    map.put("id", issue.getId());//id
                    map.put("name", collection.getName());//??????
                    map.put("img", ImgEnum.img.getUrl() + collection.getImg());//??????
                    map.put("limits", issue.getPresale());//??????
                    map.put("price", collection.getPrice());//??????
                    map.put("publisher", collection.getPublisher());//?????????
                    map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//?????????
                    List label = new ArrayList();
                    //?????????????????????????????? = -1??? ?????? =0???????????? = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());

          /*  map.put("type", 0);//1.?????????
            map.put("time", "?????????");*/

                    //?????????????????????????????? = -1??? ?????? =0???????????? = 1
                    long hms = DateUtils.dateDiff(new Date(), issue.getStarttime());
                    if (DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) == 1) {
                        map.put("second", hms / 1000);//1.?????????
                        map.put("type", 0);//
                        map.put("time", "??????????????????");
                    } else if(DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) ==1){
                        //if

                        map.put("second", hms / 1000);//1.?????????
                        map.put("type", 1);//
                        map.put("time", "????????????");
                    }else if(DateUtils.compareTo(new Date(), issue.getStarttime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) ==1){
                        map.put("second", hms / 1000);//1.?????????
                        map.put("type", 2);//
                        map.put("time", "????????????");
                    }else {
                        map.put("second", 0);//1.?????????
                        map.put("type", 3);//
                        map.put("time", "?????????");
                    }


                    list1.add(map);

                }

            }
        }


        maps.put("szcp", list1);

        return maps;
    }

    @Override
    public List draw(int userid) {
        List list=new ArrayList();
        DrawExample drawExample=new DrawExample();
        drawExample.createCriteria().andStateEqualTo(0);
        drawExample.setOrderByClause("id desc");
        List<Draw>drawList=drawDao.selectByExample(drawExample);
        if(drawList.size()>0){
            for (Draw draw : drawList) {
                Collection collection=collectionDao.selectByPrimaryKey(draw.getIssueid());
                IssueExample issueExample=new IssueExample();
                issueExample.createCriteria().andCollidEqualTo(draw.getIssueid());
                issueExample.setOrderByClause("id desc");
                List<Issue>issueList=issueDao.selectByExample(issueExample);
                Issue issue=issueList.get(0);

                Map map=new HashMap();
                map.put("buytime",DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.DATETIME_FORMAT));
                map.put("id",draw.getId());
                map.put("name",draw.getName());
                map.put("img",ImgEnum.img.getUrl()+draw.getImg());
                map.put("number",draw.getNumber());
                map.put("price",draw.getPrice());
                map.put("halfprice",draw.getPrice().divide(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_CEILING));
                map.put("drawnumber",draw.getDrawnumber());
                map.put("starttime", DateUtils.getDateToStr(draw.getStarttime(), DateUtils.DATETIME_FORMAT));
                map.put("endtime", DateUtils.getDateToStr(draw.getEndtime(), DateUtils.DATETIME_FORMAT));
                map.put("drawtime", DateUtils.getDateToStr(draw.getDrawtime(), DateUtils.DATETIME_FORMAT));
                if (DateUtils.compareTo(new Date(), draw.getStarttime(), DateUtils.DATETIME_FORMAT) == 1) {
                    map.put("type", 0);//
                    map.put("time", "??????????????????");
                } else if(DateUtils.compareTo(new Date(), draw.getStarttime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), draw.getEndtime(), DateUtils.DATETIME_FORMAT) ==1){

                    DrawrecordExample drawrecordExample=new DrawrecordExample();
                    drawrecordExample.createCriteria().andDrawidEqualTo(draw.getId()).andUseridEqualTo(userid);
                    List<Drawrecord>drawrecordList=drawrecordDao.selectByExample(drawrecordExample);
                    if(drawrecordList.size()>0){
                        map.put("type", 3);//
                        map.put("time", "?????????");
                    }else{
                        map.put("type", 1);//
                        map.put("time", "????????????");
                    }


                }else {

                    map.put("type", 2);//
                    map.put("time", "?????????");
                }

                list.add(map);
            }
        }
        return list;
    }

    @Override
    public void signup(int userid,int id) {
        Draw draw=drawDao.selectByPrimaryKey(id);
        //int Issueid=draw.getIssueid();
        Users users = userDao.selectByPrimaryKey(userid);
        if(users.getRealnametype()==0){
            throw new CloudException(ExceptionConstant.????????????);
        }


        if(DateUtils.compareTo(new Date(), draw.getStarttime(), DateUtils.DATETIME_FORMAT) <=0 && DateUtils.compareTo(new Date(), draw.getEndtime(), DateUtils.DATETIME_FORMAT) ==1){


            if(users.getMoney().compareTo(new BigDecimal(10))==-1){
                throw new CloudException(ExceptionConstant.????????????);
            }
            //?????????????????????
            DrawrecordExample drawrecordExample=new DrawrecordExample();
            drawrecordExample.createCriteria().andDrawidEqualTo(id).andUseridEqualTo(userid);
            List<Drawrecord>drawrecordList=drawrecordDao.selectByExample(drawrecordExample);
            if(drawrecordList.size()>0){
                throw new CloudException(ExceptionConstant.????????????);
            }
            int drawnum=draw.getDrawnumber()+1;
            System.out.println("drawnum :"+drawnum);
            draw.setDrawnumber(drawnum);
            drawDao.updateByPrimaryKeySelective(draw);

            Drawrecord drawrecord=new Drawrecord();
            drawrecord.setDrawid(id);
            drawrecord.setUserid(userid);
            drawrecord.setCreattime(new Date());
            drawrecordDao.insertSelective(drawrecord);
            //???????????????
            BigDecimal money=draw.getPrice();
            users.setMoney(users.getMoney().subtract(money));
            userDao.updateByPrimaryKeySelective(users);

            Moneyrecord moneyrecord=new Moneyrecord();
            moneyrecord.setName("????????????");
            moneyrecord.setTime(new Date());
            moneyrecord.setUserid(userid);
            moneyrecord.setPrice(money.negate());
            moneyrecordDao.insertSelective(moneyrecord);


        }else{
            throw new CloudException(ExceptionConstant.?????????????????????);
        }

    }

    @Override
    public List drawbanner() {
        BannerExample bannerExample=new BannerExample();
        bannerExample.createCriteria().andTypeEqualTo(1);
        List<Banner> banners = bannerDao.selectByExample(bannerExample);
        List list = new ArrayList();
        for (Banner banner : banners) {
            Map map = new HashMap();
            map.put("id", banner.getId());
            map.put("title",banner.getTitle());
            map.put("content",banner.getContent());
            map.put("title1",banner.getTitle1());
            map.put("state",banner.getState());
            map.put("link",banner.getLink());
            map.put("img", ImgEnum.img.getUrl() + banner.getImg());
            list.add(map);
        }

        return list;
    }

    @Override
    public Map cansend(int logUserPid, int id) {
        Map map=new HashMap();
        UserGrant userGrant=userGrantDao.selectByPrimaryKey(id);
        int sendtimelist=Integer.parseInt(dictService.getValue("sendtimelist"));
        Date limitday=userGrant.getBuytime();

        Date newday=DateUtils.getNextDay(limitday,sendtimelist);
        Date nowday=new Date();
        if(newday.compareTo(nowday)==1){
            map.put("type",0);
            map.put("time",DateUtils.getDateToStr(newday, DateUtils.TIME_FORMAT1));

        }else{
            map.put("type",1);
            map.put("time",0);
        }
        return map;
    }


    /**
     * ?????????????????????
     *
     * @param registerCode
     * @return
     */
    public String createUserShareImg1(String registerCode) {
        String dicturl=dictService.getValue("url");
        System.out.println("???????????????");
        String fileName = "InvQRCode" + registerCode + ".jpg";
        File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//??????????????????
        //String url = ImgEnum.img.getUrl()+ "http://nftxcsc.com:8100/#/?uid=" + registerCode;
        String url = dicturl+ ":8200/#/?uid=" + registerCode;
        boolean falg = QRcode.CreatQRCode(QrCodeFile, 100, 100, url);
        if (falg) {
            return fileName;
        } else {
            return null;
        }
    }
    public String colcreateUserShareImg1(int ids,String type) {
        String dicturl=dictService.getValue("url");
        System.out.println("???????????????");
        String fileName = "InvQRCodecol" + ids + ".jpg";
        File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//??????????????????
        //String url = ImgEnum.img.getUrl()+ "http://nftxcsc.com:8100/#/?uid=" + registerCode;
        //String url = dicturl+ ":8200/#/?uid=" + registerCode;
        String url = dicturl+ "/#/pages/index/shopDetails?id=" + ids+"&type="+type;
        boolean falg = QRcode.CreatQRCode(QrCodeFile, 100, 100, url);
        if (falg) {
            return fileName;
        } else {
            return null;
        }
    }

    public static String composePic(String templatePath, String seedPath, String name, String colname,String album,String colimg) {
        try {
            String picName = name;
            //??????????????????
            String path = ImgEnum.img.getPath() + File.separator + picName;
            //---------------------------------??????????????????-----------------------------
            //??????
            System.out.println("11111");
            File templateFlie = new File(templatePath);
            BufferedImage bg = ImageIO.read(templateFlie);//??????????????????
            int height = bg.getHeight();//??????????????????
            int width = bg.getWidth();  //??????????????????
            System.out.println("2333");
            System.out.println("seedPath :"+seedPath);
            BufferedImage qcCode = ImageIO.read(new File(seedPath));  //????????????
            System.out.println("colimg :"+colimg);
            BufferedImage colimg11 = ImageIO.read(new File(colimg));  //????????????
            System.out.println("5555");
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//????????????
            Graphics g = img.getGraphics();//???????????? ????????????
            // ??????????????????
            System.out.println("44444444");
            g.drawImage(bg.getScaledInstance(width, height, Image.SCALE_DEFAULT), 0, 0, null); // ?????????????????????
            //?????????????????????  ??????????????????????????????
            g.drawImage(qcCode.getScaledInstance(250, 250, Image.SCALE_DEFAULT), 800, 1540, null); // ?????????????????????
            System.out.println("22222");
            g.drawImage(colimg11.getScaledInstance(width, 1500, Image.SCALE_DEFAULT), 0, 0, null); // ?????????????????????
            //?????????????????????????????????????????????
            Font f = new Font("?????????", Font.BOLD, 50);
            //Color mycolor = Color.BLUE;//new Color(0, 255, 255);
            Color mycolor = new Color(0, 255, 255);
            g.setColor(mycolor);
            g.setFont(f);
            //10,20 ???????????????????????????????????????(x,y) .?????????????????????????????????
            g.drawString(colname, 50, 1580);
            Font f11 = new Font("?????????", Font.BOLD, 30);
            //Color mycolor = Color.BLUE;//new Color(0, 255, 255);
            Color mycolor11 = Color.white;
            g.setColor(mycolor11);
            g.setFont(f11);
            g.drawString(album, 580, 1575);

            //????????????
            g.dispose();
            ImageIO.write(img, "jpg", new File(path));
            //???????????????????????????
            return ImgEnum.img.getUrl() + picName;
        } catch (Exception E) {
            throw new CloudException(ExceptionConstant.????????????);
        }
    }

}
