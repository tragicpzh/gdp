package org.t01.gdp.domain;

public class ReviewSearchCase {
    private Long createTeacherId;
    private Long crossReviewTeacherId;
    private Long paperReviewTeacherId;
    private Long openReviewTeacherId;
    private Long middleReviewTeacherId;
    private Long conclusionReviewTeacherId;

    public Long getPaperReviewTeacherId() {
        return paperReviewTeacherId;
    }

    public void setPaperReviewTeacherId(Long paperReviewTeacherId) {
        this.paperReviewTeacherId = paperReviewTeacherId;
    }

    public Long getCreateTeacherId() {
        return createTeacherId;
    }

    public void setCreateTeacherId(Long createTeacherId) {
        this.createTeacherId = createTeacherId;
    }

    public Long getCrossReviewTeacherId() {
        return crossReviewTeacherId;
    }

    public void setCrossReviewTeacherId(Long crossReviewTeacherId) {
        this.crossReviewTeacherId = crossReviewTeacherId;
    }

    public Long getOpenReviewTeacherId() {
        return openReviewTeacherId;
    }

    public void setOpenReviewTeacherId(Long openReviewTeacherId) {
        this.openReviewTeacherId = openReviewTeacherId;
    }

    public Long getMiddleReviewTeacherId() {
        return middleReviewTeacherId;
    }

    public void setMiddleReviewTeacherId(Long middleReviewTeacherId) {
        this.middleReviewTeacherId = middleReviewTeacherId;
    }

    public Long getConclusionReviewTeacherId() {
        return conclusionReviewTeacherId;
    }

    public void setConclusionReviewTeacherId(Long conclusionReviewTeacherId) {
        this.conclusionReviewTeacherId = conclusionReviewTeacherId;
    }
}
