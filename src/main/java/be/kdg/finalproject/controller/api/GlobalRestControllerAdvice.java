package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.config.security.CustomOAuth2User;
import be.kdg.finalproject.config.security.CustomUserDetails;
import be.kdg.finalproject.controller.mvc.GlobalControllerAdvisor;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.service.user.UserService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvisor.class);
	private final UserService userService;

	public GlobalRestControllerAdvice(UserService userService) {this.userService = userService;}

	@ModelAttribute
	public void addAttributes(Model model, HttpSession session, Authentication authentication, HttpServletRequest request) {
		if (authentication == null) return;
		User user;
		if (authentication.getPrincipal() instanceof CustomOAuth2User) {
			user = userService.getUserByUsernameOrEmail(((CustomOAuth2User) authentication.getPrincipal()).getEmail());
		} else {
			user = userService.getUserByUsernameOrEmail(((CustomUserDetails) authentication.getPrincipal()).getUsername());
		}
		model.addAttribute("authUser", user);
	}

	@ExceptionHandler (EntityNotFoundException.class)
	public ResponseEntity<?> showEntityNotFound() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler (value = {SizeLimitExceededException.class})
	public ResponseEntity<String> handleSizeLimitExceeded() {
		return new ResponseEntity<>("File size exceeded", HttpStatus.PAYLOAD_TOO_LARGE);
	}
}
