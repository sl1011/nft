package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Users {
    /**
     * 用户ID从10001自增长
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 头像
     */
    private String headPrtraits;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 操作密码
     */
    private String tradePassWord;

    /**
     * 个性签名
     */
    private String autograph;

    /**
     * 我的资产
     */
    private BigDecimal balance;

    /**
     * 支付宝账号
     */
    private String alipay;

    /**
     * 支付宝姓名
     */
    private String alipayname;

    /**
     * 0.未实名1.待审核2.已通过
     */
    private Integer realnametype;

    /**
     * 账号创建时间
     */
    private Date createTime;

    /**
     * 状态：0启用，1禁用
     */
    private String statusId;

    /**
     * 以太坊地址
     */
    private String address;

    /**
     * 以太坊私钥
     */
    private String privatekey;

    /**
     * 
     */
    private String realname;

    /**
     * 
     */
    private String realno;

    /**
     * 0.未开启白名单1.开启
     */
    private Integer whitelist;

    /**
     * 
     */
    private Integer invitationcount;

    /**
     * 
     */
    private Integer szcount;

    /**
     * 
     */
    private Date sztime;

    /**
     * 邀请码
     */
    private String registerCode;

    /**
     * 邀请人
     */
    private Integer invitationId;

    /**
     * 冻结资金
     */
    private BigDecimal freezemoney;

    /**
     * 
     */
    private String keyseed;

    /**
     * 绑定银行卡id
     */
    private String bindCardId;

    /**
     * 鲸币
     */
    private BigDecimal money;

    /**
     * 邀请的人实名总数
     */
    private Integer realnamenum;

    /**
     * 0不是代理1是代理
     */
    private Integer isagent;

    /**
     * 天河地址
     */
    private String thaddress;

    /**
     * 天河key
     */
    private String thprivatekey;

    /**
     * 秘钥
     */
    private String userkey;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getHeadPrtraits() {
        return headPrtraits;
    }

    public void setHeadPrtraits(String headPrtraits) {
        this.headPrtraits = headPrtraits == null ? null : headPrtraits.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getTradePassWord() {
        return tradePassWord;
    }

    public void setTradePassWord(String tradePassWord) {
        this.tradePassWord = tradePassWord == null ? null : tradePassWord.trim();
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph == null ? null : autograph.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname == null ? null : alipayname.trim();
    }

    public Integer getRealnametype() {
        return realnametype;
    }

    public void setRealnametype(Integer realnametype) {
        this.realnametype = realnametype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey == null ? null : privatekey.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getRealno() {
        return realno;
    }

    public void setRealno(String realno) {
        this.realno = realno == null ? null : realno.trim();
    }

    public Integer getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(Integer whitelist) {
        this.whitelist = whitelist;
    }

    public Integer getInvitationcount() {
        return invitationcount;
    }

    public void setInvitationcount(Integer invitationcount) {
        this.invitationcount = invitationcount;
    }

    public Integer getSzcount() {
        return szcount;
    }

    public void setSzcount(Integer szcount) {
        this.szcount = szcount;
    }

    public Date getSztime() {
        return sztime;
    }

    public void setSztime(Date sztime) {
        this.sztime = sztime;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode == null ? null : registerCode.trim();
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public BigDecimal getFreezemoney() {
        return freezemoney;
    }

    public void setFreezemoney(BigDecimal freezemoney) {
        this.freezemoney = freezemoney;
    }

    public String getKeyseed() {
        return keyseed;
    }

    public void setKeyseed(String keyseed) {
        this.keyseed = keyseed == null ? null : keyseed.trim();
    }

    public String getBindCardId() {
        return bindCardId;
    }

    public void setBindCardId(String bindCardId) {
        this.bindCardId = bindCardId == null ? null : bindCardId.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRealnamenum() {
        return realnamenum;
    }

    public void setRealnamenum(Integer realnamenum) {
        this.realnamenum = realnamenum;
    }

    public Integer getIsagent() {
        return isagent;
    }

    public void setIsagent(Integer isagent) {
        this.isagent = isagent;
    }

    public String getThaddress() {
        return thaddress;
    }

    public void setThaddress(String thaddress) {
        this.thaddress = thaddress == null ? null : thaddress.trim();
    }

    public String getThprivatekey() {
        return thprivatekey;
    }

    public void setThprivatekey(String thprivatekey) {
        this.thprivatekey = thprivatekey == null ? null : thprivatekey.trim();
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey == null ? null : userkey.trim();
    }
}