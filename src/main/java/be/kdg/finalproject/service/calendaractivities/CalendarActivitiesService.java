package be.kdg.finalproject.service.calendaractivities;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;

import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;

import com.google.common.collect.ImmutableList;
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
			                                                              .orElseThrow(() -> new EntityNotFoundException("User not found"));
			calendarActivity.setTitle(updatedCalendarActivityDTO.getTitle());
	//		calendarActivity.setDate(updatedCalendarActivityDTO.getDate());
	//		calendarActivity.setStartTime(updatedCalendarActivityDTO.getStartTime());
	//		calendarActivity.setEndTime(updatedCalendarActivityDTO.getEndTime());
			calendarActivity.setDescription(updatedCalendarActivityDTO.getDescription());
			calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());
			return calendarActivityRepository.save(calendarActivity);
		}


	//////////

//	public CalendarActivity updateCalendarActivity(Long activityId, UpdatedCalendarActivityDTO updatedCalendarActivityDTO) {
//		CalendarActivity calendarActivity = calendarActivityRepository.findById(activityId)
//		                                                              .orElseThrow(() -> new EntityNotFoundException("Activity Id not found"));
//
//		LocalDate date = parseDateString(updatedCalendarActivityDTO.getDate());
//		LocalDateTime startTime = parseDateTimeString(updatedCalendarActivityDTO.getStartTime(), date.atStartOfDay());
//		LocalDateTime endTime = parseDateTimeString(updatedCalendarActivityDTO.getEndTime(), date.atStartOfDay());
//
//		calendarActivity.setTitle(updatedCalendarActivityDTO.getTitle());
//		calendarActivity.setDate(date);
//		calendarActivity.setStartTime(startTime);
//		calendarActivity.setEndTime(endTime);
//		calendarActivity.setDescription(updatedCalendarActivityDTO.getDescription());
//		calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());
//
//		return calendarActivityRepository.save(calendarActivity);
//	}
//
//	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//
//	private static LocalDate parseDateString(String dateString) {
//		if (dateString == null) {
//			return null;
//		}
//		return LocalDate.parse(dateString, dateFormatter);
//	}
//
//	private static LocalDateTime parseDateTimeString(String dateTimeString, LocalDateTime defaultDateTime) {
//		if (dateTimeString == null) {
//			return null;
//		}
//		LocalTime time = LocalTime.parse(dateTimeString, timeFormatter);
//		return LocalDateTime.of(defaultDateTime.toLocalDate(), time);
//	}
//
//	private static LocalDateTime parseDateTimeString(String dateTimeString, LocalDate defaultDate) {
//		if (dateTimeString == null) {
//			return null;
//		}
//		LocalTime time = LocalTime.parse(dateTimeString, timeFormatter);
//		return LocalDateTime.of(defaultDate, time);
//	}


	////////

	public boolean calendarActivityExists(Long id) {
		return calendarActivityRepository.existsById(id);
	}


	public List<CalendarActivity> getActivitiesByMunicipality(Long municipalityId) {
		return calendarActivityRepository.findByMunicipalityId(municipalityId);
	}


}
