package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.EmailOrUsernameConstraint;
import be.kdg.finalproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailOrUsernameValidator implements ConstraintValidator<EmailOrUsernameConstraint, String> {

	private final UserRepository userRepository;

	@Autowired
	public EmailOrUsernameValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String emailOrUsername, ConstraintValidatorContext context) {
		if (emailOrUsername.isBlank()) return false;
		return userRepository.findByUsernameOrEmail(emailOrUsername).isPresent();
	}
}