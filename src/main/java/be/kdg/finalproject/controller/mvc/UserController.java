package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.membership.MembershipService;
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

	private final MembershipService membershipService;
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	@GetMapping
	@YouthCouncilAdmin
	public ModelAndView showAllUsersOfMunicipality(@MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			logger.error("MunicipalityId is null");
			throw new EntityNotFoundException("MunicipalityId is null");
		}
		List<Membership> membershipMembers = membershipService.getMembershipsByMunicipalityIdWhereNotAdmin(municipalityId);
		return new ModelAndView("municipality/municipality-users")
				.addObject("users", membershipMembers);
	}
}
