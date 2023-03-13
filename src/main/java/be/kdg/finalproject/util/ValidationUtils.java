package be.kdg.finalproject.util;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationUtils {
	public static Map<String, List<String>> getErrorsMap(BindingResult bindingResult) {
		Map<String, List<String>> errors = new HashMap<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			String field = error.getField();
			String message = error.getDefaultMessage();
			if (!errors.containsKey(field)) {
				errors.put(field, new ArrayList<>());
			}
			errors.get(field).add(message);
		}
		return errors;
	}
}
