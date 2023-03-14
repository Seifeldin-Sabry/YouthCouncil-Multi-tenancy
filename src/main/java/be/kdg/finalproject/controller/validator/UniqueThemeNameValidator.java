package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraints.UniqueThemeNameConstraint;
import be.kdg.finalproject.repository.ThemeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueThemeNameValidator implements ConstraintValidator<UniqueThemeNameConstraint, String> {

	private final ThemeRepository themeRepository;

	public UniqueThemeNameValidator(ThemeRepository themeRepository) {this.themeRepository = themeRepository;}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.isBlank()) return false;
		return !themeRepository.existsByThemeNameIgnoreCase(value);
	}
}
