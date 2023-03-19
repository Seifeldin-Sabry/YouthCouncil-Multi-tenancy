package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.form.*;
import be.kdg.finalproject.domain.form.Form;
import be.kdg.finalproject.domain.form.FormSubmission;
import be.kdg.finalproject.domain.form.SubmissionAnswer;
import be.kdg.finalproject.service.form.*;
import be.kdg.finalproject.service.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping ("/api/form")
public class FormRestController {

    FormSubmissionService formSubmissionService;
    FormService formService;
    UserServiceImpl userService;
    TextInputQuestionService textInputQuestionService;
    NumericInputQuestionService numericInputQuestionService;
    RadioQuestionService radioQuestionService;
    MultipleChoiceQuestionService multipleChoiceQuestionService;


    public FormRestController(FormSubmissionService formSubmissionService, FormService formService,
                              UserServiceImpl userService, TextInputQuestionService textInputQuestionService,
                              NumericInputQuestionService numericInputQuestionService,
                              RadioQuestionService radioQuestionService,
                              MultipleChoiceQuestionService multipleChoiceQuestionService) {
        this.formSubmissionService = formSubmissionService;
        this.formService = formService;
        this.userService = userService;
        this.textInputQuestionService = textInputQuestionService;
        this.numericInputQuestionService = numericInputQuestionService;
        this.radioQuestionService = radioQuestionService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
    }

    @PostMapping
    public ResponseEntity<FormDTO> addForm(@RequestBody @Valid FormDTO formDTO){
        formDTO.setFormId(formService.addForm(new Form(formDTO.getTitle())).getId());
        return new ResponseEntity<>(formDTO, HttpStatus.CREATED);
    }

    @PostMapping("/formSubmission")
    public ResponseEntity<Long> addSubmission(@RequestBody @Valid FormSubmissionDTO formSubmissionDTO){
        if(formService.formExists(formSubmissionDTO.getFormId())){
            long submissionId = formSubmissionService.addSubmission(new FormSubmission(
                    formService.getFormById(formSubmissionDTO.getFormId()),
                    userService.getUserByID(formSubmissionDTO.getUserId()))).getId();
            return new ResponseEntity<>(submissionId, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/textSubmission")
    public ResponseEntity<Void> addTextAnswer(@RequestBody @Valid TextSubmissionAnswerDTO textSubmissionAnswerDTO){
        FormSubmission formSubmission = formSubmissionService.findSubmissionById(textSubmissionAnswerDTO.getFormSubmissionId());
        SubmissionAnswer submissionAnswer= new SubmissionAnswer(formSubmission,
                textInputQuestionService.getQuestionByForm(formSubmission.getForm()).get(0));
        submissionAnswer.setUserAnswer(textSubmissionAnswerDTO.getUserAnswerText());
        formSubmission.getAnswers().add(submissionAnswer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/radioSubmission")
    public ResponseEntity<Void> addRadioAnswer(@RequestBody @Valid TextSubmissionAnswerDTO textSubmissionAnswerDTO){
        FormSubmission formSubmission = formSubmissionService.findSubmissionById(textSubmissionAnswerDTO.getFormSubmissionId());
        SubmissionAnswer submissionAnswer= new SubmissionAnswer(formSubmission,
                radioQuestionService.getQuestionByForm(formSubmission.getForm()).get(0));
        submissionAnswer.setUserAnswer(textSubmissionAnswerDTO.getUserAnswerText());
        formSubmission.getAnswers().add(submissionAnswer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/numericSubmission")
    public ResponseEntity<Void> addNumericAnswer(@RequestBody @Valid NumericSubmissionAnswerDTO numericSubmissionAnswerDTO){
        FormSubmission formSubmission = formSubmissionService.findSubmissionById(numericSubmissionAnswerDTO.getFormSubmissionId());
        SubmissionAnswer submissionAnswer= new SubmissionAnswer(formSubmission,
                numericInputQuestionService.getQuestionByForm(formSubmission.getForm()).get(0));
        submissionAnswer.setUserAnswer(numericSubmissionAnswerDTO.getUserAnswerNumeric());
        formSubmission.getAnswers().add(submissionAnswer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/listSubmission")
    public ResponseEntity<Void> addListAnswer(@RequestBody @Valid ListSubmissionAnswerDTO listSubmissionAnswerDTO){
        FormSubmission formSubmission = formSubmissionService.findSubmissionById(listSubmissionAnswerDTO.getFormSubmissionId());
        SubmissionAnswer submissionAnswer= new SubmissionAnswer(formSubmission,
                multipleChoiceQuestionService.getQuestionByForm(formSubmission.getForm()).get(0));
        submissionAnswer.setUserAnswer(listSubmissionAnswerDTO.getUserAnswerChoices());
        formSubmission.getAnswers().add(submissionAnswer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
