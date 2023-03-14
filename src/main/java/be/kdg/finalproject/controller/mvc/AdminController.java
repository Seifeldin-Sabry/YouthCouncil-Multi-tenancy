package be.kdg.finalproject.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@GetMapping("/dashboard")
	public ModelAndView showDashboard() {
		var mav = new ModelAndView("html/admin-dashboard");
		return mav;
	}

	@GetMapping ("/analytics")
	public ModelAndView showAnalytics() {
		var mav = new ModelAndView("html/analytics");
		return mav;
	}

	@GetMapping ("/manage-users")
	public ModelAndView manageUsers() {
		var mav = new ModelAndView("html/users");
		return mav;
	}
}
