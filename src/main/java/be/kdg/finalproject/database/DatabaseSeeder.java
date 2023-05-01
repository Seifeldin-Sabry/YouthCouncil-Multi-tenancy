package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.page.PageTemplate;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.platform.PostCode;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;
import be.kdg.finalproject.repository.form.FormRepository;
import be.kdg.finalproject.repository.membership.UserRepository;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import be.kdg.finalproject.repository.municipality.PostCodeRepository;
import be.kdg.finalproject.repository.page.PageTemplateRepository;
import be.kdg.finalproject.repository.theme.ThemeRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile ({"dev", "prod"})
public class DatabaseSeeder {

	private final ThemeRepository themeRepository;
	private final FormRepository formRepository;
	private final UserRepository userRepository;
	private final MembershipService membershipService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EntityFactory entityFactory;
	private final MunicipalityRepository municipalityRepository;
	private final PostCodeRepository postCodeRepository;
	private final ActionPointRepository actionPointRepository;
	private final PageTemplateRepository pageTemplateRepository;
	private final CalendarActivityRepository calendarActivityRepository;

	private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

	@Autowired
	public DatabaseSeeder(ThemeRepository themeRepository, FormRepository formRepository, UserRepository userRepository, MembershipService membershipService, BCryptPasswordEncoder passwordEncoder, EntityFactory entityFactory, MunicipalityRepository municipalityRepository, PostCodeRepository postCodeRepository, ActionPointRepository actionPointRepository, PageTemplateRepository pageTemplateRepository, CalendarActivityRepository calendarActivityRepository) {
		this.themeRepository = themeRepository;
		this.formRepository = formRepository;
		this.userRepository = userRepository;
		this.membershipService = membershipService;
		this.passwordEncoder = passwordEncoder;
		this.entityFactory = entityFactory;
		this.municipalityRepository = municipalityRepository;
		this.postCodeRepository = postCodeRepository;
		this.actionPointRepository = actionPointRepository;
		this.pageTemplateRepository = pageTemplateRepository;
		this.calendarActivityRepository = calendarActivityRepository;
	}

	@PostConstruct
	public void seed() {
		//MUNICIPALITIES & POSTCODES
		Municipality antwerpen = new Municipality("Antwerpen", true);
		Set<PostCode> postCodes = new HashSet<>();
		Set<PostCode> finalPostCodes1 = postCodes;
		List.of(2000, 2018, 2020, 2030, 2040, 2050, 2060, 2100, 2140, 2170, 2180, 2600, 2610, 2660).forEach((code) -> {
			PostCode postCode = new PostCode(code);
			finalPostCodes1.add(postCode);
		});
		antwerpen.setPostcodes(postCodes);
		municipalityRepository.save(antwerpen);

		Municipality ghent = new Municipality("Ghent");
		postCodes = new HashSet<>();
		Set<PostCode> finalPostCodes = postCodes;
		List.of(1000, 3000).forEach((code) -> {
			PostCode postCode = new PostCode(code);
			finalPostCodes.add(postCode);
		});
		ghent.setPostcodes(postCodes);
		municipalityRepository.save(ghent);

		Municipality brussels = new Municipality("Brussels", true);
		postCodes = new HashSet<>();
		Set<PostCode> finalPostCodes2 = postCodes;
		List.of(1000, 3000).forEach((code) -> {
			PostCode postCode = new PostCode(code);
			finalPostCodes2.add(postCode);
		});
		brussels.setPostcodes(postCodes);
		municipalityRepository.save(brussels);

		logger.debug("Postcodes saved for Antwerpen {} ", ImmutableList.copyOf(postCodeRepository.findAll()));
		logger.debug("Municipalities saved {} ", ImmutableList.copyOf(municipalityRepository.findAll()));

		// USERS
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		User user2 = new User("user2", "user2", "user2", "user2@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);

		userRepository.saveAll(List.of(admin, youthCouncil, moderator, user, user2));

		//MEMBERSHIPS
		membershipService.addMembershipByUserAndMunicipalityId(youthCouncil, antwerpen.getId(), Role.YOUTH_COUNCIL_ADMINISTRATOR);
		membershipService.addMembershipByUserAndMunicipalityId(moderator, antwerpen.getId(), Role.YOUTH_COUNCIL_MODERATOR);
		membershipService.addMembershipByUserAndMunicipalityId(user, antwerpen.getId(), Role.USER);
		membershipService.addMembershipByUserAndMunicipalityId(user, brussels.getId(), Role.USER);


		//THEMES AND SUBTHEMES
		Theme randomThemeWithSubThemes1 = entityFactory.createRandomThemeWithSubThemes(3);
		themeRepository.save(randomThemeWithSubThemes1);
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));
		themeRepository.save(entityFactory.createRandomThemeWithSubThemes(3));

