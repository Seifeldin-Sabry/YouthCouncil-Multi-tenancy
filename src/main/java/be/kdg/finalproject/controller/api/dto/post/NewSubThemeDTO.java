package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraints.UniqueSubThemeNameConstraint;
import jakarta.validation.constraints.NotEmpty;
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
	@NotEmpty
	private String subThemeName;
}
