package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGrantExample {
    /**
     * user_grant
     */
    protected String orderByClause;

    /**
     * user_grant
     */
    protected boolean distinct;

    /**
     * user_grant
     */
    protected List<Criteria> oredCriteria;

    /**
     * user_grant
     */
    protected Integer pageNo = 1;

    /**
     * user_grant
     */
    protected Integer startRow;

    /**
     * user_grant
     */
    protected Integer pageSize = 10;

    public UserGrantExample() {
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
     * user_grant 
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCollidIsNull() {
            addCriterion("collid is null");
            return (Criteria) this;
        }

        public Criteria andCollidIsNotNull() {
            addCriterion("collid is not null");
            return (Criteria) this;
        }

        public Criteria andCollidEqualTo(Integer value) {
            addCriterion("collid =", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotEqualTo(Integer value) {
            addCriterion("collid <>", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidGreaterThan(Integer value) {
            addCriterion("collid >", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidGreaterThanOrEqualTo(Integer value) {
            addCriterion("collid >=", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidLessThan(Integer value) {
            addCriterion("collid <", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidLessThanOrEqualTo(Integer value) {
            addCriterion("collid <=", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidIn(List<Integer> values) {
            addCriterion("collid in", values, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotIn(List<Integer> values) {
            addCriterion("collid not in", values, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidBetween(Integer value1, Integer value2) {
            addCriterion("collid between", value1, value2, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotBetween(Integer value1, Integer value2) {
            addCriterion("collid not between", value1, value2, "collid");
            return (Criteria) this;
        }

        public Criteria andNumbernoIsNull() {
            addCriterion("numberno is null");
            return (Criteria) this;
        }

        public Criteria andNumbernoIsNotNull() {
            addCriterion("numberno is not null");
            return (Criteria) this;
        }

        public Criteria andNumbernoEqualTo(String value) {
            addCriterion("numberno =", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoNotEqualTo(String value) {
            addCriterion("numberno <>", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoGreaterThan(String value) {
            addCriterion("numberno >", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoGreaterThanOrEqualTo(String value) {
            addCriterion("numberno >=", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoLessThan(String value) {
            addCriterion("numberno <", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoLessThanOrEqualTo(String value) {
            addCriterion("numberno <=", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoLike(String value) {
            addCriterion("numberno like", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoNotLike(String value) {
            addCriterion("numberno not like", value, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoIn(List<String> values) {
            addCriterion("numberno in", values, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoNotIn(List<String> values) {
            addCriterion("numberno not in", values, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoBetween(String value1, String value2) {
            addCriterion("numberno between", value1, value2, "numberno");
            return (Criteria) this;
        }

        public Criteria andNumbernoNotBetween(String value1, String value2) {
            addCriterion("numberno not between", value1, value2, "numberno");
            return (Criteria) this;
        }

        public Criteria andHashsIsNull() {
            addCriterion("hashs is null");
            return (Criteria) this;
        }

        public Criteria andHashsIsNotNull() {
            addCriterion("hashs is not null");
            return (Criteria) this;
        }

        public Criteria andHashsEqualTo(String value) {
            addCriterion("hashs =", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotEqualTo(String value) {
            addCriterion("hashs <>", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsGreaterThan(String value) {
            addCriterion("hashs >", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsGreaterThanOrEqualTo(String value) {
            addCriterion("hashs >=", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLessThan(String value) {
            addCriterion("hashs <", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLessThanOrEqualTo(String value) {
            addCriterion("hashs <=", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLike(String value) {
            addCriterion("hashs like", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotLike(String value) {
            addCriterion("hashs not like", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsIn(List<String> values) {
            addCriterion("hashs in", values, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotIn(List<String> values) {
            addCriterion("hashs not in", values, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsBetween(String value1, String value2) {
            addCriterion("hashs between", value1, value2, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotBetween(String value1, String value2) {
            addCriterion("hashs not between", value1, value2, "hashs");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNull() {
            addCriterion("buytime is null");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNotNull() {
            addCriterion("buytime is not null");
            return (Criteria) this;
        }

        public Criteria andBuytimeEqualTo(Date value) {
            addCriterion("buytime =", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotEqualTo(Date value) {
            addCriterion("buytime <>", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThan(Date value) {
            addCriterion("buytime >", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buytime >=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThan(Date value) {
            addCriterion("buytime <", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThanOrEqualTo(Date value) {
            addCriterion("buytime <=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeIn(List<Date> values) {
            addCriterion("buytime in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotIn(List<Date> values) {
            addCriterion("buytime not in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeBetween(Date value1, Date value2) {
            addCriterion("buytime between", value1, value2, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotBetween(Date value1, Date value2) {
            addCriterion("buytime not between", value1, value2, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuypriceIsNull() {
            addCriterion("buyprice is null");
            return (Criteria) this;
        }

        public Criteria andBuypriceIsNotNull() {
            addCriterion("buyprice is not null");
            return (Criteria) this;
        }

        public Criteria andBuypriceEqualTo(BigDecimal value) {
            addCriterion("buyprice =", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotEqualTo(BigDecimal value) {
            addCriterion("buyprice <>", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThan(BigDecimal value) {
            addCriterion("buyprice >", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyprice >=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThan(BigDecimal value) {
            addCriterion("buyprice <", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyprice <=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceIn(List<BigDecimal> values) {
            addCriterion("buyprice in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotIn(List<BigDecimal> values) {
            addCriterion("buyprice not in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyprice between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyprice not between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNull() {
            addCriterion("sellprice is null");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNotNull() {
            addCriterion("sellprice is not null");
            return (Criteria) this;
        }

        public Criteria andSellpriceEqualTo(BigDecimal value) {
            addCriterion("sellprice =", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotEqualTo(BigDecimal value) {
            addCriterion("sellprice <>", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThan(BigDecimal value) {
            addCriterion("sellprice >", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellprice >=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThan(BigDecimal value) {
            addCriterion("sellprice <", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellprice <=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIn(List<BigDecimal> values) {
            addCriterion("sellprice in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotIn(List<BigDecimal> values) {
            addCriterion("sellprice not in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellprice between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellprice not between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andOppositeuserIsNull() {
            addCriterion("oppositeuser is null");
            return (Criteria) this;
        }

        public Criteria andOppositeuserIsNotNull() {
            addCriterion("oppositeuser is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeuserEqualTo(Integer value) {
            addCriterion("oppositeuser =", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotEqualTo(Integer value) {
            addCriterion("oppositeuser <>", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserGreaterThan(Integer value) {
            addCriterion("oppositeuser >", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserGreaterThanOrEqualTo(Integer value) {
            addCriterion("oppositeuser >=", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserLessThan(Integer value) {
            addCriterion("oppositeuser <", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserLessThanOrEqualTo(Integer value) {
            addCriterion("oppositeuser <=", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserIn(List<Integer> values) {
            addCriterion("oppositeuser in", values, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotIn(List<Integer> values) {
            addCriterion("oppositeuser not in", values, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserBetween(Integer value1, Integer value2) {
            addCriterion("oppositeuser between", value1, value2, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotBetween(Integer value1, Integer value2) {
            addCriterion("oppositeuser not between", value1, value2, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andSjtimeIsNull() {
            addCriterion("sjtime is null");
            return (Criteria) this;
        }

        public Criteria andSjtimeIsNotNull() {
            addCriterion("sjtime is not null");
            return (Criteria) this;
        }

        public Criteria andSjtimeEqualTo(Date value) {
            addCriterion("sjtime =", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotEqualTo(Date value) {
            addCriterion("sjtime <>", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeGreaterThan(Date value) {
            addCriterion("sjtime >", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sjtime >=", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeLessThan(Date value) {
            addCriterion("sjtime <", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeLessThanOrEqualTo(Date value) {
            addCriterion("sjtime <=", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeIn(List<Date> values) {
            addCriterion("sjtime in", values, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotIn(List<Date> values) {
            addCriterion("sjtime not in", values, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeBetween(Date value1, Date value2) {
            addCriterion("sjtime between", value1, value2, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotBetween(Date value1, Date value2) {
            addCriterion("sjtime not between", value1, value2, "sjtime");
            return (Criteria) this;
        }

        public Criteria andGranttypeIsNull() {
            addCriterion("granttype is null");
            return (Criteria) this;
        }

        public Criteria andGranttypeIsNotNull() {
            addCriterion("granttype is not null");
            return (Criteria) this;
        }

        public Criteria andGranttypeEqualTo(Integer value) {
            addCriterion("granttype =", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeNotEqualTo(Integer value) {
            addCriterion("granttype <>", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeGreaterThan(Integer value) {
            addCriterion("granttype >", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("granttype >=", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeLessThan(Integer value) {
            addCriterion("granttype <", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeLessThanOrEqualTo(Integer value) {
            addCriterion("granttype <=", value, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeIn(List<Integer> values) {
            addCriterion("granttype in", values, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeNotIn(List<Integer> values) {
            addCriterion("granttype not in", values, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeBetween(Integer value1, Integer value2) {
            addCriterion("granttype between", value1, value2, "granttype");
            return (Criteria) this;
        }

        public Criteria andGranttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("granttype not between", value1, value2, "granttype");
            return (Criteria) this;
        }

        public Criteria andCotypeIsNull() {
            addCriterion("cotype is null");
            return (Criteria) this;
        }

        public Criteria andCotypeIsNotNull() {
            addCriterion("cotype is not null");
            return (Criteria) this;
        }

        public Criteria andCotypeEqualTo(Integer value) {
            addCriterion("cotype =", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotEqualTo(Integer value) {
            addCriterion("cotype <>", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeGreaterThan(Integer value) {
            addCriterion("cotype >", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cotype >=", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeLessThan(Integer value) {
            addCriterion("cotype <", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeLessThanOrEqualTo(Integer value) {
            addCriterion("cotype <=", value, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeIn(List<Integer> values) {
            addCriterion("cotype in", values, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotIn(List<Integer> values) {
            addCriterion("cotype not in", values, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeBetween(Integer value1, Integer value2) {
            addCriterion("cotype between", value1, value2, "cotype");
            return (Criteria) this;
        }

        public Criteria andCotypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cotype not between", value1, value2, "cotype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Integer value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Integer value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Integer value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Integer value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Integer value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Integer> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Integer> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Integer value1, Integer value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andTokenidIsNull() {
            addCriterion("tokenId is null");
            return (Criteria) this;
        }

        public Criteria andTokenidIsNotNull() {
            addCriterion("tokenId is not null");
            return (Criteria) this;
        }

        public Criteria andTokenidEqualTo(Integer value) {
            addCriterion("tokenId =", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotEqualTo(Integer value) {
            addCriterion("tokenId <>", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidGreaterThan(Integer value) {
            addCriterion("tokenId >", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tokenId >=", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidLessThan(Integer value) {
            addCriterion("tokenId <", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidLessThanOrEqualTo(Integer value) {
            addCriterion("tokenId <=", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidIn(List<Integer> values) {
            addCriterion("tokenId in", values, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotIn(List<Integer> values) {
            addCriterion("tokenId not in", values, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidBetween(Integer value1, Integer value2) {
            addCriterion("tokenId between", value1, value2, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotBetween(Integer value1, Integer value2) {
            addCriterion("tokenId not between", value1, value2, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTruenumberIsNull() {
            addCriterion("truenumber is null");
            return (Criteria) this;
        }

        public Criteria andTruenumberIsNotNull() {
            addCriterion("truenumber is not null");
            return (Criteria) this;
        }

        public Criteria andTruenumberEqualTo(String value) {
            addCriterion("truenumber =", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotEqualTo(String value) {
            addCriterion("truenumber <>", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberGreaterThan(String value) {
            addCriterion("truenumber >", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberGreaterThanOrEqualTo(String value) {
            addCriterion("truenumber >=", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLessThan(String value) {
            addCriterion("truenumber <", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLessThanOrEqualTo(String value) {
            addCriterion("truenumber <=", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLike(String value) {
            addCriterion("truenumber like", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotLike(String value) {
            addCriterion("truenumber not like", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberIn(List<String> values) {
            addCriterion("truenumber in", values, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotIn(List<String> values) {
            addCriterion("truenumber not in", values, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberBetween(String value1, String value2) {
            addCriterion("truenumber between", value1, value2, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotBetween(String value1, String value2) {
            addCriterion("truenumber not between", value1, value2, "truenumber");
            return (Criteria) this;
        }

        public Criteria andAlbumidIsNull() {
            addCriterion("albumid is null");
            return (Criteria) this;
        }

        public Criteria andAlbumidIsNotNull() {
            addCriterion("albumid is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumidEqualTo(Integer value) {
            addCriterion("albumid =", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidNotEqualTo(Integer value) {
            addCriterion("albumid <>", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidGreaterThan(Integer value) {
            addCriterion("albumid >", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidGreaterThanOrEqualTo(Integer value) {
            addCriterion("albumid >=", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidLessThan(Integer value) {
            addCriterion("albumid <", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidLessThanOrEqualTo(Integer value) {
            addCriterion("albumid <=", value, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidIn(List<Integer> values) {
            addCriterion("albumid in", values, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidNotIn(List<Integer> values) {
            addCriterion("albumid not in", values, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidBetween(Integer value1, Integer value2) {
            addCriterion("albumid between", value1, value2, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumidNotBetween(Integer value1, Integer value2) {
            addCriterion("albumid not between", value1, value2, "albumid");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIsNull() {
            addCriterion("albumname is null");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIsNotNull() {
            addCriterion("albumname is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumnameEqualTo(String value) {
            addCriterion("albumname =", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotEqualTo(String value) {
            addCriterion("albumname <>", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameGreaterThan(String value) {
            addCriterion("albumname >", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameGreaterThanOrEqualTo(String value) {
            addCriterion("albumname >=", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLessThan(String value) {
            addCriterion("albumname <", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLessThanOrEqualTo(String value) {
            addCriterion("albumname <=", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLike(String value) {
            addCriterion("albumname like", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotLike(String value) {
            addCriterion("albumname not like", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIn(List<String> values) {
            addCriterion("albumname in", values, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotIn(List<String> values) {
            addCriterion("albumname not in", values, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameBetween(String value1, String value2) {
            addCriterion("albumname between", value1, value2, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotBetween(String value1, String value2) {
            addCriterion("albumname not between", value1, value2, "albumname");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeIsNull() {
            addCriterion("collectiontype is null");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeIsNotNull() {
            addCriterion("collectiontype is not null");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeEqualTo(Integer value) {
            addCriterion("collectiontype =", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeNotEqualTo(Integer value) {
            addCriterion("collectiontype <>", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeGreaterThan(Integer value) {
            addCriterion("collectiontype >", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("collectiontype >=", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeLessThan(Integer value) {
            addCriterion("collectiontype <", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeLessThanOrEqualTo(Integer value) {
            addCriterion("collectiontype <=", value, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeIn(List<Integer> values) {
            addCriterion("collectiontype in", values, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeNotIn(List<Integer> values) {
            addCriterion("collectiontype not in", values, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeBetween(Integer value1, Integer value2) {
            addCriterion("collectiontype between", value1, value2, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andCollectiontypeNotBetween(Integer value1, Integer value2) {
            addCriterion("collectiontype not between", value1, value2, "collectiontype");
            return (Criteria) this;
        }

        public Criteria andIssueidIsNull() {
            addCriterion("issueid is null");
            return (Criteria) this;
        }

        public Criteria andIssueidIsNotNull() {
            addCriterion("issueid is not null");
            return (Criteria) this;
        }

        public Criteria andIssueidEqualTo(Integer value) {
            addCriterion("issueid =", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidNotEqualTo(Integer value) {
            addCriterion("issueid <>", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidGreaterThan(Integer value) {
            addCriterion("issueid >", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidGreaterThanOrEqualTo(Integer value) {
            addCriterion("issueid >=", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidLessThan(Integer value) {
            addCriterion("issueid <", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidLessThanOrEqualTo(Integer value) {
            addCriterion("issueid <=", value, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidIn(List<Integer> values) {
            addCriterion("issueid in", values, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidNotIn(List<Integer> values) {
            addCriterion("issueid not in", values, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidBetween(Integer value1, Integer value2) {
            addCriterion("issueid between", value1, value2, "issueid");
            return (Criteria) this;
        }

        public Criteria andIssueidNotBetween(Integer value1, Integer value2) {
            addCriterion("issueid not between", value1, value2, "issueid");
            return (Criteria) this;
        }

        public Criteria andSendtypeIsNull() {
            addCriterion("sendtype is null");
            return (Criteria) this;
        }

        public Criteria andSendtypeIsNotNull() {
            addCriterion("sendtype is not null");
            return (Criteria) this;
        }

        public Criteria andSendtypeEqualTo(Integer value) {
            addCriterion("sendtype =", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotEqualTo(Integer value) {
            addCriterion("sendtype <>", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeGreaterThan(Integer value) {
            addCriterion("sendtype >", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendtype >=", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeLessThan(Integer value) {
            addCriterion("sendtype <", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeLessThanOrEqualTo(Integer value) {
            addCriterion("sendtype <=", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeIn(List<Integer> values) {
            addCriterion("sendtype in", values, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotIn(List<Integer> values) {
            addCriterion("sendtype not in", values, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeBetween(Integer value1, Integer value2) {
            addCriterion("sendtype between", value1, value2, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sendtype not between", value1, value2, "sendtype");
            return (Criteria) this;
        }

        public Criteria andTransactionhashIsNull() {
            addCriterion("transactionHash is null");
            return (Criteria) this;
        }

        public Criteria andTransactionhashIsNotNull() {
            addCriterion("transactionHash is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionhashEqualTo(String value) {
            addCriterion("transactionHash =", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashNotEqualTo(String value) {
            addCriterion("transactionHash <>", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashGreaterThan(String value) {
            addCriterion("transactionHash >", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashGreaterThanOrEqualTo(String value) {
            addCriterion("transactionHash >=", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashLessThan(String value) {
            addCriterion("transactionHash <", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashLessThanOrEqualTo(String value) {
            addCriterion("transactionHash <=", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashLike(String value) {
            addCriterion("transactionHash like", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashNotLike(String value) {
            addCriterion("transactionHash not like", value, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashIn(List<String> values) {
            addCriterion("transactionHash in", values, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashNotIn(List<String> values) {
            addCriterion("transactionHash not in", values, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashBetween(String value1, String value2) {
            addCriterion("transactionHash between", value1, value2, "transactionhash");
            return (Criteria) this;
        }

        public Criteria andTransactionhashNotBetween(String value1, String value2) {
            addCriterion("transactionHash not between", value1, value2, "transactionhash");
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

        public Criteria andThtokenidIsNull() {
            addCriterion("thtokenId is null");
            return (Criteria) this;
        }

        public Criteria andThtokenidIsNotNull() {
            addCriterion("thtokenId is not null");
            return (Criteria) this;
        }

        public Criteria andThtokenidEqualTo(Integer value) {
            addCriterion("thtokenId =", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidNotEqualTo(Integer value) {
            addCriterion("thtokenId <>", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidGreaterThan(Integer value) {
            addCriterion("thtokenId >", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidGreaterThanOrEqualTo(Integer value) {
            addCriterion("thtokenId >=", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidLessThan(Integer value) {
            addCriterion("thtokenId <", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidLessThanOrEqualTo(Integer value) {
            addCriterion("thtokenId <=", value, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidIn(List<Integer> values) {
            addCriterion("thtokenId in", values, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidNotIn(List<Integer> values) {
            addCriterion("thtokenId not in", values, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidBetween(Integer value1, Integer value2) {
            addCriterion("thtokenId between", value1, value2, "thtokenid");
            return (Criteria) this;
        }

        public Criteria andThtokenidNotBetween(Integer value1, Integer value2) {
            addCriterion("thtokenId not between", value1, value2, "thtokenid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * user_grant 
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