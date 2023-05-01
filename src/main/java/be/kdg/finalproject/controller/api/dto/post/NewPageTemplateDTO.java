package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.page.template.TemplateElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPageTemplateDTO {
	@Size (min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	@NotBlank (message = "Name cannot be blank")
	private String name;
	@Size (min = 1, max = 10, message = "Can only have between 1 and 10 elements")
	private List<TemplateElement> templateElements = new ArrayList<>();
}
