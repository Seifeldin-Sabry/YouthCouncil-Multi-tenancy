package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewIdeaDTO {
	@NotBlank (message = "Please enter a title")
	@Size (min = 1, max = 5000, message = "Description must be between 1 and 5000 characters")
	private String description;
	@NotNull (message = "Please select a theme")
	private SubTheme subTheme;
	@Size (min = 1, max = 1, message = "Please add 1 image")
	private List<MultipartFile> images = new ArrayList<>();
}
