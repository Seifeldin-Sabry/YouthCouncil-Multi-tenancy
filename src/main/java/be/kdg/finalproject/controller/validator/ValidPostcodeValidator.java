package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.ValidPostcodeConstraint;
import be.kdg.finalproject.repository.municipality.PostCodeRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidPostcodeValidator implements ConstraintValidator<ValidPostcodeConstraint, Integer> {
	private final PostCodeRepository postCodeRepository;
	Logger logger = LoggerFactory.getLogger(UniqueEmailValidator.class.getName());

	public ValidPostcodeValidator(PostCodeRepository postCodeRepository) {
		this.postCodeRepository = postCodeRepository;
	}

	@Override
	public boolean isValid(Integer postcode, ConstraintValidatorContext context) {
		if (postcode == null) return false;
		logger.debug("Checking if postcode {} exists", postcode);
		logger.debug("Postcode exists: {}", ImmutableList.copyOf(postCodeRepository.findAll()));
		return postCodeRepository.existsByPostcode(postcode);
	}
}