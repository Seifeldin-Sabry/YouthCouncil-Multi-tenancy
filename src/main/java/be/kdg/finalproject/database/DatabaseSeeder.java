package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.page.PageTemplate;
import be.kdg.finalproject.domain.platform.ElectionPhase;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.platform.PostCode;
import be.kdg.finalproject.domain.report.Report;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;
import be.kdg.finalproject.repository.callforidea.CallForIdeasRepository;
import be.kdg.finalproject.repository.form.*;
import be.kdg.finalproject.repository.callforidea.IdeaRepository;
import be.kdg.finalproject.repository.membership.UserRepository;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import be.kdg.finalproject.repository.municipality.PostCodeRepository;
import be.kdg.finalproject.repository.page.PageTemplateRepository;
import be.kdg.finalproject.repository.report.ReportRepository;
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
@Profile ({"dev", "devpsql"})
public class DatabaseSeeder {

	private final ThemeRepository themeRepository;
	private final FormRepository formRepository;
	private final UserRepository userRepository;
	private final MembershipService membershipService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EntityFactory entityFactory;
	private final MunicipalityRepository municipalityRepository;
	private final PostCodeRepository postCodeRepository;
	private final CallForIdeasRepository callForIdeasRepository;
	private final IdeaRepository ideaRepository;

	private final ActionPointRepository actionPointRepository;
	private final TextInputQuestionRepository textInputQuestionRepository;
	private final RadioQuestionRepository radioQuestionRepository;
	private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
	private final NumericInputQuestionRepository numericInputQuestionRepository;
	private final PageTemplateRepository pageTemplateRepository;
	private final CalendarActivityRepository calendarActivityRepository;

	private final ReportRepository reportRepository;

	private final MunicipalitySeeder municipalitySeeder;

	private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

	@Autowired
	public DatabaseSeeder(ThemeRepository themeRepository, FormRepository formRepository, UserRepository userRepository, MembershipService membershipService, BCryptPasswordEncoder passwordEncoder, EntityFactory entityFactory, MunicipalityRepository municipalityRepository, PostCodeRepository postCodeRepository, CallForIdeasRepository callForIdeasRepository, IdeaRepository ideaRepository, ActionPointRepository actionPointRepository, TextInputQuestionRepository textInputQuestionRepository, RadioQuestionRepository radioQuestionRepository, MultipleChoiceQuestionRepository multipleChoiceQuestionRepository, NumericInputQuestionRepository numericInputQuestionRepository, PageTemplateRepository pageTemplateRepository, CalendarActivityRepository calendarActivityRepository, ReportRepository reportRepository, MunicipalitySeeder municipalitySeeder) {
		this.themeRepository = themeRepository;
		this.formRepository = formRepository;
		this.userRepository = userRepository;
		this.membershipService = membershipService;
		this.passwordEncoder = passwordEncoder;
		this.entityFactory = entityFactory;
		this.municipalityRepository = municipalityRepository;
		this.postCodeRepository = postCodeRepository;
		this.callForIdeasRepository = callForIdeasRepository;
		this.ideaRepository = ideaRepository;
		this.actionPointRepository = actionPointRepository;
		this.pageTemplateRepository = pageTemplateRepository;
		this.calendarActivityRepository = calendarActivityRepository;
		this.textInputQuestionRepository = textInputQuestionRepository;
		this.radioQuestionRepository = radioQuestionRepository;
		this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
		this.numericInputQuestionRepository = numericInputQuestionRepository;
		this.reportRepository = reportRepository;
		this.municipalitySeeder = municipalitySeeder;
	}

