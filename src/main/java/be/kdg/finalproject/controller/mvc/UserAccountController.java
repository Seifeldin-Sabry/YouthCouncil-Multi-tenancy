package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserAccountController {


	private final BCryptPasswordEncoder passwordEncoder;
	private final MembershipService membershipService;
	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserAccountController(BCryptPasswordEncoder passwordEncoder, MembershipService membershipService, UserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.membershipService = membershipService;
		this.userService = userService;
	}

	@PreAuthorize ("isAuthenticated()")
	@GetMapping ("/my-account")
	public ModelAndView showUserDetails() {
		return new ModelAndView("account-details");
	}

	//TODO: fix admin login
	@GetMapping ("/login")
	public ModelAndView showLogin(@RequestParam (name = "error", required = false) String error, HttpSession session, @PathVariable (required = false) String municipality) {
		if (error != null) {
			return new ModelAndView("login")
					.addObject("errorMessage", "Invalid email/username or password");
		}
		return new ModelAndView("login");
	}

	@GetMapping ("/sign-up")
	public ModelAndView showSignUp(@PathVariable (required = false) String municipality) {
		return new ModelAndView("sign-up", "user", new UserSignUpViewModel());
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
			return new ModelAndView("sign-up", "user", userSignUpViewModel);
		}
		User persistedUser = userService.addUser(userSignUpViewModel);
		membershipService.addMembershipByUserAndMunicipalityId(persistedUser, MunicipalityContext.getCurrentMunicipalityId(), Role.USER);
		request.login(
				persistedUser.getUsername(),
				userSignUpViewModel.getPassword()
		);
		return new ModelAndView("redirect:/home");
	}

}
