package be.kdg.finalproject.service.calendaractivities;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalendarActivitiesService {
	List<CalendarActivity> getAllCalendarActivities();
}
