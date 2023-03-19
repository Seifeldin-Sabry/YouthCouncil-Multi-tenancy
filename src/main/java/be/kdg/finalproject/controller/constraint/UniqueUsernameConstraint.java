package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.UniqueUsernameValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {UniqueUsernameValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface UniqueUsernameConstraint {
	String message() default "Username already taken";

	Class[] groups() default {};

	Class[] payload() default {};
}
