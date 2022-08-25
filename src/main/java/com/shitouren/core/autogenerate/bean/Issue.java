package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Issue {
    /**
     * 
     */
    private Integer id;

    /**
     * 藏品id
     */
    private Integer collid;

    /**
     * 发行时间
     */
    private Date releasetime;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 预售数量
     */
    private Integer presale;

    /**
     * 已售数量
     */
    private Integer sold;

    /**
     * 本轮每人限购数量
     */
    private Integer limitcount;

    /**
     * 预约人数
     */
    private Long appointment;

    /**
     * 0未开始1进行中2已结束
     */
    private Integer state;

    /**
     * 绑定的优先购id
     */
    private Integer drawid;

    /**
     * 0显示1隐藏
     */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPresale() {
        return presale;
    }

    public void setPresale(Integer presale) {
        this.presale = presale;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getLimitcount() {
        return limitcount;
    }

    public void setLimitcount(Integer limitcount) {
        this.limitcount = limitcount;
    }

    public Long getAppointment() {
        return appointment;
    }

    public void setAppointment(Long appointment) {
        this.appointment = appointment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDrawid() {
        return drawid;
    }

    public void setDrawid(Integer drawid) {
        this.drawid = drawid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}