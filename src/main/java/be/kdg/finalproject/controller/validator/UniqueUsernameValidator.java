package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.UniqueUsernameConstraint;
import be.kdg.finalproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameConstraint, String> {
	private final UserRepository userRepository;
	Logger logger = Logger.getLogger(UniqueUsernameValidator.class.getName());

	@Autowired
	public UniqueUsernameValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email.isBlank()) return false;
		return !userRepository.existsByUsernameIgnoreCase(email);
	}
}