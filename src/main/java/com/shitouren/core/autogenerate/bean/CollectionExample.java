package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CollectionExample {
    /**
     * collection
     */
    protected String orderByClause;

    /**
     * collection
     */
    protected boolean distinct;

    /**
     * collection
     */
    protected List<Criteria> oredCriteria;

    /**
     * collection
     */
    protected Integer pageNo = 1;

    /**
     * collection
     */
    protected Integer startRow;

    /**
     * collection
     */
    protected Integer pageSize = 10;

    public CollectionExample() {
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
     * collection 
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

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMinnameIsNull() {
            addCriterion("minname is null");
            return (Criteria) this;
        }

        public Criteria andMinnameIsNotNull() {
            addCriterion("minname is not null");
            return (Criteria) this;
        }

        public Criteria andMinnameEqualTo(String value) {
            addCriterion("minname =", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotEqualTo(String value) {
            addCriterion("minname <>", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameGreaterThan(String value) {
            addCriterion("minname >", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameGreaterThanOrEqualTo(String value) {
            addCriterion("minname >=", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLessThan(String value) {
            addCriterion("minname <", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLessThanOrEqualTo(String value) {
            addCriterion("minname <=", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLike(String value) {
            addCriterion("minname like", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotLike(String value) {
            addCriterion("minname not like", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameIn(List<String> values) {
            addCriterion("minname in", values, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotIn(List<String> values) {
            addCriterion("minname not in", values, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameBetween(String value1, String value2) {
            addCriterion("minname between", value1, value2, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotBetween(String value1, String value2) {
            addCriterion("minname not between", value1, value2, "minname");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNull() {
            addCriterion("limits is null");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNotNull() {
            addCriterion("limits is not null");
            return (Criteria) this;
        }

        public Criteria andLimitsEqualTo(Integer value) {
            addCriterion("limits =", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotEqualTo(Integer value) {
            addCriterion("limits <>", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThan(Integer value) {
            addCriterion("limits >", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("limits >=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThan(Integer value) {
            addCriterion("limits <", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThanOrEqualTo(Integer value) {
            addCriterion("limits <=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsIn(List<Integer> values) {
            addCriterion("limits in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotIn(List<Integer> values) {
            addCriterion("limits not in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsBetween(Integer value1, Integer value2) {
            addCriterion("limits between", value1, value2, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotBetween(Integer value1, Integer value2) {
            addCriterion("limits not between", value1, value2, "limits");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andImg1IsNull() {
            addCriterion("img1 is null");
            return (Criteria) this;
        }

        public Criteria andImg1IsNotNull() {
            addCriterion("img1 is not null");
            return (Criteria) this;
        }

        public Criteria andImg1EqualTo(String value) {
            addCriterion("img1 =", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotEqualTo(String value) {
            addCriterion("img1 <>", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1GreaterThan(String value) {
            addCriterion("img1 >", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1GreaterThanOrEqualTo(String value) {
            addCriterion("img1 >=", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1LessThan(String value) {
            addCriterion("img1 <", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1LessThanOrEqualTo(String value) {
            addCriterion("img1 <=", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1Like(String value) {
            addCriterion("img1 like", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotLike(String value) {
            addCriterion("img1 not like", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1In(List<String> values) {
            addCriterion("img1 in", values, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotIn(List<String> values) {
            addCriterion("img1 not in", values, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1Between(String value1, String value2) {
            addCriterion("img1 between", value1, value2, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotBetween(String value1, String value2) {
            addCriterion("img1 not between", value1, value2, "img1");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("publisher not between", value1, value2, "publisher");
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

        public Criteria andBoxidIsNull() {
            addCriterion("boxid is null");
            return (Criteria) this;
        }

        public Criteria andBoxidIsNotNull() {
            addCriterion("boxid is not null");
            return (Criteria) this;
        }

        public Criteria andBoxidEqualTo(Integer value) {
            addCriterion("boxid =", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidNotEqualTo(Integer value) {
            addCriterion("boxid <>", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidGreaterThan(Integer value) {
            addCriterion("boxid >", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidGreaterThanOrEqualTo(Integer value) {
            addCriterion("boxid >=", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidLessThan(Integer value) {
            addCriterion("boxid <", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidLessThanOrEqualTo(Integer value) {
            addCriterion("boxid <=", value, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidIn(List<Integer> values) {
            addCriterion("boxid in", values, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidNotIn(List<Integer> values) {
            addCriterion("boxid not in", values, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidBetween(Integer value1, Integer value2) {
            addCriterion("boxid between", value1, value2, "boxid");
            return (Criteria) this;
        }

        public Criteria andBoxidNotBetween(Integer value1, Integer value2) {
            addCriterion("boxid not between", value1, value2, "boxid");
            return (Criteria) this;
        }

        public Criteria andIsdeployIsNull() {
            addCriterion("isdeploy is null");
            return (Criteria) this;
        }

        public Criteria andIsdeployIsNotNull() {
            addCriterion("isdeploy is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeployEqualTo(Integer value) {
            addCriterion("isdeploy =", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotEqualTo(Integer value) {
            addCriterion("isdeploy <>", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployGreaterThan(Integer value) {
            addCriterion("isdeploy >", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdeploy >=", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployLessThan(Integer value) {
            addCriterion("isdeploy <", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployLessThanOrEqualTo(Integer value) {
            addCriterion("isdeploy <=", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployIn(List<Integer> values) {
            addCriterion("isdeploy in", values, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotIn(List<Integer> values) {
            addCriterion("isdeploy not in", values, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployBetween(Integer value1, Integer value2) {
            addCriterion("isdeploy between", value1, value2, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotBetween(Integer value1, Integer value2) {
            addCriterion("isdeploy not between", value1, value2, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andContractaddressIsNull() {
            addCriterion("contractAddress is null");
            return (Criteria) this;
        }

        public Criteria andContractaddressIsNotNull() {
            addCriterion("contractAddress is not null");
            return (Criteria) this;
        }

        public Criteria andContractaddressEqualTo(String value) {
            addCriterion("contractAddress =", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotEqualTo(String value) {
            addCriterion("contractAddress <>", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressGreaterThan(String value) {
            addCriterion("contractAddress >", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressGreaterThanOrEqualTo(String value) {
            addCriterion("contractAddress >=", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLessThan(String value) {
            addCriterion("contractAddress <", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLessThanOrEqualTo(String value) {
            addCriterion("contractAddress <=", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLike(String value) {
            addCriterion("contractAddress like", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotLike(String value) {
            addCriterion("contractAddress not like", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressIn(List<String> values) {
            addCriterion("contractAddress in", values, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotIn(List<String> values) {
            addCriterion("contractAddress not in", values, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressBetween(String value1, String value2) {
            addCriterion("contractAddress between", value1, value2, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotBetween(String value1, String value2) {
            addCriterion("contractAddress not between", value1, value2, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andSoldIsNull() {
            addCriterion("sold is null");
            return (Criteria) this;
        }

        public Criteria andSoldIsNotNull() {
            addCriterion("sold is not null");
            return (Criteria) this;
        }

        public Criteria andSoldEqualTo(Integer value) {
            addCriterion("sold =", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotEqualTo(Integer value) {
            addCriterion("sold <>", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldGreaterThan(Integer value) {
            addCriterion("sold >", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold >=", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldLessThan(Integer value) {
            addCriterion("sold <", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldLessThanOrEqualTo(Integer value) {
            addCriterion("sold <=", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldIn(List<Integer> values) {
            addCriterion("sold in", values, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotIn(List<Integer> values) {
            addCriterion("sold not in", values, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldBetween(Integer value1, Integer value2) {
            addCriterion("sold between", value1, value2, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotBetween(Integer value1, Integer value2) {
            addCriterion("sold not between", value1, value2, "sold");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIsNull() {
            addCriterion("creatorimg is null");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIsNotNull() {
            addCriterion("creatorimg is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorimgEqualTo(String value) {
            addCriterion("creatorimg =", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotEqualTo(String value) {
            addCriterion("creatorimg <>", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgGreaterThan(String value) {
            addCriterion("creatorimg >", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgGreaterThanOrEqualTo(String value) {
            addCriterion("creatorimg >=", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLessThan(String value) {
            addCriterion("creatorimg <", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLessThanOrEqualTo(String value) {
            addCriterion("creatorimg <=", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLike(String value) {
            addCriterion("creatorimg like", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotLike(String value) {
            addCriterion("creatorimg not like", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIn(List<String> values) {
            addCriterion("creatorimg in", values, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotIn(List<String> values) {
            addCriterion("creatorimg not in", values, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgBetween(String value1, String value2) {
            addCriterion("creatorimg between", value1, value2, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotBetween(String value1, String value2) {
            addCriterion("creatorimg not between", value1, value2, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andStockcIsNull() {
            addCriterion("stockc is null");
            return (Criteria) this;
        }

        public Criteria andStockcIsNotNull() {
            addCriterion("stockc is not null");
            return (Criteria) this;
        }

        public Criteria andStockcEqualTo(Integer value) {
            addCriterion("stockc =", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotEqualTo(Integer value) {
            addCriterion("stockc <>", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcGreaterThan(Integer value) {
            addCriterion("stockc >", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcGreaterThanOrEqualTo(Integer value) {
            addCriterion("stockc >=", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcLessThan(Integer value) {
            addCriterion("stockc <", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcLessThanOrEqualTo(Integer value) {
            addCriterion("stockc <=", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcIn(List<Integer> values) {
            addCriterion("stockc in", values, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotIn(List<Integer> values) {
            addCriterion("stockc not in", values, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcBetween(Integer value1, Integer value2) {
            addCriterion("stockc between", value1, value2, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotBetween(Integer value1, Integer value2) {
            addCriterion("stockc not between", value1, value2, "stockc");
            return (Criteria) this;
        }

        public Criteria andNosetupIsNull() {
            addCriterion("nosetup is null");
            return (Criteria) this;
        }

        public Criteria andNosetupIsNotNull() {
            addCriterion("nosetup is not null");
            return (Criteria) this;
        }

        public Criteria andNosetupEqualTo(String value) {
            addCriterion("nosetup =", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotEqualTo(String value) {
            addCriterion("nosetup <>", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupGreaterThan(String value) {
            addCriterion("nosetup >", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupGreaterThanOrEqualTo(String value) {
            addCriterion("nosetup >=", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLessThan(String value) {
            addCriterion("nosetup <", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLessThanOrEqualTo(String value) {
            addCriterion("nosetup <=", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLike(String value) {
            addCriterion("nosetup like", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotLike(String value) {
            addCriterion("nosetup not like", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupIn(List<String> values) {
            addCriterion("nosetup in", values, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotIn(List<String> values) {
            addCriterion("nosetup not in", values, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupBetween(String value1, String value2) {
            addCriterion("nosetup between", value1, value2, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotBetween(String value1, String value2) {
            addCriterion("nosetup not between", value1, value2, "nosetup");
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

        public Criteria andDdcidIsNull() {
            addCriterion("ddcid is null");
            return (Criteria) this;
        }

        public Criteria andDdcidIsNotNull() {
            addCriterion("ddcid is not null");
            return (Criteria) this;
        }

        public Criteria andDdcidEqualTo(Long value) {
            addCriterion("ddcid =", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidNotEqualTo(Long value) {
            addCriterion("ddcid <>", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidGreaterThan(Long value) {
            addCriterion("ddcid >", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidGreaterThanOrEqualTo(Long value) {
            addCriterion("ddcid >=", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidLessThan(Long value) {
            addCriterion("ddcid <", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidLessThanOrEqualTo(Long value) {
            addCriterion("ddcid <=", value, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidIn(List<Long> values) {
            addCriterion("ddcid in", values, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidNotIn(List<Long> values) {
            addCriterion("ddcid not in", values, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidBetween(Long value1, Long value2) {
            addCriterion("ddcid between", value1, value2, "ddcid");
            return (Criteria) this;
        }

        public Criteria andDdcidNotBetween(Long value1, Long value2) {
            addCriterion("ddcid not between", value1, value2, "ddcid");
            return (Criteria) this;
        }

        public Criteria andPravirtyIsNull() {
            addCriterion("pravirty is null");
            return (Criteria) this;
        }

        public Criteria andPravirtyIsNotNull() {
            addCriterion("pravirty is not null");
            return (Criteria) this;
        }

        public Criteria andPravirtyEqualTo(String value) {
            addCriterion("pravirty =", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyNotEqualTo(String value) {
            addCriterion("pravirty <>", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyGreaterThan(String value) {
            addCriterion("pravirty >", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyGreaterThanOrEqualTo(String value) {
            addCriterion("pravirty >=", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyLessThan(String value) {
            addCriterion("pravirty <", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyLessThanOrEqualTo(String value) {
            addCriterion("pravirty <=", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyLike(String value) {
            addCriterion("pravirty like", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyNotLike(String value) {
            addCriterion("pravirty not like", value, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyIn(List<String> values) {
            addCriterion("pravirty in", values, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyNotIn(List<String> values) {
            addCriterion("pravirty not in", values, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyBetween(String value1, String value2) {
            addCriterion("pravirty between", value1, value2, "pravirty");
            return (Criteria) this;
        }

        public Criteria andPravirtyNotBetween(String value1, String value2) {
            addCriterion("pravirty not between", value1, value2, "pravirty");
            return (Criteria) this;
        }

        public Criteria andDrawidIsNull() {
            addCriterion("drawid is null");
            return (Criteria) this;
        }

        public Criteria andDrawidIsNotNull() {
            addCriterion("drawid is not null");
            return (Criteria) this;
        }

        public Criteria andDrawidEqualTo(Integer value) {
            addCriterion("drawid =", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidNotEqualTo(Integer value) {
            addCriterion("drawid <>", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidGreaterThan(Integer value) {
            addCriterion("drawid >", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidGreaterThanOrEqualTo(Integer value) {
            addCriterion("drawid >=", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidLessThan(Integer value) {
            addCriterion("drawid <", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidLessThanOrEqualTo(Integer value) {
            addCriterion("drawid <=", value, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidIn(List<Integer> values) {
            addCriterion("drawid in", values, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidNotIn(List<Integer> values) {
            addCriterion("drawid not in", values, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidBetween(Integer value1, Integer value2) {
            addCriterion("drawid between", value1, value2, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawidNotBetween(Integer value1, Integer value2) {
            addCriterion("drawid not between", value1, value2, "drawid");
            return (Criteria) this;
        }

        public Criteria andDrawimgIsNull() {
            addCriterion("drawimg is null");
            return (Criteria) this;
        }

        public Criteria andDrawimgIsNotNull() {
            addCriterion("drawimg is not null");
            return (Criteria) this;
        }

        public Criteria andDrawimgEqualTo(String value) {
            addCriterion("drawimg =", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgNotEqualTo(String value) {
            addCriterion("drawimg <>", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgGreaterThan(String value) {
            addCriterion("drawimg >", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgGreaterThanOrEqualTo(String value) {
            addCriterion("drawimg >=", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgLessThan(String value) {
            addCriterion("drawimg <", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgLessThanOrEqualTo(String value) {
            addCriterion("drawimg <=", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgLike(String value) {
            addCriterion("drawimg like", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgNotLike(String value) {
            addCriterion("drawimg not like", value, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgIn(List<String> values) {
            addCriterion("drawimg in", values, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgNotIn(List<String> values) {
            addCriterion("drawimg not in", values, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgBetween(String value1, String value2) {
            addCriterion("drawimg between", value1, value2, "drawimg");
            return (Criteria) this;
        }

        public Criteria andDrawimgNotBetween(String value1, String value2) {
            addCriterion("drawimg not between", value1, value2, "drawimg");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressIsNull() {
            addCriterion("thcontractAddress is null");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressIsNotNull() {
            addCriterion("thcontractAddress is not null");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressEqualTo(String value) {
            addCriterion("thcontractAddress =", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressNotEqualTo(String value) {
            addCriterion("thcontractAddress <>", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressGreaterThan(String value) {
            addCriterion("thcontractAddress >", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressGreaterThanOrEqualTo(String value) {
            addCriterion("thcontractAddress >=", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressLessThan(String value) {
            addCriterion("thcontractAddress <", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressLessThanOrEqualTo(String value) {
            addCriterion("thcontractAddress <=", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressLike(String value) {
            addCriterion("thcontractAddress like", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressNotLike(String value) {
            addCriterion("thcontractAddress not like", value, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressIn(List<String> values) {
            addCriterion("thcontractAddress in", values, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressNotIn(List<String> values) {
            addCriterion("thcontractAddress not in", values, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressBetween(String value1, String value2) {
            addCriterion("thcontractAddress between", value1, value2, "thcontractaddress");
            return (Criteria) this;
        }

        public Criteria andThcontractaddressNotBetween(String value1, String value2) {
            addCriterion("thcontractAddress not between", value1, value2, "thcontractaddress");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * collection 
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