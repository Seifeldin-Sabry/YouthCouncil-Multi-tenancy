package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.mvc.viewmodel.UserLoginViewModel;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {

	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	public UserAccountController(UserService userService) {this.userService = userService;}

	@GetMapping ("/login")
	public ModelAndView showLogin() {
		return new ModelAndView("html/login", "user", new UserLoginViewModel());
	}

	@PostMapping ("/login")
	public ModelAndView loginUser(@Valid @ModelAttribute ("user") UserLoginViewModel userLoginViewModel, BindingResult errors, HttpSession session) {
		logger.info("Logging in user...");
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("html/login", "user", userLoginViewModel);
		}

		return new ModelAndView("redirect:/home");
	}

	@GetMapping ("/sign-up")
	public ModelAndView showSignUp() {
		return new ModelAndView("html/sign-up", "user", new UserSignUpViewModel());
	}

	@PostMapping ("/register")
	public ModelAndView registerUser(@Valid @ModelAttribute ("user") UserSignUpViewModel userSignUpViewModel, BindingResult errors, HttpServletRequest request) {
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors())
				errors.rejectValue("confirmPassword", "error.passwords.do.not.match",
						errors.getGlobalError().getDefaultMessage());

			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("html/sign-up", "user", userSignUpViewModel);
		}
		User user = userService.addRegularUser(userSignUpViewModel);
		try {
			request.login(
					user.getUsername(),
					userSignUpViewModel.getPassword()
			);
		} catch (ServletException e) {
			//			TODO: handle exception
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:/home");
	}
}
