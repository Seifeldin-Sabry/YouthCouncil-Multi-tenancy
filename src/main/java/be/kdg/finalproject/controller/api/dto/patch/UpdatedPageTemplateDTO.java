package be.kdg.finalproject.controller.api.dto.patch;

import be.kdg.finalproject.domain.page.template.TemplateElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedPageTemplateDTO {
	@Size (min = 1, max = 10, message = "Can only have between 1 and 10 elements")
	private List<TemplateElement> templateElements = new ArrayList<>();
}
