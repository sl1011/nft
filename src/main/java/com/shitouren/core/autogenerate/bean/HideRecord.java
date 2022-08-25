package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class HideRecord {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userid;

    /**
     * 藏品地址
     */
    private String address;

    /**
     * 
     */
    private String img;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private String no;

    /**
     * 
     */
    private String ms;

    /**
     * 0未付款1已付款
     */
    private Integer type;

    /**
     * 
     */
    private Date createtime;

    /**
     * 发行id
     */
    private Integer issueid;

    /**
     * 
     */
    private String thenum;

    /**
     * 0首发转售的id
     */
    private Integer usergrantid;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms == null ? null : ms.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public String getThenum() {
        return thenum;
    }

    public void setThenum(String thenum) {
        this.thenum = thenum == null ? null : thenum.trim();
    }

    public Integer getUsergrantid() {
        return usergrantid;
    }

    public void setUsergrantid(Integer usergrantid) {
        this.usergrantid = usergrantid;
    }
}