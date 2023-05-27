package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.controller.constraint.UniquePageTitleConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewInformativePageDTO {

	@UniquePageTitleConstraint
	@Size (min = 1, max = 50, message = "Title must be between 1 and 50 characters")
	@NotBlank (message = "Title cannot be blank")
	private String title;
}
