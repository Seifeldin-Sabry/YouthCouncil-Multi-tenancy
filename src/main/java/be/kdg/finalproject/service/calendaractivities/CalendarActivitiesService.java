package be.kdg.finalproject.service.calendaractivities;
import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CalendarActivitiesService {
	List<CalendarActivity> getAllCalendarActivities();
	CalendarActivity addCalendarActivity(String title, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description);
	void deleteCalendarActivity(Long id);
	public CalendarActivity updateCalendarActivity(Long activityId, UpdatedCalendarActivityDTO updatedCalendarActivityDTO);
	boolean calendarActivityExists(Long id);
	public List<CalendarActivity> getActivitiesByMunicipality(Long municipalityId);
}
