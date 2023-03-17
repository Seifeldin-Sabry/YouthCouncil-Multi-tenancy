package be.kdg.finalproject.database;

import be.kdg.finalproject.repository.SubThemeRepository;
import be.kdg.finalproject.repository.ThemeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile ("dev")
public class DatabaseSeeder {

	private final ThemeRepository themeRepository;
	private final SubThemeRepository subThemeRepository;
	private final EntityFactory entityFactory;

	public DatabaseSeeder(ThemeRepository themeRepository, SubThemeRepository subThemeRepository, EntityFactory entityFactory) {
		this.themeRepository = themeRepository;
		this.subThemeRepository = subThemeRepository;
		this.entityFactory = entityFactory;
	}

	@PostConstruct
	public void seed() {
		//		THEMES AND SUBTHEMES
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
	}
}
