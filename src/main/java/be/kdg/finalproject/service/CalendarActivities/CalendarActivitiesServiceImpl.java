package be.kdg.finalproject.service.CalendarActivities;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.repository.calendaractivities.CalendarActivityRepository;
import be.kdg.finalproject.service.municipality.MunicipalityServiceImpl;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarActivitiesServiceImpl implements CalendarActivitiesService{

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


}
