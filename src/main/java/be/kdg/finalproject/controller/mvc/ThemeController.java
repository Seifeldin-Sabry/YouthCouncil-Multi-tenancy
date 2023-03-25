package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.service.theme.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@GeneralAdminOnly
@RequestMapping ("/dashboard/themes")
public class ThemeController {

	private final ThemeService themeService;

	public ThemeController(ThemeService themeService) {
		this.themeService = themeService;
	}

	@GetMapping ("/dashboard/themes")
	public ModelAndView showThemes() {
		return new ModelAndView("themes")
				.addObject("themes", themeService.getAllThemes());
	}
}
