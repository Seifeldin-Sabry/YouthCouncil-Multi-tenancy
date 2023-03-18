package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {
    List<MultipleChoiceQuestion> getMultipleChoiceQuestionByForm(Form form);
}
