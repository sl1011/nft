package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GiveUser {
    /**
     * 
     */
    private Integer id;

    /**
     * 1.盲盒2.藏品
     */
    private Integer type;

    /**
     * 赠送id
     */
    private Integer collid;

    /**
     * 赠送鲸币数量
     */
    private BigDecimal money;

    /**
     * 最少多少人奖励
     */
    private Integer limitinfo;

    /**
     * 人数限制
     */
    private Integer number;

    /**
     * 开始时间
     */
    private Date creattime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 抽签id
     */
    private Integer drawid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getLimitinfo() {
        return limitinfo;
    }

    public void setLimitinfo(Integer limitinfo) {
        this.limitinfo = limitinfo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDrawid() {
        return drawid;
    }

    public void setDrawid(Integer drawid) {
        this.drawid = drawid;
    }
}