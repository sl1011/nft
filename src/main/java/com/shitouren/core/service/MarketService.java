package com.shitouren.core.service;

import java.util.List;
import java.util.Map;

public interface MarketService {
    List show(int userid,int collectionstype,int albumid,String search, int type);

    List search(int userid, String search, int type,int row);

    Map details(int userid, int id);

    int Confirmorder(int userid, int id);

    Map Confirmorderdetails(int userid, int id);

    String Payorder(int userid, int id, int type, String paytype,String ip) throws Exception;

    List allalbum(Integer logUserPid);

    Map noregdetails(int id);
}
