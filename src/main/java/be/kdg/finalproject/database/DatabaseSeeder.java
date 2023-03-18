package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.SubThemeRepository;
import be.kdg.finalproject.repository.ThemeRepository;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.repository.form.FormRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile ("dev")
public class DatabaseSeeder {

	private final ThemeRepository themeRepository;
	private final SubThemeRepository subThemeRepository;
	private final FormRepository formRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EntityFactory entityFactory;

	public DatabaseSeeder(ThemeRepository themeRepository, SubThemeRepository subThemeRepository, FormRepository formRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EntityFactory entityFactory) {
		this.themeRepository = themeRepository;
		this.subThemeRepository = subThemeRepository;
		this.formRepository = formRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.entityFactory = entityFactory;
	}

	@PostConstruct
	public void seed() {
		// USERS
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		userRepository.saveAll(List.of(admin, youthCouncil, moderator, user));


		//THEMES AND SUBTHEMES
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));


		//FORMS AND QUESTIONS
		formRepository.save(entityFactory.createRandomFormWithQuestions());
		formRepository.save(entityFactory.createRandomFormWithQuestions());
		formRepository.save(entityFactory.createRandomFormWithQuestions());
	}
}
