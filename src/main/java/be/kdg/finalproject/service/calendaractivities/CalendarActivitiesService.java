package be.kdg.finalproject.service.calendaractivities;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;

import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;

import jdk.swing.interop.SwingInterOpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalendarActivitiesService {

	private final CalendarActivityRepository calendarActivityRepository;
	private final Logger logger = LoggerFactory.getLogger(CalendarActivitiesService.class);

	@Autowired
	public CalendarActivitiesService(CalendarActivityRepository calendarActivityRepository) {
		this.calendarActivityRepository = calendarActivityRepository;
	}

	public CalendarActivity addCalendarActivity(String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description) {
		CalendarActivity calendarActivity = new CalendarActivity(title, date, startTime, endTime, description);
		return calendarActivityRepository.save(calendarActivity);
	}

	public void deleteCalendarActivity(Long id) {
		calendarActivityRepository.deleteById(id);
	}

		public CalendarActivity updateCalendarActivity(Long activityId, UpdatedCalendarActivityDTO updatedCalendarActivityDTO) {
			CalendarActivity calendarActivity = calendarActivityRepository.findById(activityId)
			                                                              .orElseThrow(() -> new EntityNotFoundException("activityId not found"));

			calendarActivity.setTitle(updatedCalendarActivityDTO.getTitle());
			calendarActivity.setDescription(updatedCalendarActivityDTO.getDescription());
			calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());

			// Update date field
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(updatedCalendarActivityDTO.getDate(), dateFormatter);
			calendarActivity.setDate(date);

			// Update start time field
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.parse(updatedCalendarActivityDTO.getStartTime(), timeFormatter));
			calendarActivity.setStartTime(startDateTime);

			// Update end time field
			LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.parse(updatedCalendarActivityDTO.getEndTime(), timeFormatter));
			calendarActivity.setEndTime(endDateTime);

			return calendarActivityRepository.save(calendarActivity);
		}

	public boolean calendarActivityExists(Long id) {
		return calendarActivityRepository.existsById(id);
	}

	public List<CalendarActivity> getActivitiesByMunicipality(Long municipalityId) {
		return calendarActivityRepository.findByMunicipalityId(municipalityId);
	}
}
