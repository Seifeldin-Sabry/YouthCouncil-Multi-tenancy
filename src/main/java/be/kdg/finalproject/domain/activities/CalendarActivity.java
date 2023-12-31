package be.kdg.finalproject.domain.activities;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity (name = "CALENDAR_ACTIVITIES")
@Getter
@Setter
@NoArgsConstructor
public class CalendarActivity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "calendar_activity_id", nullable = false)
	private Long id;

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "date", nullable = false)
	private LocalDate date;

	@Column (name = "startTime", nullable = false)
	private LocalTime startTime;

	@Column (name = "endTime", nullable = false)
	private LocalTime endTime;

	@Column (name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	private Municipality municipality;

	public CalendarActivity(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
		this.title = title;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

}
