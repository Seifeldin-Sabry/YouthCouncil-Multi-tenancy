package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.form.*;
import be.kdg.finalproject.domain.form.*;
import be.kdg.finalproject.service.form.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/question")
public class QuestionRestController {

    TextInputQuestionService textInputQuestionService;
    NumericInputQuestionService numericInputQuestionService;
    RadioQuestionService radioQuestionService;
    MultipleChoiceQuestionService multipleChoiceQuestionService;

    FormService formService;

    public QuestionRestController(TextInputQuestionService textInputQuestionService, NumericInputQuestionService numericInputQuestionService, RadioQuestionService radioQuestionService, MultipleChoiceQuestionService multipleChoiceQuestionService, FormService formService) {
        this.textInputQuestionService = textInputQuestionService;
        this.numericInputQuestionService = numericInputQuestionService;
        this.radioQuestionService = radioQuestionService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.formService = formService;
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody @Valid QuestionDTO questionDTO){
        Form form = formService.getFormById(questionDTO.getFormId());
        if(questionDTO.getQuestionType()== QuestionType.TEXT_QUESTION){
            questionDTO.setQuestionId(textInputQuestionService.insertQuestion(
                    new TextInputQuestion(questionDTO.getQuestionText(), questionDTO.isRequired(), questionDTO.getOrder(), form)).getId());
        } else if (questionDTO.getQuestionType()== QuestionType.NUMBER_QUESTION){
            questionDTO.setQuestionId(numericInputQuestionService.insertQuestion(
                    new NumericInputQuestion(questionDTO.getQuestionText(), questionDTO.isRequired(), questionDTO.getOrder(), form)).getId());
        } else if (questionDTO.getQuestionType()== QuestionType.RADIO_QUESTION){
            questionDTO.setQuestionId(radioQuestionService.insertQuestion(
                    new RadioQuestion(questionDTO.getQuestionText(), questionDTO.isRequired(), questionDTO.getOrder(), form, questionDTO.getChoices())).getId());
        } else if (questionDTO.getQuestionType()== QuestionType.MULTIPLE_CHOICE_QUESTION){
            questionDTO.setQuestionId(multipleChoiceQuestionService.insertQuestion(
                    new MultipleChoiceQuestion(questionDTO.getQuestionText(), questionDTO.isRequired(), questionDTO.getOrder(), form, questionDTO.getChoices())).getId());
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        };
        return new ResponseEntity<>(questionDTO, HttpStatus.CREATED);
    }
}
