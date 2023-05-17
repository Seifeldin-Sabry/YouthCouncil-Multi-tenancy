package be.kdg.finalproject.controller.mvc;


import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping ("/{municipality}")
public class MunicipalityController {

	private final MunicipalityService municipalityService;

	public MunicipalityController(MunicipalityService municipalityService) {
		this.municipalityService = municipalityService;
	}

	@GetMapping ({"/", "/home"})
	public String showPlatformHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().stream().findFirst().get().getAuthority();
		String currentMunicipality = MunicipalityContext.getCurrentMunicipalityName();
		if (currentMunicipality != null) {
			return "municipality/municipality-home";
		}
		if (Objects.equals(role, "ROLE_ADMINISTRATOR")) {
			return "platform/platform-dashboard";
		}
		return "redirect:/login";
	}

	@GetMapping ("/dashboard/municipalities")
	@GeneralAdminOnly
	public ModelAndView showMunicipalities(@MunicipalityId Long muid) {
		if (muid != null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ModelAndView("platform/platform-municipalities")
				.addObject("municipalities", municipalityService.getAllMunicipalitiesAndMembers());
	}

	@GetMapping ("/dashboard/municipalities/{uuid}")
	@GeneralAdminOnly
	public ModelAndView showMunicipalityDetails(@PathVariable UUID uuid, @MunicipalityId Long muid) throws EntityNotFoundException {
		if (muid != null) {
			throw new EntityNotFoundException("Page not found");
		}
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
