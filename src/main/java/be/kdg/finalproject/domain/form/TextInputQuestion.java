package be.kdg.finalproject.domain.form;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@DiscriminatorValue ("TEXT_QUESTION")
public class TextInputQuestion extends Question {
	public TextInputQuestion(String questionText, Integer order) {
		super(questionText, order);
		this.setQuestionType(QuestionType.TEXT_QUESTION);
	}

	public TextInputQuestion(String questionText, boolean isRequired, Integer order) {
		super(questionText, isRequired, order);
		this.setQuestionType(QuestionType.TEXT_QUESTION);
	}

	public TextInputQuestion(String questionText, boolean isRequired, Integer questionOrder, Form form) {
		super(questionText, isRequired, questionOrder, form);
		this.setQuestionType(QuestionType.TEXT_QUESTION);
	}
}
