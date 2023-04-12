package be.kdg.finalproject.domain.activities;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity (name = "CALENDAR_ACTIVITIES")
@Getter
@Setter
@NoArgsConstructor
public class CalendarActivity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "calendar_activity_id", nullable = false)
	private Long id;
	private String title;
	private LocalDate date;
	//add also time? if needed then use localDateTime like below
	// @Column(nullable = false)
	// private LocalDateTime dateTime;
	private String description;
	@ManyToOne
	private Municipality municipality;

	public CalendarActivity(String title) {
		this.title = title;
		this.date = LocalDate.now();
	}
}
