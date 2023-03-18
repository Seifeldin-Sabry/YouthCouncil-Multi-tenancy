package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.RadioQuestion;
import be.kdg.finalproject.repository.form.RadioQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioQuestionService {
    RadioQuestionRepository radioQuestionRepository;

    public RadioQuestionService(RadioQuestionRepository radioQuestionRepository) {
        this.radioQuestionRepository = radioQuestionRepository;
    }

    public RadioQuestion insertQuestion(RadioQuestion radioQuestion){
        return radioQuestionRepository.save(radioQuestion);
    }

    public List<RadioQuestion> getAllQuestions(){
        return radioQuestionRepository.findAll();
    }

    public List<RadioQuestion> getQuestionByForm(Form form){
        return radioQuestionRepository.getRadioQuestionByForm(form);
    }
}
