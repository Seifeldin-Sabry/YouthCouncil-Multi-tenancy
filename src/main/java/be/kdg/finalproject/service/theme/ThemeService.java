package be.kdg.finalproject.service.theme;

import be.kdg.finalproject.controller.api.dto.post.NewSubThemeDTO;
import be.kdg.finalproject.controller.api.dto.post.NewThemeDTO;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {

	List<Theme> getAllThemes();

	Optional<Theme> getThemeById(Long themeId);

	Optional<SubTheme> getSubThemeById(Long subThemeId, Long themeId);

	void deleteThemeById(Long themeId);

	boolean deleteSubThemeById(Long subThemeId, Long themeId);

	Theme addTheme(NewThemeDTO theme);

	SubTheme addSubTheme(NewSubThemeDTO subTheme, Long themeId);

	void updateTheme(Long themeId, NewThemeDTO theme);

	void updateThemeSubThemes(Long themeId, List<NewSubThemeDTO> subThemes);

	void updateSubTheme(NewSubThemeDTO subTheme, Long subThemeId, Long themeId);
}
