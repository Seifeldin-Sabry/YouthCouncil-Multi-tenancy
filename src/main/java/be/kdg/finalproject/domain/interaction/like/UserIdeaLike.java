package be.kdg.finalproject.domain.interaction.like;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "user_idea_like")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserIdeaLike {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_action_point_like_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User liker;

	@ManyToOne
	@JoinColumn(name = "idea_id")
	private Idea idea;

	@Enumerated(EnumType.STRING)
	@Column(name = "emoji")
	private Emoji emoji;

	public UserIdeaLike(User liker, Idea idea, Emoji emoji) {
		this.liker = liker;
		this.idea = idea;
		this.emoji = emoji;
	}
}
