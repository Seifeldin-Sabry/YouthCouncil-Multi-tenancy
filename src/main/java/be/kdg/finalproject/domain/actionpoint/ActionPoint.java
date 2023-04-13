package be.kdg.finalproject.domain.actionpoint;

import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.theme.SubTheme;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity (name = "ACTION_POINTS")
@Getter
@Setter
@ToString
public class ActionPoint {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "action_point_id", nullable = false)
	private Long id;

	private UUID uuid = UUID.randomUUID();

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "description")
	private String description;

	@Column (name = "date_created", nullable = false)
	private LocalDate dateCreated;

	@OneToMany (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "action_point_id")
	@ToString.Exclude
	private Set<ActionPointProposal> actionPointProposals = new HashSet<>();


	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "sub_theme_id", nullable = false)
	private SubTheme subTheme;

	@ElementCollection (fetch = FetchType.EAGER)
	private Set<String> images = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserActionPointFollow> followers = new HashSet<>();

	@OneToMany (mappedBy = "actionPoint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserActionPointLike> likers = new HashSet<>();

	@Column (name = "follow_count", nullable = false, columnDefinition = "int default 0")
	private int followCount;

	@Transient
	private boolean followed;

	@Transient
	private boolean liked;

	@Column (name = "like_count", nullable = false, columnDefinition = "int default 0")
	private int likeCount;

	@JoinColumn (name = "municipalitiy_id", nullable = false, updatable = false, insertable = false)
	private Long municipalityId;

	public ActionPoint(String title, String description) {
		this.title = title;
		this.description = description;
		this.dateCreated = LocalDate.now();
	}

	public ActionPoint() {
		this.dateCreated = LocalDate.now();
	}

	public void addFollower() {
		followCount++;
	}

	public void removeFollower() {
		followCount--;
	}

	public void addLiker() {
		likeCount++;
	}

	public void removeLiker() {
		likeCount--;
	}

	public void addProposal(String actionPointProposal) {
		this.actionPointProposals.add(new ActionPointProposal(actionPointProposal));
	}
}
