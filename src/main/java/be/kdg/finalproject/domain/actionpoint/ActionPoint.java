package be.kdg.finalproject.domain.actionpoint;

import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.theme.SubTheme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "ACTION_POINTS")
@NoArgsConstructor
@Getter
@Setter
public class ActionPoint {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "action_point_id", nullable = false)
	private Long id;

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "description")
	private String description;

	@Column (name = "date_created", nullable = false)
	private LocalDate dateCreated;

	@Column (name = "status", nullable = false)
	@Enumerated (EnumType.STRING)
	private ActionPointStatus status;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "sub_theme_id", nullable = false)
	private SubTheme subTheme;

	@ElementCollection
	private Set<String> images = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL)
	private Set<UserActionPointFollow> followers = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL)
	private Set<UserActionPointLike> likers = new HashSet<>();

	@Column (name = "follow_count", nullable = false, columnDefinition = "int default 0")
	private int followCount;

	@Column (name = "like_count", nullable = false, columnDefinition = "int default 0")
	private int likeCount;
	@ManyToOne
	@JoinColumn (name = "municipalitiy_id")
	private Municipality municipality;

	public ActionPoint(String title, String description) {
		this.title = title;
		this.description = description;
		this.dateCreated = LocalDate.now();
		this.status = ActionPointStatus.IN_PROGRESS;
	}
}
