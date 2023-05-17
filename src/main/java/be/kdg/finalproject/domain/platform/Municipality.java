package be.kdg.finalproject.domain.platform;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.news.NewsItem;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.*;

@Entity (name = "MUNICIPALITIES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Municipality {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "municipality_id", nullable = false)
	private Long id;

	@Column (name = "uuid", nullable = true, unique = true)
	private UUID uuid = UUID.randomUUID();

	@Column (name = "name", nullable = false, unique = true)
	private String name;

	@Column (name = "logo")
	private String logo;

	@Column (name = "has_platform")
	private boolean hasPlatform;

	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<SocialMediaLink> socialMediaLinks = new ArrayList<>();

	@Column(name = "election_phase")
	@Enumerated (EnumType.STRING)
	private ElectionPhase electionPhase;

	@OneToMany (cascade = CascadeType.PERSIST)
	@ToString.Exclude
	private Set<User> members = new HashSet<>();

	@OneToMany
	@ToString.Exclude
	private List<ActionPoint> actionPoints = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private Set<Membership> memberships = new HashSet<>();

	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "municipality_id")
	@ToString.Exclude
	private Set<PostCode> postcodes = new HashSet<>();

	@OneToMany
	@ToString.Exclude
	private List<InformativePage> informativePages = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private List<NewsItem> newsItems = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private List<CalendarActivity> calendarActivities = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private List<CallForIdeas> callForIdeas = new ArrayList<>();


	public Municipality(String name) {
		this.name = name;
	}

	public Municipality(String name, boolean hasPlatform) {
		this.name = name;
		this.hasPlatform = hasPlatform;
	}

	public Municipality(String name, Set<PostCode> postcodes) {
		this.name = name;
		this.postcodes = postcodes;
	}

	public void addMember(User user) {
		members.add(user);
	}
}
