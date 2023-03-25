package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.service.theme.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlatformController {

	private final ThemeService themeService;

	public PlatformController(ThemeService themeService) {
		this.themeService = themeService;
	}

	@GetMapping ({"/", "/home"})
	public ModelAndView showPlatformHome() {
		return new ModelAndView("platform/platform-home");
	}

	@GeneralAdminOnly
	@GetMapping ("/dashboard")
	public ModelAndView showPlatformDashboard() {
		return new ModelAndView("platform/platform-dashboard");
	}

	@GeneralAdminOnly
	@GetMapping ("/dashboard/themes")
	public ModelAndView showThemes() {
		return new ModelAndView("themes")
				.addObject("themes", themeService.getAllThemes());
	}


}
