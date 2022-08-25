package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;

public class Rechargelist {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String orderno;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private BigDecimal money;

    /**
     * 0未支付1成功2失败
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}