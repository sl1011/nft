package com.shitouren.core.service;

import com.alipay.api.AlipayApiException;

import java.util.List;
import java.util.Map;

public interface HomeService {
    Map show();

    String bannerdetails(int id);

    Map shows(int userid);

    Map details(int userid, int id);

    int detailss(int userid, int id);

    int Confirmorder(int userid, int id);

    int ordertype(int id);

    Map Confirmorderdetails(int userid, int id);

    Map Payorder(int userid, int id, int type , int paytype, String ip) throws Exception;

    Map mhPayorder(int userid, int id, int type, String password, int paytype) throws Exception;

    void mintnft(int collectionid, int user_grantid);
    void thmintnft(int collectionid, int user_grantid);

    void transfercoin(String privateKey, String fromaddress, String toaddress, String tokenId, String contractAddress);

    void adduser(int uid, String address, String privateKey);

    Map houtaidetails(int id);

    void updateaddress(int id, String address);

    String agreementss(int id);

    Map hqaddress(String count,String code);

    void tui();

    Map persondetails(Integer logUserPid, int id);

    int personConfirmorder(Integer logUserPid, int id, String orderno);

    Map personPayorder(Integer logUserPid, int id, int type , int paytype) throws Exception;

    List searchcommodity(String content,int type);

    Map appointmentinfo(int uid, int id);

    Map prompt(Integer logUserPid);

    void readprompt(Integer logUserPid, int id);

    String detailsimg(Integer logUserPid, int id,int ids,String type);

    Map noregdetails(int id);

    void trial(int type);


    Map collections(int type);

    List draw(int userid);

    void signup(int userid,int id);

    List drawbanner();

    Map cansend(int logUserPid, int id);
}
