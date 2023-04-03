package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.membership.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserAccountController {


	private final BCryptPasswordEncoder passwordEncoder;
	private final MembershipService membershipService;
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	public UserAccountController(BCryptPasswordEncoder passwordEncoder, MembershipService membershipService) {
		this.passwordEncoder = passwordEncoder;
		this.membershipService = membershipService;
	}

	@PreAuthorize ("isAuthenticated()")
	@GetMapping ("/my-account")
	public ModelAndView showUserDetails() {
		return new ModelAndView("account-details");
	}

	@GetMapping ("/login")
	public ModelAndView showLogin(@RequestParam (name = "error", required = false) String error, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("Authentication: " + authentication);
		logger.debug("Role: " + authentication.getAuthorities().toString());
		logger.debug("Is authenticated: " + authentication.isAuthenticated());
		logger.debug("Authentication name: " + authentication.getDetails());
		if (error != null) {
			return new ModelAndView("login")
					.addObject("errorMessage", "Invalid email/username or password");
		}
		return new ModelAndView("login");
	}

	@GetMapping ("/sign-up")
	public ModelAndView showSignUp() {
		if (MunicipalityContext.getCurrentMunicipality() == null) {
			return new ModelAndView("redirect:/");
		}
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
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(), userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(), passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.USER, Provider.LOCAL);
		membershipService.addMembershipByUserAndMunicipalityId(user, MunicipalityContext.getCurrentMunicipalityId(), Role.USER);
		request.login(
				user.getUsername(),
				userSignUpViewModel.getPassword()
		);
		return new ModelAndView("redirect:/home");
	}

}
