package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Mybox {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 盲盒id
     */
    private Integer boxid;

    /**
     * 碎片id
     */
    private Integer spid;

    /**
     * 0未开启1.已开启
     */
    private Integer type;

    /**
     * 
     */
    private Date creattime;

    /**
     * 
     */
    private Integer usergrantid;

    /**
     * 
     */
    private Integer issuid;

    /**
     * 
     */
    private String hash;

    /**
     * 
     */
    private String truenumber;

    /**
     * 
     */
    private Integer colid;

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

    public Integer getBoxid() {
        return boxid;
    }

    public void setBoxid(Integer boxid) {
        this.boxid = boxid;
    }

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getUsergrantid() {
        return usergrantid;
    }

    public void setUsergrantid(Integer usergrantid) {
        this.usergrantid = usergrantid;
    }

    public Integer getIssuid() {
        return issuid;
    }

    public void setIssuid(Integer issuid) {
        this.issuid = issuid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getTruenumber() {
        return truenumber;
    }

    public void setTruenumber(String truenumber) {
        this.truenumber = truenumber == null ? null : truenumber.trim();
    }

    public Integer getColid() {
        return colid;
    }

    public void setColid(Integer colid) {
        this.colid = colid;
    }
}