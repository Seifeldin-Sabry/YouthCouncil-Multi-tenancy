package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.TextInputQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextInputQuestionRepository extends JpaRepository<TextInputQuestion, Long> {
    List<TextInputQuestion> getTextInputQuestionByForm(Form form);
}
