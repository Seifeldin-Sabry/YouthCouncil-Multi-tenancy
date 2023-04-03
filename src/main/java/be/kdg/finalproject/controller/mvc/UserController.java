package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping ("/youth-council-dashboard/users")
public class UserController {

	private final UserService userService;
	private final MembershipService membershipService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService, MembershipService membershipService) {
		this.userService = userService;
		this.membershipService = membershipService;
	}

	@GetMapping
	@YouthCouncilAdmin
	public ModelAndView showAllUsersOfMunicipality(@MunicipalityId Long municipalityId) {
		List<Membership> membershipMembers = membershipService.getMembershipsByMunicipalityIdWhereNotAdmin(municipalityId);
		return new ModelAndView("municipality/municipality-users")
				.addObject("users", membershipMembers);
	}

	@GetMapping ("/superUserManagement")
	public ModelAndView showSuperUserManagement() {
		ModelAndView modelAndView = new ModelAndView("super-user-management");
		modelAndView.addObject("YCAdmins", userService.getUsersByRole(Role.YOUTH_COUNCIL_ADMINISTRATOR));
		modelAndView.addObject("YCModerators", userService.getUsersByRole(Role.YOUTH_COUNCIL_MODERATOR));
		modelAndView.addObject("user", new UserSignUpViewModel());
		return modelAndView;
	}
}
