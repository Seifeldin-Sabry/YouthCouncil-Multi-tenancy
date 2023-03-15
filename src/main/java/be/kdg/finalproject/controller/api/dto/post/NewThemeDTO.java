package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraint.UniqueThemeNameConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * USE DTO for
 * - POST
 * - PUT
 * - PATCH
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewThemeDTO {
	@UniqueThemeNameConstraint
	@NotEmpty
	private String themeName;
}
