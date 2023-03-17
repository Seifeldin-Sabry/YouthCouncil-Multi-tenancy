package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	private final UserService userService;

	public MainController(UserService userService) {this.userService = userService;}

	@GetMapping ("/home")
	public ModelAndView showHome() {
		return new ModelAndView("html/home");
	}

	@GetMapping ("/manage")
	public ModelAndView showAnalytics() {
		return new ModelAndView("html/manage");
	}


}
