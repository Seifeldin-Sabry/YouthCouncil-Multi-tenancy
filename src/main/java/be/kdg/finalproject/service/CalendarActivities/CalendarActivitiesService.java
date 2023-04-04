package be.kdg.finalproject.service.CalendarActivities;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalendarActivitiesService {
	List<CalendarActivity> getAllCalendarActivities();
}
