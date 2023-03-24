package be.kdg.finalproject.controller.authority;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.TYPE, ElementType.METHOD})
@Retention (RetentionPolicy.RUNTIME)
@PreAuthorize ("hasRole('ROLE_ADMINISTRATOR')")
public @interface GeneralAdminOnly {
}
