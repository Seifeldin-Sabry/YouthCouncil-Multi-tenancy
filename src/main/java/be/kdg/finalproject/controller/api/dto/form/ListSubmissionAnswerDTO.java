package be.kdg.finalproject.controller.api.dto.form;

import java.util.List;

public class ListSubmissionAnswerDTO {
    private long formSubmissionId;
    private long questionId;
    private List<String> userAnswerChoices;

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

    public List<String> getUserAnswerChoices() {
        return userAnswerChoices;
    }

    public void setUserAnswerChoices(List<String> userAnswerChoices) {
        this.userAnswerChoices = userAnswerChoices;
    }
}
