package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.actionpoints.ActionPointService;
import be.kdg.finalproject.service.theme.SubThemeService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class ActionPointController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);
	private final ActionPointService actionPointService;
	private final SubThemeService subThemeService;

	public ActionPointController(ActionPointService actionPointService, SubThemeService subThemeService) {
		this.actionPointService = actionPointService;
		this.subThemeService = subThemeService;
	}

	@GetMapping ("/action-points")
	public ModelAndView showActionPoints(@MunicipalityId Long municipalityId, @ModelAttribute ("authUser") User user) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		Set<ActionPoint> actionPoints = actionPointService.getActionPointsByMunicipalityIdAndUserIdWithImages(municipalityId, user.getId());
		logger.debug("Action points found: {}", actionPoints);
		return new ModelAndView("/action-points/action-points")
				.addObject("actionPoints", actionPoints)
				.addObject("subThemes", subThemeService.getAllSubThemes());
	}

	@GetMapping ("/action-points/{uuid}")
	public ModelAndView showActionPoint(@MunicipalityId Long municipalityId, @ModelAttribute ("authUser") User user, @PathVariable String uuid) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		Set<ActionPoint> actionPoints = actionPointService.getActionPointsByMunicipalityIdAndUserIdWithImages(municipalityId, user.getId());
		logger.debug("Action points found: {}", actionPoints);
		return new ModelAndView("/action-points/action-points")
				.addObject("actionPoints", actionPoints)
				.addObject("subThemes", subThemeService.getAllSubThemes());
	}

}
