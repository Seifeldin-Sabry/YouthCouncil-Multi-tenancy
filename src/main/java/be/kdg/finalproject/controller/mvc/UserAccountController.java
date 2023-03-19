package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserAccountController {

	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	public UserAccountController(UserService userService) {this.userService = userService;}

	@GetMapping ("/login")
	public ModelAndView showLogin(@RequestParam (name = "error", required = false) String error) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("Authentication: " + authentication);
		logger.debug("Role: " + authentication.getAuthorities().toString());
		logger.debug("Is authenticated: " + authentication.isAuthenticated());
		logger.debug("Authentication name: " + authentication.getDetails());
		if (error != null) {
			return new ModelAndView("html/login")
					.addObject("errorMessage", "Invalid email/username or password");
		}
		return new ModelAndView("html/login");
	}

	@GetMapping ("/sign-up")
	public ModelAndView showSignUp() {
		return new ModelAndView("html/sign-up", "user", new UserSignUpViewModel());
	}

	@PostMapping ("/register")
	public ModelAndView registerUser(@Valid @ModelAttribute ("user") UserSignUpViewModel userSignUpViewModel, BindingResult errors, HttpServletRequest request) throws ServletException {
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors()) {
				String defaultMessage = errors.getGlobalError().getDefaultMessage();
				errors.rejectValue("confirmPassword", "error.passwords.do.not.match",
						defaultMessage);
				errors.rejectValue("password", "error.passwords.do.not.match",
						defaultMessage);
			}
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("html/sign-up", "user", userSignUpViewModel);
		}
		User user = userService.addRegularUser(userSignUpViewModel);
		request.login(
				user.getUsername(),
				userSignUpViewModel.getPassword()
		);
		return new ModelAndView("redirect:/home");
	}
}
