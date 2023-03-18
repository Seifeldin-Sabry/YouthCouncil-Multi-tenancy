package be.kdg.finalproject.repository.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.RadioQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadioQuestionRepository extends JpaRepository<RadioQuestion, Long> {
    List<RadioQuestion> getRadioQuestionByForm(Form form);
}
