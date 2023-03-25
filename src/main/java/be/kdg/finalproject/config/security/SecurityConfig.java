package be.kdg.finalproject.config.security;

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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomOAuth2UserService oauthUserService;
	private final OAuthSuccessHandler oAuthSuccessHandler;

	@Autowired
	public SecurityConfig(CustomOAuth2UserService oauthUserService, OAuthSuccessHandler oAuthSuccessHandler) {
		this.oauthUserService = oauthUserService;
		this.oAuthSuccessHandler = oAuthSuccessHandler;
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
				.accessDeniedPage("/error/access-denied.html");

		return http.build();
	}

	private AuthenticationEntryPoint httpStatusEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}
}
