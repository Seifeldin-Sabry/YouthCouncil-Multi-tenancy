package be.kdg.finalproject.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submission_answers")
@Getter
@Setter
@NoArgsConstructor
public class SubmissionAnswer {

	@Id
	@Column (name = "submission_answer_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "form_submission_id")
	private FormSubmission formSubmission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(name = "user_answer_text")
	private String userAnswerText;

	@ElementCollection
	@CollectionTable(name = "submission_answer_choices", joinColumns = @JoinColumn(name = "submission_answer_id"))
	private List<String> userAnswerChoices;

	@Column(name = "user_answer_numeric")
	private Double userAnswerNumeric;

	public SubmissionAnswer(FormSubmission formSubmission, Question question) {
		this.formSubmission = formSubmission;
		this.question = question;
	}
	public void setUserAnswer(Object answer) {
		switch (getQuestionType()) {
			case TEXT_QUESTION, RADIO_QUESTION -> this.userAnswerText = (String) answer;
			case MULTIPLE_CHOICE_QUESTION -> this.userAnswerChoices = (List<String>) answer;
			case NUMBER_QUESTION -> this.userAnswerNumeric = (Double) answer;
		}
	}

	public Object getUserAnswer() {
		return switch (getQuestionType()) {
			case TEXT_QUESTION, RADIO_QUESTION -> this.userAnswerText;
			case MULTIPLE_CHOICE_QUESTION -> this.userAnswerChoices;
			case NUMBER_QUESTION -> this.userAnswerNumeric;
		};
	}
	private QuestionType getQuestionType() {
		return this.question.getQuestionType();
	}
}
