package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.NumericInputQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumericInputQuestionRepository extends JpaRepository<NumericInputQuestion, Long> {
    List<NumericInputQuestion> getNumericInputQuestionByForm(Form form);
}
