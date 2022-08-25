package com.shitouren.core.autogenerate.bean;

public class Transforlist {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer colid;

    /**
     * hash
     */
    private String transactionhash;

    /**
     * thaddress
     */
    private String thaddress;

    /**
     * thtokenId
     */
    private Integer thtokenid;

    /**
     * 0未使用1使用
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColid() {
        return colid;
    }

    public void setColid(Integer colid) {
        this.colid = colid;
    }

    public String getTransactionhash() {
        return transactionhash;
    }

    public void setTransactionhash(String transactionhash) {
        this.transactionhash = transactionhash == null ? null : transactionhash.trim();
    }

    public String getThaddress() {
        return thaddress;
    }

    public void setThaddress(String thaddress) {
        this.thaddress = thaddress == null ? null : thaddress.trim();
    }

    public Integer getThtokenid() {
        return thtokenid;
    }

    public void setThtokenid(Integer thtokenid) {
        this.thtokenid = thtokenid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}