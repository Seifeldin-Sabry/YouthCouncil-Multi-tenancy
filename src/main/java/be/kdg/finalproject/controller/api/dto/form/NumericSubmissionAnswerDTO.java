package be.kdg.finalproject.controller.api.dto.form;

public class NumericSubmissionAnswerDTO {
    private long formSubmissionId;
    private long questionId;
    private Double userAnswerNumeric;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getFormSubmissionId() {
        return formSubmissionId;
    }

    public void setFormSubmissionId(long questionId) {
        this.formSubmissionId = questionId;
    }

    public Double getUserAnswerNumeric() {
        return userAnswerNumeric;
    }

    public void setUserAnswerNumeric(Double userAnswerNumeric) {
        this.userAnswerNumeric = userAnswerNumeric;
    }
}
