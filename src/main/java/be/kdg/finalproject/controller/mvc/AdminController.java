package be.kdg.finalproject.controller.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping ("/analytics")
	public ModelAndView showAnalytics() {
		return new ModelAndView("analytics");
	}

	@GetMapping ("/manage-users")
	public ModelAndView manageUsers() {
		return new ModelAndView("users");
	}


}
