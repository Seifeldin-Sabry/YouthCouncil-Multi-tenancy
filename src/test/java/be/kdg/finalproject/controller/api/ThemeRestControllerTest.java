package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.config.security.CustomUserDetails;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.repository.theme.ThemeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ThemeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ThemeRepository themeRepository;
	private Long themeId;

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
	void getSubthemesWithCorrectThemeIdShouldReturnOkAndReturnSubthemes() throws Exception {
		var authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.ADMINISTRATOR.getAuthority()));
		var user = new CustomUserDetails(1L, "test", "test@mail", "test", authorities);
		// Act
		mockMvc.perform(get("/api/themes/{themeId}/subthemes", themeId)
				       .with(csrf())
				       .with(user(user)))
		       // Assert
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.length()", equalTo(3)))
		       .andExpect(jsonPath("$[*].subThemeName", containsInAnyOrder("testSub1", "testSub2", "testSub3")));
	}
}
