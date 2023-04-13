package be.kdg.finalproject.domain.activities;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity (name = "CALENDAR_ACTIVITIES")
@Getter
@Setter
@NoArgsConstructor
public class CalendarActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_activity_id", nullable = false)
	private Long id;

	private String title;

	private LocalDate date;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String description;

	@ManyToOne
	private Municipality municipality;

	public CalendarActivity(String title,LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String description) {
		this.title = title;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

}
