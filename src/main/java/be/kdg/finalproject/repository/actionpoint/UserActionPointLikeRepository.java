package be.kdg.finalproject.repository.actionpoint;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActionPointLikeRepository extends CrudRepository<UserActionPointLike, Long> {
	@Query ("select u from USER_ACTION_POINT_LIKE u where u.liker = ?1 and u.actionPoint = ?2")
	Optional<UserActionPointLike> findByLikerAndActionPoint(User user, ActionPoint actionPoint);

	@Override
	@Modifying
	@Query ("DELETE FROM USER_ACTION_POINT_LIKE u WHERE u.id = ?1")
	void deleteById(Long id);
}
