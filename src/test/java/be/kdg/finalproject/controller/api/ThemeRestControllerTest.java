package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.repository.theme.ThemeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class ThemeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ThemeRepository themeRepository;
	private Long themeId;


	@BeforeAll
	void beforeAll() {
		themeRepository.deleteAll();
	}

	@BeforeEach
	void setUp() {
		Theme theme = new Theme("test");
		theme.addSubTheme(new SubTheme("testSub1"));
		theme.addSubTheme(new SubTheme("testSub2"));
		theme.addSubTheme(new SubTheme("testSub3"));
		themeRepository.save(theme);
		themeId = theme.getId();
	}

	@AfterEach
	void tearDown() {
		themeRepository.deleteAll();
	}

	@Test
	@WithUserDetails (value = "admin", userDetailsServiceBeanName = "customUserDetailsService")
	void getSubthemesWithCorrectThemeIdShouldReturnOkAndReturnSubthemes() throws Exception {
		// Act
		mockMvc.perform(get("/api/themes/{themeId}/subthemes", themeId)
				       .with(csrf()))
		       // Assert
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.length()", equalTo(3)))
		       .andExpect(jsonPath("$[*].subThemeName", containsInAnyOrder("testSub1", "testSub2", "testSub3")));
	}
}
