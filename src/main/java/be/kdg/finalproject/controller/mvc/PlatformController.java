package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PlatformController {

	Logger logger = LoggerFactory.getLogger(PlatformController.class);

	@GeneralAdminOnly
	@GetMapping ("/dashboard")
	public ModelAndView showPlatformDashboard() {
		return new ModelAndView("platform-dashboard");
	}

	@GetMapping ("/youth-council-dashboard")
	@YouthCouncilAdmin
	public ModelAndView showYouthCouncilDashboard() {
		return new ModelAndView("municipality-dashboard");
	}
}
