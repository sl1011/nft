package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Imglist {
    /**
     * 
     */
    private Integer id;

    /**
     * 图片合约地址
     */
    private String address;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 时间
     */
    private Date starttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
}