package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends CrudRepository<Form, Long> {
	boolean existsByTitleIgnoreCase(String title);
//	@Query("SELECT f FROM FORMS f JOIN FETCH f.questions q WHERE f.id = ?1 ORDER BY q.order ASC")
//	Form findByIdWithQuestionsInOrder(Long id);
}
