package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
