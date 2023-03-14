package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.ValidPostcodeConstraint;
import be.kdg.finalproject.repository.PostCodeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.logging.Logger;

public class ValidPostcodeValidator implements ConstraintValidator<ValidPostcodeConstraint, Integer> {
	private final PostCodeRepository postCodeRepository;
	Logger logger = Logger.getLogger(UniqueEmailValidator.class.getName());

	public ValidPostcodeValidator(PostCodeRepository postCodeRepository) {
		this.postCodeRepository = postCodeRepository;
	}

	@Override
	public boolean isValid(Integer postcode, ConstraintValidatorContext context) {
		if (postcode == null) return false;
		return postCodeRepository.existsByPostcode(postcode);
	}
}