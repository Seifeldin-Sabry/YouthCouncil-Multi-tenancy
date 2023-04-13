package be.kdg.finalproject.service.theme;

import be.kdg.finalproject.controller.api.dto.patch.ActivateDTO;
import be.kdg.finalproject.controller.api.dto.post.NewSubThemeDTO;
import be.kdg.finalproject.controller.api.dto.post.NewThemeDTO;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.theme.SubThemeRepository;
import be.kdg.finalproject.repository.theme.ThemeRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ThemeService {
	private final ThemeRepository themeRepository;
	private final SubThemeRepository subThemeRepository;

	private final Logger logger = getLogger(ThemeService.class);

	public ThemeService(ThemeRepository themeRepository, SubThemeRepository subThemeRepository) {
		this.themeRepository = themeRepository;
		this.subThemeRepository = subThemeRepository;
	}

	public List<Theme> getAllThemes() {
		ImmutableList<Theme> themes = ImmutableList.copyOf(themeRepository.findAllOrderByIdAsc());
		logger.info("Themes: {}", themes);
		return themes;
	}

	public Optional<Theme> getThemeById(Long themeId) {
		return themeRepository.findById(themeId);
	}

	public Optional<SubTheme> getSubThemeById(Long subThemeId, Long themeId) {
		return subThemeRepository.findByIdAndTheme_IdOrderById(subThemeId, themeId);
	}

	public void deactivateOrReactivateTheme(Theme theme, ActivateDTO activateDTO) {
		theme.setActive(activateDTO.isActive());
		themeRepository.save(theme);
	}

	public void deactivateOrReactivateSubthemeByIdAndThemeId(Long subThemeId, Long themeId, ActivateDTO activateDTO) {
		SubTheme subTheme = subThemeRepository.findByIdAndTheme_IdOrderById(subThemeId, themeId)
		                                      .orElseThrow(() -> new EntityNotFoundException("SubTheme not found"));
		subTheme.setActive(activateDTO.isActive());
		subThemeRepository.save(subTheme);
	}

	public Theme addTheme(NewThemeDTO theme) {
		Theme newTheme = new Theme(theme.getThemeName());
		return themeRepository.save(newTheme);
	}

	public SubTheme addSubTheme(NewSubThemeDTO subTheme, Long themeId) {
		Theme theme = themeRepository.findById(themeId)
		                             .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
		SubTheme newSubTheme = new SubTheme(subTheme.getSubThemeName());
		theme.addSubTheme(newSubTheme);
		subThemeRepository.save(newSubTheme);
		return newSubTheme;
	}

	public void updateTheme(Long themeId, NewThemeDTO theme) {
		themeRepository.updateThemeNameById(theme.getThemeName(), themeId);
	}

	public void updateSubTheme(NewSubThemeDTO subTheme, Long subThemeId, Long themeId) {
		subThemeRepository.updateSubThemeNameById(subTheme.getSubThemeName(), subThemeId, themeId);
	}
}