	@PostConstruct
	public void seed() {
		municipalitySeeder.seed();
		//MUNICIPALITIES & POSTCODES
		Municipality antwerpen = municipalityRepository.findByNameIgnoreCase("antwerp").get();
		Municipality bruges = municipalityRepository.findByNameIgnoreCase("bruges").get();

		// USERS
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		User user2 = new User("user2", "user2", "user2", "user2@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		User offlineUser= new User("offline", "idea", "offline_idea", "offline@idea.user", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);

		userRepository.saveAll(List.of(admin, youthCouncil, moderator, user, user2, offlineUser));

		//MEMBERSHIPS
		membershipService.addMembershipByUserAndMunicipalityId(youthCouncil, antwerpen.getId(), Role.YOUTH_COUNCIL_ADMINISTRATOR);
		membershipService.addMembershipByUserAndMunicipalityId(moderator, antwerpen.getId(), Role.YOUTH_COUNCIL_MODERATOR);
		membershipService.addMembershipByUserAndMunicipalityId(user, antwerpen.getId(), Role.USER);
		membershipService.addMembershipByUserAndMunicipalityId(user2, antwerpen.getId(), Role.USER);
		membershipService.addMembershipByUserAndMunicipalityId(user, bruges.getId(), Role.USER);


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
		calendarActivity2.setMunicipality(bruges);

		CalendarActivity calendarActivity3 = entityFactory.createRandomCalendarActivity();
		calendarActivity3.setMunicipality(antwerpen);

		CalendarActivity calendarActivity4 = entityFactory.createRandomCalendarActivity();
		calendarActivity4.setMunicipality(bruges);

		calendarActivityRepository.saveAll(Arrays.asList(calendarActivity1, calendarActivity2, calendarActivity3, calendarActivity4));


		// CALL FOR IDEAS AND IDEAS
		CallForIdeas callForIdeas1 = entityFactory.createRandomCallForIdeas();
		CallForIdeas callForIdeas2 = entityFactory.createRandomCallForIdeas();

		callForIdeas1.setActive(true);

		callForIdeas1.setMunicipality(antwerpen);
		callForIdeas2.setMunicipality(bruges);

		callForIdeas1.setTheme(randomThemeWithSubThemes1);
		callForIdeas2.setTheme(randomThemeWithSubThemes1);

		Idea idea1 = entityFactory.createRandomIdea();
		Idea idea2 = entityFactory.createRandomIdea();
		Idea idea3 = entityFactory.createRandomIdea();
		idea1.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(0));
		idea2.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(1));
		idea3.setSubTheme(randomThemeWithSubThemes1.getSubThemes().get(2));

		user.setIdeas(Set.of(idea1, idea2, idea3));
		userRepository.save(user);
		idea1.setCreator(user);
		idea2.setCreator(user);
		idea3.setCreator(user);

		callForIdeasRepository.save(callForIdeas1);
		callForIdeasRepository.save(callForIdeas2);

		idea1.setCallForIdeasId(callForIdeas1.getId());
		idea2.setCallForIdeasId(callForIdeas1.getId());
		idea3.setCallForIdeasId(callForIdeas2.getId());

		ideaRepository.save(idea1);
		ideaRepository.save(idea2);
		ideaRepository.save(idea3);

		Set<Idea> ideas = new HashSet<>();
		ideas.add(idea1);
		ideas.add(idea2);

		Set<Idea> ideas2 = new HashSet<>();
		ideas2.add(idea3);

		callForIdeas1.setIdeas(ideas);
		callForIdeas2.setIdeas(ideas2);

		callForIdeasRepository.save(callForIdeas1);
		callForIdeasRepository.save(callForIdeas2);

		Report report1 = entityFactory.createRandomReport();
		Report report2 = entityFactory.createRandomReport();
		Report report3 = entityFactory.createRandomReport();
		Report report4 = entityFactory.createRandomReport();

		report1.setIdeaId(idea1.getId());
		report2.setIdeaId(idea2.getId());
		report3.setIdeaId(idea3.getId());
		report4.setIdeaId(idea1.getId());

		reportRepository.save(report1);
		reportRepository.save(report2);
		reportRepository.save(report3);
		reportRepository.save(report4);


	}
}
