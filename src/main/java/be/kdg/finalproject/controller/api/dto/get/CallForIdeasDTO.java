package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.theme.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallForIdeasDTO {
	private Long id;
	private String title;
	private String description;
	private ThemeDTO theme;
}
