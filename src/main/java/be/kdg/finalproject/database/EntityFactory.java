package be.kdg.finalproject.database;

import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
}
