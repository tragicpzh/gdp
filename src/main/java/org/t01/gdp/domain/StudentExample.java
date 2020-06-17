package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
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

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
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

        public Criteria andHeadPortraitIsNull() {
            addCriterion("head_portrait is null");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitIsNotNull() {
            addCriterion("head_portrait is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitEqualTo(String value) {
            addCriterion("head_portrait =", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitNotEqualTo(String value) {
            addCriterion("head_portrait <>", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitGreaterThan(String value) {
            addCriterion("head_portrait >", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitGreaterThanOrEqualTo(String value) {
            addCriterion("head_portrait >=", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitLessThan(String value) {
            addCriterion("head_portrait <", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitLessThanOrEqualTo(String value) {
            addCriterion("head_portrait <=", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitLike(String value) {
            addCriterion("head_portrait like", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitNotLike(String value) {
            addCriterion("head_portrait not like", value, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitIn(List<String> values) {
            addCriterion("head_portrait in", values, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitNotIn(List<String> values) {
            addCriterion("head_portrait not in", values, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitBetween(String value1, String value2) {
            addCriterion("head_portrait between", value1, value2, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andHeadPortraitNotBetween(String value1, String value2) {
            addCriterion("head_portrait not between", value1, value2, "headPortrait");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(String value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(String value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(String value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(String value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(String value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(String value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLike(String value) {
            addCriterion("major_id like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotLike(String value) {
            addCriterion("major_id not like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<String> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<String> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(String value1, String value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(String value1, String value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
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

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("subject_id is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Long value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Long value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Long value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Long value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Long> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Long> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Long value1, Long value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentIsNull() {
            addCriterion("open_document is null");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentIsNotNull() {
            addCriterion("open_document is not null");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentEqualTo(String value) {
            addCriterion("open_document =", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentNotEqualTo(String value) {
            addCriterion("open_document <>", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentGreaterThan(String value) {
            addCriterion("open_document >", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("open_document >=", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentLessThan(String value) {
            addCriterion("open_document <", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentLessThanOrEqualTo(String value) {
            addCriterion("open_document <=", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentLike(String value) {
            addCriterion("open_document like", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentNotLike(String value) {
            addCriterion("open_document not like", value, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentIn(List<String> values) {
            addCriterion("open_document in", values, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentNotIn(List<String> values) {
            addCriterion("open_document not in", values, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentBetween(String value1, String value2) {
            addCriterion("open_document between", value1, value2, "openDocument");
            return (Criteria) this;
        }

        public Criteria andOpenDocumentNotBetween(String value1, String value2) {
            addCriterion("open_document not between", value1, value2, "openDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentIsNull() {
            addCriterion("middle_document is null");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentIsNotNull() {
            addCriterion("middle_document is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentEqualTo(String value) {
            addCriterion("middle_document =", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentNotEqualTo(String value) {
            addCriterion("middle_document <>", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentGreaterThan(String value) {
            addCriterion("middle_document >", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("middle_document >=", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentLessThan(String value) {
            addCriterion("middle_document <", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentLessThanOrEqualTo(String value) {
            addCriterion("middle_document <=", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentLike(String value) {
            addCriterion("middle_document like", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentNotLike(String value) {
            addCriterion("middle_document not like", value, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentIn(List<String> values) {
            addCriterion("middle_document in", values, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentNotIn(List<String> values) {
            addCriterion("middle_document not in", values, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentBetween(String value1, String value2) {
            addCriterion("middle_document between", value1, value2, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andMiddleDocumentNotBetween(String value1, String value2) {
            addCriterion("middle_document not between", value1, value2, "middleDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentIsNull() {
            addCriterion("conclusion_document is null");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentIsNotNull() {
            addCriterion("conclusion_document is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentEqualTo(String value) {
            addCriterion("conclusion_document =", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentNotEqualTo(String value) {
            addCriterion("conclusion_document <>", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentGreaterThan(String value) {
            addCriterion("conclusion_document >", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("conclusion_document >=", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentLessThan(String value) {
            addCriterion("conclusion_document <", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentLessThanOrEqualTo(String value) {
            addCriterion("conclusion_document <=", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentLike(String value) {
            addCriterion("conclusion_document like", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentNotLike(String value) {
            addCriterion("conclusion_document not like", value, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentIn(List<String> values) {
            addCriterion("conclusion_document in", values, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentNotIn(List<String> values) {
            addCriterion("conclusion_document not in", values, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentBetween(String value1, String value2) {
            addCriterion("conclusion_document between", value1, value2, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andConclusionDocumentNotBetween(String value1, String value2) {
            addCriterion("conclusion_document not between", value1, value2, "conclusionDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentIsNull() {
            addCriterion("paper_document is null");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentIsNotNull() {
            addCriterion("paper_document is not null");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentEqualTo(String value) {
            addCriterion("paper_document =", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentNotEqualTo(String value) {
            addCriterion("paper_document <>", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentGreaterThan(String value) {
            addCriterion("paper_document >", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("paper_document >=", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentLessThan(String value) {
            addCriterion("paper_document <", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentLessThanOrEqualTo(String value) {
            addCriterion("paper_document <=", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentLike(String value) {
            addCriterion("paper_document like", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentNotLike(String value) {
            addCriterion("paper_document not like", value, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentIn(List<String> values) {
            addCriterion("paper_document in", values, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentNotIn(List<String> values) {
            addCriterion("paper_document not in", values, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentBetween(String value1, String value2) {
            addCriterion("paper_document between", value1, value2, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andPaperDocumentNotBetween(String value1, String value2) {
            addCriterion("paper_document not between", value1, value2, "paperDocument");
            return (Criteria) this;
        }

        public Criteria andOpenScore1IsNull() {
            addCriterion("open_score1 is null");
            return (Criteria) this;
        }

        public Criteria andOpenScore1IsNotNull() {
            addCriterion("open_score1 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenScore1EqualTo(Integer value) {
            addCriterion("open_score1 =", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1NotEqualTo(Integer value) {
            addCriterion("open_score1 <>", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1GreaterThan(Integer value) {
            addCriterion("open_score1 >", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1GreaterThanOrEqualTo(Integer value) {
            addCriterion("open_score1 >=", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1LessThan(Integer value) {
            addCriterion("open_score1 <", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1LessThanOrEqualTo(Integer value) {
            addCriterion("open_score1 <=", value, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1In(List<Integer> values) {
            addCriterion("open_score1 in", values, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1NotIn(List<Integer> values) {
            addCriterion("open_score1 not in", values, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1Between(Integer value1, Integer value2) {
            addCriterion("open_score1 between", value1, value2, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore1NotBetween(Integer value1, Integer value2) {
            addCriterion("open_score1 not between", value1, value2, "openScore1");
            return (Criteria) this;
        }

        public Criteria andOpenScore2IsNull() {
            addCriterion("open_score2 is null");
            return (Criteria) this;
        }

        public Criteria andOpenScore2IsNotNull() {
            addCriterion("open_score2 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenScore2EqualTo(Integer value) {
            addCriterion("open_score2 =", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2NotEqualTo(Integer value) {
            addCriterion("open_score2 <>", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2GreaterThan(Integer value) {
            addCriterion("open_score2 >", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2GreaterThanOrEqualTo(Integer value) {
            addCriterion("open_score2 >=", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2LessThan(Integer value) {
            addCriterion("open_score2 <", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2LessThanOrEqualTo(Integer value) {
            addCriterion("open_score2 <=", value, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2In(List<Integer> values) {
            addCriterion("open_score2 in", values, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2NotIn(List<Integer> values) {
            addCriterion("open_score2 not in", values, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2Between(Integer value1, Integer value2) {
            addCriterion("open_score2 between", value1, value2, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore2NotBetween(Integer value1, Integer value2) {
            addCriterion("open_score2 not between", value1, value2, "openScore2");
            return (Criteria) this;
        }

        public Criteria andOpenScore3IsNull() {
            addCriterion("open_score3 is null");
            return (Criteria) this;
        }

        public Criteria andOpenScore3IsNotNull() {
            addCriterion("open_score3 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenScore3EqualTo(Integer value) {
            addCriterion("open_score3 =", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3NotEqualTo(Integer value) {
            addCriterion("open_score3 <>", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3GreaterThan(Integer value) {
            addCriterion("open_score3 >", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3GreaterThanOrEqualTo(Integer value) {
            addCriterion("open_score3 >=", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3LessThan(Integer value) {
            addCriterion("open_score3 <", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3LessThanOrEqualTo(Integer value) {
            addCriterion("open_score3 <=", value, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3In(List<Integer> values) {
            addCriterion("open_score3 in", values, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3NotIn(List<Integer> values) {
            addCriterion("open_score3 not in", values, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3Between(Integer value1, Integer value2) {
            addCriterion("open_score3 between", value1, value2, "openScore3");
            return (Criteria) this;
        }

        public Criteria andOpenScore3NotBetween(Integer value1, Integer value2) {
            addCriterion("open_score3 not between", value1, value2, "openScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1IsNull() {
            addCriterion("middle_score1 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1IsNotNull() {
            addCriterion("middle_score1 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1EqualTo(Integer value) {
            addCriterion("middle_score1 =", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1NotEqualTo(Integer value) {
            addCriterion("middle_score1 <>", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1GreaterThan(Integer value) {
            addCriterion("middle_score1 >", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1GreaterThanOrEqualTo(Integer value) {
            addCriterion("middle_score1 >=", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1LessThan(Integer value) {
            addCriterion("middle_score1 <", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1LessThanOrEqualTo(Integer value) {
            addCriterion("middle_score1 <=", value, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1In(List<Integer> values) {
            addCriterion("middle_score1 in", values, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1NotIn(List<Integer> values) {
            addCriterion("middle_score1 not in", values, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1Between(Integer value1, Integer value2) {
            addCriterion("middle_score1 between", value1, value2, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore1NotBetween(Integer value1, Integer value2) {
            addCriterion("middle_score1 not between", value1, value2, "middleScore1");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2IsNull() {
            addCriterion("middle_score2 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2IsNotNull() {
            addCriterion("middle_score2 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2EqualTo(Integer value) {
            addCriterion("middle_score2 =", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2NotEqualTo(Integer value) {
            addCriterion("middle_score2 <>", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2GreaterThan(Integer value) {
            addCriterion("middle_score2 >", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2GreaterThanOrEqualTo(Integer value) {
            addCriterion("middle_score2 >=", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2LessThan(Integer value) {
            addCriterion("middle_score2 <", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2LessThanOrEqualTo(Integer value) {
            addCriterion("middle_score2 <=", value, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2In(List<Integer> values) {
            addCriterion("middle_score2 in", values, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2NotIn(List<Integer> values) {
            addCriterion("middle_score2 not in", values, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2Between(Integer value1, Integer value2) {
            addCriterion("middle_score2 between", value1, value2, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore2NotBetween(Integer value1, Integer value2) {
            addCriterion("middle_score2 not between", value1, value2, "middleScore2");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3IsNull() {
            addCriterion("middle_score3 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3IsNotNull() {
            addCriterion("middle_score3 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3EqualTo(Integer value) {
            addCriterion("middle_score3 =", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3NotEqualTo(Integer value) {
            addCriterion("middle_score3 <>", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3GreaterThan(Integer value) {
            addCriterion("middle_score3 >", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3GreaterThanOrEqualTo(Integer value) {
            addCriterion("middle_score3 >=", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3LessThan(Integer value) {
            addCriterion("middle_score3 <", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3LessThanOrEqualTo(Integer value) {
            addCriterion("middle_score3 <=", value, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3In(List<Integer> values) {
            addCriterion("middle_score3 in", values, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3NotIn(List<Integer> values) {
            addCriterion("middle_score3 not in", values, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3Between(Integer value1, Integer value2) {
            addCriterion("middle_score3 between", value1, value2, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andMiddleScore3NotBetween(Integer value1, Integer value2) {
            addCriterion("middle_score3 not between", value1, value2, "middleScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1IsNull() {
            addCriterion("conclusion_score1 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1IsNotNull() {
            addCriterion("conclusion_score1 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1EqualTo(Integer value) {
            addCriterion("conclusion_score1 =", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1NotEqualTo(Integer value) {
            addCriterion("conclusion_score1 <>", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1GreaterThan(Integer value) {
            addCriterion("conclusion_score1 >", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1GreaterThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score1 >=", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1LessThan(Integer value) {
            addCriterion("conclusion_score1 <", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1LessThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score1 <=", value, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1In(List<Integer> values) {
            addCriterion("conclusion_score1 in", values, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1NotIn(List<Integer> values) {
            addCriterion("conclusion_score1 not in", values, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1Between(Integer value1, Integer value2) {
            addCriterion("conclusion_score1 between", value1, value2, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore1NotBetween(Integer value1, Integer value2) {
            addCriterion("conclusion_score1 not between", value1, value2, "conclusionScore1");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2IsNull() {
            addCriterion("conclusion_score2 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2IsNotNull() {
            addCriterion("conclusion_score2 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2EqualTo(Integer value) {
            addCriterion("conclusion_score2 =", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2NotEqualTo(Integer value) {
            addCriterion("conclusion_score2 <>", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2GreaterThan(Integer value) {
            addCriterion("conclusion_score2 >", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2GreaterThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score2 >=", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2LessThan(Integer value) {
            addCriterion("conclusion_score2 <", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2LessThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score2 <=", value, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2In(List<Integer> values) {
            addCriterion("conclusion_score2 in", values, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2NotIn(List<Integer> values) {
            addCriterion("conclusion_score2 not in", values, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2Between(Integer value1, Integer value2) {
            addCriterion("conclusion_score2 between", value1, value2, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore2NotBetween(Integer value1, Integer value2) {
            addCriterion("conclusion_score2 not between", value1, value2, "conclusionScore2");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3IsNull() {
            addCriterion("conclusion_score3 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3IsNotNull() {
            addCriterion("conclusion_score3 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3EqualTo(Integer value) {
            addCriterion("conclusion_score3 =", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3NotEqualTo(Integer value) {
            addCriterion("conclusion_score3 <>", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3GreaterThan(Integer value) {
            addCriterion("conclusion_score3 >", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3GreaterThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score3 >=", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3LessThan(Integer value) {
            addCriterion("conclusion_score3 <", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3LessThanOrEqualTo(Integer value) {
            addCriterion("conclusion_score3 <=", value, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3In(List<Integer> values) {
            addCriterion("conclusion_score3 in", values, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3NotIn(List<Integer> values) {
            addCriterion("conclusion_score3 not in", values, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3Between(Integer value1, Integer value2) {
            addCriterion("conclusion_score3 between", value1, value2, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andConclusionScore3NotBetween(Integer value1, Integer value2) {
            addCriterion("conclusion_score3 not between", value1, value2, "conclusionScore3");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreIsNull() {
            addCriterion("teacher_paper_score is null");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreIsNotNull() {
            addCriterion("teacher_paper_score is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreEqualTo(Integer value) {
            addCriterion("teacher_paper_score =", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreNotEqualTo(Integer value) {
            addCriterion("teacher_paper_score <>", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreGreaterThan(Integer value) {
            addCriterion("teacher_paper_score >", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("teacher_paper_score >=", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreLessThan(Integer value) {
            addCriterion("teacher_paper_score <", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreLessThanOrEqualTo(Integer value) {
            addCriterion("teacher_paper_score <=", value, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreIn(List<Integer> values) {
            addCriterion("teacher_paper_score in", values, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreNotIn(List<Integer> values) {
            addCriterion("teacher_paper_score not in", values, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreBetween(Integer value1, Integer value2) {
            addCriterion("teacher_paper_score between", value1, value2, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("teacher_paper_score not between", value1, value2, "teacherPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreIsNull() {
            addCriterion("student_paper_score is null");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreIsNotNull() {
            addCriterion("student_paper_score is not null");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreEqualTo(Integer value) {
            addCriterion("student_paper_score =", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreNotEqualTo(Integer value) {
            addCriterion("student_paper_score <>", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreGreaterThan(Integer value) {
            addCriterion("student_paper_score >", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_paper_score >=", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreLessThan(Integer value) {
            addCriterion("student_paper_score <", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreLessThanOrEqualTo(Integer value) {
            addCriterion("student_paper_score <=", value, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreIn(List<Integer> values) {
            addCriterion("student_paper_score in", values, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreNotIn(List<Integer> values) {
            addCriterion("student_paper_score not in", values, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreBetween(Integer value1, Integer value2) {
            addCriterion("student_paper_score between", value1, value2, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andStudentPaperScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("student_paper_score not between", value1, value2, "studentPaperScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreIsNull() {
            addCriterion("final_score is null");
            return (Criteria) this;
        }

        public Criteria andFinalScoreIsNotNull() {
            addCriterion("final_score is not null");
            return (Criteria) this;
        }

        public Criteria andFinalScoreEqualTo(BigDecimal value) {
            addCriterion("final_score =", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreNotEqualTo(BigDecimal value) {
            addCriterion("final_score <>", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreGreaterThan(BigDecimal value) {
            addCriterion("final_score >", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("final_score >=", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreLessThan(BigDecimal value) {
            addCriterion("final_score <", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("final_score <=", value, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreIn(List<BigDecimal> values) {
            addCriterion("final_score in", values, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreNotIn(List<BigDecimal> values) {
            addCriterion("final_score not in", values, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_score between", value1, value2, "finalScore");
            return (Criteria) this;
        }

        public Criteria andFinalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_score not between", value1, value2, "finalScore");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1IsNull() {
            addCriterion("open_evaluation1 is null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1IsNotNull() {
            addCriterion("open_evaluation1 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1EqualTo(String value) {
            addCriterion("open_evaluation1 =", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1NotEqualTo(String value) {
            addCriterion("open_evaluation1 <>", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1GreaterThan(String value) {
            addCriterion("open_evaluation1 >", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1GreaterThanOrEqualTo(String value) {
            addCriterion("open_evaluation1 >=", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1LessThan(String value) {
            addCriterion("open_evaluation1 <", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1LessThanOrEqualTo(String value) {
            addCriterion("open_evaluation1 <=", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1Like(String value) {
            addCriterion("open_evaluation1 like", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1NotLike(String value) {
            addCriterion("open_evaluation1 not like", value, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1In(List<String> values) {
            addCriterion("open_evaluation1 in", values, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1NotIn(List<String> values) {
            addCriterion("open_evaluation1 not in", values, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1Between(String value1, String value2) {
            addCriterion("open_evaluation1 between", value1, value2, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation1NotBetween(String value1, String value2) {
            addCriterion("open_evaluation1 not between", value1, value2, "openEvaluation1");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2IsNull() {
            addCriterion("open_evaluation2 is null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2IsNotNull() {
            addCriterion("open_evaluation2 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2EqualTo(String value) {
            addCriterion("open_evaluation2 =", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2NotEqualTo(String value) {
            addCriterion("open_evaluation2 <>", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2GreaterThan(String value) {
            addCriterion("open_evaluation2 >", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2GreaterThanOrEqualTo(String value) {
            addCriterion("open_evaluation2 >=", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2LessThan(String value) {
            addCriterion("open_evaluation2 <", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2LessThanOrEqualTo(String value) {
            addCriterion("open_evaluation2 <=", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2Like(String value) {
            addCriterion("open_evaluation2 like", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2NotLike(String value) {
            addCriterion("open_evaluation2 not like", value, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2In(List<String> values) {
            addCriterion("open_evaluation2 in", values, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2NotIn(List<String> values) {
            addCriterion("open_evaluation2 not in", values, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2Between(String value1, String value2) {
            addCriterion("open_evaluation2 between", value1, value2, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation2NotBetween(String value1, String value2) {
            addCriterion("open_evaluation2 not between", value1, value2, "openEvaluation2");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3IsNull() {
            addCriterion("open_evaluation3 is null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3IsNotNull() {
            addCriterion("open_evaluation3 is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3EqualTo(String value) {
            addCriterion("open_evaluation3 =", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3NotEqualTo(String value) {
            addCriterion("open_evaluation3 <>", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3GreaterThan(String value) {
            addCriterion("open_evaluation3 >", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3GreaterThanOrEqualTo(String value) {
            addCriterion("open_evaluation3 >=", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3LessThan(String value) {
            addCriterion("open_evaluation3 <", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3LessThanOrEqualTo(String value) {
            addCriterion("open_evaluation3 <=", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3Like(String value) {
            addCriterion("open_evaluation3 like", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3NotLike(String value) {
            addCriterion("open_evaluation3 not like", value, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3In(List<String> values) {
            addCriterion("open_evaluation3 in", values, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3NotIn(List<String> values) {
            addCriterion("open_evaluation3 not in", values, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3Between(String value1, String value2) {
            addCriterion("open_evaluation3 between", value1, value2, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andOpenEvaluation3NotBetween(String value1, String value2) {
            addCriterion("open_evaluation3 not between", value1, value2, "openEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1IsNull() {
            addCriterion("middle_evaluation1 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1IsNotNull() {
            addCriterion("middle_evaluation1 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1EqualTo(String value) {
            addCriterion("middle_evaluation1 =", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1NotEqualTo(String value) {
            addCriterion("middle_evaluation1 <>", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1GreaterThan(String value) {
            addCriterion("middle_evaluation1 >", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1GreaterThanOrEqualTo(String value) {
            addCriterion("middle_evaluation1 >=", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1LessThan(String value) {
            addCriterion("middle_evaluation1 <", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1LessThanOrEqualTo(String value) {
            addCriterion("middle_evaluation1 <=", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1Like(String value) {
            addCriterion("middle_evaluation1 like", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1NotLike(String value) {
            addCriterion("middle_evaluation1 not like", value, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1In(List<String> values) {
            addCriterion("middle_evaluation1 in", values, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1NotIn(List<String> values) {
            addCriterion("middle_evaluation1 not in", values, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1Between(String value1, String value2) {
            addCriterion("middle_evaluation1 between", value1, value2, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation1NotBetween(String value1, String value2) {
            addCriterion("middle_evaluation1 not between", value1, value2, "middleEvaluation1");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2IsNull() {
            addCriterion("middle_evaluation2 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2IsNotNull() {
            addCriterion("middle_evaluation2 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2EqualTo(String value) {
            addCriterion("middle_evaluation2 =", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2NotEqualTo(String value) {
            addCriterion("middle_evaluation2 <>", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2GreaterThan(String value) {
            addCriterion("middle_evaluation2 >", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2GreaterThanOrEqualTo(String value) {
            addCriterion("middle_evaluation2 >=", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2LessThan(String value) {
            addCriterion("middle_evaluation2 <", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2LessThanOrEqualTo(String value) {
            addCriterion("middle_evaluation2 <=", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2Like(String value) {
            addCriterion("middle_evaluation2 like", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2NotLike(String value) {
            addCriterion("middle_evaluation2 not like", value, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2In(List<String> values) {
            addCriterion("middle_evaluation2 in", values, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2NotIn(List<String> values) {
            addCriterion("middle_evaluation2 not in", values, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2Between(String value1, String value2) {
            addCriterion("middle_evaluation2 between", value1, value2, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation2NotBetween(String value1, String value2) {
            addCriterion("middle_evaluation2 not between", value1, value2, "middleEvaluation2");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3IsNull() {
            addCriterion("middle_evaluation3 is null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3IsNotNull() {
            addCriterion("middle_evaluation3 is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3EqualTo(String value) {
            addCriterion("middle_evaluation3 =", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3NotEqualTo(String value) {
            addCriterion("middle_evaluation3 <>", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3GreaterThan(String value) {
            addCriterion("middle_evaluation3 >", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3GreaterThanOrEqualTo(String value) {
            addCriterion("middle_evaluation3 >=", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3LessThan(String value) {
            addCriterion("middle_evaluation3 <", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3LessThanOrEqualTo(String value) {
            addCriterion("middle_evaluation3 <=", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3Like(String value) {
            addCriterion("middle_evaluation3 like", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3NotLike(String value) {
            addCriterion("middle_evaluation3 not like", value, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3In(List<String> values) {
            addCriterion("middle_evaluation3 in", values, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3NotIn(List<String> values) {
            addCriterion("middle_evaluation3 not in", values, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3Between(String value1, String value2) {
            addCriterion("middle_evaluation3 between", value1, value2, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andMiddleEvaluation3NotBetween(String value1, String value2) {
            addCriterion("middle_evaluation3 not between", value1, value2, "middleEvaluation3");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationIsNull() {
            addCriterion("teacher_paper_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationIsNotNull() {
            addCriterion("teacher_paper_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationEqualTo(String value) {
            addCriterion("teacher_paper_evaluation =", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationNotEqualTo(String value) {
            addCriterion("teacher_paper_evaluation <>", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationGreaterThan(String value) {
            addCriterion("teacher_paper_evaluation >", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_paper_evaluation >=", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationLessThan(String value) {
            addCriterion("teacher_paper_evaluation <", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationLessThanOrEqualTo(String value) {
            addCriterion("teacher_paper_evaluation <=", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationLike(String value) {
            addCriterion("teacher_paper_evaluation like", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationNotLike(String value) {
            addCriterion("teacher_paper_evaluation not like", value, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationIn(List<String> values) {
            addCriterion("teacher_paper_evaluation in", values, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationNotIn(List<String> values) {
            addCriterion("teacher_paper_evaluation not in", values, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationBetween(String value1, String value2) {
            addCriterion("teacher_paper_evaluation between", value1, value2, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andTeacherPaperEvaluationNotBetween(String value1, String value2) {
            addCriterion("teacher_paper_evaluation not between", value1, value2, "teacherPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationIsNull() {
            addCriterion("cross_paper_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationIsNotNull() {
            addCriterion("cross_paper_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationEqualTo(String value) {
            addCriterion("cross_paper_evaluation =", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationNotEqualTo(String value) {
            addCriterion("cross_paper_evaluation <>", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationGreaterThan(String value) {
            addCriterion("cross_paper_evaluation >", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationGreaterThanOrEqualTo(String value) {
            addCriterion("cross_paper_evaluation >=", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationLessThan(String value) {
            addCriterion("cross_paper_evaluation <", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationLessThanOrEqualTo(String value) {
            addCriterion("cross_paper_evaluation <=", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationLike(String value) {
            addCriterion("cross_paper_evaluation like", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationNotLike(String value) {
            addCriterion("cross_paper_evaluation not like", value, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationIn(List<String> values) {
            addCriterion("cross_paper_evaluation in", values, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationNotIn(List<String> values) {
            addCriterion("cross_paper_evaluation not in", values, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationBetween(String value1, String value2) {
            addCriterion("cross_paper_evaluation between", value1, value2, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andCrossPaperEvaluationNotBetween(String value1, String value2) {
            addCriterion("cross_paper_evaluation not between", value1, value2, "crossPaperEvaluation");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1IsNull() {
            addCriterion("conclusion_evaluation1 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1IsNotNull() {
            addCriterion("conclusion_evaluation1 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1EqualTo(String value) {
            addCriterion("conclusion_evaluation1 =", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1NotEqualTo(String value) {
            addCriterion("conclusion_evaluation1 <>", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1GreaterThan(String value) {
            addCriterion("conclusion_evaluation1 >", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1GreaterThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation1 >=", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1LessThan(String value) {
            addCriterion("conclusion_evaluation1 <", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1LessThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation1 <=", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1Like(String value) {
            addCriterion("conclusion_evaluation1 like", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1NotLike(String value) {
            addCriterion("conclusion_evaluation1 not like", value, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1In(List<String> values) {
            addCriterion("conclusion_evaluation1 in", values, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1NotIn(List<String> values) {
            addCriterion("conclusion_evaluation1 not in", values, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1Between(String value1, String value2) {
            addCriterion("conclusion_evaluation1 between", value1, value2, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation1NotBetween(String value1, String value2) {
            addCriterion("conclusion_evaluation1 not between", value1, value2, "conclusionEvaluation1");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2IsNull() {
            addCriterion("conclusion_evaluation2 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2IsNotNull() {
            addCriterion("conclusion_evaluation2 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2EqualTo(String value) {
            addCriterion("conclusion_evaluation2 =", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2NotEqualTo(String value) {
            addCriterion("conclusion_evaluation2 <>", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2GreaterThan(String value) {
            addCriterion("conclusion_evaluation2 >", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2GreaterThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation2 >=", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2LessThan(String value) {
            addCriterion("conclusion_evaluation2 <", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2LessThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation2 <=", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2Like(String value) {
            addCriterion("conclusion_evaluation2 like", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2NotLike(String value) {
            addCriterion("conclusion_evaluation2 not like", value, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2In(List<String> values) {
            addCriterion("conclusion_evaluation2 in", values, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2NotIn(List<String> values) {
            addCriterion("conclusion_evaluation2 not in", values, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2Between(String value1, String value2) {
            addCriterion("conclusion_evaluation2 between", value1, value2, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation2NotBetween(String value1, String value2) {
            addCriterion("conclusion_evaluation2 not between", value1, value2, "conclusionEvaluation2");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3IsNull() {
            addCriterion("conclusion_evaluation3 is null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3IsNotNull() {
            addCriterion("conclusion_evaluation3 is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3EqualTo(String value) {
            addCriterion("conclusion_evaluation3 =", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3NotEqualTo(String value) {
            addCriterion("conclusion_evaluation3 <>", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3GreaterThan(String value) {
            addCriterion("conclusion_evaluation3 >", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3GreaterThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation3 >=", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3LessThan(String value) {
            addCriterion("conclusion_evaluation3 <", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3LessThanOrEqualTo(String value) {
            addCriterion("conclusion_evaluation3 <=", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3Like(String value) {
            addCriterion("conclusion_evaluation3 like", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3NotLike(String value) {
            addCriterion("conclusion_evaluation3 not like", value, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3In(List<String> values) {
            addCriterion("conclusion_evaluation3 in", values, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3NotIn(List<String> values) {
            addCriterion("conclusion_evaluation3 not in", values, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3Between(String value1, String value2) {
            addCriterion("conclusion_evaluation3 between", value1, value2, "conclusionEvaluation3");
            return (Criteria) this;
        }

        public Criteria andConclusionEvaluation3NotBetween(String value1, String value2) {
            addCriterion("conclusion_evaluation3 not between", value1, value2, "conclusionEvaluation3");
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