package be.kdg.finalproject.service.calendaractivities;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.controller.api.dto.post.NewCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;

import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;


import org.modelmapper.ModelMapper;
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

//	public CalendarActivityDTO addCalendarActivity(NewCalendarActivityDTO newActivityDto) {
//		ModelMapper modelMapper = new ModelMapper();
//		CalendarActivity calendarActivity = modelMapper.map(newActivityDto, CalendarActivity.class);
//		calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());
//		CalendarActivity createdActivity = calendarActivityRepository.save(calendarActivity);
//		return modelMapper.map(createdActivity, CalendarActivityDTO.class);
//	}

	public CalendarActivity addCalendarActivity(NewCalendarActivityDTO newActivityDto) {
		ModelMapper modelMapper = new ModelMapper();
		CalendarActivity calendarActivity = modelMapper.map(newActivityDto, CalendarActivity.class);
		calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());
		CalendarActivity createdActivity = calendarActivityRepository.save(calendarActivity);
		return createdActivity;
	}


	public void deleteCalendarActivity(Long id) {
		logger.debug("Deleting calendar activity with id: " + id);
		calendarActivityRepository.deleteById(id);
	}

		public CalendarActivity updateCalendarActivity(Long activityId, UpdatedCalendarActivityDTO updatedCalendarActivityDTO) {
			logger.debug("Updating calendar activity with id: " + activityId);
			CalendarActivity calendarActivity = calendarActivityRepository.findById(activityId)
			                                                              .orElseThrow(() -> new EntityNotFoundException("activityId not found"));

			calendarActivity.setTitle(updatedCalendarActivityDTO.getTitle());

			// Update date field
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(updatedCalendarActivityDTO.getDate(), dateFormatter);
			calendarActivity.setDate(date);

			// Update start time field
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.parse(updatedCalendarActivityDTO.getStartTime(), timeFormatter));
			calendarActivity.setStartTime(LocalTime.from(startDateTime));

			// Update end time field
			LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.parse(updatedCalendarActivityDTO.getEndTime(), timeFormatter));
			calendarActivity.setEndTime(LocalTime.from(endDateTime));

			calendarActivity.setDescription(updatedCalendarActivityDTO.getDescription());
			calendarActivity.setMunicipality(MunicipalityContext.getCurrentMunicipality());
			return calendarActivityRepository.save(calendarActivity);
		}

	public boolean calendarActivityExists(Long id) {
		return calendarActivityRepository.existsById(id);
	}

	public List<CalendarActivity> getActivitiesByMunicipality(Long municipalityId) {
		return calendarActivityRepository.findByMunicipalityId(municipalityId);
	}
}
