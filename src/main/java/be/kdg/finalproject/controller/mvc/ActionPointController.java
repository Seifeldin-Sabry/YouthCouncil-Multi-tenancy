package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.actionpoints.ActionPointService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping ("/action-points")
public class ActionPointController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);
	private final ActionPointService actionPointService;

	public ActionPointController(ActionPointService actionPointService) {this.actionPointService = actionPointService;}

	@GetMapping
	public ModelAndView showActionPoints(@MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		List<ActionPoint> actionPoints = actionPointService.getActionPointsByMunicipalityId(municipalityId);
		return new ModelAndView("action-points")
				.addObject("actionPoints", actionPoints);
	}
}
