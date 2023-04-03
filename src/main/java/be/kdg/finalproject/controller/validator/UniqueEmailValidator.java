package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.UniqueEmailConstraint;
import be.kdg.finalproject.repository.membership.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {
	private final UserRepository userRepository;
	Logger logger = Logger.getLogger(UniqueEmailValidator.class.getName());

	@Autowired
	public UniqueEmailValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email.isBlank()) return false;
		return !userRepository.existsByEmailIgnoreCase(email);
	}
}

