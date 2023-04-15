package be.kdg.finalproject.service.calendaractivities;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.platform.Municipality;
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
	public boolean updateCalendarActivity(Long id, String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description, Municipality municipality) {
		CalendarActivity calendarActivity = calendarActivityRepository.findById(id).orElse(null);
		if (calendarActivity == null) {
			return false;
		}
		calendarActivity.setTitle(title);
		calendarActivity.setDate(date);
		calendarActivity.setStartTime(startTime);
		calendarActivity.setEndTime(endTime);
		calendarActivity.setDescription(description);
		calendarActivity.setMunicipality(municipality);
		calendarActivityRepository.save(calendarActivity);
		return true;
	}

	@Override
	public boolean calendarActivityExists(Long id) {
		return calendarActivityRepository.existsById(id);
	}


}
