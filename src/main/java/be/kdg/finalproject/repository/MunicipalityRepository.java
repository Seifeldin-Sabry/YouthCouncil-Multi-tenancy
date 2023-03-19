package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	List<Municipality> findByNameContainingIgnoreCase(String name);


	@Query ("SELECT m FROM MUNICIPALITIES m WHERE UPPER(m.name) LIKE CONCAT('%', UPPER(:namePart), '%')")
	List<Municipality> findByNamePart(@Param ("namePart") String namePart);

	//	@Query ("SELECT m FROM MUNICIPALITIES m JOIN m.postcodes p WHERE CAST(p.postcode as varchar(255)) LIKE CONCAT('%', :codePart, '%')")
	//	List<Municipality> findByPostcodePart(@Param ("codePart") String codePart);


	@Query (value = "SELECT * FROM MUNICIPALITIES m JOIN POSTCODES p ON m.municipality_id = p.municipality_id WHERE p.postcode = :code", nativeQuery = true)
	Municipality findByPostcode(@Param ("code") int code);
}
