package be.kdg.finalproject.domain.form;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue ("RADIO_QUESTION")
public class RadioQuestion extends Question {
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> choices = new ArrayList<>();

	public RadioQuestion(String questionText, Integer order) {
		super(questionText, order);
		this.setQuestionType(QuestionType.RADIO_QUESTION);
	}
	public RadioQuestion(String questionText, Integer order ,List<String> choices) {
		super(questionText, order);
		this.choices = choices;
		this.setQuestionType(QuestionType.RADIO_QUESTION);
	}

	public RadioQuestion(String questionText, boolean isRequired, Integer order, List<String> choices) {
		super(questionText, isRequired, order);
		this.choices = choices;
		this.setQuestionType(QuestionType.RADIO_QUESTION);
	}

	public RadioQuestion(String questionText, boolean isRequired, Integer questionOrder, Form form, List<String> choices) {
		super(questionText, isRequired, questionOrder, form);
		this.choices = choices;
		this.setQuestionType(QuestionType.RADIO_QUESTION);
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
}
