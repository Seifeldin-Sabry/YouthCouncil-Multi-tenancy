package be.kdg.finalproject.domain.form;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table (name = "forms")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Form {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "form_id", nullable = false)
	private Long id;

	@Column (name = "form_name", nullable = false)
	private String formName;

	@Column (name = "form_description")
	private String formDescription;

	@Column(name = "date_created", nullable = false)
	private LocalDate dateCreated;

	@Column(name = "form_url", nullable = false)
	private String formUrl;

	public Form(String formName, String formDescription, String formUrl) {
		this.formName = formName;
		this.formDescription = formDescription;
		this.dateCreated = LocalDate.now();
		this.formUrl = formUrl;
	}
}
