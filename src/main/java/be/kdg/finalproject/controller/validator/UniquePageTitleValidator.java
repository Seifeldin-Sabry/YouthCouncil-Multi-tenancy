package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.UniquePageTitleConstraint;
import be.kdg.finalproject.repository.page.InformativePageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePageTitleValidator implements ConstraintValidator<UniquePageTitleConstraint, String> {

	private final InformativePageRepository informativePageRepository;

	@Autowired
	public UniquePageTitleValidator(InformativePageRepository informativePageRepository) {this.informativePageRepository = informativePageRepository;}

	@Override
	public boolean isValid(String title, ConstraintValidatorContext context) {
		return !informativePageRepository.existsByTitleIgnoreCaseOrPageNameIgnoreCase(title);
	}
}
