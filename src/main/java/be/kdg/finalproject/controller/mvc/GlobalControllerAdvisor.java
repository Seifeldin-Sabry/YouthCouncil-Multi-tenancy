package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvisor {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvisor.class);
	private final UserService userService;

	public GlobalControllerAdvisor(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsernameOrEmail(authentication.getName());
		if (user == null) {
			logger.debug("User not found");
			return;
		}
		logger.debug("User found: " + user.getUsername());
		model.addAttribute("authUser", user);
	}
}
