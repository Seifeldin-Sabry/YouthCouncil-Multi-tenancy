package be.kdg.finalproject.domain.form;

import be.kdg.finalproject.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "FORM_SUBMISSIONS")
@Table
@Getter
@Setter
@NoArgsConstructor
public class FormSubmission {
	@Id
	@Column (name = "form_submission_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "form_id")
	private Form form;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "formSubmission" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubmissionAnswer> answers = new ArrayList<>();

	public FormSubmission(Form form, User user) {
		this.form = form;
		this.user = user;
	}

}