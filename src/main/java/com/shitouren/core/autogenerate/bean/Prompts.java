package com.shitouren.core.autogenerate.bean;

public class Prompts {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private String content;

    /**
     * 0未读1已读
     */
    private Integer type;

    /**
     * 0失败1成功
     */
    private Integer state;

    /**
     * 
     */
    private Integer usergrantid;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUsergrantid() {
        return usergrantid;
    }

    public void setUsergrantid(Integer usergrantid) {
        this.usergrantid = usergrantid;
    }
}