package be.kdg.finalproject.database;

import be.kdg.finalproject.repository.ThemeRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile ("dev")
public class HSQLDatabaseSeeder implements DatabaseSeeder {

	private final PasswordEncoder passwordEncoder;
	private final JdbcTemplate jdbcTemplate;
	private final ThemeRepository themeRepository;
	private final Logger logger;

	@Autowired
	public HSQLDatabaseSeeder(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate, ThemeRepository themeRepository) {
		this.passwordEncoder = passwordEncoder;
		this.jdbcTemplate = jdbcTemplate;
		this.themeRepository = themeRepository;
		this.logger = LoggerFactory.getLogger(HSQLDatabaseSeeder.class);
	}

	@PostConstruct
	@Override
	public void seedDatabase() {
		//		var app_users = new ArrayList<Pair<String, String>>();
		//		for (int i = 1; i <= 3; i++) {
		//			app_users.add(Pair.of("user" + i, passwordEncoder.encode("password")));
		//		}
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

		//		app_users.forEach(user -> jdbcTemplate.update("INSERT INTO APP_USERS (email, first_name, last_name, password, role, username) VALUES (?, ?, ?, ?, ?, ?)", String.format("%s@g.com",user.getKey()),  user.getKey(), "Tim", user.getValue(), Role.USER, user.getKey()));
		logger.info("Database seeded");
	}


}
