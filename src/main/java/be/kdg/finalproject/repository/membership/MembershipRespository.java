package be.kdg.finalproject.repository.membership;

import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MembershipRespository extends CrudRepository<Membership, Long> {
	@Query ("select (count(m) > 0) from MEMBERSHIPS m where m.user.email = ?1 and m.municipality.id = ?2")
	boolean existsByUserEmailAndMunicipalityId(String email, Long id);

	@Query ("select m from MEMBERSHIPS m where m.user.id = ?1 and m.municipality.id = ?2")
	Optional<Membership> findByUser_IdAndMunicipality_Id(Long userId, Long municipalityId);

	@Query ("SELECT m FROM MEMBERSHIPS m join fetch m.user WHERE m.municipality.id = :id AND m.role != 'YOUTH_COUNCIL_ADMINISTRATOR'")
	List<Membership> findByMunicipalityIdWithUsers(@Param ("id") Long id);

	Optional<Membership> findByUser_Memberships_UserAndMunicipality_NameIgnoreCase(User user, String name);

	@Query ("SELECT m FROM MEMBERSHIPS m WHERE m.user = ?1")
	Iterable<Membership> findAllMembershipsByUser(User user);
}
