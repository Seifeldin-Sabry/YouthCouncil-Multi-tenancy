package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityCalendarController {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActivityCalendarController.class);
	private final CalendarActivitiesService calendarActivitiesService;
	private final MunicipalityService municipalityService;

	public ActivityCalendarController(CalendarActivitiesService calendarActivitiesService,
	                                  MunicipalityService municipalityService) {
		this.calendarActivitiesService = calendarActivitiesService;
		this.municipalityService = municipalityService;
	}

	@GetMapping ("/calendar-activities") //
	public ModelAndView showActivitiesCalendar(@MunicipalityId Long municipalityId) {
		List<CalendarActivity> activities;
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		} else {
			activities = calendarActivitiesService.getActivitiesByMunicipality(municipalityId);
		}
		logger.debug("Calendar activities found: {}", activities);
		ModelAndView modelAndView = new ModelAndView("calendar-activities");
		modelAndView.addObject("activities", activities);
		return modelAndView;
	}

}
