package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.service.theme.ThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	private final ThemeService themeService;
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public AdminController(ThemeService themeService) {
		this.themeService = themeService;
	}

	@GetMapping ("/dashboard")
	public ModelAndView showDashboard() {
		return new ModelAndView("html/admin-dashboard");
	}

	@GetMapping ("/analytics")
	public ModelAndView showAnalytics() {
		return new ModelAndView("html/analytics");
	}

	@GetMapping ("/manage-users")
	public ModelAndView manageUsers() {
		return new ModelAndView("html/users");
	}

	@GetMapping ("/themes")
	public ModelAndView showThemes() {
		return new ModelAndView("html/themes")
				.addObject("themes", themeService.getAllThemes());
	}
}
