package be.kdg.finalproject.config.security;

import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final UserService userService;

	public OAuthSuccessHandler(UserService userService) {
		this.userService = userService;
	}

	//TODO: fix this
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String url = request.getRequestURL().toString();
		Provider provider = url.contains("google") ? Provider.GOOGLE : Provider.FACEBOOK;
		CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
		logger.debug("{}", oauthUser.getAttributes());
		if (provider == Provider.GOOGLE) {
			userService.processOAuthPostLoginGoogle(oauthUser.getEmail(), oauthUser.getGivenName(), oauthUser.getFamilyName(), provider, MunicipalityContext.getCurrentMunicipalityId());
		} else {
			userService.processOAuthPostLoginFaceBook(oauthUser.getEmail(), oauthUser.getName(), provider, MunicipalityContext.getCurrentMunicipalityId());
		}
		String redirectUrl = "/";
		response.sendRedirect(redirectUrl);
	}
}
