package be.kdg.finalproject.domain.user;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.location.Municipality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "councils")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class YouthCouncil {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "council_id", nullable = false)
	private Long id;

	@Column (name = "council_name", nullable = false)
	private String name;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "municipality_id")
	private Municipality municipality;

	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Set<User> members = new HashSet<>();

	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Set<User> moderators = new HashSet<>();

	//	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	//	@ToString.Exclude
	//	private Set<Page> pages = new HashSet<>();

	@OneToMany (cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<ActionPoint> actionPoints = new HashSet<>();

	public YouthCouncil(String name) {
		this.name = name;
	}
}
