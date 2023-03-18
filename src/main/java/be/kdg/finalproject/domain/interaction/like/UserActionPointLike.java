package be.kdg.finalproject.domain.interaction.like;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity (name = "USER_ACTION_POINT_LIKE")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (uniqueConstraints = @UniqueConstraint (columnNames = {"user_id", "action_point_id"}))
public class UserActionPointLike {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_action_point_like_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User liker;

	@ManyToOne
	@JoinColumn(name = "action_point_id")
	private ActionPoint actionPoint;

	@Enumerated(EnumType.STRING)
	@Column(name = "emoji")
	private Emoji emoji;

	public UserActionPointLike(User liker, ActionPoint actionPoint, Emoji emoji) {
		this.liker = liker;
		this.actionPoint = actionPoint;
		this.emoji = emoji;
	}
}
