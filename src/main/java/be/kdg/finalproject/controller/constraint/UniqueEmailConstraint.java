package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {UniqueEmailValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface UniqueEmailConstraint {
	String message() default "User with this email already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}

