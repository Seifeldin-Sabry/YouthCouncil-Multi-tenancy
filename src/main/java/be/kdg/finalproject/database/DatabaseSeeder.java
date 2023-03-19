package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.platform.PostCode;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.MunicipalityRepository;
import be.kdg.finalproject.repository.PostCodeRepository;
import be.kdg.finalproject.repository.ThemeRepository;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.repository.form.FormRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile ("dev")
public class DatabaseSeeder {

	private final ThemeRepository themeRepository;
	private final FormRepository formRepository;
	private final UserRepository userRepository;
	private final MembershipService membershipService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EntityFactory entityFactory;
	private final MunicipalityRepository municipalityRepository;
	private final PostCodeRepository postCodeRepository;

	private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

	public DatabaseSeeder(ThemeRepository themeRepository, FormRepository formRepository, UserRepository userRepository, MembershipService membershipService, BCryptPasswordEncoder passwordEncoder, EntityFactory entityFactory,
	                      MunicipalityRepository municipalityRepository, PostCodeRepository postCodeRepository) {
		this.themeRepository = themeRepository;
		this.formRepository = formRepository;
		this.userRepository = userRepository;
		this.membershipService = membershipService;
		this.passwordEncoder = passwordEncoder;
		this.entityFactory = entityFactory;
		this.municipalityRepository = municipalityRepository;
		this.postCodeRepository = postCodeRepository;
	}

	@PostConstruct
	public void seed() {
		//MUNICIPALITIES & POSTCODES
		Municipality antwerpen = new Municipality("Antwerpen");
		Set<PostCode> postCodes = new HashSet<>();
		List.of(2000, 2018, 2020, 2030, 2040, 2050, 2060, 2100, 2140, 2170, 2180, 2600, 2610, 2660).forEach((code) -> {
			PostCode postCode = new PostCode(code);
			postCodes.add(postCode);
		});
		antwerpen.setPostcodes(postCodes);
		municipalityRepository.save(antwerpen);

		//		logger.debug("Postcodes saved for Antwerpen {} ", ImmutableList.copyOf(postCodeRepository.findAll()));
		//		logger.debug("Municipalities saved {} ", ImmutableList.copyOf(municipalityRepository.findAll()));

		// USERS
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);

		//MEMBERSHIPS
		membershipService.addMembershipByUserAndPostCode(admin, 2000, Role.ADMINISTRATOR);
		membershipService.addMembershipByUserAndPostCode(youthCouncil, 2000, Role.YOUTH_COUNCIL_ADMINISTRATOR);
		membershipService.addMembershipByUserAndPostCode(moderator, 2000, Role.YOUTH_COUNCIL_MODERATOR);
		membershipService.addMembershipByUserAndPostCode(user, 2000, Role.USER);
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
