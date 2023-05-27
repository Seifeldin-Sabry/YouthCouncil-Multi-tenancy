package be.kdg.finalproject.repository.page;

import be.kdg.finalproject.domain.page.InformativePage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InformativePageRepository extends CrudRepository<InformativePage, Long> {
	@Query ("select (count(p) > 0) from PAGES p where upper(p.title) = upper(?1) or upper(p.pageName) = upper(?1)")
	boolean existsByTitleIgnoreCaseOrPageNameIgnoreCase(String title);

	@Query (value = "SELECT p FROM PAGES p WHERE p.municipalityId = ?1 AND (upper(p.title) = ?2 OR upper(p.pageName) = ?2) AND p.active = true")
	Optional<InformativePage> findByMunicipalityIdAndPageName(Long municipalityId, String title);

	List<InformativePage> findByMunicipalityId(Long municipalityId);

}
