package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	Optional<Municipality> findByNameIgnoreCase(String name);

	@Query ("SELECT m FROM MUNICIPALITIES m JOIN FETCH m.members JOIN fetch m.postcodes WHERE m.uuid = :uuid")
	Optional<Municipality> findByUuid(UUID uuid);

	List<Municipality> findByNameContainingIgnoreCase(String name);


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


}
