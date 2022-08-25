package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyboxExample {
    /**
     * mybox
     */
    protected String orderByClause;

    /**
     * mybox
     */
    protected boolean distinct;

    /**
     * mybox
     */
    protected List<Criteria> oredCriteria;

    /**
     * mybox
     */
    protected Integer pageNo = 1;

    /**
     * mybox
     */
    protected Integer startRow;

    /**
     * mybox
     */
    protected Integer pageSize = 10;

    public MyboxExample() {
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
     * mybox 
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

        public Criteria andSpidIsNull() {
            addCriterion("spid is null");
            return (Criteria) this;
        }

        public Criteria andSpidIsNotNull() {
            addCriterion("spid is not null");
            return (Criteria) this;
        }

        public Criteria andSpidEqualTo(Integer value) {
            addCriterion("spid =", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotEqualTo(Integer value) {
            addCriterion("spid <>", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidGreaterThan(Integer value) {
            addCriterion("spid >", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("spid >=", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidLessThan(Integer value) {
            addCriterion("spid <", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidLessThanOrEqualTo(Integer value) {
            addCriterion("spid <=", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidIn(List<Integer> values) {
            addCriterion("spid in", values, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotIn(List<Integer> values) {
            addCriterion("spid not in", values, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidBetween(Integer value1, Integer value2) {
            addCriterion("spid between", value1, value2, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotBetween(Integer value1, Integer value2) {
            addCriterion("spid not between", value1, value2, "spid");
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

        public Criteria andCreattimeIsNull() {
            addCriterion("creattime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("creattime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("creattime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("creattime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("creattime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creattime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("creattime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("creattime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("creattime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("creattime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("creattime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("creattime not between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIsNull() {
            addCriterion("usergrantid is null");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIsNotNull() {
            addCriterion("usergrantid is not null");
            return (Criteria) this;
        }

        public Criteria andUsergrantidEqualTo(Integer value) {
            addCriterion("usergrantid =", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotEqualTo(Integer value) {
            addCriterion("usergrantid <>", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidGreaterThan(Integer value) {
            addCriterion("usergrantid >", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidGreaterThanOrEqualTo(Integer value) {
            addCriterion("usergrantid >=", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidLessThan(Integer value) {
            addCriterion("usergrantid <", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidLessThanOrEqualTo(Integer value) {
            addCriterion("usergrantid <=", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIn(List<Integer> values) {
            addCriterion("usergrantid in", values, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotIn(List<Integer> values) {
            addCriterion("usergrantid not in", values, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidBetween(Integer value1, Integer value2) {
            addCriterion("usergrantid between", value1, value2, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotBetween(Integer value1, Integer value2) {
            addCriterion("usergrantid not between", value1, value2, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andIssuidIsNull() {
            addCriterion("issuid is null");
            return (Criteria) this;
        }

        public Criteria andIssuidIsNotNull() {
            addCriterion("issuid is not null");
            return (Criteria) this;
        }

        public Criteria andIssuidEqualTo(Integer value) {
            addCriterion("issuid =", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidNotEqualTo(Integer value) {
            addCriterion("issuid <>", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidGreaterThan(Integer value) {
            addCriterion("issuid >", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("issuid >=", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidLessThan(Integer value) {
            addCriterion("issuid <", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidLessThanOrEqualTo(Integer value) {
            addCriterion("issuid <=", value, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidIn(List<Integer> values) {
            addCriterion("issuid in", values, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidNotIn(List<Integer> values) {
            addCriterion("issuid not in", values, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidBetween(Integer value1, Integer value2) {
            addCriterion("issuid between", value1, value2, "issuid");
            return (Criteria) this;
        }

        public Criteria andIssuidNotBetween(Integer value1, Integer value2) {
            addCriterion("issuid not between", value1, value2, "issuid");
            return (Criteria) this;
        }

        public Criteria andHashIsNull() {
            addCriterion("hash is null");
            return (Criteria) this;
        }

        public Criteria andHashIsNotNull() {
            addCriterion("hash is not null");
            return (Criteria) this;
        }

        public Criteria andHashEqualTo(String value) {
            addCriterion("hash =", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotEqualTo(String value) {
            addCriterion("hash <>", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThan(String value) {
            addCriterion("hash >", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThanOrEqualTo(String value) {
            addCriterion("hash >=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThan(String value) {
            addCriterion("hash <", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThanOrEqualTo(String value) {
            addCriterion("hash <=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLike(String value) {
            addCriterion("hash like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotLike(String value) {
            addCriterion("hash not like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashIn(List<String> values) {
            addCriterion("hash in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotIn(List<String> values) {
            addCriterion("hash not in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashBetween(String value1, String value2) {
            addCriterion("hash between", value1, value2, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotBetween(String value1, String value2) {
            addCriterion("hash not between", value1, value2, "hash");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * mybox 
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