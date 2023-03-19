package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.UniqueThemeNameConstraint;
import be.kdg.finalproject.repository.ThemeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueThemeNameValidator implements ConstraintValidator<UniqueThemeNameConstraint, String> {

	private final ThemeRepository themeRepository;

	public UniqueThemeNameValidator(ThemeRepository themeRepository) {this.themeRepository = themeRepository;}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !themeRepository.existsByThemeNameIgnoreCase(value);
	}
}
