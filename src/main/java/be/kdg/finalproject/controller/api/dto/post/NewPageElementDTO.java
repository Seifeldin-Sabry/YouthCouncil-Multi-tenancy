package be.kdg.finalproject.controller.api.dto.post;

import lombok.*;
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
public class NewPageElementDTO {
	@NotBlank
	private String elementType;
	@Size (max = 5000, message = "Text can't be more than 5000 characters")
	private String text;
	private int fontSize=24;
	@Size (max = 1, message = "Please add only 1 image")
	private List<MultipartFile> src = new ArrayList<>();
	@NotNull
	private int order;
}
