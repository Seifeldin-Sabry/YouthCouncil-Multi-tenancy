package be.kdg.finalproject.domain.idea;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "CALL_FOR_IDEAS")
@Getter
@Setter
@NoArgsConstructor
public class CallForIdea {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "call_for_idea_id", nullable = false)
	private Long id;

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "description")
	private String description;

	@JoinColumn (name = "municipality_id", nullable = false, updatable = false, insertable = false)
	private Long municipalityId;

	@OneToMany (mappedBy = "callForIdea", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Idea> ideas = new HashSet<>();

	@Column (name = "active", columnDefinition = "boolean default true")
	private boolean active;

	@Column (name = "date_time_created", nullable = false)
	private Timestamp dateCreated;

	public CallForIdea(String title, String description) {
		this.title = title;
		this.description = description;
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
	}
}
