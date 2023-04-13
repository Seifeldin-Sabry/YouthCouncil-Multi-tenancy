package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;

import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ActivityCalendarController {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);
	private final CalendarActivitiesService calendarActivitiesService;
	private final MunicipalityService municipalityService;

	public ActivityCalendarController(CalendarActivitiesService calendarActivitiesService,
	                                  MunicipalityService municipalityService) {
		this.calendarActivitiesService = calendarActivitiesService;
		this.municipalityService = municipalityService;
	}


//	@GetMapping("/calendar-activities")
//	public ModelAndView showActivitiesCalendar(@MunicipalityId Long municipalityId, HttpServletRequest request) {
//		if (municipalityId == null) {
//			logger.debug("No municipality ID found");
//			throw new EntityNotFoundException("Not found");
//		}
//		List<CalendarActivity> activities = calendarActivitiesService.getAllCalendarActivities();
//		logger.debug("Calendar activities found: {}", activities);
//		ModelAndView mav = new ModelAndView("calendar-activities")
//				.addObject("activities", activities);
//		if (request.getUserPrincipal() != null) { // check if the user is authenticated
//			mav.setViewName("redirect:/login"); // redirect to the login page if authenticated
//		}
//		return mav;
//	}

	@GetMapping("/calendar-activities")
	public ModelAndView showActivitiesCalendar(Model model) {
		List<CalendarActivity> activities = calendarActivitiesService.getAllCalendarActivities();
		List<Municipality> municipalities = municipalityService.getAllMunicipalities();
		logger.debug("Calendar activities found: {}", activities);
		model.addAttribute("activities", activities);
		model.addAttribute("municipalities", municipalities);
		return new ModelAndView("calendar-activities");
	}


}
