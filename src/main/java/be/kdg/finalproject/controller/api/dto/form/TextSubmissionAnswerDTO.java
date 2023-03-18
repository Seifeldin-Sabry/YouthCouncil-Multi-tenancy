package be.kdg.finalproject.controller.api.dto.form;

public class TextSubmissionAnswerDTO {
    private long formSubmissionId;
    private long questionId;

    private String userAnswerText;

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

    public String getUserAnswerText() {
        return userAnswerText;
    }

    public void setUserAnswerText(String userAnswerText) {
        this.userAnswerText = userAnswerText;
    }
}
