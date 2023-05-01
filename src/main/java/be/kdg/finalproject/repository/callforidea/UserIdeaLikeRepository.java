package be.kdg.finalproject.repository.callforidea;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserIdeaLikeRepository extends CrudRepository<UserIdeaLike, Long> {
	UserIdeaLike findByIdeaAndLiker(Idea idea, User liker);
}
