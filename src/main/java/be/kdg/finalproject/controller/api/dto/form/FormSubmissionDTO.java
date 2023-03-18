package be.kdg.finalproject.controller.api.dto.form;

public class FormSubmissionDTO {

    private long formId;

    private long userId;

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
