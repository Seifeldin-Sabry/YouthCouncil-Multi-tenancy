package be.kdg.finalproject.util;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationUtils {
	public static Map<String, String> getErrorsMap(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			String field = error.getField();
			String message = error.getDefaultMessage();
			if (!errors.containsKey(field)) {
				errors.put(field, message);
			}
		}
		return errors;
	}
}
