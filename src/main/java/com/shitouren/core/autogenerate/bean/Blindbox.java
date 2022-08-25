package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Blindbox {
    /**
     * id
     */
    private Integer id;

    /**
     * 盲盒图片
     */
    private String img;

    /**
     * 盲盒名称
     */
    private String name;

    /**
     * 所需碎片藏品
     */
    private String fragment;

    /**
     * 合成藏品id
     */
    private Integer synthesis;

    /**
     * 开启时间
     */
    private Date begintime;

    /**
     * 关闭时间
     */
    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment == null ? null : fragment.trim();
    }

    public Integer getSynthesis() {
        return synthesis;
    }

    public void setSynthesis(Integer synthesis) {
        this.synthesis = synthesis;
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