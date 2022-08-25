package com.shitouren.core.service;

import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MineService {

    Map<String, Object> getMineInfo(Integer userId);

    Map<String, Object> Acsecurity(Integer userId);

    void usedPhone(Integer userId, String code);

    void updPhone(Integer userId, String phone, String code);

    void aplPhone(Integer userId, String phone, String name,String code);

    void addtradepassword(SysUserParam sysUserParam, String TradePassWord);

    void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2);

    String setup(Integer userId);

    //个人信息
    Map personal(Integer userId);

    //修改个人信息
    void updateUserInfo(Integer userId, String avatar, String nickname, String autograph);

    List message(int userid);

    Map<String, Object> messagedetails(int userid,Integer id);

    BigDecimal Myassets(Integer userId);

    Map realname(int userid);

    //提现
    void withdrawal(int userId, BigDecimal count );

    List withdrawalrecode(int userId);

    Map balancerecords(int userId);

    List Collectionrecords(int userId);

    Map addRealName(RealNameParam RealNameParam, Integer userId) ;

    Map blindboxs(Integer userid,int id);

    List myblindboxs(Integer id);

    Map openbox(int userid, int id);

    Map synthesis(int userid, int id);

    Map synthesisprize(int userid, int id);

    List myorder(int userid, int type);

    void beoverdue();

    void checkTradeStatus();

    void cancelorder(int userid, int id);

    Map<String, Object> getInviteInfo(SysUserParam param);

    String inviteInfoimgs(SysUserParam param);

    Map chat();

    String recharge(int logUserPid, BigDecimal count,String ip);

    void settle();
    void changetime();

    void compound(Integer logUserPid,String ids);

    Map bindbanone(Integer userId, RealNameParam realNameParam, HttpServletResponse response);

    Map bindbantwo(Integer userId, HttpServletRequest request, HttpServletResponse response);

    Map getrechargeone(Integer logUserPid, BigDecimal count,String code, HttpServletResponse response);

    void getrechargetwo(Integer logUserPid , String order_no,String code, HttpServletResponse response);


    Map money(int logUserPid);

    Map moneytask(int logUserPid);

    Map homemessage();

    void dailytask(int logUserPid);

    List myticket(int logUserPid);

    void changeendtime();

    Map newinvite();

    void sendhash();

    Map myorderdetails(int logUserPid, int id);

    //void changetickttime();
}
