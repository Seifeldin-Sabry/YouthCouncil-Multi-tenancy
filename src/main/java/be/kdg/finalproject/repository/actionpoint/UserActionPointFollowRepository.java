package be.kdg.finalproject.repository.actionpoint;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActionPointFollowRepository extends CrudRepository<UserActionPointFollow, Long> {
	@Query ("select u from USER_ACTION_POINT_FOLLOW u where u.follower = ?1 and u.actionPoint = ?2")
	Optional<UserActionPointFollow> findByFollowerAndActionPoint(User user, ActionPoint actionPoint);

	@Override
	@Modifying
	@Query ("DELETE FROM USER_ACTION_POINT_FOLLOW u WHERE u.id = ?1")
	void deleteById(Long id);
}
