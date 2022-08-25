package com.shitouren.core.autogenerate.bean;

public class Grant {
    /**
     * 
     */
    private Integer id;

    /**
     * 发行藏品id
     */
    private Integer collid;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 0.未被交易1.已交易
     */
    private Integer type;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 商品tokenid
     */
    private Integer tokenid;

    /**
     * hash
     */
    private String useridhash;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public String getUseridhash() {
        return useridhash;
    }

    public void setUseridhash(String useridhash) {
        this.useridhash = useridhash == null ? null : useridhash.trim();
    }
}