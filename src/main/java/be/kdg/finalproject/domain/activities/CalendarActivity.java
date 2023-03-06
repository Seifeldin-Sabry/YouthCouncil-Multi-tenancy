package be.kdg.finalproject.domain.activities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	public CalendarActivity(String title) {
		this.title = title;
		this.date = LocalDate.now();
	}
}
