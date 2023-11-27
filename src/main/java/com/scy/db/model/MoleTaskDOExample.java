package com.scy.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoleTaskDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer offset;

    private Integer limit;

    public MoleTaskDOExample() {
        oredCriteria = new ArrayList<>();
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

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeIsNull() {
            addCriterion("next_exe_time is null");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeIsNotNull() {
            addCriterion("next_exe_time is not null");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeEqualTo(Date value) {
            addCriterion("next_exe_time =", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeNotEqualTo(Date value) {
            addCriterion("next_exe_time <>", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeGreaterThan(Date value) {
            addCriterion("next_exe_time >", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("next_exe_time >=", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeLessThan(Date value) {
            addCriterion("next_exe_time <", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeLessThanOrEqualTo(Date value) {
            addCriterion("next_exe_time <=", value, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeIn(List<Date> values) {
            addCriterion("next_exe_time in", values, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeNotIn(List<Date> values) {
            addCriterion("next_exe_time not in", values, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeBetween(Date value1, Date value2) {
            addCriterion("next_exe_time between", value1, value2, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andNextExeTimeNotBetween(Date value1, Date value2) {
            addCriterion("next_exe_time not between", value1, value2, "nextExeTime");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecIsNull() {
            addCriterion("exe_interval_sec is null");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecIsNotNull() {
            addCriterion("exe_interval_sec is not null");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecEqualTo(Integer value) {
            addCriterion("exe_interval_sec =", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecNotEqualTo(Integer value) {
            addCriterion("exe_interval_sec <>", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecGreaterThan(Integer value) {
            addCriterion("exe_interval_sec >", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecGreaterThanOrEqualTo(Integer value) {
            addCriterion("exe_interval_sec >=", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecLessThan(Integer value) {
            addCriterion("exe_interval_sec <", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecLessThanOrEqualTo(Integer value) {
            addCriterion("exe_interval_sec <=", value, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecIn(List<Integer> values) {
            addCriterion("exe_interval_sec in", values, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecNotIn(List<Integer> values) {
            addCriterion("exe_interval_sec not in", values, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecBetween(Integer value1, Integer value2) {
            addCriterion("exe_interval_sec between", value1, value2, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeIntervalSecNotBetween(Integer value1, Integer value2) {
            addCriterion("exe_interval_sec not between", value1, value2, "exeIntervalSec");
            return (Criteria) this;
        }

        public Criteria andExeCountIsNull() {
            addCriterion("exe_count is null");
            return (Criteria) this;
        }

        public Criteria andExeCountIsNotNull() {
            addCriterion("exe_count is not null");
            return (Criteria) this;
        }

        public Criteria andExeCountEqualTo(Integer value) {
            addCriterion("exe_count =", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotEqualTo(Integer value) {
            addCriterion("exe_count <>", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountGreaterThan(Integer value) {
            addCriterion("exe_count >", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("exe_count >=", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountLessThan(Integer value) {
            addCriterion("exe_count <", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountLessThanOrEqualTo(Integer value) {
            addCriterion("exe_count <=", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountIn(List<Integer> values) {
            addCriterion("exe_count in", values, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotIn(List<Integer> values) {
            addCriterion("exe_count not in", values, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountBetween(Integer value1, Integer value2) {
            addCriterion("exe_count between", value1, value2, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("exe_count not between", value1, value2, "exeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountIsNull() {
            addCriterion("max_exe_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountIsNotNull() {
            addCriterion("max_exe_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountEqualTo(Integer value) {
            addCriterion("max_exe_count =", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountNotEqualTo(Integer value) {
            addCriterion("max_exe_count <>", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountGreaterThan(Integer value) {
            addCriterion("max_exe_count >", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_exe_count >=", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountLessThan(Integer value) {
            addCriterion("max_exe_count <", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountLessThanOrEqualTo(Integer value) {
            addCriterion("max_exe_count <=", value, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountIn(List<Integer> values) {
            addCriterion("max_exe_count in", values, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountNotIn(List<Integer> values) {
            addCriterion("max_exe_count not in", values, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountBetween(Integer value1, Integer value2) {
            addCriterion("max_exe_count between", value1, value2, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andMaxExeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_exe_count not between", value1, value2, "maxExeCount");
            return (Criteria) this;
        }

        public Criteria andExeStatusIsNull() {
            addCriterion("exe_status is null");
            return (Criteria) this;
        }

        public Criteria andExeStatusIsNotNull() {
            addCriterion("exe_status is not null");
            return (Criteria) this;
        }

        public Criteria andExeStatusEqualTo(Integer value) {
            addCriterion("exe_status =", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotEqualTo(Integer value) {
            addCriterion("exe_status <>", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusGreaterThan(Integer value) {
            addCriterion("exe_status >", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("exe_status >=", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusLessThan(Integer value) {
            addCriterion("exe_status <", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("exe_status <=", value, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusIn(List<Integer> values) {
            addCriterion("exe_status in", values, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotIn(List<Integer> values) {
            addCriterion("exe_status not in", values, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusBetween(Integer value1, Integer value2) {
            addCriterion("exe_status between", value1, value2, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andExeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("exe_status not between", value1, value2, "exeStatus");
            return (Criteria) this;
        }

        public Criteria andSharedIdIsNull() {
            addCriterion("shared_id is null");
            return (Criteria) this;
        }

        public Criteria andSharedIdIsNotNull() {
            addCriterion("shared_id is not null");
            return (Criteria) this;
        }

        public Criteria andSharedIdEqualTo(Long value) {
            addCriterion("shared_id =", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdNotEqualTo(Long value) {
            addCriterion("shared_id <>", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdGreaterThan(Long value) {
            addCriterion("shared_id >", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shared_id >=", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdLessThan(Long value) {
            addCriterion("shared_id <", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdLessThanOrEqualTo(Long value) {
            addCriterion("shared_id <=", value, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdIn(List<Long> values) {
            addCriterion("shared_id in", values, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdNotIn(List<Long> values) {
            addCriterion("shared_id not in", values, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdBetween(Long value1, Long value2) {
            addCriterion("shared_id between", value1, value2, "sharedId");
            return (Criteria) this;
        }

        public Criteria andSharedIdNotBetween(Long value1, Long value2) {
            addCriterion("shared_id not between", value1, value2, "sharedId");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonIsNull() {
            addCriterion("param_type_json is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonIsNotNull() {
            addCriterion("param_type_json is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonEqualTo(String value) {
            addCriterion("param_type_json =", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonNotEqualTo(String value) {
            addCriterion("param_type_json <>", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonGreaterThan(String value) {
            addCriterion("param_type_json >", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonGreaterThanOrEqualTo(String value) {
            addCriterion("param_type_json >=", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonLessThan(String value) {
            addCriterion("param_type_json <", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonLessThanOrEqualTo(String value) {
            addCriterion("param_type_json <=", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonLike(String value) {
            addCriterion("param_type_json like", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonNotLike(String value) {
            addCriterion("param_type_json not like", value, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonIn(List<String> values) {
            addCriterion("param_type_json in", values, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonNotIn(List<String> values) {
            addCriterion("param_type_json not in", values, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonBetween(String value1, String value2) {
            addCriterion("param_type_json between", value1, value2, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andParamTypeJsonNotBetween(String value1, String value2) {
            addCriterion("param_type_json not between", value1, value2, "paramTypeJson");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameIsNull() {
            addCriterion("target_class_name is null");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameIsNotNull() {
            addCriterion("target_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameEqualTo(String value) {
            addCriterion("target_class_name =", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameNotEqualTo(String value) {
            addCriterion("target_class_name <>", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameGreaterThan(String value) {
            addCriterion("target_class_name >", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("target_class_name >=", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameLessThan(String value) {
            addCriterion("target_class_name <", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameLessThanOrEqualTo(String value) {
            addCriterion("target_class_name <=", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameLike(String value) {
            addCriterion("target_class_name like", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameNotLike(String value) {
            addCriterion("target_class_name not like", value, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameIn(List<String> values) {
            addCriterion("target_class_name in", values, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameNotIn(List<String> values) {
            addCriterion("target_class_name not in", values, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameBetween(String value1, String value2) {
            addCriterion("target_class_name between", value1, value2, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andTargetClassNameNotBetween(String value1, String value2) {
            addCriterion("target_class_name not between", value1, value2, "targetClassName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("method_name is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("method_name is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("method_name =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("method_name <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("method_name >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("method_name >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("method_name <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("method_name <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("method_name like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("method_name not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("method_name in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("method_name not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("method_name between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("method_name not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andBeanNameIsNull() {
            addCriterion("bean_name is null");
            return (Criteria) this;
        }

        public Criteria andBeanNameIsNotNull() {
            addCriterion("bean_name is not null");
            return (Criteria) this;
        }

        public Criteria andBeanNameEqualTo(String value) {
            addCriterion("bean_name =", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameNotEqualTo(String value) {
            addCriterion("bean_name <>", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameGreaterThan(String value) {
            addCriterion("bean_name >", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameGreaterThanOrEqualTo(String value) {
            addCriterion("bean_name >=", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameLessThan(String value) {
            addCriterion("bean_name <", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameLessThanOrEqualTo(String value) {
            addCriterion("bean_name <=", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameLike(String value) {
            addCriterion("bean_name like", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameNotLike(String value) {
            addCriterion("bean_name not like", value, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameIn(List<String> values) {
            addCriterion("bean_name in", values, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameNotIn(List<String> values) {
            addCriterion("bean_name not in", values, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameBetween(String value1, String value2) {
            addCriterion("bean_name between", value1, value2, "beanName");
            return (Criteria) this;
        }

        public Criteria andBeanNameNotBetween(String value1, String value2) {
            addCriterion("bean_name not between", value1, value2, "beanName");
            return (Criteria) this;
        }

        public Criteria andEnvIsNull() {
            addCriterion("env is null");
            return (Criteria) this;
        }

        public Criteria andEnvIsNotNull() {
            addCriterion("env is not null");
            return (Criteria) this;
        }

        public Criteria andEnvEqualTo(String value) {
            addCriterion("env =", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotEqualTo(String value) {
            addCriterion("env <>", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThan(String value) {
            addCriterion("env >", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThanOrEqualTo(String value) {
            addCriterion("env >=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThan(String value) {
            addCriterion("env <", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThanOrEqualTo(String value) {
            addCriterion("env <=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLike(String value) {
            addCriterion("env like", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotLike(String value) {
            addCriterion("env not like", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvIn(List<String> values) {
            addCriterion("env in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotIn(List<String> values) {
            addCriterion("env not in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvBetween(String value1, String value2) {
            addCriterion("env between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotBetween(String value1, String value2) {
            addCriterion("env not between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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