package be.kdg.finalproject.service.theme;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.repository.theme.SubThemeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubThemeService {

	private final SubThemeRepository subThemeRepository;

	public SubThemeService(SubThemeRepository subThemeRepository) {
		this.subThemeRepository = subThemeRepository;
	}

	public List<SubTheme> getAllSubThemes() {
		return subThemeRepository.findByIsActiveTrue();
	}

	public List<SubTheme> findActionPointsBySubtheme(Long municipId){
		return subThemeRepository.findDistinctSubthemesByMunicipalityId(municipId);
	}

}
