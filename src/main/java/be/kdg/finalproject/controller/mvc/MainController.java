package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	private final UserService userService;

	public MainController(UserService userService) {this.userService = userService;}

	@GetMapping ("/home")
	public ModelAndView showHome() {
		var mav = new ModelAndView("html/home");
		mav.addObject("home");
		return mav;
	}

	@GetMapping ("/manage")
	public ModelAndView showAnalytics() {
		var mav = new ModelAndView("html/manage");
		mav.addObject("manage");
		return mav;
	}


	@GetMapping ("/themes")
	public ModelAndView showThemes() {
		var mav = new ModelAndView("html/themes");
		mav.addObject("themes");
		return mav;
	}

}
