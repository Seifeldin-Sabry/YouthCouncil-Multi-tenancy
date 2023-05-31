package be.kdg.finalproject.repository.page;

import be.kdg.finalproject.domain.page.elements.ListElement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ListElementRepository extends CrudRepository<ListElement, Long> {
	@Modifying
	@Query("""
UPDATE ListElement e SET e.pageOrder=?1 WHERE e.id=?2
""")
	void updateOrderById(int order, long id);
}
