package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Blindboxtrue {
    /**
     * 
     */
    private Integer id;

    /**
     * 对应的盲盒id
     */
    private Integer colid;

    /**
     * 开出的藏品id
     */
    private Integer openid;

    /**
     * 藏品的数量
     */
    private Integer number;

    /**
     * 
     */
    private Date begintime;

    /**
     * 
     */
    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColid() {
        return colid;
    }

    public void setColid(Integer colid) {
        this.colid = colid;
    }

    public Integer getOpenid() {
        return openid;
    }

    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}