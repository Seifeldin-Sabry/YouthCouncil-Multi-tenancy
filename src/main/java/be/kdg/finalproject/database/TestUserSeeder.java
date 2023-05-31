package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.membership.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Profile ("test")
public class TestUserSeeder {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public TestUserSeeder(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void seed() {
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		User user2 = new User("user2", "user2", "user2", "user2@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		User offlineUser = new User("offline", "idea", "offline_idea", "offline@idea.user", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);

		userRepository.saveAll(List.of(admin, youthCouncil, moderator, user, user2, offlineUser));
	}
}
