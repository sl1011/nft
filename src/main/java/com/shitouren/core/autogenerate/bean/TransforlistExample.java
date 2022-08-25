package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.List;

public class TransforlistExample {
    /**
     * transforlist
     */
    protected String orderByClause;

    /**
     * transforlist
     */
    protected boolean distinct;

    /**
     * transforlist
     */
    protected List<Criteria> oredCriteria;

    /**
     * transforlist
     */
    protected Integer pageNo = 1;

    /**
     * transforlist
     */
    protected Integer startRow;

    /**
     * transforlist
     */
    protected Integer pageSize = 10;

    public TransforlistExample() {
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
     * transforlist 
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

        public Criteria andColidIsNull() {
            addCriterion("colid is null");
            return (Criteria) this;
        }

        public Criteria andColidIsNotNull() {
            addCriterion("colid is not null");
            return (Criteria) this;
        }

        public Criteria andColidEqualTo(Integer value) {
            addCriterion("colid =", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidNotEqualTo(Integer value) {
            addCriterion("colid <>", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidGreaterThan(Integer value) {
            addCriterion("colid >", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidGreaterThanOrEqualTo(Integer value) {
            addCriterion("colid >=", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidLessThan(Integer value) {
            addCriterion("colid <", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidLessThanOrEqualTo(Integer value) {
            addCriterion("colid <=", value, "colid");
            return (Criteria) this;
        }

        public Criteria andColidIn(List<Integer> values) {
            addCriterion("colid in", values, "colid");
            return (Criteria) this;
        }

        public Criteria andColidNotIn(List<Integer> values) {
            addCriterion("colid not in", values, "colid");
            return (Criteria) this;
        }

        public Criteria andColidBetween(Integer value1, Integer value2) {
            addCriterion("colid between", value1, value2, "colid");
            return (Criteria) this;
        }

        public Criteria andColidNotBetween(Integer value1, Integer value2) {
            addCriterion("colid not between", value1, value2, "colid");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * transforlist 
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