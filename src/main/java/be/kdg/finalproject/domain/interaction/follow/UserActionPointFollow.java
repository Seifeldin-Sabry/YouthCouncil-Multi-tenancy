package be.kdg.finalproject.domain.interaction.follow;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity (name = "USER_ACTION_POINT_FOLLOW")
@Getter
@Setter
@NoArgsConstructor
@Table (uniqueConstraints = @UniqueConstraint (columnNames = {"user_id", "action_point_id"}))
public class UserActionPointFollow {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User follower;

	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.DETACH})
	@JoinColumn (name = "action_point_id")
	private ActionPoint actionPoint;

	public UserActionPointFollow(User follower, ActionPoint actionPoint) {
		this.follower = follower;
		this.actionPoint = actionPoint;
	}

}
