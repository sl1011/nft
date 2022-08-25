package com.shitouren.core.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CollectionService {
    Map show(int userid,int type);

    Map details(int userid,int id);

    void sell(int userid, int id, BigDecimal price, String pass);

    void Passon(int userid, int id, String phone);

    void withdraw(int userid, int id);

    Map canshu();

    void lock(Integer logUserPid);

    void unlock(Integer logUserPid);

    void soldout(Integer logUserPid);

    List newshow(int logUserPid, int type);

    Map myblindbox(int logUserPid);

    List Passonlist(int logUserPid);

    List synthesisprizelist(int logUserPid);
}
