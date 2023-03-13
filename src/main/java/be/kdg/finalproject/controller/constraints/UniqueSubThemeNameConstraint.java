package be.kdg.finalproject.controller.constraints;

import be.kdg.finalproject.controller.validator.UniqueSubThemeNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {UniqueSubThemeNameValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface UniqueSubThemeNameConstraint {
	String message() default "Subtheme with this name already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}
