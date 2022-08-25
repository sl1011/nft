package com.shitouren.core.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.controller.DDCSdkClient;
import com.shitouren.core.controller.SignEventTest;
import com.shitouren.core.utils.DateUtils;
import com.shitouren.core.utils.MD5Util;
import com.shitouren.core.utils.StringUtil;
import com.shitouren.core.utils.UtilDate;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    AlbumDao albumDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    IssueDao issueDao;

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

    @Override
    public Map show(int userid,int type) {
        Map maps = new HashMap();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        //我的
        List list1 = new ArrayList();
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andUseridEqualTo(userid).andCotypeNotEqualTo(0).andTypeIn(integers);
        userGrantExample1.setOrderByClause("id desc");
//        userGrantExample1.createCriteria().andUseridEqualTo(userid).andTypeEqualTo(0).andGranttypeEqualTo(2);
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        for (UserGrant grant : userGrant) {
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            Map map = new HashMap();
            if(type==collection.getType()){
                map.put("id", grant.getId());//id
               // map.put("no", grant.getNumberno());//编号 .getTruenumber() != null
               /* if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                    map.put("no", grant.getTruenumber());//编号
                }*/
                map.put("no", grant.getTruenumber()+"/"+collection.getLimits());//编号
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("creator", collection.getPublisher());//发行者
                map.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());//发行者
                map.put("type",grant.getType());
                map.put("isbuy",grant.getGranttype());
            }
            if(!map.isEmpty()){
                list1.add(map);
            }


        }
        //上架
       /* List list2 = new ArrayList();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(5);
        UserGrantExample userGrantExample2 = new UserGrantExample();
        userGrantExample2.createCriteria().andUseridEqualTo(userid).andTypeIn(integers).andGranttypeEqualTo(2);
        List<UserGrant> userGrant1 = userGrantDao.selectByExample(userGrantExample2);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant1) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getNumberno());//编号
            if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                map.put("no", grant.getTruenumber());//编号
            }
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("creator", collection.getPublisher());//发行者
            map.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());//发行者
            map.put("price", grant.getSellprice());//
            map.put("type", grant.getType());//
            list2.add(map);
        }

        //卖出
        List list3 = new ArrayList();
        List<Integer> integers3 = new ArrayList<>();
        integers3.add(3);

        UserGrantExample userGrantExample33 = new UserGrantExample();
        userGrantExample33.createCriteria().andUseridEqualTo(userid).andTypeIn(integers3).andGranttypeEqualTo(2).andOppositeuserIsNotNull();
        List<UserGrant> userGrant333 = userGrantDao.selectByExample(userGrantExample33);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant333) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getNumberno());//编号
            if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                map.put("no", grant.getTruenumber());//编号
            }
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("creator", collection.getPublisher());//发行者
            map.put("creatorimg", collection.getCreatorimg());//发行者
            map.put("price", grant.getSellprice());//
            map.put("type", grant.getType());//
            list3.add(map);
        }*/

        maps.put("list1", list1);//数字藏品
        //maps.put("list2", list2);//上架
       // maps.put("list3", list3);//上架
        return maps;
    }

    @Override
    public Map details(int userid,int id) {
        Users users=userDao.selectByPrimaryKey(userid);
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());



        List list1 = new ArrayList();
        if(collection.getType()==1){
            Blindbox blindbox=blindboxDao.selectByPrimaryKey(collection.getBoxid());

           /* List<Integer> idl = new ArrayList();

            com.alibaba.fastjson.JSONArray jsonArray= JSONArray.parseArray(blindbox.getFragment());
            System.out.println("jsonArray :"+jsonArray);
            for(int i=0;i<jsonArray.size();i++){
                com.alibaba.fastjson.JSONObject object= (JSONObject) jsonArray.get(i);
                //labelstring=labelstring+object.get("label").toString()+",";
                String isnum=object.get("label").toString();
                idl.add(Integer.parseInt(isnum));

            }


            for (Integer integer : idl) {
                Map map1 = new HashMap();
                Collection collectionnew = collectionDao.selectByPrimaryKey(integer);
                map1.put("name", collectionnew.getName());
                map1.put("img", ImgEnum.img.getUrl() + collectionnew.getImg());


                list1.add(map1);
            }*/
        }
        //map.put("fragment", list1);






        map.put("id", userGrant.getId());//id
        map.put("username", users.getNickName());//id
        map.put("no", userGrant.getTruenumber()+"/"+collection.getLimits());//编号
        Users user = userDao.selectByPrimaryKey(userGrant.getUserid());
        map.put("collector", user.getNickName());//编号
        map.put("details", collection.getDetails());//详情
        map.put("minname",collection.getAlbumname());
        map.put("creator",collection.getCreator());
        map.put("creatorimg",collection.getCreatorimg());
        map.put("type",userGrant.getType());
        int chaintype=Integer.parseInt(dictService.getValue("chaintype"));
        if(chaintype==0){
            map.put("contractAddress",collection.getContractaddress());
        }else{
            map.put("contractAddress",collection.getThcontractaddress());
        }
       // map.put("contractAddress",collection.getContractaddress());
        map.put("biaoshi","ERC721");
        map.put("hash", userGrant.getHashs());//编号
        map.put("name", collection.getName());//名称
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
        map.put("creator", collection.getCreator());//创作者
        map.put("buyprice", userGrant.getBuyprice());//创作者
        map.put("date", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.DATETIME_FORMAT));//创作者
        return map;
    }

    @Override
    public void sell(int userid, int id, BigDecimal price, String pass) {
        int issale=Integer.parseInt(dictService.getValue("issale"));
        if(issale==0){
            throw new CloudException(ExceptionConstant.暂未开放);
        }

        Users user = userDao.selectByPrimaryKey(userid);
      /*  if (StringUtil.isEmpty(user.getTradePassWord())) {
            throw new CloudException(ExceptionConstant.请先设置操作密码);
        }
        if (!user.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
            throw new CloudException(ExceptionConstant.操作密码错误);
        }*/
        if (price.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.价格有误);
        }
       /* BigDecimal csed = new BigDecimal(dictService.getValue("csed"));
        if (price.compareTo(csed) == 1) {
            throw new CloudException(ExceptionConstant.出售超出最大限额);
        }*/
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        if (userid != userGrant.getUserid()) {
            throw new CloudException(ExceptionConstant.请勿进行非法操作);
        }
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        if (userGrant.getType() != 3) {
            throw new CloudException(ExceptionConstant.请勿进行非法操作);
        }


        userGrant.setSellprice(price);
        userGrant.setType(7);
        userGrant.setSjtime(new Date());
        userGrantDao.updateByPrimaryKeySelective(userGrant);
        /*HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(userid);
        hideRecord.setImg(collection.getImg());
        hideRecord.setName(collection.getName());
        hideRecord.setPrice(price);
        hideRecord.setNo(userGrant.getNumberno());
        if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
            hideRecord.setNo(userGrant.getTruenumber());
        }
        hideRecord.setMs("出售成功");
        hideRecord.setCreatetime(new Date());
        hideRecord.setType(1);//0.黄的1.绿2.红
        hideRecordDao.insertSelective(hideRecord);*/
    }

    @SneakyThrows
    @Override
    public void Passon(int userid, int id, String phone) {
        Users user = userDao.selectByPrimaryKey(userid);
        int userstate =Integer.parseInt(user.getStatusId());
        if(userstate==1){
            throw new CloudException(ExceptionConstant.账号已被禁用);
        }

        int isgive=Integer.parseInt(dictService.getValue("isgive"));
        if(isgive==0){
            throw new CloudException(ExceptionConstant.暂未开放);
        }



        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<Users> users = userDao.selectByExample(usersExample);
        if (users.size() == 0) {
            throw new CloudException(ExceptionConstant.查无此人);
        }
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        if (userid != userGrant.getUserid()) {
            throw new CloudException(ExceptionConstant.请勿进行非法操作);
        }
        if(userGrant.getType()!=3){
            throw new CloudException(ExceptionConstant.请勿进行非法操作);
        }

        int sendtimelist=Integer.parseInt(dictService.getValue("sendtimelist"));
        Date limitday=userGrant.getBuytime();

       Date newday=DateUtils.getNextDay(limitday,sendtimelist);
       Date nowday=new Date();
       if(newday.compareTo(nowday)==1){
           throw new CloudException(ExceptionConstant.转赠时间未到);
       }


        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        if (userGrant.getCotype() == 1) {
            throw new CloudException(ExceptionConstant.暂未发放请稍等);
        }
        userGrant.setBuytime(new Date());
        userGrant.setUserid(users.get(0).getUserId());
        userGrantDao.updateByPrimaryKeySelective(userGrant);
        HideRecord hideRecord = new HideRecord();
        hideRecord.setAddress(userGrant.getHashs());
        hideRecord.setUserid(userid);
        hideRecord.setImg(collection.getImg());
        hideRecord.setName(collection.getName());
        hideRecord.setPrice(collection.getPrice());
        hideRecord.setNo(userGrant.getNumberno());
        hideRecord.setThenum(userGrant.getTruenumber()+"/"+collection.getLimits());
      /*  if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
            hideRecord.setNo(userGrant.getTruenumber());
        }*/
        hideRecord.setMs("转赠成功");
        hideRecord.setCreatetime(new Date());
        hideRecord.setType(10);//0.黄的1.绿2.红
        hideRecordDao.insertSelective(hideRecord);

        HideRecord gethideRecord = new HideRecord();
        gethideRecord.setAddress(userGrant.getHashs());
        gethideRecord.setUserid(users.get(0).getUserId());
        gethideRecord.setImg(collection.getImg());
        gethideRecord.setName(collection.getName());
        gethideRecord.setPrice(collection.getPrice());
        gethideRecord.setNo(userGrant.getNumberno());
        gethideRecord.setThenum(userGrant.getTruenumber()+"/"+collection.getLimits());
       /* if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
            gethideRecord.setNo(userGrant.getTruenumber());
        }*/
        gethideRecord.setMs("转入成功");
        gethideRecord.setCreatetime(new Date());
        gethideRecord.setType(10);//0.黄的1.绿2.红
        hideRecordDao.insertSelective(gethideRecord);



    }

    @Override
    public void withdraw(int userid, int id) {
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        if (userGrant.getType() != 1) {
            throw new CloudException(ExceptionConstant.正在交易无法撤回);
        }
        userGrant.setType(0);
        userGrantDao.updateByPrimaryKeySelective(userGrant);
    }

    @Override
    public Map canshu() {
        Map map = new HashMap();

        BigDecimal fwfsxf=new BigDecimal(dictService.getValue("fwfsxf")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);
        map.put("fwfsxf", fwfsxf);
        map.put("fwfbs", dictService.getValue("fwfbs"));
        return map;
    }

    @Override
    public void lock(Integer logUserPid) {
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andUseridEqualTo(logUserPid).andTypeEqualTo(1).andGranttypeEqualTo(2);
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        if(userGrant.size()>0){
            for (UserGrant grant : userGrant) {
                grant.setType(5);
                userGrantDao.updateByPrimaryKeySelective(grant);
            }
            //userGrantDao.
        }
    }

    @Override
    public void unlock(Integer logUserPid) {

        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andUseridEqualTo(logUserPid).andTypeEqualTo(5).andGranttypeEqualTo(2);
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        if(userGrant.size()>0){
            for (UserGrant grant : userGrant) {
                grant.setType(1);
                userGrantDao.updateByPrimaryKeySelective(grant);
            }
            //userGrantDao.
        }
    }

    @Override
    public void soldout(Integer logUserPid) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);

        integers.add(5);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andUseridEqualTo(logUserPid).andTypeIn(integers).andGranttypeEqualTo(2);
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        if(userGrant.size()>0){
            for (UserGrant grant : userGrant) {
                grant.setType(0);
                userGrantDao.updateByPrimaryKeySelective(grant);
            }
            //userGrantDao.
        }

        /*UserGrant userGrant=userGrantDao.selectByPrimaryKey(id);
        if(userGrant!=null){
            if(userGrant.getUserid()!=logUserPid){
                throw new CloudException(ExceptionConstant.请勿进行非法操作);
            }
            if(userGrant.getType()==1){
                userGrant.setType(0);
                userGrantDao.updateByPrimaryKeySelective(userGrant);
            }
        }*/
    }

    @Override
    public List newshow(int logUserPid, int type) {
        List newlist=new ArrayList();
        AlbumExample albumExample=new AlbumExample();
        albumExample.setOrderByClause("id desc");
        List<Album>albumList=albumDao.selectByExample(albumExample);
        if(albumList.size()>0){
            for (Album album : albumList) {
                System.out.println("albumid :"+album.getId());
                Map totalmap=new HashMap();
                List<Integer> integers = new ArrayList<>();
                integers.add(1);
                integers.add(3);
                integers.add(7);
                integers.add(8);
                integers.add(9);

                UserGrantExample userGrantExample=new UserGrantExample();
                userGrantExample.createCriteria().andAlbumidEqualTo(album.getId()).andUseridEqualTo(logUserPid).andCotypeNotEqualTo(0).andTypeIn(integers);
                userGrantExample.setOrderByClause("id desc");
                List<UserGrant>userGrantList=userGrantDao.selectByExample(userGrantExample);
                List list=new ArrayList();
                if(userGrantList.size()>0){
                    for (UserGrant userGrant : userGrantList) {
                        System.out.println("userGrant :"+userGrant);
                        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                        Map map=new HashMap();
                        System.out.println("type :"+type);
                        System.out.println("collection.getType() :"+collection.getType());
                        if(collection.getType()==type){
                            map.put("id", userGrant.getId());//id
                            /*map.put("no", userGrant.getNumberno());//编号 .getTruenumber() != null
                            if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
                                map.put("no", userGrant.getTruenumber());//编号
                            }*/

                            map.put("no", userGrant.getTruenumber()+"/"+collection.getLimits());//编号
                            map.put("name", collection.getName());//名称
                            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                            map.put("creator", collection.getPublisher());//发行者
                            map.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());//发行者
                            map.put("type",userGrant.getType());
                            map.put("isbuy",userGrant.getGranttype());
                        }
                        System.out.println("map :"+map);
                        if(!map.isEmpty()){
                            list.add(map);
                        }


                    }
                }
                if(list.size()>0){
                    totalmap.put("size",list.size());
                    totalmap.put("list",list);
                    totalmap.put("name",album.getName());
                    newlist.add(totalmap);
                }


            }
        }
        return newlist;
    }

    @Override
    public Map myblindbox(int userid) {
        Map totalmap=new HashMap();
        //我的
        List list1 = new ArrayList();
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.setOrderByClause("id desc");
        userGrantExample1.createCriteria().andUseridEqualTo(userid).andGranttypeEqualTo(2);
        List<UserGrant> userGrant11 = userGrantDao.selectByExample(userGrantExample1);
        for (UserGrant grant : userGrant11) {
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            Map map = new HashMap();
            if(1==collection.getType()){
                map.put("id", grant.getId());//id
               /* map.put("no", grant.getNumberno());//编号 .getTruenumber() != null
                if (StringUtil.getLength(grant.getTruenumber()) > 0) {
                    map.put("no", grant.getTruenumber());//编号
                }*/
                map.put("no", grant.getTruenumber()+"/"+collection.getLimits());//编号
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("creator", collection.getPublisher());//发行者
                map.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());//发行者
                map.put("type",grant.getType());
            }
            if(!map.isEmpty()){
                list1.add(map);
            }


        }


        totalmap.put("size",list1.size());
        totalmap.put("list",list1);

        
        //开启记录
        MyboxExample openmyboxExample=new MyboxExample();
        openmyboxExample.setOrderByClause("id desc");
        openmyboxExample.createCriteria().andTypeEqualTo(1).andUseridEqualTo(userid);
        List<Mybox>openmyboxList=myboxDao.selectByExample(openmyboxExample);
        List list22=new ArrayList();
        if(openmyboxList.size()>0){
            for (Mybox mybox : openmyboxList) {
                Map newmap=new HashMap();
                UserGrant userGrant=userGrantDao.selectByPrimaryKey(mybox.getUsergrantid());

                Collection collection=collectionDao.selectByPrimaryKey(mybox.getColid());
               // Collection collection=collectionDao.selectByPrimaryKey(mybox.getBoxid());
                newmap.put("id",mybox.getId());
                newmap.put("name", collection.getName());//名称
                newmap.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                newmap.put("creator", collection.getPublisher());//发行者
                newmap.put("creatorimg",ImgEnum.img.getUrl()+ collection.getCreatorimg());//发行者

                //newmap.put("truenumber",mybox.getTruenumber());
                newmap.put("truenumber", mybox.getTruenumber());//编号
                newmap.put("hashs",mybox.getHash());
                newmap.put("creattime",DateUtils.getDateToStr(mybox.getCreattime(), DateUtils.DATETIME_FORMAT));
                list22.add(newmap);
            }
        }



        totalmap.put("openlist",list22);


        return  totalmap;
    }

    @Override
    public List Passonlist(int logUserPid) {
        List list=new ArrayList();
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.setOrderByClause("id desc");
        hideRecordExample.createCriteria().andUseridEqualTo(logUserPid).andTypeEqualTo(10);
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        if(hideRecordList.size()>0){
            for (HideRecord hideRecord : hideRecordList) {
                Map map=new HashMap();
                map.put("img",ImgEnum.img.getUrl()+hideRecord.getImg());
                map.put("name",hideRecord.getName());
                map.put("ms",hideRecord.getMs());
                map.put("address",hideRecord.getAddress());
                map.put("time",DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.TIME_FORMAT1));
                map.put("no", hideRecord.getThenum());
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public List synthesisprizelist(int logUserPid) {
        List list=new ArrayList();
        HideRecordExample hideRecordExample=new HideRecordExample();
        hideRecordExample.setOrderByClause("id desc");
        hideRecordExample.createCriteria().andUseridEqualTo(logUserPid).andTypeEqualTo(9);
        List<HideRecord>hideRecordList=hideRecordDao.selectByExample(hideRecordExample);
        if(hideRecordList.size()>0){
            for (HideRecord hideRecord : hideRecordList) {
                Map map=new HashMap();
                map.put("img",ImgEnum.img.getUrl()+hideRecord.getImg());
                map.put("name",hideRecord.getName());
                map.put("ms",hideRecord.getMs());
                map.put("address",hideRecord.getAddress());
                map.put("time",DateUtils.getDateToStr(hideRecord.getCreatetime(), DateUtils.TIME_FORMAT1));
                map.put("no",hideRecord.getNo());
                list.add(map);
            }
        }

        return list;
    }

}
