package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.location.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
	List<Municipality> findByNameContainingIgnoreCase(String name);

	@Query ("SELECT m FROM Municipality m JOIN m.postcodes p WHERE p.postcode = :code")
	Optional<Municipality> findByPostcode(@Param ("code") int code);

	@Query ("SELECT m FROM Municipality m WHERE UPPER(m.name) LIKE CONCAT('%', UPPER(:namePart), '%')")
	List<Municipality> findByNamePart(@Param ("namePart") String namePart);

	@Query ("SELECT m FROM Municipality m JOIN m.postcodes p WHERE CAST(p.postcode as STRING) LIKE CONCAT('%', :codePart, '%')")
	List<Municipality> findByPostcodePart(@Param ("codePart") String codePart);
}
