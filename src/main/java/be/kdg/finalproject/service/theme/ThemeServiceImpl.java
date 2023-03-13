package be.kdg.finalproject.service.theme;

import be.kdg.finalproject.controller.api.dto.post.NewSubThemeDTO;
import be.kdg.finalproject.controller.api.dto.post.NewThemeDTO;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.SubThemeRepository;
import be.kdg.finalproject.repository.ThemeRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ThemeServiceImpl implements ThemeService {
	private final ThemeRepository themeRepository;
	private final SubThemeRepository subThemeRepository;

	private final Logger logger = getLogger(ThemeServiceImpl.class);

	public ThemeServiceImpl(ThemeRepository themeRepository, SubThemeRepository subThemeRepository) {
		this.themeRepository = themeRepository;
		this.subThemeRepository = subThemeRepository;
	}

	@Override
	public List<Theme> getAllThemes() {
		ImmutableList<Theme> themes = ImmutableList.copyOf(themeRepository.findAllOrderByIdAsc());
		logger.info("Themes: {}", themes);
		return themes;
	}

	@Override
	public Optional<Theme> getThemeById(Long themeId) {
		return themeRepository.findById(themeId);
	}

	@Override
	public Optional<SubTheme> getSubThemeById(Long subThemeId, Long themeId) {
		return subThemeRepository.findByIdAndTheme_IdOrderByIdAsc(subThemeId, themeId);
	}

	@Override
	public void deleteThemeById(Long themeId) {
		themeRepository.deleteById(themeId);
	}

	@Override
	public boolean deleteSubThemeById(Long subThemeId, Long themeId) {
		long countDeleted = subThemeRepository.deleteByIdAndTheme_Id(subThemeId, themeId);
		return countDeleted > 0;
	}

	@Override
	public Theme addTheme(NewThemeDTO theme) {
		Theme newTheme = new Theme(theme.getThemeName());
		return themeRepository.save(newTheme);
	}

	@Override
	public SubTheme addSubTheme(NewSubThemeDTO subTheme, Long themeId) {
		Theme theme = themeRepository.findById(themeId)
		                             .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
		SubTheme newSubTheme = new SubTheme(subTheme.getSubThemeName());
		theme.addSubTheme(newSubTheme);
		subThemeRepository.save(newSubTheme);
		return newSubTheme;
	}

	@Override
	public void updateTheme(Long themeId, NewThemeDTO theme) {
		themeRepository.updateThemeNameById(theme.getThemeName(), themeId);
	}

	@Override
	public void updateThemeSubThemes(Long themeId, List<NewSubThemeDTO> subThemes) {

	}

	@Override
	public void updateSubTheme(NewSubThemeDTO subTheme, Long subThemeId, Long themeId) {
		subThemeRepository.updateSubThemeNameById(subTheme.getSubThemeName(), subThemeId, themeId);
	}
}
