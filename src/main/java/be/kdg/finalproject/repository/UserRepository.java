package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query ("SELECT u FROM APP_USERS u WHERE upper(u.username) = upper(:usernameOrEmail) OR upper(u.email) = upper(:usernameOrEmail)")
	Optional<User> findByUsernameOrEmail(@Param ("usernameOrEmail") String usernameOrEmail);

	boolean existsByUsernameIgnoreCase(String username);

	boolean existsByEmailIgnoreCase(String email);
	List<User> findUsersByRole(Role role);
}
