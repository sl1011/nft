package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Fragment {
    /**
     * 
     */
    private Integer id;

    /**
     * 藏品id
     */
    private Integer collid;

    /**
     * 藏品hash值
     */
    private String hashs;

    /**
     * 本次盲盒开启时间
     */
    private Date begintime;

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

    public String getHashs() {
        return hashs;
    }

    public void setHashs(String hashs) {
        this.hashs = hashs == null ? null : hashs.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }
}