package be.kdg.finalproject.domain.module;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue ("list_calendar_activites")
public class ListCalendarActivites extends Module {
	@OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CalendarActivity> calendarItems = new HashSet<>();


	public ListCalendarActivites() {
		super();
		this.setModuleType(ModuleType.LIST_CALENDAR_ACTIVITIES);
	}
}
