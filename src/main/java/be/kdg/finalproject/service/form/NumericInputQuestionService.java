package be.kdg.finalproject.service.form;

import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.NumericInputQuestion;
import be.kdg.finalproject.repository.form.NumericInputQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumericInputQuestionService {
    NumericInputQuestionRepository numericInputQuestionRepository;

    public NumericInputQuestionService(NumericInputQuestionRepository numericInputQuestionRepository) {
        this.numericInputQuestionRepository = numericInputQuestionRepository;
    }

    public NumericInputQuestion insertQuestion(NumericInputQuestion numericInputQuestion){
        return numericInputQuestionRepository.save(numericInputQuestion);
    }

    public List<NumericInputQuestion> getAllQuestions(){
        return numericInputQuestionRepository.findAll();
    }

    public List<NumericInputQuestion> getQuestionByForm(Form form){
        return numericInputQuestionRepository.getNumericInputQuestionByForm(form);
    }
}
