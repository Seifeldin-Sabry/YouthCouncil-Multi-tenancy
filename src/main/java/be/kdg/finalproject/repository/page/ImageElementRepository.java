package be.kdg.finalproject.repository.page;

import be.kdg.finalproject.domain.page.elements.ImageElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ImageElementRepository extends CrudRepository<ImageElement, Long> {
	@Modifying
	@Query ("""
UPDATE ImageElement e SET e.pageOrder=?1 WHERE e.id=?2
""")
	void updateOrderById(int order, long id);
}
