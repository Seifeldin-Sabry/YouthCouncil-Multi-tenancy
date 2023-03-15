package be.kdg.finalproject.controller.constraint;

import be.kdg.finalproject.controller.validator.UniqueThemeNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {UniqueThemeNameValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface UniqueThemeNameConstraint {
	String message() default "Theme with this name already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}
