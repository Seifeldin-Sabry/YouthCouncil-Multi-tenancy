package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
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

	@GetMapping
	public ModelAndView showThemes(@MunicipalityId Long muid) {
		if (muid != null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ModelAndView("platform/platform-themes")
				.addObject("themes", themeService.getAllThemes());
	}
}
