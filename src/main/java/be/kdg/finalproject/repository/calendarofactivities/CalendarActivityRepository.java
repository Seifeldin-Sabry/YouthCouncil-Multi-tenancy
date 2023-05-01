package be.kdg.finalproject.repository.calendarofactivities;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarActivityRepository extends CrudRepository<CalendarActivity, Long> {
	List<CalendarActivity> findByMunicipalityId(Long municipalityId);
}