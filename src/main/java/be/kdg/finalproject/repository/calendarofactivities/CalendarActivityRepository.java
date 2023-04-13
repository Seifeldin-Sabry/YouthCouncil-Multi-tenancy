package be.kdg.finalproject.repository.calendarofactivities;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarActivityRepository extends CrudRepository<CalendarActivity, Long> {}