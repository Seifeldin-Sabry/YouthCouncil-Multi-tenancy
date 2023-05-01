package be.kdg.finalproject.controller.mvc;


import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.UUID;

@Controller
public class MunicipalityController {

	private final Logger logger = LoggerFactory.getLogger(MunicipalityController.class);
	private final MunicipalityService municipalityService;

	private final UserService userService;

	public MunicipalityController(MunicipalityService municipalityService, UserService userService) {this.municipalityService = municipalityService;
		this.userService = userService;
	}

	@GetMapping ({"/", "/home"})
	public ModelAndView showPlatformHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().stream().findFirst().get().getAuthority();
		String currentMunicipality = MunicipalityContext.getCurrentMunicipalityName();
		if (currentMunicipality != null && !Objects.equals(role, "ROLE_ANONYMOUS")) {
			return new ModelAndView("municipality/municipality-home");
		}
		if (currentMunicipality == null && Objects.equals(role, "ROLE_ADMINISTRATOR")) {
			return new ModelAndView("/platform/platform-dashboard");
		} else if (currentMunicipality == null) {
			return new ModelAndView("/login");
		}
		return new ModelAndView("platform/platform-home");
	}

	@GetMapping ("/dashboard/municipalities")
	@GeneralAdminOnly
	public ModelAndView showMunicipalities() {
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

	@GetMapping ("/youth-council-dashboard/settings")
	public ModelAndView showMunicipalitySettings(@MunicipalityId Long municipalityId) {
		return new ModelAndView("municipality/municipality-settings")
				.addObject("municipality", municipalityService.getMunicipalityByIdWithSocialMediaLinks(municipalityId));
	}
}
