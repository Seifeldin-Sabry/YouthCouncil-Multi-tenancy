package be.kdg.finalproject.domain.platform;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity (name = "MUNICIPALITIES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Municipality {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "municipalitiy_id", nullable = false)
	private Long id;

	@Column (name = "name", nullable = false)
	private String name;

	@OneToMany
	@ToString.Exclude
	private Set<User> members = new HashSet<>();

	@OneToMany (cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<ActionPoint> actionPoints = new HashSet<>();

	@OneToMany (mappedBy = "municipality")
	@ToString.Exclude
	private Set<Membership> memberships = new HashSet<>();

	@OneToMany
	@ToString.Exclude
	private Set<PostCode> postcodes = new HashSet<>();

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
