package be.kdg.finalproject.domain.user;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity (name = "MEMBERSHIPS")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table (uniqueConstraints = @UniqueConstraint (columnNames = {"user_id", "municipality_id"}))
public class Membership {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "membership_id", nullable = false)
	private Long id;

	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn (name = "municipality_id")
	private Municipality municipality;

	@Enumerated (EnumType.STRING)
	private Role role;

	public Membership(User user, Municipality municipality, Role role) {
		this.user = user;
		this.municipality = municipality;
		this.role = role;
	}
}
