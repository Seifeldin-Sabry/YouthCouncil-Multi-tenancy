package be.kdg.finalproject.domain.interaction.follow;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "USER_ACTION_POINT_FOLLOW")
@Getter
@Setter
@NoArgsConstructor
public class UserActionPointFollow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User follower;

	@ManyToOne
	@JoinColumn(name = "action_point_id")
	private ActionPoint actionPoint;

	public UserActionPointFollow(User follower, ActionPoint actionPoint) {
		this.follower = follower;
		this.actionPoint = actionPoint;
	}

}
