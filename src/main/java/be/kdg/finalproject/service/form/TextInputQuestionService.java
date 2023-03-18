package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.TextInputQuestion;
import be.kdg.finalproject.repository.form.TextInputQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextInputQuestionService {
    TextInputQuestionRepository textInputQuestionRepository;

    public TextInputQuestionService(TextInputQuestionRepository textInputQuestionRepository) {
        this.textInputQuestionRepository = textInputQuestionRepository;
    }

    public TextInputQuestion insertQuestion(TextInputQuestion textInputQuestion){
        return textInputQuestionRepository.save(textInputQuestion);
    }

    public List<TextInputQuestion> getAllQuestions(){
        return textInputQuestionRepository.findAll();
    }

    public List<TextInputQuestion> getQuestionByForm(Form form){
        return textInputQuestionRepository.getTextInputQuestionByForm(form);
    }
}
