package be.kdg.finalproject.domain.platform;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.news.NewsItem;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Column (name = "name", nullable = false)
	private String name;

	private String logo;

	@ElementCollection
	private List<String> socialMediaLinks = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private Set<User> members = new HashSet<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private List<ActionPoint> actionPoints = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private Set<Membership> memberships = new HashSet<>();

	@OneToMany
	@JoinColumn (name = "municipality_id")
	@ToString.Exclude
	private Set<PostCode> postcodes = new HashSet<>();

	@OneToMany
	@ToString.Exclude
	private List<InformativePage> infoInformativePages = new ArrayList<>();

	@OneToMany
	@ToString.Exclude
	private List<NewsItem> newsItems = new ArrayList<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private List<CalendarActivity> calendarActivities = new ArrayList<>();


	public Municipality(String name) {
		this.name = name;
	}

	public Municipality(String name, Set<PostCode> postcodes) {
		this.name = name;
		this.postcodes = postcodes;
	}

	public void addMembership(Membership membership) {
		getMemberships().add(membership);
	}
}
