package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.form.*;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Profile ("dev")
public class EntityFactory {

	private final Faker faker = new Faker();


	public Theme createRandomThemeWithSubThemes(int amount) {
		Theme theme = createRandomTheme();
		for (int i = 0; i < amount; i++) {
			theme.addSubTheme(createRandomSubTheme());
		}
		return theme;
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

	public ActionPoint createRandomActionPoint() {
		ActionPoint actionPoint = new ActionPoint(faker.lorem().sentence(), faker.lorem().sentence());
		actionPoint.getImages()
		           .addAll(List.of("/images/tree.jpeg", "/images/sky.jpeg", "/images/pexels.jpeg"));
		return actionPoint;
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


	//Checks if any of the parameters (title, date, startTime, endTime, description) are null,
	// and generates random values for them if they are null. Otherwise, it uses the provided values.
	public CalendarActivity createRandomCalendarActivity(String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description, Municipality municipality) {
		LocalDate activityDate = date == null ? faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : date;
		LocalDateTime activityStartTime = startTime == null ? LocalDateTime.of(activityDate, LocalTime.of(faker.number().numberBetween(0, 23), faker.number().numberBetween(0, 59))) : startTime;
		LocalDateTime activityEndTime = endTime == null ? activityStartTime.plusHours(faker.number().numberBetween(1, 4)) : endTime;
		String activityTitle = title == null ? faker.lorem().sentence() : title;
		String activityDescription = description == null ? faker.lorem().paragraph() : description;

		CalendarActivity calendarActivity = new CalendarActivity(activityTitle, activityDate, activityStartTime, activityEndTime, activityDescription);
		calendarActivity.setMunicipality(municipality);

		return calendarActivity;
	}




}
