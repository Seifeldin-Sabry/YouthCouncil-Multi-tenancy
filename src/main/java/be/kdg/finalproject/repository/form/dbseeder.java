package be.kdg.finalproject.repository.form;


import be.kdg.finalproject.domain.form.*;
import be.kdg.finalproject.service.form.*;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class dbseeder {
	FormService formService;
	MultipleChoiceQuestionService multipleChoiceQuestionService;
	NumericInputQuestionService numericInputQuestionService;
	RadioQuestionService radioQuestionService;
	TextInputQuestionService textInputQuestionService;

	public dbseeder(FormService formService, MultipleChoiceQuestionService multipleChoiceQuestionService,
	                NumericInputQuestionService numericInputQuestionService, RadioQuestionService radioQuestionService,
	                TextInputQuestionService textInputQuestionService) {
		this.formService = formService;
		this.multipleChoiceQuestionService = multipleChoiceQuestionService;
		this.numericInputQuestionService = numericInputQuestionService;
		this.radioQuestionService = radioQuestionService;
		this.textInputQuestionService = textInputQuestionService;
	}

	@PostConstruct
	@Profile ("dev")
	private void seed(){
		Form form1 = new Form("testform1");
		Form form2 = new Form("testform2");
		Form form3 = new Form("testform3");
		formService.addForm(form1);
		formService.addForm(form2);
		formService.addForm(form3);
		TextInputQuestion textInputQuestion = new TextInputQuestion("what is your favorite musician?", 1);
		NumericInputQuestion numericInputQuestion = new NumericInputQuestion("how many pizzas can u eat in 1 sitting?", 2);
		RadioQuestion radioQuestion = new RadioQuestion("What is your favorite pokemon heart gold starter?", 3);
		MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Check what you agree with", 4);
		radioQuestion.setChoices(Arrays.asList("Chikorita", "Cyndaquil", "Totodile"));
		multipleChoiceQuestion.setChoices(Arrays.asList("Water tastes good", "Elden ring is the best game of 2022", "Cancer is bad"));
		textInputQuestion.setForm(form1);
		numericInputQuestion.setForm(form1);
		radioQuestion.setForm(form1);
		multipleChoiceQuestion.setForm(form1);
		textInputQuestionService.insertQuestion(textInputQuestion);
		numericInputQuestionService.insertQuestion(numericInputQuestion);
		radioQuestionService.insertQuestion(radioQuestion);
		multipleChoiceQuestionService.insertQuestion(multipleChoiceQuestion);
//		List<Question> questions = new ArrayList<>();
//		questions.add(textInputQuestion);
//		questions.add(numericInputQuestion);
//		questions.add(radioQuestion);
//		questions.add(multipleChoiceQuestion);
//		form1.setQuestions(questions);


	}
}
