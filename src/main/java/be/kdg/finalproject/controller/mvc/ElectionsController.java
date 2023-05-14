package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ElectionsController {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);


	@GetMapping ("/elections")
	public ModelAndView showElectionInformation(@MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		return new ModelAndView("/informative-pages/elections");
	}

}
