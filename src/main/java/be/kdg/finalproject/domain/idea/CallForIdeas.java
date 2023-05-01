package be.kdg.finalproject.domain.idea;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.theme.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity (name = "CALL_FOR_IDEAS")
@Getter
@Setter
@NoArgsConstructor
public class CallForIdeas {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "call_for_idea_id", nullable = false)
	private Long id;

	private UUID uuid = UUID.randomUUID();

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "description")
	private String description;

	@ManyToOne
	private Municipality municipality;

	@OneToMany (mappedBy = "callForIdeasId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Idea> ideas = new HashSet<>();

	@Column (name = "is_active", columnDefinition = "boolean default false")
	private boolean isActive;

	@Column (name = "date_time_created", nullable = false)
	private Timestamp dateCreated;

	@ManyToOne
	private Theme theme;

	public CallForIdeas(String title, String description) {
		this.title = title;
		this.description = description;
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
	}
}
