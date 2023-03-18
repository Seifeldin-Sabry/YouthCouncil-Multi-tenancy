package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.MultipleChoiceQuestion;
import be.kdg.finalproject.repository.form.MultipleChoiceQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultipleChoiceQuestionService {
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    public MultipleChoiceQuestionService(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository) {
        this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
    }

    public MultipleChoiceQuestion insertQuestion(MultipleChoiceQuestion multipleChoiceQuestion){
        return multipleChoiceQuestionRepository.save(multipleChoiceQuestion);
    }

    public List<MultipleChoiceQuestion> getAllQuestions(){
        return multipleChoiceQuestionRepository.findAll();
    }

    public List<MultipleChoiceQuestion> getQuestionByForm(Form form){
        return multipleChoiceQuestionRepository.getMultipleChoiceQuestionByForm(form);
    }
}
