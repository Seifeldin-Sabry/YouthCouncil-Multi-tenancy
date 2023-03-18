package be.kdg.finalproject.domain.form;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity (name = "FORMS")
@Getter
@Setter
@NoArgsConstructor
@Table
public class Form {
	@Id
	@Column (name = "form_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@OneToMany (mappedBy = "form", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Question> questions = new ArrayList<>();

	@OneToMany (mappedBy = "form")
	private List<FormSubmission> formSubmissions = new ArrayList<>();

	public Form(String title) {
		this.title = title;
	}

	public void addQuestion(Question question) {
		questions.add(question);
		question.setForm(this);
	}
}
