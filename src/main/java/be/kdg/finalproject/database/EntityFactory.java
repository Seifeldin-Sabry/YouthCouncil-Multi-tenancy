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

	public CalendarActivity createRandomCalendarActivity(Municipality municipality) {
		CalendarActivity calendarActivity = new CalendarActivity(
				faker.lorem().sentence(), // title
				faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), // date
				LocalDateTime.of(faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), // start time
						LocalTime.of(faker.number().numberBetween(0, 23), faker.number().numberBetween(0, 59))), // random time between 00:00 and 23:59
				LocalDateTime.of(faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), // end time
						LocalTime.of(faker.number().numberBetween(0, 23), faker.number().numberBetween(0, 59)).plusHours(faker.number().numberBetween(1, 4))), // add a random duration of 1 to 4 hours
				faker.lorem().paragraph() // description
		);
		calendarActivity.setMunicipality(municipality);
		return calendarActivity;
	}



}
