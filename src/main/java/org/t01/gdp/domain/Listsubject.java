package org.t01.gdp.domain;

import java.math.BigDecimal;

public class Listsubject {
    private Long subject_id;
    private String create_teacher;
    private String mayjor;
    private String name;
    private String direction;
    private BigDecimal difficult_min;
    private BigDecimal difficult_max;

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public String getCreate_teacher() {
        return create_teacher;
    }

    public void setCreate_teacher(String create_teacher) {
        this.create_teacher = create_teacher;
    }

    public String getMayjor() {
        return mayjor;
    }

    public void setMayjor(String mayjor) {
        this.mayjor = mayjor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getDifficult_min() {
        return difficult_min;
    }

    public void setDifficult_min(BigDecimal difficult_min) {
        this.difficult_min = difficult_min;
    }

    public BigDecimal getDifficult_max() {
        return difficult_max;
    }

    public void setDifficult_max(BigDecimal difficult_max) {
        this.difficult_max = difficult_max;
    }
}
