package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.form.*;
import be.kdg.finalproject.service.form.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FormController {

	private final FormService formService;
	private final TextInputQuestionService textInputQuestionService;
	private final RadioQuestionService radioQuestionService;
	private final MultipleChoiceQuestionService multipleChoiceQuestionService;
	private final NumericInputQuestionService numericInputQuestionService;

	public FormController(FormService formService, TextInputQuestionService textInputQuestionService,
	                      RadioQuestionService radioQuestionService,
	                      MultipleChoiceQuestionService multipleChoiceQuestionService,
	                      NumericInputQuestionService numericInputQuestionService) {
		this.formService = formService;
		this.textInputQuestionService = textInputQuestionService;
		this.radioQuestionService = radioQuestionService;
		this.multipleChoiceQuestionService = multipleChoiceQuestionService;
		this.numericInputQuestionService = numericInputQuestionService;
	}


	@RequestMapping("/forms")
	public ModelAndView getForms() {
		return new ModelAndView("html/form/forms", "forms", formService.getAllForms());
	}

	@RequestMapping ("/user-form")
	public ModelAndView getFormForUser(@RequestParam Long formId) {
		Form form = formService.getFormById(formId);
		List<TextInputQuestion> textInputQuestions = textInputQuestionService.getQuestionByForm(form);
		List<RadioQuestion> radioQuestions = radioQuestionService.getQuestionByForm(form);
		List<MultipleChoiceQuestion> multipleChoiceQuestions = multipleChoiceQuestionService.getQuestionByForm(form);
		List<NumericInputQuestion> numericInputQuestions = numericInputQuestionService.getQuestionByForm(form);

		ModelAndView modelAndView = new ModelAndView("html/form/user-form");

		modelAndView.addObject("textQuestions", textInputQuestions);
		modelAndView.addObject("radioQuestions", radioQuestions);
		modelAndView.addObject("choiceQuestions", multipleChoiceQuestions);
		modelAndView.addObject("numericQuestions", numericInputQuestions);
		modelAndView.addObject("form", form);

		return modelAndView;
	}

	@RequestMapping("/form")
	public ModelAndView getForm(@RequestParam Long formId) {
		Form form = formService.getFormById(formId);
		List<TextInputQuestion> textInputQuestions = textInputQuestionService.getQuestionByForm(form);
		List<RadioQuestion> radioQuestions = radioQuestionService.getQuestionByForm(form);
		List<MultipleChoiceQuestion> multipleChoiceQuestions = multipleChoiceQuestionService.getQuestionByForm(form);
		List<NumericInputQuestion> numericInputQuestions = numericInputQuestionService.getQuestionByForm(form);

		ModelAndView modelAndView = new ModelAndView("html/form/form");

		modelAndView.addObject("textQuestions", textInputQuestions);
		modelAndView.addObject("radioQuestions", radioQuestions);
		modelAndView.addObject("choiceQuestions", multipleChoiceQuestions);
		modelAndView.addObject("numericQuestions", numericInputQuestions);
		modelAndView.addObject("form", form);

		return modelAndView;
	}
}
