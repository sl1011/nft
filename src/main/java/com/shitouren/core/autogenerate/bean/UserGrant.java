package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class UserGrant {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id(0则为官方)
     */
    private Integer userid;

    /**
     * 藏品id
     */
    private Integer collid;

    /**
     * 编号
     */
    private String numberno;

    /**
     * 藏品hash值
     */
    private String hashs;

    /**
     * 生成时间
     */
    private Date createtime;

    /**
     * 购买时间
     */
    private Date buytime;

    /**
     * 购买价格
     */
    private BigDecimal buyprice;

    /**
     * 出售价格
     */
    private BigDecimal sellprice;

    /**
     * 0.待付款1已付款3.已完成4已消耗6已预约7转卖中8转卖待付款9已售出10后台生成
     */
    private Integer type;

    /**
     * 对方用户
     */
    private Integer oppositeuser;

    /**
     * 上架时间
     */
    private Date sjtime;

    /**
     * 1.未发放2.已发放
     */
    private Integer granttype;

    /**
     * 0.藏品待支付1.藏品待发放2.藏品已发放
     */
    private Integer cotype;

    /**
     * 1.平台2.微信3.支付宝4.其他
     */
    private Integer paytype;

    /**
     * nft币id
     */
    private Integer tokenid;

    /**
     * 发行编号
     */
    private String truenumber;

    /**
     * 
     */
    private Integer albumid;

    /**
     * 
     */
    private String albumname;

    /**
     * 
     */
    private Integer collectiontype;

    /**
     * 
     */
    private Integer issueid;

    /**
     * 0正常交易1空投2合成
     */
    private Integer sendtype;

    /**
     * transactionHash
     */
    private String transactionhash;

    /**
     * address
     */
    private String thaddress;

    /**
     * tokenId
     */
    private Integer thtokenid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public String getNumberno() {
        return numberno;
    }

    public void setNumberno(String numberno) {
        this.numberno = numberno == null ? null : numberno.trim();
    }

    public String getHashs() {
        return hashs;
    }

    public void setHashs(String hashs) {
        this.hashs = hashs == null ? null : hashs.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public BigDecimal getSellprice() {
        return sellprice;
    }

    public void setSellprice(BigDecimal sellprice) {
        this.sellprice = sellprice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOppositeuser() {
        return oppositeuser;
    }

    public void setOppositeuser(Integer oppositeuser) {
        this.oppositeuser = oppositeuser;
    }

    public Date getSjtime() {
        return sjtime;
    }

    public void setSjtime(Date sjtime) {
        this.sjtime = sjtime;
    }

    public Integer getGranttype() {
        return granttype;
    }

    public void setGranttype(Integer granttype) {
        this.granttype = granttype;
    }

    public Integer getCotype() {
        return cotype;
    }

    public void setCotype(Integer cotype) {
        this.cotype = cotype;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public String getTruenumber() {
        return truenumber;
    }

    public void setTruenumber(String truenumber) {
        this.truenumber = truenumber == null ? null : truenumber.trim();
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname == null ? null : albumname.trim();
    }

    public Integer getCollectiontype() {
        return collectiontype;
    }

    public void setCollectiontype(Integer collectiontype) {
        this.collectiontype = collectiontype;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public String getTransactionhash() {
        return transactionhash;
    }

    public void setTransactionhash(String transactionhash) {
        this.transactionhash = transactionhash == null ? null : transactionhash.trim();
    }

    public String getThaddress() {
        return thaddress;
    }

    public void setThaddress(String thaddress) {
        this.thaddress = thaddress == null ? null : thaddress.trim();
    }

    public Integer getThtokenid() {
        return thtokenid;
    }

    public void setThtokenid(Integer thtokenid) {
        this.thtokenid = thtokenid;
    }
}