package be.kdg.finalproject.domain.interaction.like;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity (name = "USER_IDEA_LIKE")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (uniqueConstraints = @UniqueConstraint (columnNames = {"user_id", "idea_id"}))
public class UserIdeaLike {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "user_action_point_like_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User liker;

	@ManyToOne
	@JoinColumn (name = "idea_id")
	private Idea idea;


	public UserIdeaLike(User liker, Idea idea) {
		this.liker = liker;
		this.idea = idea;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserIdeaLike that)) return false;

		if (!liker.equals(that.liker)) return false;
		return idea.equals(that.idea);
	}

	@Override
	public int hashCode() {
		int result = liker.hashCode();
		result = 31 * result + idea.hashCode();
		return result;
	}
}
