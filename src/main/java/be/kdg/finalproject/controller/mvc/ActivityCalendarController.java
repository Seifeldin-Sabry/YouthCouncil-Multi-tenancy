package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.CalendarActivities.CalendarActivitiesService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityCalendarController {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointController.class);
	private final CalendarActivitiesService calendarActivitiesService;

	public ActivityCalendarController(CalendarActivitiesService calendarActivitiesService) {this.calendarActivitiesService = calendarActivitiesService;}
//	@GetMapping ("/calendar-activities")
//	public ModelAndView showActivitiesCalendar(@MunicipalityId Long municipalityId, @ModelAttribute ("authUser") User user) {
//		if (municipalityId == null) {
//			logger.debug("No municipality ID found");
//			throw new EntityNotFoundException("Not found");
//		}
//		List<CalendarActivity> activities = calendarActivitiesService.getAllCalendarActivities();
//		logger.debug("Calendar activities found: {}", activities);
//		return new ModelAndView("calendar-activities")
//				.addObject("activities", activities);
//	}


	//Municip is optional in this method
//	@GetMapping("/calendar-activities")
//	public ModelAndView showActivitiesCalendar(@MunicipalityId(required = false) Long municipalityId, @ModelAttribute("authUser") User user) {
//		List<CalendarActivity> activities;
//		if (municipalityId == null) {
//			activities = calendarActivitiesService.getAllCalendarActivities();
//		} else {
//			activities = calendarActivitiesService.getAllCalendarActivitiesForMunicipality(municipalityId);
//		}
//		logger.debug("Calendar activities found: {}", activities);
//		return new ModelAndView("calendar-activities")
//				.addObject("activities", activities);
//	}

	//FOR TESTING
	@GetMapping ("/calendar-activities")
	public ModelAndView showActivitiesCalendar() {
		List<CalendarActivity> activities = calendarActivitiesService.getAllCalendarActivities();
		logger.debug("Calendar activities found: {}", activities);
		return new ModelAndView("calendar-activities")
				.addObject("activities", activities);
	}
}
