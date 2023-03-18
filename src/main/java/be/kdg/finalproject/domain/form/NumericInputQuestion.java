package be.kdg.finalproject.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("NUMBER_QUESTION")
public class NumericInputQuestion extends Question{
	public NumericInputQuestion(String questionText, Integer order) {
		super(questionText, order);
		this.setQuestionType(QuestionType.NUMBER_QUESTION);
	}

	public NumericInputQuestion(String questionText, boolean isRequired, Integer order) {
		super(questionText, isRequired, order);
		this.setQuestionType(QuestionType.NUMBER_QUESTION);
	}

	public NumericInputQuestion(String questionText, boolean isRequired, Integer questionOrder, Form form) {
		super(questionText, isRequired, questionOrder, form);
		this.setQuestionType(QuestionType.NUMBER_QUESTION);
	}
}
