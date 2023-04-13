package be.kdg.finalproject.domain.activities;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String description;

	@ManyToOne
	private Municipality municipality;

	public CalendarActivity(String title, LocalDateTime startTime, LocalDateTime endTime, String description) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

}
