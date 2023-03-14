package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query ("SELECT u FROM APP_USERS u WHERE upper(u.username) = upper(?1) OR upper(u.email) = upper(?1)")
	Optional<User> findByUsernameOrEmail(String emailOrUsername);

	Optional<User> findByUsernameIgnoreCase(String username);

	Optional<User> findByEmailIgnoreCase(String email);

	boolean existsByUsernameIgnoreCase(String username);

	boolean existsByEmailIgnoreCase(String email);
}
