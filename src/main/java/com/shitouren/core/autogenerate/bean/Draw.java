package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Draw {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String img;

    /**
     * 发放人数
     */
    private Integer number;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 报名人数
     */
    private Integer drawnumber;

    /**
     * 
     */
    private Date starttime;

    /**
     * 
     */
    private Date endtime;

    /**
     * 
     */
    private Date drawtime;

    /**
     * 0未抽签1以抽签
     */
    private Integer type;

    /**
     * 
     */
    private Integer issueid;

    /**
     * 展示0不展示1
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDrawnumber() {
        return drawnumber;
    }

    public void setDrawnumber(Integer drawnumber) {
        this.drawnumber = drawnumber;
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

    public Date getDrawtime() {
        return drawtime;
    }

    public void setDrawtime(Date drawtime) {
        this.drawtime = drawtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}