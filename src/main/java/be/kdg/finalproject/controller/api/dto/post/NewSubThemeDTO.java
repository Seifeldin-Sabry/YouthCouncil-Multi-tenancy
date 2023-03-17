package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraint.UniqueSubThemeNameConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewSubThemeDTO {
	@UniqueSubThemeNameConstraint
	@NotBlank
	private String subThemeName;
}
