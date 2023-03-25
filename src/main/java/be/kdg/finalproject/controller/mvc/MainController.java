package be.kdg.finalproject.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {


	@GetMapping ("/manage")
	public ModelAndView showAnalytics() {
		return new ModelAndView("manage");
	}

}
