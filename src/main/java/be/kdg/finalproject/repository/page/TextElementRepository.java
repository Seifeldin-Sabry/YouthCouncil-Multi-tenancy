package be.kdg.finalproject.repository.page;

import be.kdg.finalproject.domain.page.elements.PageElement;
import be.kdg.finalproject.domain.page.elements.TextElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TextElementRepository extends CrudRepository<TextElement, Long> {
	@Modifying
	@Query("""
UPDATE TextElement e SET e.pageOrder=?1 WHERE e.id=?2
""")
	void updateOrderById(int order, long id);
}
