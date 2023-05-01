package be.kdg.finalproject.repository.page;

import be.kdg.finalproject.domain.page.PageTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PageTemplateRepository extends CrudRepository<PageTemplate, Long> {
	Optional<PageTemplate> findByUuid(UUID uuid);
}
