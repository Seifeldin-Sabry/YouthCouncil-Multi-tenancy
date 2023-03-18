package be.kdg.finalproject.domain.form;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue ("MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceQuestion extends Question {
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> choices = new ArrayList<>();

	public MultipleChoiceQuestion(String questionText, Integer order) {
		super(questionText, order);
		this.setQuestionType(QuestionType.MULTIPLE_CHOICE_QUESTION);
	}

	public MultipleChoiceQuestion(String questionText, boolean isRequired, Integer order, List<String> choices) {
		super(questionText, isRequired, order);
		this.setQuestionType(QuestionType.MULTIPLE_CHOICE_QUESTION);
		this.choices = choices;
	}

	public MultipleChoiceQuestion(String questionText, Integer order, List<String> choices) {
		super(questionText, order);
		this.setQuestionType(QuestionType.MULTIPLE_CHOICE_QUESTION);
		this.choices = choices;
	}

	public MultipleChoiceQuestion(String questionText, boolean isRequired, Integer questionOrder, Form form, List<String> choices) {
		super(questionText, isRequired, questionOrder, form);
		this.choices = choices;
		this.setQuestionType(QuestionType.MULTIPLE_CHOICE_QUESTION);
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
}