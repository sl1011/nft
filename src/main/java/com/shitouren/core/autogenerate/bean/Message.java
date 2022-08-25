package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Message {
    /**
     * 
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    private Date createtime;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 内容
     */
    private String notice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }
}