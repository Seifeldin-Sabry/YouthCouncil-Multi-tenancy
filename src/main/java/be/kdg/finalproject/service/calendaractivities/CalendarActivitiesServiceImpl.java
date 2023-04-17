package be.kdg.finalproject.service.calendaractivities;
import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;

import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.calendarofactivities.CalendarActivityRepository;
import be.kdg.finalproject.service.municipality.MunicipalityServiceImpl;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarActivitiesServiceImpl implements CalendarActivitiesService {

	private final CalendarActivityRepository calendarActivityRepository;
	private final Logger logger = LoggerFactory.getLogger(MunicipalityServiceImpl.class);

	@Autowired
	public CalendarActivitiesServiceImpl(CalendarActivityRepository calendarActivityRepository) {
		this.calendarActivityRepository = calendarActivityRepository;
	}

	@Override
	public List<CalendarActivity> getAllCalendarActivities() {
		return ImmutableList.copyOf(calendarActivityRepository.findAll());
	}

	@Override
	public CalendarActivity addCalendarActivity(String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description) {
		CalendarActivity calendarActivity = new CalendarActivity(title, date, startTime, endTime, description);
		return calendarActivityRepository.save(calendarActivity);
	}

	@Override
	public void deleteCalendarActivity(Long id) {
		calendarActivityRepository.deleteById(id);
	}

	@Override
	public CalendarActivity updateCalendarActivity(Long activityId, UpdatedCalendarActivityDTO updatedCalendarActivityDTO) {
		CalendarActivity calendarActivity = calendarActivityRepository.findById(activityId).orElseThrow(() -> new EntityNotFoundException("User not found"));
		calendarActivity.setTitle(updatedCalendarActivityDTO.getTitle());
		calendarActivity.setDate(updatedCalendarActivityDTO.getDate());
		calendarActivity.setStartTime(updatedCalendarActivityDTO.getStartTime());
		calendarActivity.setEndTime(updatedCalendarActivityDTO.getEndTime());
		calendarActivity.setDescription(updatedCalendarActivityDTO.getDescription());
		calendarActivity.setMunicipality(updatedCalendarActivityDTO.getMunicipality());
		return calendarActivityRepository.save(calendarActivity);
	}

	@Override
	public boolean calendarActivityExists(Long id) {
		return calendarActivityRepository.existsById(id);
	}

	@Override
	public List<CalendarActivity> getActivitiesByMunicipality(Long municipalityId) {
			return calendarActivityRepository.findByMunicipalityId(municipalityId);
	}


}
