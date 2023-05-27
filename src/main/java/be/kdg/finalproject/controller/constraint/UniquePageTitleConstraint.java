package be.kdg.finalproject.controller.constraint;


import be.kdg.finalproject.controller.validator.UniquePageTitleValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {UniquePageTitleValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface UniquePageTitleConstraint {

	String message() default "Page with this title already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}
