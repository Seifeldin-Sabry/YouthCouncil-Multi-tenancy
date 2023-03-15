package be.kdg.finalproject.domain.idea;

import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "IDEAS")
@Getter
@Setter
public class Idea {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "idea_id", nullable = false)
	private Long id;
	@Column (name = "idea_description", nullable = false)
	private String ideaDescription;

	@Column (name = "image", nullable = false)
	private String image;

	@Column (name = "is_flagged", nullable = false)
	private boolean isFlagged;

	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn (name = "theme_id", nullable = false)
	private Theme theme;

	@OneToMany (mappedBy = "idea", cascade = CascadeType.ALL)
	private Set<UserIdeaLike> likers = new HashSet<>();

	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn (name = "user_id", nullable = false)
	private User creator;

	@ManyToOne
	@JoinColumn (name = "call_for_idea_id", nullable = false)
	private CallForIdea callForIdea;

	@Column (name = "date_created", nullable = false)
	private LocalDate dateCreated;

	public Idea(String ideaDescription, String image, Theme theme) {
		this.ideaDescription = ideaDescription;
		this.image = image;
		this.theme = theme;
		this.dateCreated = LocalDate.now();
		this.isFlagged = false;
	}

	public Idea(String ideaDescription, String image, boolean isFlagged, Theme theme) {
		this(ideaDescription, image, theme);
		this.isFlagged = isFlagged;
	}

	public Idea() {
		this.dateCreated = LocalDate.now();
	}
}
