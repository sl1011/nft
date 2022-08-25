package com.shitouren.core.controller;

import com.shitouren.core.annotation.Access;
import com.shitouren.core.annotation.GetLoginUser;
import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.DictService;
import com.shitouren.core.service.MineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "我的", tags = "我的")
public class MineController {
    @Autowired
    MineService mineService;
    @Autowired
    DictService dictService;

    @PostMapping("/mine/mine")
    @ApiOperation(value = "我的信息", notes = "\n" +
            "avatar: 头像\n" +
            "nickname: 昵称\n" +
            "phone: 手机号,\n" +
            "address: 地址,\n" +
            "money: 鲸币,\n" +
            "freezemoney: 冻结资金,\n" +
            "number: 我的优惠券数量,\n" +
            "numbertwo: 未读消息,\n" +
            "realnametype: 0.未实名1.待审核2.已通过,\n" +
            "")
    @GetLoginUser
    @Access
    public Map<String, Object> mine(SysUserParam sysUserParam) {
        Integer userId = sysUserParam.getLogUserPid();
        return mineService.getMineInfo(userId);
    }

    @PostMapping("/mine/Acsecurity")
    @ApiOperation(value = "账号安全", notes = "\n" +
            "phone: 手机号,\n" +
            "alipay: 支付宝账号\n" +
            "isRealName: 0.未认证1.已认证\n" +
            "")
    @GetLoginUser
    @Access
    public Map<String, Object> Acsecurity(SysUserParam sysUserParam) {
        return mineService.Acsecurity(sysUserParam.getLogUserPid());
    }

    @PostMapping("/mine/usedPhone")
    @ApiOperation(value = "旧手机号验证", notes = "\n" +
            "")
    @GetLoginUser
    @Access
    @ApiImplicitParam(name = "code", value = "验证码")
    public void usedPhone(SysUserParam sysUserParam, String code) {
        mineService.usedPhone(sysUserParam.getLogUserPid(), code);
    }

    @PostMapping("/mine/updPhone")
    @ApiOperation(value = "更换新手机号", notes = "\n" +
            "")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "新手机号"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    public void updPhone(SysUserParam sysUserParam, String phone, String code) {
        mineService.updPhone(sysUserParam.getLogUserPid(), phone, code);
    }

