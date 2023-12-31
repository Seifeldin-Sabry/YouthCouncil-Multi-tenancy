package be.kdg.finalproject.controller.validator;

import be.kdg.finalproject.controller.constraint.PasswordMatchConstraint;
import be.kdg.finalproject.controller.mvc.viewmodel.password.PasswordMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchConstraint, PasswordMatcher> {

	@Override
	public boolean isValid(PasswordMatcher viewModel, ConstraintValidatorContext context) {
		return viewModel.passwordsMatch();
	}
}

