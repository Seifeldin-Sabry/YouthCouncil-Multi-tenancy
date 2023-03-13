package be.kdg.finalproject.controller.api.dto.get;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThemeDTO {
	private Long id;
	private String themeName;
	private List<SubThemeDTO> subThemes;
}
