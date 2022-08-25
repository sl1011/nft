package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersExample {
    /**
     * users
     */
    protected String orderByClause;

    /**
     * users
     */
    protected boolean distinct;

    /**
     * users
     */
    protected List<Criteria> oredCriteria;

    /**
     * users
     */
    protected Integer pageNo = 1;

    /**
     * users
     */
    protected Integer startRow;

    /**
     * users
     */
    protected Integer pageSize = 10;

    public UsersExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * users 
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIsNull() {
            addCriterion("head_prtraits is null");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIsNotNull() {
            addCriterion("head_prtraits is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsEqualTo(String value) {
            addCriterion("head_prtraits =", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotEqualTo(String value) {
            addCriterion("head_prtraits <>", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsGreaterThan(String value) {
            addCriterion("head_prtraits >", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsGreaterThanOrEqualTo(String value) {
            addCriterion("head_prtraits >=", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLessThan(String value) {
            addCriterion("head_prtraits <", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLessThanOrEqualTo(String value) {
            addCriterion("head_prtraits <=", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLike(String value) {
            addCriterion("head_prtraits like", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotLike(String value) {
            addCriterion("head_prtraits not like", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIn(List<String> values) {
            addCriterion("head_prtraits in", values, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotIn(List<String> values) {
            addCriterion("head_prtraits not in", values, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsBetween(String value1, String value2) {
            addCriterion("head_prtraits between", value1, value2, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotBetween(String value1, String value2) {
            addCriterion("head_prtraits not between", value1, value2, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIsNull() {
            addCriterion("trade_pass_word is null");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIsNotNull() {
            addCriterion("trade_pass_word is not null");
            return (Criteria) this;
        }

        public Criteria andTradePassWordEqualTo(String value) {
            addCriterion("trade_pass_word =", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotEqualTo(String value) {
            addCriterion("trade_pass_word <>", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordGreaterThan(String value) {
            addCriterion("trade_pass_word >", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordGreaterThanOrEqualTo(String value) {
            addCriterion("trade_pass_word >=", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLessThan(String value) {
            addCriterion("trade_pass_word <", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLessThanOrEqualTo(String value) {
            addCriterion("trade_pass_word <=", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLike(String value) {
            addCriterion("trade_pass_word like", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotLike(String value) {
            addCriterion("trade_pass_word not like", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIn(List<String> values) {
            addCriterion("trade_pass_word in", values, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotIn(List<String> values) {
            addCriterion("trade_pass_word not in", values, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordBetween(String value1, String value2) {
            addCriterion("trade_pass_word between", value1, value2, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotBetween(String value1, String value2) {
            addCriterion("trade_pass_word not between", value1, value2, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andAutographIsNull() {
            addCriterion("autograph is null");
            return (Criteria) this;
        }

        public Criteria andAutographIsNotNull() {
            addCriterion("autograph is not null");
            return (Criteria) this;
        }

        public Criteria andAutographEqualTo(String value) {
            addCriterion("autograph =", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotEqualTo(String value) {
            addCriterion("autograph <>", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographGreaterThan(String value) {
            addCriterion("autograph >", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographGreaterThanOrEqualTo(String value) {
            addCriterion("autograph >=", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLessThan(String value) {
            addCriterion("autograph <", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLessThanOrEqualTo(String value) {
            addCriterion("autograph <=", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLike(String value) {
            addCriterion("autograph like", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotLike(String value) {
            addCriterion("autograph not like", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographIn(List<String> values) {
            addCriterion("autograph in", values, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotIn(List<String> values) {
            addCriterion("autograph not in", values, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographBetween(String value1, String value2) {
            addCriterion("autograph between", value1, value2, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotBetween(String value1, String value2) {
            addCriterion("autograph not between", value1, value2, "autograph");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNull() {
            addCriterion("alipay is null");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNotNull() {
            addCriterion("alipay is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayEqualTo(String value) {
            addCriterion("alipay =", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotEqualTo(String value) {
            addCriterion("alipay <>", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThan(String value) {
            addCriterion("alipay >", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("alipay >=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThan(String value) {
            addCriterion("alipay <", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThanOrEqualTo(String value) {
            addCriterion("alipay <=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLike(String value) {
            addCriterion("alipay like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotLike(String value) {
            addCriterion("alipay not like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayIn(List<String> values) {
            addCriterion("alipay in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotIn(List<String> values) {
            addCriterion("alipay not in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayBetween(String value1, String value2) {
            addCriterion("alipay between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotBetween(String value1, String value2) {
            addCriterion("alipay not between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNull() {
            addCriterion("alipayname is null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNotNull() {
            addCriterion("alipayname is not null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameEqualTo(String value) {
            addCriterion("alipayname =", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotEqualTo(String value) {
            addCriterion("alipayname <>", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThan(String value) {
            addCriterion("alipayname >", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThanOrEqualTo(String value) {
            addCriterion("alipayname >=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThan(String value) {
            addCriterion("alipayname <", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThanOrEqualTo(String value) {
            addCriterion("alipayname <=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLike(String value) {
            addCriterion("alipayname like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotLike(String value) {
            addCriterion("alipayname not like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIn(List<String> values) {
            addCriterion("alipayname in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotIn(List<String> values) {
            addCriterion("alipayname not in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameBetween(String value1, String value2) {
            addCriterion("alipayname between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotBetween(String value1, String value2) {
            addCriterion("alipayname not between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIsNull() {
            addCriterion("realnametype is null");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIsNotNull() {
            addCriterion("realnametype is not null");
            return (Criteria) this;
        }

        public Criteria andRealnametypeEqualTo(Integer value) {
            addCriterion("realnametype =", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotEqualTo(Integer value) {
            addCriterion("realnametype <>", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeGreaterThan(Integer value) {
            addCriterion("realnametype >", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("realnametype >=", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeLessThan(Integer value) {
            addCriterion("realnametype <", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeLessThanOrEqualTo(Integer value) {
            addCriterion("realnametype <=", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIn(List<Integer> values) {
            addCriterion("realnametype in", values, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotIn(List<Integer> values) {
            addCriterion("realnametype not in", values, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeBetween(Integer value1, Integer value2) {
            addCriterion("realnametype between", value1, value2, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotBetween(Integer value1, Integer value2) {
            addCriterion("realnametype not between", value1, value2, "realnametype");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("status_id =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("status_id <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("status_id >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("status_id >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("status_id <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("status_id <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("status_id like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("status_id not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("status_id in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("status_id not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("status_id between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("status_id not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNull() {
            addCriterion("privateKey is null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNotNull() {
            addCriterion("privateKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyEqualTo(String value) {
            addCriterion("privateKey =", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotEqualTo(String value) {
            addCriterion("privateKey <>", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThan(String value) {
            addCriterion("privateKey >", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThanOrEqualTo(String value) {
            addCriterion("privateKey >=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThan(String value) {
            addCriterion("privateKey <", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThanOrEqualTo(String value) {
            addCriterion("privateKey <=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLike(String value) {
            addCriterion("privateKey like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotLike(String value) {
            addCriterion("privateKey not like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIn(List<String> values) {
            addCriterion("privateKey in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotIn(List<String> values) {
            addCriterion("privateKey not in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyBetween(String value1, String value2) {
            addCriterion("privateKey between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotBetween(String value1, String value2) {
            addCriterion("privateKey not between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnoIsNull() {
            addCriterion("realno is null");
            return (Criteria) this;
        }

        public Criteria andRealnoIsNotNull() {
            addCriterion("realno is not null");
            return (Criteria) this;
        }

        public Criteria andRealnoEqualTo(String value) {
            addCriterion("realno =", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotEqualTo(String value) {
            addCriterion("realno <>", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoGreaterThan(String value) {
            addCriterion("realno >", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoGreaterThanOrEqualTo(String value) {
            addCriterion("realno >=", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLessThan(String value) {
            addCriterion("realno <", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLessThanOrEqualTo(String value) {
            addCriterion("realno <=", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLike(String value) {
            addCriterion("realno like", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotLike(String value) {
            addCriterion("realno not like", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoIn(List<String> values) {
            addCriterion("realno in", values, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotIn(List<String> values) {
            addCriterion("realno not in", values, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoBetween(String value1, String value2) {
            addCriterion("realno between", value1, value2, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotBetween(String value1, String value2) {
            addCriterion("realno not between", value1, value2, "realno");
            return (Criteria) this;
        }

        public Criteria andWhitelistIsNull() {
            addCriterion("whitelist is null");
            return (Criteria) this;
        }

        public Criteria andWhitelistIsNotNull() {
            addCriterion("whitelist is not null");
            return (Criteria) this;
        }

        public Criteria andWhitelistEqualTo(Integer value) {
            addCriterion("whitelist =", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotEqualTo(Integer value) {
            addCriterion("whitelist <>", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistGreaterThan(Integer value) {
            addCriterion("whitelist >", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistGreaterThanOrEqualTo(Integer value) {
            addCriterion("whitelist >=", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistLessThan(Integer value) {
            addCriterion("whitelist <", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistLessThanOrEqualTo(Integer value) {
            addCriterion("whitelist <=", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistIn(List<Integer> values) {
            addCriterion("whitelist in", values, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotIn(List<Integer> values) {
            addCriterion("whitelist not in", values, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistBetween(Integer value1, Integer value2) {
            addCriterion("whitelist between", value1, value2, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotBetween(Integer value1, Integer value2) {
            addCriterion("whitelist not between", value1, value2, "whitelist");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIsNull() {
            addCriterion("invitationcount is null");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIsNotNull() {
            addCriterion("invitationcount is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationcountEqualTo(Integer value) {
            addCriterion("invitationcount =", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotEqualTo(Integer value) {
            addCriterion("invitationcount <>", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountGreaterThan(Integer value) {
            addCriterion("invitationcount >", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitationcount >=", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountLessThan(Integer value) {
            addCriterion("invitationcount <", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountLessThanOrEqualTo(Integer value) {
            addCriterion("invitationcount <=", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIn(List<Integer> values) {
            addCriterion("invitationcount in", values, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotIn(List<Integer> values) {
            addCriterion("invitationcount not in", values, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountBetween(Integer value1, Integer value2) {
            addCriterion("invitationcount between", value1, value2, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotBetween(Integer value1, Integer value2) {
            addCriterion("invitationcount not between", value1, value2, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andSzcountIsNull() {
            addCriterion("szcount is null");
            return (Criteria) this;
        }

        public Criteria andSzcountIsNotNull() {
            addCriterion("szcount is not null");
            return (Criteria) this;
        }

        public Criteria andSzcountEqualTo(Integer value) {
            addCriterion("szcount =", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotEqualTo(Integer value) {
            addCriterion("szcount <>", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountGreaterThan(Integer value) {
            addCriterion("szcount >", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("szcount >=", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountLessThan(Integer value) {
            addCriterion("szcount <", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountLessThanOrEqualTo(Integer value) {
            addCriterion("szcount <=", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountIn(List<Integer> values) {
            addCriterion("szcount in", values, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotIn(List<Integer> values) {
            addCriterion("szcount not in", values, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountBetween(Integer value1, Integer value2) {
            addCriterion("szcount between", value1, value2, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotBetween(Integer value1, Integer value2) {
            addCriterion("szcount not between", value1, value2, "szcount");
            return (Criteria) this;
        }

        public Criteria andSztimeIsNull() {
            addCriterion("sztime is null");
            return (Criteria) this;
        }

        public Criteria andSztimeIsNotNull() {
            addCriterion("sztime is not null");
            return (Criteria) this;
        }

        public Criteria andSztimeEqualTo(Date value) {
            addCriterion("sztime =", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotEqualTo(Date value) {
            addCriterion("sztime <>", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeGreaterThan(Date value) {
            addCriterion("sztime >", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sztime >=", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeLessThan(Date value) {
            addCriterion("sztime <", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeLessThanOrEqualTo(Date value) {
            addCriterion("sztime <=", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeIn(List<Date> values) {
            addCriterion("sztime in", values, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotIn(List<Date> values) {
            addCriterion("sztime not in", values, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeBetween(Date value1, Date value2) {
            addCriterion("sztime between", value1, value2, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotBetween(Date value1, Date value2) {
            addCriterion("sztime not between", value1, value2, "sztime");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNull() {
            addCriterion("register_code is null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNotNull() {
            addCriterion("register_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeEqualTo(String value) {
            addCriterion("register_code =", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotEqualTo(String value) {
            addCriterion("register_code <>", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThan(String value) {
            addCriterion("register_code >", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("register_code >=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThan(String value) {
            addCriterion("register_code <", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThanOrEqualTo(String value) {
            addCriterion("register_code <=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLike(String value) {
            addCriterion("register_code like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotLike(String value) {
            addCriterion("register_code not like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIn(List<String> values) {
            addCriterion("register_code in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotIn(List<String> values) {
            addCriterion("register_code not in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeBetween(String value1, String value2) {
            addCriterion("register_code between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotBetween(String value1, String value2) {
            addCriterion("register_code not between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNull() {
            addCriterion("invitation_id is null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNotNull() {
            addCriterion("invitation_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdEqualTo(Integer value) {
            addCriterion("invitation_id =", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotEqualTo(Integer value) {
            addCriterion("invitation_id <>", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThan(Integer value) {
            addCriterion("invitation_id >", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitation_id >=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThan(Integer value) {
            addCriterion("invitation_id <", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThanOrEqualTo(Integer value) {
            addCriterion("invitation_id <=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIn(List<Integer> values) {
            addCriterion("invitation_id in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotIn(List<Integer> values) {
            addCriterion("invitation_id not in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdBetween(Integer value1, Integer value2) {
            addCriterion("invitation_id between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invitation_id not between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIsNull() {
            addCriterion("freezemoney is null");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIsNotNull() {
            addCriterion("freezemoney is not null");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyEqualTo(BigDecimal value) {
            addCriterion("freezemoney =", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotEqualTo(BigDecimal value) {
            addCriterion("freezemoney <>", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyGreaterThan(BigDecimal value) {
            addCriterion("freezemoney >", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freezemoney >=", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyLessThan(BigDecimal value) {
            addCriterion("freezemoney <", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freezemoney <=", value, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyIn(List<BigDecimal> values) {
            addCriterion("freezemoney in", values, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotIn(List<BigDecimal> values) {
            addCriterion("freezemoney not in", values, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezemoney between", value1, value2, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andFreezemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freezemoney not between", value1, value2, "freezemoney");
            return (Criteria) this;
        }

        public Criteria andKeyseedIsNull() {
            addCriterion("Keyseed is null");
            return (Criteria) this;
        }

        public Criteria andKeyseedIsNotNull() {
            addCriterion("Keyseed is not null");
            return (Criteria) this;
        }

        public Criteria andKeyseedEqualTo(String value) {
            addCriterion("Keyseed =", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedNotEqualTo(String value) {
            addCriterion("Keyseed <>", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedGreaterThan(String value) {
            addCriterion("Keyseed >", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedGreaterThanOrEqualTo(String value) {
            addCriterion("Keyseed >=", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedLessThan(String value) {
            addCriterion("Keyseed <", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedLessThanOrEqualTo(String value) {
            addCriterion("Keyseed <=", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedLike(String value) {
            addCriterion("Keyseed like", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedNotLike(String value) {
            addCriterion("Keyseed not like", value, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedIn(List<String> values) {
            addCriterion("Keyseed in", values, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedNotIn(List<String> values) {
            addCriterion("Keyseed not in", values, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedBetween(String value1, String value2) {
            addCriterion("Keyseed between", value1, value2, "keyseed");
            return (Criteria) this;
        }

        public Criteria andKeyseedNotBetween(String value1, String value2) {
            addCriterion("Keyseed not between", value1, value2, "keyseed");
            return (Criteria) this;
        }

        public Criteria andBindCardIdIsNull() {
            addCriterion("bind_card_id is null");
            return (Criteria) this;
        }

        public Criteria andBindCardIdIsNotNull() {
            addCriterion("bind_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindCardIdEqualTo(String value) {
            addCriterion("bind_card_id =", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdNotEqualTo(String value) {
            addCriterion("bind_card_id <>", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdGreaterThan(String value) {
            addCriterion("bind_card_id >", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("bind_card_id >=", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdLessThan(String value) {
            addCriterion("bind_card_id <", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdLessThanOrEqualTo(String value) {
            addCriterion("bind_card_id <=", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdLike(String value) {
            addCriterion("bind_card_id like", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdNotLike(String value) {
            addCriterion("bind_card_id not like", value, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdIn(List<String> values) {
            addCriterion("bind_card_id in", values, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdNotIn(List<String> values) {
            addCriterion("bind_card_id not in", values, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdBetween(String value1, String value2) {
            addCriterion("bind_card_id between", value1, value2, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andBindCardIdNotBetween(String value1, String value2) {
            addCriterion("bind_card_id not between", value1, value2, "bindCardId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andRealnamenumIsNull() {
            addCriterion("realnamenum is null");
            return (Criteria) this;
        }

        public Criteria andRealnamenumIsNotNull() {
            addCriterion("realnamenum is not null");
            return (Criteria) this;
        }

        public Criteria andRealnamenumEqualTo(Integer value) {
            addCriterion("realnamenum =", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumNotEqualTo(Integer value) {
            addCriterion("realnamenum <>", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumGreaterThan(Integer value) {
            addCriterion("realnamenum >", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("realnamenum >=", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumLessThan(Integer value) {
            addCriterion("realnamenum <", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumLessThanOrEqualTo(Integer value) {
            addCriterion("realnamenum <=", value, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumIn(List<Integer> values) {
            addCriterion("realnamenum in", values, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumNotIn(List<Integer> values) {
            addCriterion("realnamenum not in", values, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumBetween(Integer value1, Integer value2) {
            addCriterion("realnamenum between", value1, value2, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andRealnamenumNotBetween(Integer value1, Integer value2) {
            addCriterion("realnamenum not between", value1, value2, "realnamenum");
            return (Criteria) this;
        }

        public Criteria andIsagentIsNull() {
            addCriterion("isagent is null");
            return (Criteria) this;
        }

        public Criteria andIsagentIsNotNull() {
            addCriterion("isagent is not null");
            return (Criteria) this;
        }

        public Criteria andIsagentEqualTo(Integer value) {
            addCriterion("isagent =", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentNotEqualTo(Integer value) {
            addCriterion("isagent <>", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentGreaterThan(Integer value) {
            addCriterion("isagent >", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentGreaterThanOrEqualTo(Integer value) {
            addCriterion("isagent >=", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentLessThan(Integer value) {
            addCriterion("isagent <", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentLessThanOrEqualTo(Integer value) {
            addCriterion("isagent <=", value, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentIn(List<Integer> values) {
            addCriterion("isagent in", values, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentNotIn(List<Integer> values) {
            addCriterion("isagent not in", values, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentBetween(Integer value1, Integer value2) {
            addCriterion("isagent between", value1, value2, "isagent");
            return (Criteria) this;
        }

        public Criteria andIsagentNotBetween(Integer value1, Integer value2) {
            addCriterion("isagent not between", value1, value2, "isagent");
            return (Criteria) this;
        }

        public Criteria andThaddressIsNull() {
            addCriterion("thaddress is null");
            return (Criteria) this;
        }

        public Criteria andThaddressIsNotNull() {
            addCriterion("thaddress is not null");
            return (Criteria) this;
        }

        public Criteria andThaddressEqualTo(String value) {
            addCriterion("thaddress =", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressNotEqualTo(String value) {
            addCriterion("thaddress <>", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressGreaterThan(String value) {
            addCriterion("thaddress >", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressGreaterThanOrEqualTo(String value) {
            addCriterion("thaddress >=", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressLessThan(String value) {
            addCriterion("thaddress <", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressLessThanOrEqualTo(String value) {
            addCriterion("thaddress <=", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressLike(String value) {
            addCriterion("thaddress like", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressNotLike(String value) {
            addCriterion("thaddress not like", value, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressIn(List<String> values) {
            addCriterion("thaddress in", values, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressNotIn(List<String> values) {
            addCriterion("thaddress not in", values, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressBetween(String value1, String value2) {
            addCriterion("thaddress between", value1, value2, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThaddressNotBetween(String value1, String value2) {
            addCriterion("thaddress not between", value1, value2, "thaddress");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyIsNull() {
            addCriterion("thprivatekey is null");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyIsNotNull() {
            addCriterion("thprivatekey is not null");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyEqualTo(String value) {
            addCriterion("thprivatekey =", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyNotEqualTo(String value) {
            addCriterion("thprivatekey <>", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyGreaterThan(String value) {
            addCriterion("thprivatekey >", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyGreaterThanOrEqualTo(String value) {
            addCriterion("thprivatekey >=", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyLessThan(String value) {
            addCriterion("thprivatekey <", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyLessThanOrEqualTo(String value) {
            addCriterion("thprivatekey <=", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyLike(String value) {
            addCriterion("thprivatekey like", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyNotLike(String value) {
            addCriterion("thprivatekey not like", value, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyIn(List<String> values) {
            addCriterion("thprivatekey in", values, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyNotIn(List<String> values) {
            addCriterion("thprivatekey not in", values, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyBetween(String value1, String value2) {
            addCriterion("thprivatekey between", value1, value2, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andThprivatekeyNotBetween(String value1, String value2) {
            addCriterion("thprivatekey not between", value1, value2, "thprivatekey");
            return (Criteria) this;
        }

        public Criteria andUserkeyIsNull() {
            addCriterion("userkey is null");
            return (Criteria) this;
        }

        public Criteria andUserkeyIsNotNull() {
            addCriterion("userkey is not null");
            return (Criteria) this;
        }

        public Criteria andUserkeyEqualTo(String value) {
            addCriterion("userkey =", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotEqualTo(String value) {
            addCriterion("userkey <>", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyGreaterThan(String value) {
            addCriterion("userkey >", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyGreaterThanOrEqualTo(String value) {
            addCriterion("userkey >=", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLessThan(String value) {
            addCriterion("userkey <", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLessThanOrEqualTo(String value) {
            addCriterion("userkey <=", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyLike(String value) {
            addCriterion("userkey like", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotLike(String value) {
            addCriterion("userkey not like", value, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyIn(List<String> values) {
            addCriterion("userkey in", values, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotIn(List<String> values) {
            addCriterion("userkey not in", values, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyBetween(String value1, String value2) {
            addCriterion("userkey between", value1, value2, "userkey");
            return (Criteria) this;
        }

        public Criteria andUserkeyNotBetween(String value1, String value2) {
            addCriterion("userkey not between", value1, value2, "userkey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * users 
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}