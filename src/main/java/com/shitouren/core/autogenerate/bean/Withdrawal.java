package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Withdrawal {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 提现金额
     */
    private BigDecimal count;

    /**
     * 提现时间
     */
    private Date createtime;

    /**
     * 支付宝账号
     */
    private String alipay;

    /**
     * 支付宝姓名
     */
    private String alipayname;

    /**
     * 0正在审核1审核通过2审核失败
     */
    private Integer state;

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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname == null ? null : alipayname.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}