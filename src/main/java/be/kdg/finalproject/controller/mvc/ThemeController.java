package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.service.theme.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping ("/themes")
public class ThemeController {

	private final ThemeService themeService;

	@Autowired
	public ThemeController(ThemeService themeService) {
		this.themeService = themeService;
	}

	@GetMapping
	public ModelAndView getThemes() {
		return new ModelAndView("themes", "themes", themeService.getAllThemes());
	}
}
