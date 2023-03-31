package be.kdg.finalproject.config.security;

import be.kdg.finalproject.municipalities.MunicipalityAuthorizationFilter;
import be.kdg.finalproject.municipalities.MunicipalityFilter;
import be.kdg.finalproject.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomOAuth2UserService oauthUserService;
	private final OAuthSuccessHandler oAuthSuccessHandler;
	private final MunicipalityRepository municipalityRepository;

	@Autowired
	public SecurityConfig(CustomOAuth2UserService oauthUserService, OAuthSuccessHandler oAuthSuccessHandler, MunicipalityRepository municipalityRepository) {
		this.oauthUserService = oauthUserService;
		this.oAuthSuccessHandler = oAuthSuccessHandler;
		this.municipalityRepository = municipalityRepository;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.authenticationEntryPoint(httpStatusEntryPoint())
				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.addFilterBefore(new MunicipalityFilter(municipalityRepository), UsernamePasswordAuthenticationFilter.class)
				.addFilterAfter(new MunicipalityAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(
						auths ->
								auths
										.antMatchers(HttpMethod.POST, "/register", "/login", "/logout")
										.permitAll()
										.antMatchers("/api/**")
										.authenticated()
										.antMatchers(HttpMethod.GET, "/**")
										.permitAll()
										.anyRequest()
										.authenticated()
				)
				.sessionManagement() // enable session management
				.sessionFixation()
				.migrateSession() // set session fixation strategy
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(oauthUserService)
				.and()
				.successHandler(oAuthSuccessHandler)
				.and()
				.exceptionHandling()
				.accessDeniedPage("/error/access-denied.html")
				.and();

		return http.build();
	}

	private AuthenticationEntryPoint httpStatusEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}
}
