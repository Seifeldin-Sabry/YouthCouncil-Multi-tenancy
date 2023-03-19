package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.ValidPostcodeValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {ValidPostcodeValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface ValidPostcodeConstraint {
	String message() default "Postcode is not valid";

	Class[] groups() default {};

	Class[] payload() default {};
}