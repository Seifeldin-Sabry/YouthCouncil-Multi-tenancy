package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	List<Municipality> findByNameContainingIgnoreCase(String name);

	@Query ("SELECT m FROM MUNICIPALITIES m JOIN m.postcodes p WHERE p.postcode = :code")
	Optional<Municipality> findByPostcode(@Param ("code") int code);

	@Query ("SELECT m FROM MUNICIPALITIES m WHERE UPPER(m.name) LIKE CONCAT('%', UPPER(:namePart), '%')")
	List<Municipality> findByNamePart(@Param ("namePart") String namePart);

	@Query ("SELECT m FROM MUNICIPALITIES m JOIN m.postcodes p WHERE CAST(p.postcode as STRING) LIKE CONCAT('%', :codePart, '%')")
	List<Municipality> findByPostcodePart(@Param ("codePart") String codePart);
}
