package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.config.security.CustomOAuth2User;
import be.kdg.finalproject.config.security.CustomUserDetails;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvisor {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvisor.class);
	private final UserService userService;
	private final MembershipService membershipService;

	public GlobalControllerAdvisor(UserService userService, MembershipService membershipService) {
		this.userService = userService;
		this.membershipService = membershipService;
	}

	@ModelAttribute
	public void addAttributes(Model model, HttpSession session, Authentication authentication, HttpServletRequest request) {
		if (authentication == null) return;
		User user;
		if (authentication.getPrincipal() instanceof CustomOAuth2User) {
			user = userService.getUserByUsernameOrEmail(((CustomOAuth2User) authentication.getPrincipal()).getEmail());
		} else {
			user = userService.getUserByUsernameOrEmail(((CustomUserDetails) authentication.getPrincipal()).getUsername());
		}
		if (session.getAttribute("memberships") == null) {
			session.setAttribute("memberships", membershipService.getAllMembershipsByUser(user));
		}
		if (session.getAttribute("userId") == null) {
			session.setAttribute("userId", user.getId());
		}
		logger.debug("User: {}", user);
		Membership currentMembership = membershipService.getMembershipByUserAndMunicipalityName(user, getMunicipality(request));
		logger.debug("Current membership: {}", currentMembership);
		logger.debug("Current municipality ID: {}", getMunicipality(request));
		model.addAttribute("authUser", user);
		model.addAttribute("memberships", session.getAttribute("memberships"));
		model.addAttribute("currentMembership", currentMembership);
	}

	@ExceptionHandler (AccessDeniedException.class)
	public ModelAndView showAccessDenied() {
		logger.debug("Access denied");
		return new ModelAndView("error/access-denied");
	}

	@ExceptionHandler (EntityNotFoundException.class)
	public ModelAndView showEntityNotFound() {
		return new ModelAndView("error/404");
	}

	private String getMunicipality(HttpServletRequest request) {
		var domain = request.getServerName();
		var dotIndex = domain.indexOf(".");
		logger.debug("domain: " + domain);
		if (dotIndex != -1) {
			logger.debug("dotIndex: " + domain.substring(0, dotIndex));
			return domain.substring(0, dotIndex);
		}
		return "";
	}
}
