package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.EmailOrUsernameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {EmailOrUsernameValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface EmailOrUsernameConstraint {
	String message() default "Invalid Credentials";

	Class[] groups() default {};

	Class[] payload() default {};
}
