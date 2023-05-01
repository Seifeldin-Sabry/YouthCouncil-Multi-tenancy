package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.theme.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCallForIdeasDTO {
	@NotBlank (message = "Please enter a title")
	@Size (min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	private String title;
	@NotBlank (message = "Please enter a description")
	@Size (min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
	private String description;
	@NotNull
	private Theme theme;
}
