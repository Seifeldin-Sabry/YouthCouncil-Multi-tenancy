package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraint.UniqueThemeNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


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
