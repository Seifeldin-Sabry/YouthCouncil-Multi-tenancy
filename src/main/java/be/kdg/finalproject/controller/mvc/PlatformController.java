package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlatformController {


	@GetMapping ({"/", "/home"})
	public ModelAndView showPlatformHome() {
		return new ModelAndView("platform/platform-home");
	}

	@GeneralAdminOnly
	@GetMapping ("/dashboard")
	public ModelAndView showPlatformDashboard() {
		return new ModelAndView("platform/platform-dashboard");
	}

}