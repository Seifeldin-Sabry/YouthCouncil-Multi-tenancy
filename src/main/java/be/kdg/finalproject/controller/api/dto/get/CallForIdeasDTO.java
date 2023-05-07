package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.theme.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallForIdeasDTO {
	private Long id;
	private UUID uuid;
	private String title;
	private String description;
	private ThemeDTO theme;
}
