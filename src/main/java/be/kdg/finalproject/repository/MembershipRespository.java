package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRespository extends CrudRepository<Membership, Long> {

	@Query ("SELECT m FROM MEMBERSHIPS m WHERE m.user = ?1")
	Iterable<Membership> findAllMembershipsByUser(User user);
}
