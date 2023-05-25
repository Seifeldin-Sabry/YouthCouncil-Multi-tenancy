package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.actionpoint.ActionPointProposal;
import be.kdg.finalproject.domain.actionpoint.ActionPointProposalStatus;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.form.*;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.report.Report;
import be.kdg.finalproject.domain.report.ReportReason;
import be.kdg.finalproject.domain.page.PageTemplate;
import be.kdg.finalproject.domain.page.template.TemplateElement;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
@Profile ({"dev", "devpsql"})
public class EntityFactory {

	private final Faker faker = new Faker();


	public Theme createRandomThemeWithSubThemes(int amount) {
		Theme theme = createRandomTheme();
		for (int i = 0; i < amount; i++) {
			theme.addSubTheme(createRandomSubTheme());
		}
		return theme;
	}

	public Report createRandomReport(){
		Report report = new Report();
		report.setReportDescription(faker.lorem().sentence());
		report.setReportReason(ReportReason.values()[faker.random().nextInt(0, 3)]);
		return report;
	}

	private Theme createRandomTheme() {
		return new Theme(faker.job().field());
	}

	private SubTheme createRandomSubTheme() {
		return new SubTheme(faker.job().position());
	}


	public Form createRandomFormWithQuestions() {
		Form form = createRandomForm();
		for (int i = 0; i < 5; i++) {
			form.addQuestion(createRandomQuestion(i));
		}
		return form;
	}

	public CallForIdeas createRandomCallForIdeas() {
		return new CallForIdeas(faker.lorem().sentence(), faker.lorem().sentence());
	}

	public String randomImage(){
		return String.format("https://loremflickr.com/320/240?random=%s", faker.random()
		                                                                       .nextInt(1, 100));
	}

	public Idea createRandomIdea() {
		Idea idea = new Idea(faker.lorem().sentence(),
				String.format("https://loremflickr.com/320/240?random=%s", faker.random()
				                                                                .nextInt(1, 100)));
		idea.setDateCreated(Timestamp.valueOf(faker.date().past(100, java.util.concurrent.TimeUnit.DAYS)
		                                           .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
		return idea;
	}

	public ActionPoint createRandomActionPoint() {
		ActionPoint actionPoint = new ActionPoint(faker.lorem().sentence(), faker.lorem().sentence());
		actionPoint.getImages()
		           .addAll(List.of(String.format("https://loremflickr.com/320/240?random=%s", faker.random()
		                                                                                           .nextInt(1, 100)),
				           String.format("https://loremflickr.com/320/240?random=%s", faker.random().nextInt(1, 100)),
				           String.format("https://loremflickr.com/320/240?random=%s", faker.random().nextInt(1, 100)),
				           String.format("https://loremflickr.com/320/240?random=%s", faker.random().nextInt(1, 100)),
				           String.format("https://loremflickr.com/320/240?random=%s", faker.random().nextInt(1, 100))));
		var date = faker.date().past(100, java.util.concurrent.TimeUnit.DAYS);
		actionPoint.setDateCreated(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		for (int i = 0; i < 3; i++) {
			actionPoint.addProposal(createRandomActionPointProposal());
		}
		return actionPoint;
	}


	public PageTemplate createRandomPageTemplate() {
		return new PageTemplate(faker.funnyName().name(), createRandomPageTemplateElements());
	}

	private List<TemplateElement> createRandomPageTemplateElements() {
		return Stream.generate(this::createRandomPageTemplateElement)
		             .limit(faker.random().nextInt(3, 10))
		             .toList();
	}

	private TemplateElement createRandomPageTemplateElement() {
		return faker.options().option(TemplateElement.class);
	}

	private ActionPointProposal createRandomActionPointProposal() {
		return new ActionPointProposal(faker.options().option(ActionPointProposalStatus.class), faker.lorem()
		                                                                                             .sentence(faker.random()
		                                                                                                            .nextInt(1, 200)));
	}

	private Form createRandomForm() {
		return new Form(faker.job().title());
	}

	private Question createRandomQuestion(final Integer order) {
		final QuestionType questionType = QuestionType.values()[faker.random()
		                                                             .nextInt(0, QuestionType.values().length - 1)];
		return switch (questionType) {
			case MULTIPLE_CHOICE_QUESTION -> randomMultipleChoiceQuestion(order);
			case RADIO_QUESTION -> randomRadioQuestion(order);
			case TEXT_QUESTION -> randomTextQuestion(order);
			case NUMBER_QUESTION -> randomNumericQuestion(order);
		};
	}

	private TextInputQuestion randomTextQuestion(Integer order) {
		return new TextInputQuestion(faker.lorem().sentence(), faker.bool().bool(), order + 1);
	}

	private RadioQuestion randomRadioQuestion(final Integer order) {
		List<String> answers = faker.lorem().words(4);
		return new RadioQuestion(faker.lorem().sentence(), faker.bool().bool(), order + 1, answers);
	}

	private MultipleChoiceQuestion randomMultipleChoiceQuestion(final Integer order) {
		List<String> answers = faker.lorem().words(4);
		return new MultipleChoiceQuestion(faker.lorem().sentence(), faker.bool().bool(), order + 1, answers);
	}

	private NumericInputQuestion randomNumericQuestion(Integer order) {
		return new NumericInputQuestion(faker.lorem().sentence(), faker.bool().bool(), order + 1);
	}

	public CalendarActivity createRandomCalendarActivity() {
		LocalDate activityDate = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
		                              .toLocalDate();
		LocalTime activityStartTime = LocalTime.from(LocalDateTime.of(activityDate, LocalTime.of(faker.number()
		                                                                                              .numberBetween(0, 23), faker.number().numberBetween(0, 59))));
		LocalTime activityEndTime = activityStartTime.plusHours(faker.number().numberBetween(1, 4));
		String activityTitle = faker.lorem().sentence();
		String activityDescription = faker.lorem().sentence();

		return new CalendarActivity(activityTitle, activityDate, activityStartTime, activityEndTime, activityDescription);
	}
}
