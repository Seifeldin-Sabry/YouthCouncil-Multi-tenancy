package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityCalendarController {
	private final Logger logger = LoggerFactory.getLogger(ActivityCalendarController.class);
	private final CalendarActivitiesService calendarActivitiesService;

	public ActivityCalendarController(CalendarActivitiesService calendarActivitiesService) {
		this.calendarActivitiesService = calendarActivitiesService;
	}

	@GetMapping ("/calendar-activities")
	public ModelAndView showActivitiesCalendar(@MunicipalityId Long municipalityId) {
		List<CalendarActivity> activities;
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		} else {
			activities = calendarActivitiesService.getActivitiesByMunicipality(municipalityId);
		}
		logger.debug("Calendar activities found: {}", activities);
		ModelAndView modelAndView = new ModelAndView("municipality/municipality-activities");
		modelAndView.addObject("activities", activities);
		return modelAndView;
	}

}
