package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.config.security.CustomOAuth2User;
import be.kdg.finalproject.config.security.CustomUserDetails;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.exceptions.NoPlatformException;
import be.kdg.finalproject.exceptions.UserBannedException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.SessionService;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
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

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvisor {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvisor.class);
	private final UserService userService;
	private final MembershipService membershipService;
	private final MunicipalityService municipalityService;
	private final SessionService sessionService;
	private boolean isFirstTimeMembershipFlag = true;
	private boolean isFirstTimeMunicipalityFlag = true;
	private Object currentPrincipal;

	public GlobalControllerAdvisor(UserService userService, MembershipService membershipService, MunicipalityService municipalityService, SessionService sessionService) {
		this.userService = userService;
		this.membershipService = membershipService;
		this.municipalityService = municipalityService;
		this.sessionService = sessionService;
	}

	@ModelAttribute
	public void addAttributes(Model model, Authentication authentication, HttpSession session) {
		Long currentMunicipalityId = MunicipalityContext.getCurrentMunicipalityId();
		boolean isMunicipalityChanged = sessionService.isMunicipalityChanged(session, currentMunicipalityId);
		model.addAttribute("currentMunicipality", MunicipalityContext.getCurrentMunicipality());
		model.addAttribute("munName", MunicipalityContext.getCurrentMunicipalityName());
		if (authentication == null) return;
		boolean isInSession = sessionService.isUserInSession();
		User user;
		boolean credentialsChanged = sessionService.isCredentialsChanged() || currentPrincipal == null || !currentPrincipal.equals(authentication.getPrincipal());
		if (!isInSession || credentialsChanged) {
			currentPrincipal = authentication.getPrincipal();
			if (authentication.getPrincipal() instanceof CustomOAuth2User) {
				user = userService.getUserByUsernameOrEmail(((CustomOAuth2User) authentication.getPrincipal()).getEmail());
			} else {
				user = userService.getUserByUsernameOrEmail(((CustomUserDetails) authentication.getPrincipal()).getUsername());
			}
			sessionService.setUserInSession(session, user);
		}
		user = sessionService.getUser(session);
		model.addAttribute("authUser", user);
		if (isMunicipalityChanged || isFirstTimeMembershipFlag || credentialsChanged) {
			sessionService.setCurrentMunicipalityId(session, currentMunicipalityId);
			sessionService.setCurrentMembership(session, membershipService.getMembershipByUserAndMunicipalityName(user, MunicipalityContext.getCurrentMunicipalityName()));
			isFirstTimeMembershipFlag = false;
		}
		model.addAttribute("currentMembership", sessionService.getCurrentMembership(session));
		logger.debug("Current membership: " + sessionService.getCurrentMembership(session));
		logger.debug("Current municipality: " + MunicipalityContext.getCurrentMunicipality());
		logger.debug("Current municipality id: " + MunicipalityContext.getCurrentMunicipalityId());
	}

	@ExceptionHandler (AccessDeniedException.class)
	public ModelAndView showAccessDenied() {
		logger.debug("Access denied");
		return new ModelAndView("error/access-denied");
	}

	@ExceptionHandler (EntityNotFoundException.class)
	public ModelAndView showEntityNotFound() {
		logger.debug("Entity not found");
		return new ModelAndView("error/404");
	}

	@ExceptionHandler (NoPlatformException.class)
	public ModelAndView showNoPlatform() {
		return new ModelAndView("error/no-platform");
	}

	@ExceptionHandler (UserBannedException.class)
	public ModelAndView showUserBanned() {
		return new ModelAndView("error/user-banned");
	}

}
