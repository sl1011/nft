package com.shitouren.core.autogenerate.bean;

public class Bank {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private String accountno;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String idcardcode;

    /**
     * 
     */
    private String bankpremobile;

    /**
     * 
     */
    private String bankname;

    /**
     * 
     */
    private String cardid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcardcode() {
        return idcardcode;
    }

    public void setIdcardcode(String idcardcode) {
        this.idcardcode = idcardcode == null ? null : idcardcode.trim();
    }

    public String getBankpremobile() {
        return bankpremobile;
    }

    public void setBankpremobile(String bankpremobile) {
        this.bankpremobile = bankpremobile == null ? null : bankpremobile.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }
}