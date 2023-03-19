package be.kdg.finalproject.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity (name = "questions")
@Table (name = "questions")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorColumn (name = "question_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Question {
	@Id
	@Column (name = "question_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_required", nullable = false, columnDefinition = "boolean default false")
	private boolean isRequired;

	@Column(name = "question_text", nullable = false)
	private String questionText;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "form_id")
	private Form form;


	@Enumerated (EnumType.STRING)
	@Column (name = "question_type", insertable = false, updatable = false)
	private QuestionType questionType;

	@Column(name = "question_order", nullable = false)
	private Integer questionOrder;

	public Question(String questionText, Integer questionOrder) {
		this(questionText, false, questionOrder);
	}
	public Question(String questionText, boolean isRequired, Integer questionOrder) {
		this.questionText = questionText;
		this.isRequired = isRequired;
		this.questionOrder = questionOrder;
	}

	public Question(String questionText, boolean isRequired, Integer questionOrder, Form form) {
		this.isRequired = isRequired;
		this.questionText = questionText;
		this.form = form;
		this.questionOrder = questionOrder;
	}
}
