package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.theme.SubTheme;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewActionPointDTO {
	@NotBlank (message = "Please enter a title")
	private String title;

	private String description;

	@NotNull (message = "Please select a subtheme")
	private SubTheme subTheme;

	@Size (min = 1, message = "Please add at least one proposal")
	@NotNull (message = "Please add at least one proposal")
	private List<String> actionPointProposals = new ArrayList<>();

	private List<String> imageSources = new ArrayList<>();
}
