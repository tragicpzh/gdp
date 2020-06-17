package org.t01.gdp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubjectExample() {
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

        public Criteria andCreateTeacherIdIsNull() {
            addCriterion("create_teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdIsNotNull() {
            addCriterion("create_teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdEqualTo(String value) {
            addCriterion("create_teacher_id =", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdNotEqualTo(String value) {
            addCriterion("create_teacher_id <>", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdGreaterThan(String value) {
            addCriterion("create_teacher_id >", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_teacher_id >=", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdLessThan(String value) {
            addCriterion("create_teacher_id <", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdLessThanOrEqualTo(String value) {
            addCriterion("create_teacher_id <=", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdLike(String value) {
            addCriterion("create_teacher_id like", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdNotLike(String value) {
            addCriterion("create_teacher_id not like", value, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdIn(List<String> values) {
            addCriterion("create_teacher_id in", values, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdNotIn(List<String> values) {
            addCriterion("create_teacher_id not in", values, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdBetween(String value1, String value2) {
            addCriterion("create_teacher_id between", value1, value2, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCreateTeacherIdNotBetween(String value1, String value2) {
            addCriterion("create_teacher_id not between", value1, value2, "createTeacherId");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherIsNull() {
            addCriterion("cross_review_teacher is null");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherIsNotNull() {
            addCriterion("cross_review_teacher is not null");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherEqualTo(String value) {
            addCriterion("cross_review_teacher =", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherNotEqualTo(String value) {
            addCriterion("cross_review_teacher <>", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherGreaterThan(String value) {
            addCriterion("cross_review_teacher >", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("cross_review_teacher >=", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherLessThan(String value) {
            addCriterion("cross_review_teacher <", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherLessThanOrEqualTo(String value) {
            addCriterion("cross_review_teacher <=", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherLike(String value) {
            addCriterion("cross_review_teacher like", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherNotLike(String value) {
            addCriterion("cross_review_teacher not like", value, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherIn(List<String> values) {
            addCriterion("cross_review_teacher in", values, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherNotIn(List<String> values) {
            addCriterion("cross_review_teacher not in", values, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherBetween(String value1, String value2) {
            addCriterion("cross_review_teacher between", value1, value2, "crossReviewTeacher");
            return (Criteria) this;
        }

        public Criteria andCrossReviewTeacherNotBetween(String value1, String value2) {
            addCriterion("cross_review_teacher not between", value1, value2, "crossReviewTeacher");
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

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(String value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(String value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(String value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(String value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(String value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLike(String value) {
            addCriterion("direction like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotLike(String value) {
            addCriterion("direction not like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<String> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<String> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(String value1, String value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(String value1, String value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDifficultyIsNull() {
            addCriterion("difficulty is null");
            return (Criteria) this;
        }

        public Criteria andDifficultyIsNotNull() {
            addCriterion("difficulty is not null");
            return (Criteria) this;
        }

        public Criteria andDifficultyEqualTo(BigDecimal value) {
            addCriterion("difficulty =", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotEqualTo(BigDecimal value) {
            addCriterion("difficulty <>", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyGreaterThan(BigDecimal value) {
            addCriterion("difficulty >", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("difficulty >=", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyLessThan(BigDecimal value) {
            addCriterion("difficulty <", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("difficulty <=", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyIn(List<BigDecimal> values) {
            addCriterion("difficulty in", values, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotIn(List<BigDecimal> values) {
            addCriterion("difficulty not in", values, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("difficulty between", value1, value2, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("difficulty not between", value1, value2, "difficulty");
            return (Criteria) this;
        }

        public Criteria andTechnologyIsNull() {
            addCriterion("technology is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyIsNotNull() {
            addCriterion("technology is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyEqualTo(String value) {
            addCriterion("technology =", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotEqualTo(String value) {
            addCriterion("technology <>", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyGreaterThan(String value) {
            addCriterion("technology >", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyGreaterThanOrEqualTo(String value) {
            addCriterion("technology >=", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLessThan(String value) {
            addCriterion("technology <", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLessThanOrEqualTo(String value) {
            addCriterion("technology <=", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyLike(String value) {
            addCriterion("technology like", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotLike(String value) {
            addCriterion("technology not like", value, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyIn(List<String> values) {
            addCriterion("technology in", values, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotIn(List<String> values) {
            addCriterion("technology not in", values, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyBetween(String value1, String value2) {
            addCriterion("technology between", value1, value2, "technology");
            return (Criteria) this;
        }

        public Criteria andTechnologyNotBetween(String value1, String value2) {
            addCriterion("technology not between", value1, value2, "technology");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNull() {
            addCriterion("describe is null");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNotNull() {
            addCriterion("describe is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeEqualTo(String value) {
            addCriterion("describe =", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotEqualTo(String value) {
            addCriterion("describe <>", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThan(String value) {
            addCriterion("describe >", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("describe >=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThan(String value) {
            addCriterion("describe <", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThanOrEqualTo(String value) {
            addCriterion("describe <=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLike(String value) {
            addCriterion("describe like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotLike(String value) {
            addCriterion("describe not like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeIn(List<String> values) {
            addCriterion("describe in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotIn(List<String> values) {
            addCriterion("describe not in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeBetween(String value1, String value2) {
            addCriterion("describe between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotBetween(String value1, String value2) {
            addCriterion("describe not between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDocumentIsNull() {
            addCriterion("document is null");
            return (Criteria) this;
        }

        public Criteria andDocumentIsNotNull() {
            addCriterion("document is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentEqualTo(String value) {
            addCriterion("document =", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotEqualTo(String value) {
            addCriterion("document <>", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThan(String value) {
            addCriterion("document >", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("document >=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThan(String value) {
            addCriterion("document <", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThanOrEqualTo(String value) {
            addCriterion("document <=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLike(String value) {
            addCriterion("document like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotLike(String value) {
            addCriterion("document not like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentIn(List<String> values) {
            addCriterion("document in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotIn(List<String> values) {
            addCriterion("document not in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentBetween(String value1, String value2) {
            addCriterion("document between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotBetween(String value1, String value2) {
            addCriterion("document not between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1IsNull() {
            addCriterion("review_teacher_id1 is null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1IsNotNull() {
            addCriterion("review_teacher_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1EqualTo(String value) {
            addCriterion("review_teacher_id1 =", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1NotEqualTo(String value) {
            addCriterion("review_teacher_id1 <>", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1GreaterThan(String value) {
            addCriterion("review_teacher_id1 >", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1GreaterThanOrEqualTo(String value) {
            addCriterion("review_teacher_id1 >=", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1LessThan(String value) {
            addCriterion("review_teacher_id1 <", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1LessThanOrEqualTo(String value) {
            addCriterion("review_teacher_id1 <=", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1Like(String value) {
            addCriterion("review_teacher_id1 like", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1NotLike(String value) {
            addCriterion("review_teacher_id1 not like", value, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1In(List<String> values) {
            addCriterion("review_teacher_id1 in", values, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1NotIn(List<String> values) {
            addCriterion("review_teacher_id1 not in", values, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1Between(String value1, String value2) {
            addCriterion("review_teacher_id1 between", value1, value2, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId1NotBetween(String value1, String value2) {
            addCriterion("review_teacher_id1 not between", value1, value2, "reviewTeacherId1");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2IsNull() {
            addCriterion("review_teacher_id2 is null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2IsNotNull() {
            addCriterion("review_teacher_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2EqualTo(String value) {
            addCriterion("review_teacher_id2 =", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2NotEqualTo(String value) {
            addCriterion("review_teacher_id2 <>", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2GreaterThan(String value) {
            addCriterion("review_teacher_id2 >", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2GreaterThanOrEqualTo(String value) {
            addCriterion("review_teacher_id2 >=", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2LessThan(String value) {
            addCriterion("review_teacher_id2 <", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2LessThanOrEqualTo(String value) {
            addCriterion("review_teacher_id2 <=", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2Like(String value) {
            addCriterion("review_teacher_id2 like", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2NotLike(String value) {
            addCriterion("review_teacher_id2 not like", value, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2In(List<String> values) {
            addCriterion("review_teacher_id2 in", values, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2NotIn(List<String> values) {
            addCriterion("review_teacher_id2 not in", values, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2Between(String value1, String value2) {
            addCriterion("review_teacher_id2 between", value1, value2, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId2NotBetween(String value1, String value2) {
            addCriterion("review_teacher_id2 not between", value1, value2, "reviewTeacherId2");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3IsNull() {
            addCriterion("review_teacher_id3 is null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3IsNotNull() {
            addCriterion("review_teacher_id3 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3EqualTo(String value) {
            addCriterion("review_teacher_id3 =", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3NotEqualTo(String value) {
            addCriterion("review_teacher_id3 <>", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3GreaterThan(String value) {
            addCriterion("review_teacher_id3 >", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3GreaterThanOrEqualTo(String value) {
            addCriterion("review_teacher_id3 >=", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3LessThan(String value) {
            addCriterion("review_teacher_id3 <", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3LessThanOrEqualTo(String value) {
            addCriterion("review_teacher_id3 <=", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3Like(String value) {
            addCriterion("review_teacher_id3 like", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3NotLike(String value) {
            addCriterion("review_teacher_id3 not like", value, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3In(List<String> values) {
            addCriterion("review_teacher_id3 in", values, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3NotIn(List<String> values) {
            addCriterion("review_teacher_id3 not in", values, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3Between(String value1, String value2) {
            addCriterion("review_teacher_id3 between", value1, value2, "reviewTeacherId3");
            return (Criteria) this;
        }

        public Criteria andReviewTeacherId3NotBetween(String value1, String value2) {
            addCriterion("review_teacher_id3 not between", value1, value2, "reviewTeacherId3");
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