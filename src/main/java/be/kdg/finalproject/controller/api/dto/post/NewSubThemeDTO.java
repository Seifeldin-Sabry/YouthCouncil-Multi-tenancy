package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraint.UniqueSubThemeNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewSubThemeDTO {
	@UniqueSubThemeNameConstraint
	@NotBlank
	private String subThemeName;
}
