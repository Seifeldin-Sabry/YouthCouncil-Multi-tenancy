package be.kdg.finalproject.controller.mvc;


import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class MunicipalityController {

	private final Logger logger = LoggerFactory.getLogger(MunicipalityController.class);
	private final MunicipalityService municipalityService;

	public MunicipalityController(MunicipalityService municipalityService) {this.municipalityService = municipalityService;}

	@GetMapping ("/dashboard/municipalities")
	@GeneralAdminOnly
	public ModelAndView showMunicipalities() {
		logger.debug("{}", municipalityService.getAllMunicipalitiesAndMembers());
		return new ModelAndView("platform/platform-municipalities")
				.addObject("municipalities", municipalityService.getAllMunicipalitiesAndMembers());
	}

	@GetMapping ("/dashboard/municipalities/{uuid}")
	@GeneralAdminOnly
	public ModelAndView showMunicipalityDetails(@PathVariable UUID uuid) throws EntityNotFoundException {
		Municipality municipality = municipalityService.getMunicipalityByUUID(uuid);
		return new ModelAndView("platform/platform-municipality")
				.addObject("municipality", municipality);
	}
}
