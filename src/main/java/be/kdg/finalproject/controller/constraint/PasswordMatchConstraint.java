package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {PasswordMatchValidator.class})
@Target ({ElementType.TYPE})
@Retention (RetentionPolicy.RUNTIME)
public @interface PasswordMatchConstraint {
	String message() default "Passwords do not match";

	Class[] groups() default {};

	Class[] payload() default {};
}