		// Page Templates
		PageTemplate pageTemplate1 = entityFactory.createRandomPageTemplate();
		PageTemplate pageTemplate2 = entityFactory.createRandomPageTemplate();
		logger.debug("Page template 1: {}", pageTemplate1);
		logger.debug("Page template 2: {}", pageTemplate2);

		pageTemplateRepository.saveAll(List.of(pageTemplate1, pageTemplate2));


		// ACTION POINTS
		ActionPoint randomActionPoint1 = entityFactory.createRandomActionPoint();
		randomActionPoint1.setMunicipalityId(antwerpen.getId());
		randomActionPoint1.getFollowers().add(new UserActionPointFollow(user, randomActionPoint1));
		randomActionPoint1.getLikers().add(new UserActionPointLike(moderator, randomActionPoint1));
		randomActionPoint1.getLikers().add(new UserActionPointLike(user, randomActionPoint1));
		randomActionPoint1.setLikeCount(2);
		randomActionPoint1.setFollowCount(1);
		randomActionPoint1.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(0));

		ActionPoint randomActionPoint2 = entityFactory.createRandomActionPoint();
		randomActionPoint2.getFollowers().add(new UserActionPointFollow(moderator, randomActionPoint2));
		randomActionPoint2.setFollowCount(1);
		randomActionPoint2.setMunicipalityId(antwerpen.getId());
		randomActionPoint2.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(0));

		ActionPoint randomActionPoint3 = entityFactory.createRandomActionPoint();
		randomActionPoint3.setMunicipalityId(antwerpen.getId());
		randomActionPoint3.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(1));

		ActionPoint randomActionPoint4 = entityFactory.createRandomActionPoint();
		randomActionPoint4.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(2));
		randomActionPoint4.setMunicipalityId(antwerpen.getId());

		actionPointRepository.save(randomActionPoint1);
		actionPointRepository.save(randomActionPoint2);
		actionPointRepository.save(randomActionPoint3);
		actionPointRepository.save(randomActionPoint4);

		//FORMS AND QUESTIONS
		formRepository.save(entityFactory.createRandomFormWithQuestions());
		formRepository.save(entityFactory.createRandomFormWithQuestions());
		formRepository.save(entityFactory.createRandomFormWithQuestions());


		//CALENDAR OF ACTIVITIES

		CalendarActivity calendarActivity1 = entityFactory.createRandomCalendarActivity();
		calendarActivity1.setMunicipality(antwerpen);

		CalendarActivity calendarActivity2 = entityFactory.createRandomCalendarActivity();
		calendarActivity2.setMunicipality(ghent);

		CalendarActivity calendarActivity3 = entityFactory.createRandomCalendarActivity();
		calendarActivity3.setMunicipality(antwerpen);

		CalendarActivity calendarActivity4 = entityFactory.createRandomCalendarActivity();
		calendarActivity4.setMunicipality(ghent);

		calendarActivityRepository.saveAll(Arrays.asList(calendarActivity1, calendarActivity2, calendarActivity3, calendarActivity4));



	}
}
