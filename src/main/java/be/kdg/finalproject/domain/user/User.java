package be.kdg.finalproject.domain.user;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity (name = "APP_USERS")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_id", nullable = false)
	private Long id;

	@Column (name = "username")
	private String username;

	@Column (name = "first_name")
	private String firstName;

	@Column (name = "surname")
	private String surname;

	@Column (name = "email", nullable = false)
	private String email;

	@Column (name = "password")
	private String password;

	@Enumerated (EnumType.STRING)
	@Column (name = "provider", nullable = false, length = 10)
	private Provider provider;

	@Column (name = "role", nullable = false)
	@Enumerated (EnumType.STRING)
	private Role role;

	// for youth council members
	@ManyToOne
	@JoinColumn (name = "municipality_id")
	private Municipality municipality;

	@OneToMany (mappedBy = "creator")
	@ToString.Exclude
	private Set<Idea> ideas = new HashSet<>();

	@OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<Membership> memberships = new HashSet<>();

	@OneToMany (mappedBy = "follower", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<UserActionPointFollow> followedActionPoints = new HashSet<>();

	@OneToMany (mappedBy = "liker", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<UserActionPointLike> likedActionPoints = new HashSet<>();

	@OneToMany (mappedBy = "liker", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<UserIdeaLike> likedIdeas = new HashSet<>();

	public User(String firstName, String surname, String username, String email, String password, Role role, Provider provider) {
		this.firstName = firstName;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.provider = provider;
	}

	public void addMembership(Membership membership) {
		getMemberships().add(membership);
	}
}

