package be.kdg.finalproject.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActivityCalendarController {

	@GetMapping ("/activities-calendar")
	public ModelAndView showCalendar() {
		ModelAndView modelAndView = new ModelAndView("activities-calendar");
		//modelAndView.addObject("activities-schedule", attributeValue);
		return modelAndView;
	}
}
