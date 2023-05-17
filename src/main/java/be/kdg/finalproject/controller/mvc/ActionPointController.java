package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.SessionService;
import be.kdg.finalproject.service.actionpoints.ActionPointService;
import be.kdg.finalproject.service.theme.SubThemeService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping ("/{municipality}")
public class ActionPointController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);
	private final ActionPointService actionPointService;
	private final SubThemeService subThemeService;

	private final SessionService sessionService;

	public ActionPointController(ActionPointService actionPointService, SubThemeService subThemeService, SessionService sessionService) {
		this.actionPointService = actionPointService;
		this.subThemeService = subThemeService;
		this.sessionService = sessionService;
	}

	@GetMapping ("/action-points")
	public ModelAndView showActionPoints(@MunicipalityId Long municipalityId, HttpSession session) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		Set<ActionPoint> actionPoints;
		User user = sessionService.getUser(session);
		if (user != null) {
			actionPoints = actionPointService.getActionPointsByMunicipalityIdAndUserIdWithImages(municipalityId, user.getId());
		} else {
			logger.debug("No user found");
			actionPoints = actionPointService.getActionPointsByMunicipalityId(municipalityId);
		}
		List<SubTheme> subThemesFiltered = subThemeService.findActionPointsBySubtheme(municipalityId);
		logger.debug("Action points found: {}", actionPoints);
		return new ModelAndView("action-points/action-points")
				.addObject("actionPoints", actionPoints)
				.addObject("subThemes", subThemeService.getAllSubThemes())
				.addObject("subthemesFiltered", subThemesFiltered);
	}

	@GetMapping ("/action-points/{uuid}")
	public ModelAndView showActionPoint(@MunicipalityId Long municipalityId, @ModelAttribute ("authUser") User user, @PathVariable UUID uuid) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		ActionPoint actionPoint = actionPointService.getActionPointByUUID(municipalityId, uuid);
		logger.debug("Action points found: {}", actionPoint);
		return new ModelAndView("action-points/action-point")
				.addObject("actionPoint", actionPoint);
	}
}