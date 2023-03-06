package be.kdg.finalproject.domain.user;

import be.kdg.finalproject.domain.platform.Municipality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "MEMBERSHIPS")
@Getter
@Setter
@NoArgsConstructor
public class Membership {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "membership_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn (name = "municipality_id")
	private Municipality municipality;

	public Membership(User user, Municipality municipality) {
		this.user = user;
		this.municipality = municipality;
	}
}
