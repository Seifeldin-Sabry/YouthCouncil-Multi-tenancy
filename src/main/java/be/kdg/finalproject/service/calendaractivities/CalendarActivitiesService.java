package be.kdg.finalproject.service.calendaractivities;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//@Service
public interface CalendarActivitiesService {
	List<CalendarActivity> getAllCalendarActivities();
	CalendarActivity addCalendarActivity(String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description);
	void deleteCalendarActivity(Long id);
	boolean updateCalendarActivity(Long id, String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description, Municipality municipality);
	boolean calendarActivityExists(Long id);
}
