package be.kdg.finalproject.repository.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	@Transactional
	@Modifying
	@Query ("update MUNICIPALITIES m set m.hasPlatform = ?1 where m.id = ?2")
	void updatePlatformStatus(boolean hasPlatform, Long id);

	Optional<Municipality> findByNameIgnoreCase(String name);

	@Query ("SELECT m FROM MUNICIPALITIES m LEFT JOIN FETCH m.members JOIN fetch m.postcodes WHERE m.uuid = :uuid")
	Optional<Municipality> findByUuidWithMembers(UUID uuid);

	@Query ("SELECT m FROM MUNICIPALITIES m WHERE m.uuid = :uuid")
	Optional<Municipality> findByUuid(UUID uuid);

	List<Municipality> findByNameContainingIgnoreCase(String name);

	@Query ("SELECT distinct m FROM MUNICIPALITIES m left JOIN FETCH m.members WHERE m.hasPlatform = true AND m.id = :id")
	Optional<Municipality> findMunicipalityThatHasPlatformWithMembers(@Param ("id") Long id);


	@Query ("SELECT m FROM MUNICIPALITIES m WHERE UPPER(m.name) LIKE CONCAT('%', UPPER(:namePart), '%')")
	List<Municipality> findByNamePart(@Param ("namePart") String namePart);

	//	@Query ("SELECT m FROM MUNICIPALITIES m JOIN m.postcodes p WHERE CAST(p.postcode as varchar(255)) LIKE CONCAT('%', :codePart, '%')")
	//	List<Municipality> findByPostcodePart(@Param ("codePart") String codePart);


	@Query (value = "SELECT m FROM MUNICIPALITIES m LEFT JOIN FETCH m.members JOIN m.postcodes p WHERE p.postcode = :code")
	Municipality findByPostcode(@Param ("code") int code);

	@Query ("SELECT distinct m FROM MUNICIPALITIES m LEFT JOIN FETCH m.members")
	List<Municipality> findAllMunicipalitiesAndMembers();

	@Override
	Optional<Municipality> findById(Long aLong);


	@Query ("SELECT m FROM MUNICIPALITIES m left join fetch m.socialMediaLinks WHERE m.id = :id")
	Municipality findMunicipalityById(Long id);
}
