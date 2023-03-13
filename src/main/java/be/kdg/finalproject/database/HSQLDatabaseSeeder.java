package be.kdg.finalproject.database;

import be.kdg.finalproject.repository.ThemeRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile ("dev")
public class HSQLDatabaseSeeder implements DatabaseSeeder {

	private final JdbcTemplate jdbcTemplate;
	private final ThemeRepository themeRepository;
	private final Logger logger;

	@Autowired
	public HSQLDatabaseSeeder(JdbcTemplate jdbcTemplate, ThemeRepository themeRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.themeRepository = themeRepository;
		this.logger = LoggerFactory.getLogger(HSQLDatabaseSeeder.class);
	}

	@PostConstruct
	@Override
	public void seedDatabase() {
		jdbcTemplate.execute("""
				INSERT INTO THEMES (theme_name) VALUES ('Sport');
				INSERT INTO THEMES (theme_name) VALUES ('Muziek');
				INSERT INTO THEMES (theme_name) VALUES ('Film');
									
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Voetbal', 1);
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Basketbal', 1);
									
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Klassiek', 2);
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Pop', 2);
									
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Actie', 3);
				INSERT INTO sub_themes (sub_them_name, theme_id) VALUES ('Drama', 3);
								
				""");
		logger.info("Database seeded");
		logger.debug("Database seeded with themes and subthemes", themeRepository.findAll());
	}


}
