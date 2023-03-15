package be.kdg.finalproject.config.security;

import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.service.user.UserService;
import jakarta.persistence.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Cacheable (false)
public class SecurityConfig {

	private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	private final CustomOAuth2UserService oauthUserService;
	private final UserService userService;

	@Autowired
	public SecurityConfig(CustomOAuth2UserService oauthUserService, UserService userService) {
		this.oauthUserService = oauthUserService;
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.authenticationEntryPoint(httpStatusEntryPoint())
				.and()
				.csrf()
				.csrfTokenRepository(new CookieCsrfTokenRepository())
				.and()
				.authorizeHttpRequests(
						auths ->
								auths
										.requestMatchers("/**", "/sign-up", "/login", "/oauth/**", "/error**", "/webjars/**", "/css/**", "/js/**", "/img/**")
										.permitAll()
				)
				.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(oauthUserService)
				.and()
				.successHandler((request, response, authentication) -> {
					String url = request.getRequestURL().toString();
					Provider provider = url.contains("google") ? Provider.GOOGLE : Provider.FACEBOOK;
					CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
					logger.debug("{}", oauthUser.getAttributes());
					if (provider == Provider.GOOGLE) {
						userService.processOAuthPostLoginGoogle(oauthUser.getEmail(), oauthUser.getGivenName(), oauthUser.getFamilyName(), provider);
					} else {
						userService.processOAuthPostLoginFaceBook(oauthUser.getEmail(), oauthUser.getName(), provider);
					}
					response.sendRedirect("/home");
				});

		return http.build();
	}

	private AuthenticationEntryPoint httpStatusEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
