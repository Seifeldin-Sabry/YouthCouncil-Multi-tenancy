package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.api.dto.get.UserDto;
import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	private final UserService userService;

	private final MembershipService membershipService;
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	public UserAccountController(UserService userService, MembershipService membershipService) {
		this.userService = userService;
		this.membershipService = membershipService;
	}

	@GetMapping("/users/{userId}")
	public ModelAndView showUserDetails(@PathVariable long userId){
		ModelAndView modelAndView = new ModelAndView("html/user-details");
		User user = userService.getUserByID(userId);
		modelAndView.addObject("user", user);
		modelAndView.addObject("memberships", membershipService.getAllMembershipsByUser(user));
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//		User user = userService.getUserByUsernameOrEmail(authentication.getName());
		//		if (user == null) {
		//			logger.debug("User not found");
		//			return;
		//		}
		//		logger.debug("User found: " + user.getUsername());
		//		modelAndView.addObject("authUser", user);
		return modelAndView;
	}

	@GetMapping("/userManagement")
	public ModelAndView showUserManagement(){
		ModelAndView modelAndView = new ModelAndView("html/user-management");
		modelAndView.addObject("users", userService.getAllUsers());
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.getUserByUsernameOrEmail(authentication.getName());
//		if (user == null) {
//			logger.debug("User not found");
//			return;
//		}
//		logger.debug("User found: " + user.getUsername());
//		modelAndView.addObject("authUser", user);
		return modelAndView;
	}

	@GetMapping("/superUserManagement")
	public ModelAndView showSuperUserManagement(){
		ModelAndView modelAndView = new ModelAndView("html/super-user-management");
		modelAndView.addObject("YCAdmins", userService.getUsersByRole(Role.YOUTH_COUNCIL_ADMINISTRATOR));
		modelAndView.addObject("YCModerators", userService.getUsersByRole(Role.YOUTH_COUNCIL_MODERATOR));
		modelAndView.addObject("user", new UserSignUpViewModel());
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//		User user = userService.getUserByUsernameOrEmail(authentication.getName());
		//		if (user == null) {
		//			logger.debug("User not found");
		//			return;
		//		}
		//		logger.debug("User found: " + user.getUsername());
		//		modelAndView.addObject("authUser", user);
		return modelAndView;
	}

	@GetMapping ("/login")
	public ModelAndView showLogin(@RequestParam (name = "error", required = false) String error, HttpSession session) {
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
