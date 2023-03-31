package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class PlatformController {

	Logger logger = LoggerFactory.getLogger(PlatformController.class);

	@GetMapping ({"/", "/home"})
	public ModelAndView showPlatformHome(HttpSession session) {
		logger.debug("HOME");
		return new ModelAndView("platform/platform-home");
	}

	@GeneralAdminOnly
	@GetMapping ("/dashboard")
	public ModelAndView showPlatformDashboard() {
		return new ModelAndView("platform/platform-dashboard");
	}


}
