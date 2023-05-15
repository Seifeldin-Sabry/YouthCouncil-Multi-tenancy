package be.kdg.finalproject.repository.membership;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query ("SELECT u FROM APP_USERS u WHERE upper(u.username) = upper(:usernameOrEmail) OR upper(u.email) = upper(:usernameOrEmail)")
	Optional<User> findByUsernameOrEmail(@Param ("usernameOrEmail") String usernameOrEmail);

	@Query ("SELECT u FROM APP_USERS u left join fetch u.memberships WHERE upper(u.username) = upper(:usernameOrEmail) OR upper(u.email) = upper(:usernameOrEmail)")
	Optional<User> findByUsernameOrEmailWithMemberShips(@Param ("usernameOrEmail") String usernameOrEmail);

	@Query ("""
SELECT u.likedIdeas
FROM APP_USERS u
WHERE u.id=:userID
""")
	Set<UserIdeaLike> findLikedIdeasByUserId(long userID);

	boolean existsByUsernameIgnoreCase(String username);

	boolean existsByEmailIgnoreCase(String email);

	List<User> findUsersByRole(Role role);

	@Transactional
	@Modifying
	@Query ("update APP_USERS a set a.username = ?1, a.firstName = ?2, a.surname = ?3, a.email = ?4 where a.id = ?5")
	void updateUserById(String username, String firstName, String surname, String email, Long id);


}
