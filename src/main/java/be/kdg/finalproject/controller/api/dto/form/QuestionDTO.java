package be.kdg.finalproject.controller.api.dto.form;

import be.kdg.finalproject.domain.form.QuestionType;

import java.util.Arrays;
import java.util.List;

public class QuestionDTO {
	private long questionId;
	private String questionText;
	private QuestionType questionType;
	private boolean isRequired;
	private Integer order;
	private List<String> choices;
	private long formId;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		if(choices!=null){
			String[] stringSplit = choices
					.replace('[', ' ')
					.replace(']', ' ')
					.trim()
					.split(", ");
			this.choices = Arrays.asList(stringSplit);
		}

	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean required) {
		isRequired = required;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}
}