    @PostMapping("/mine/aplPhone")
    @ApiOperation(value = "更换支付宝", notes = "\n" +
            "")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "支付宝账号"),
            @ApiImplicitParam(name = "name", value = "支付宝姓名"),
            @ApiImplicitParam(name = "code", value = "验证码"),
    })
    public void aplPhone(SysUserParam sysUserParam, String phone, String name,String code) {
        mineService.aplPhone(sysUserParam.getLogUserPid(), phone, name,code);
    }

    @PostMapping("/mine/updatetradepassword")
    @ApiOperation("修改密码")
    @GetLoginUser
    @Access
    public void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        mineService.updateTradePassWord(sysUserParam, phone, code, newTradePassWord, newTradePassWord2);
    }

    /*@PostMapping("/mine/addtradepassword")
    @ApiOperation("设置操作密码")
    @GetLoginUser
    @Access
    public void addtradepassword(SysUserParam sysUserParam, String TradePassWord) {
        mineService.addtradepassword(sysUserParam, TradePassWord);
    }

    @PostMapping("/mine/updatetradepassword")
    @ApiOperation("修改操作密码")
    @GetLoginUser
    @Access
    public void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        mineService.updateTradePassWord(sysUserParam, phone, code, newTradePassWord, newTradePassWord2);
    }*/

  /*  @PostMapping("/mine/setup")
    @ApiOperation(value = "设置", notes = "" +
            "进入提示操作密码：0.未设置1.已设置")
    @GetLoginUser
    @Access
    public String setup(SysUserParam sysUserParam) {
        return mineService.setup(sysUserParam.getLogUserPid());
    }
*/
    @PostMapping("/mine/personal")
    @ApiOperation(value = "个人信息", notes = "" +
            "avatar: 头像\n" +
            "nickname: 昵称,\n" +
            "autograph: 个性签名,\n" +
            "")
    @GetLoginUser
    @Access
    public Map personal(SysUserParam sysUserParam) {
        return mineService.personal(sysUserParam.getLogUserPid());
    }

    @PostMapping("/mine/updateUserInfo")
    @ApiOperation("修改个人信息")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "avatar", value = "头像"),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "autograph", value = "个性签名"),
    })
    public void updateUserInfo(SysUserParam sysUserParam, String avatar, String nickname, String autograph) {
        mineService.updateUserInfo(sysUserParam.getLogUserPid(), avatar, nickname, autograph);
    }

    @PostMapping("/Home/message")
    @GetLoginUser
    @Access
    @ApiOperation("消息")
    public List message(SysUserParam sysUserParam) {
        return mineService.message(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Home/homemessage")
    @GetLoginUser
    @ApiOperation("首页消息")
    public Map homemessage() {
        return mineService.homemessage();
    }

    @PostMapping("/Home/messagedetails")
    @GetLoginUser
    @ApiOperation("消息详情")
    public Map messagedetails(SysUserParam sysUserParam,int id) {
        return mineService.messagedetails(sysUserParam.getLogUserPid(),id);
    }

    @PostMapping("/mine/Myassets")
    @Access
    @ApiOperation(value = "我的资产", notes = "")
    @GetLoginUser
    public BigDecimal Myassets(SysUserParam userParam) {
        return mineService.Myassets(userParam.getLogUserPid());
    }

    @PostMapping("/mine/withdrawal")
    @Access
    @ApiOperation(value = "提现", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "提现金额"),
           // @ApiImplicitParam(name = "pass", value = "操作密码"),
    })
    public void withdrawal(SysUserParam userParam, BigDecimal count) {
        mineService.withdrawal(userParam.getLogUserPid(), count);
    }

    @PostMapping("/mine/recharge")
    @Access
    @ApiOperation(value = "充值", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "充值"),
            @ApiImplicitParam(name = "ip", value = "ip"),

    })
    public String recharge(SysUserParam userParam, BigDecimal count,String ip)  {
      return   mineService.recharge(userParam.getLogUserPid(), count,ip);
    }

    /*@PostMapping("/mine/getrechargeone")
    @Access
    @ApiOperation(value = "充值第一步", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "充值"),
            @ApiImplicitParam(name = "code", value = "验证码"),
    })
    public Map getrechargeone(SysUserParam userParam, BigDecimal count,String code, HttpServletResponse response) throws ServletException, IOException {
      return   mineService.getrechargeone(userParam.getLogUserPid(), count,code, response);
    }

    @PostMapping("/mine/getrechargetwo")
    @Access
    @ApiOperation(value = "充值第二步", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
           // @ApiImplicitParam(name = "count", value = "充值"),
            @ApiImplicitParam(name = "order_no", value = "订单号"),
            @ApiImplicitParam(name = "code", value = "验证码"),
    })
    public void getrechargetwo(SysUserParam userParam ,String order_no,String code, HttpServletResponse response) throws ServletException, IOException {
           mineService.getrechargetwo(userParam.getLogUserPid(),order_no,code, response);
    }*/




    @PostMapping("/mine/withdrawalrecode")
    @Access
    @ApiOperation(value = "提现记录", notes = "")
    @GetLoginUser
    public List withdrawalrecode(SysUserParam userParam) {
        return mineService.withdrawalrecode(userParam.getLogUserPid());
    }

    @PostMapping("/mine/balancerecords")
    @Access
    @ApiOperation(value = "资金记录", notes = "\n" +
            "list: 全部,\n" +
            "list1: 支出\n" +
            "list2: 收入\n" +
            "list3: 充值\n" +
            "list4: 提现\n" +
            "balance: 余额\n" +
            "frozen: 冻结\n" +
            "},\n")
    @GetLoginUser
    public Map balancerecords(SysUserParam userParam) {
        return mineService.balancerecords(userParam.getLogUserPid());
    }

    @PostMapping("/mine/Collectionrecords")
    @Access
    @ApiOperation(value = "藏品记录", notes = "")
    @GetLoginUser
    public List Collectionrecords(SysUserParam userParam) {
        return mineService.Collectionrecords(userParam.getLogUserPid());
    }

    @PostMapping("/mine/realname")
    @ApiOperation("实名认证")
    @GetLoginUser
    @Access
    public Map addRealName(SysUserParam userParam) {
        return mineService.realname(userParam.getLogUserPid());
    }

    @PostMapping("/mine/addrealname")
    @ApiOperation("提交实名认证")
    @GetLoginUser
    @Access
    public Map addRealName(SysUserParam userParam, RealNameParam realNameParam)  {
        Integer userId = userParam.getLogUserPid();
      return   mineService.addRealName(realNameParam, userId);
    }

   /* @PostMapping("/mine/bindbanone")
    @ApiOperation("银行卡绑定第一步")
    @GetLoginUser
    @Access
    public Map bindbanone(SysUserParam userParam,RealNameParam realNameParam, HttpServletResponse response) throws ServletException, IOException  {
        Integer userId = userParam.getLogUserPid();
        return   mineService.bindbanone(userId,realNameParam,response);
    }

    @PostMapping("/mine/bindbantwo")
    @ApiOperation("银行卡绑定第二步")
    @GetLoginUser
    @Access
    public Map bindbantwo(SysUserParam userParam,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Integer userId = userParam.getLogUserPid();
        return   mineService.bindbantwo(userId,request,response);
    }*/



    @PostMapping("/mine/blindboxs")
    @Access
    @ApiOperation(value = "盲盒", notes = "")
    @GetLoginUser
    public Map blindboxs(SysUserParam userParam,int id) {
        return mineService.blindboxs(userParam.getLogUserPid(),id);
    }

    @PostMapping("/mine/myblindboxs")
    @Access
    @ApiOperation(value = "我的盲盒", notes = "")
    @GetLoginUser
    public List myblindboxs(SysUserParam userParam) {
        return mineService.myblindboxs(userParam.getLogUserPid());
    }

    @PostMapping("/mine/openbox")
    @Access
    @ApiOperation(value = "开盲盒", notes = "")
    @GetLoginUser
    public Map openbox(SysUserParam userParam, int id) {
        return mineService.openbox(userParam.getLogUserPid(), id);
    }

   /* @PostMapping("/mine/synthesis")
    @Access
    @ApiOperation(value = "合成", notes = "")
    @GetLoginUser
    public Map synthesis(SysUserParam userParam, int id) {
        return mineService.synthesis(userParam.getLogUserPid(), id);
    }*/

    @PostMapping("/mine/synthesisprize")
    @Access
    @ApiOperation(value = "合成奖品", notes = "")
    @GetLoginUser
    public Map synthesisprize(SysUserParam userParam, int id) {
        return mineService.synthesisprize(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/myorder")
    @Access
    @ApiOperation(value = "我的订单", notes = "")
    @GetLoginUser
    @ApiImplicitParam(name = "type", value = "0.全部1.待付款.2已付款")
    public List myorder(SysUserParam userParam, int type) {
        return mineService.myorder(userParam.getLogUserPid(), type);
    }

    //我的订单详情
    @PostMapping("/mine/myorderdetails")
    @Access
    @ApiOperation(value = "我的订单详情", notes = "")
    @GetLoginUser
    @ApiImplicitParam(name = "id", value = "订单id")
    public Map myorderdetails(SysUserParam userParam, int id) {
        return mineService.myorderdetails(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/cancelorder")
    @Access
    @ApiOperation(value = "取消订单", notes = "")
    @GetLoginUser
    public void cancelorder(SysUserParam userParam, int id) {
        mineService.cancelorder(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/inviteInfo")
    @ApiOperation(value = "获取邀请信息",notes = "\n" +
            "total: 总邀请人,\n" +
            "code: 邀请码,\n" +
            "list: 记录,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +

            "},\n")
    @GetLoginUser
    @Access
    public Map<String, Object> inviteInfo(SysUserParam userParam) {
        return mineService.getInviteInfo(userParam);
    }

    @PostMapping("/mine/inviteInfos")
    @ApiOperation("获取邀请图")
    @GetLoginUser
    @Access
    public String inviteInfos(SysUserParam userParam) {
        return mineService.inviteInfoimgs(userParam);
    }

    @PostMapping("/mine/chat")
    @ApiOperation("客服")
    @GetLoginUser
    @Access
    public Map chat(SysUserParam userParam) {
        return mineService.chat();
    }

    @PostMapping("/mine/newinvite")
    @ApiOperation("新分享页面")
    @GetLoginUser
    @Access
    public Map newinvite(SysUserParam userParam) {
        return mineService.newinvite();
    }

    @PostMapping("/mine/settle")
    @ApiOperation("测试接口")
    @Access(value = false)
    public void settle(SysUserParam userParam) {
         mineService.settle();
    }

    @PostMapping("/mine/compound")
    @ApiOperation("最新合成")
    @Access
    @GetLoginUser
    public void compound(SysUserParam userParam,String id) {
        mineService.compound(userParam.getLogUserPid(),id);
    }

    @PostMapping("/mine/money")
    @ApiOperation(value = "鲸币",notes = "\n" +
            "money: 鲸币,\n" +
            "list: 全部,\n" +
            "list1: 支出,\n" +
            "list2: 收入,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map money(SysUserParam userParam) {
       return mineService.money(userParam.getLogUserPid());
    }


    @PostMapping("/mine/moneytask")
    @ApiOperation(value = "鲸币任务",notes = "\n" +
            "regist: 每日签到任务 1完成0没有,\n" +
            "realname: 实名任务 1完成0没有,\n" +
            "invite: 邀请任务 1完成0没有,\n" +
            "number1: 邀请任务数量,\n" +
            "buysize: 藏品任务 1完成0没有,\n" +
            "number2: 藏品任务数量,\n" +
            "\n")
    @Access
    @GetLoginUser
    public Map moneytask(SysUserParam userParam) {
        return mineService.moneytask(userParam.getLogUserPid());
    }

    @PostMapping("/mine/dailytask")
    @ApiOperation(value = "每日任务",notes = "\n" +
            "\n")
    @Access
    @GetLoginUser
    public void dailytask(SysUserParam userParam) {
         mineService.dailytask(userParam.getLogUserPid());
    }

    @PostMapping("/mine/myticket")
    @ApiOperation(value = "我的优先购卡片",notes = "\n" +
            "\n")
    @Access
    @GetLoginUser
    public List myticket(SysUserParam userParam) {
       return mineService.myticket(userParam.getLogUserPid());
    }



}
