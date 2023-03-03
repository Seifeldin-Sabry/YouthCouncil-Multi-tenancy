package be.kdg.finalproject.domain.actionpoint;

import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.media.Image;
import be.kdg.finalproject.domain.theme.SubTheme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "action_points")
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

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "image_id")
	private Set<Image> images = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL)
	private Set<UserActionPointFollow> followers = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL)
	private Set<UserActionPointLike> likers = new HashSet<>();

	@JoinColumn (name = "youth_council_id", nullable = false, updatable = false, insertable = false)
	private Long youthCouncil;

	@Column (name = "follow_count", nullable = false, columnDefinition = "int default 0")
	private int followCount;

	@Column (name = "like_count", nullable = false, columnDefinition = "int default 0")
	private int likeCount;

	public ActionPoint(String title, String description) {
		this.title = title;
		this.description = description;
		this.dateCreated = LocalDate.now();
		this.status = ActionPointStatus.IN_PROGRESS;
	}
}
