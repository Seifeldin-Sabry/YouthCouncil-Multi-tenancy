package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.UniqueSubThemeNameConstraint;
import be.kdg.finalproject.repository.theme.SubThemeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueSubThemeNameValidator implements ConstraintValidator<UniqueSubThemeNameConstraint, String> {

	private final SubThemeRepository themeRepository;

	public UniqueSubThemeNameValidator(SubThemeRepository themeRepository) {this.themeRepository = themeRepository;}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !themeRepository.existsBySubThemeNameIgnoreCase(value);
	}
}
