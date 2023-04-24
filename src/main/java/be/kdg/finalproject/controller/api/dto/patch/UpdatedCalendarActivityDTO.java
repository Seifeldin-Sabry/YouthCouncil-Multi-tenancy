package be.kdg.finalproject.controller.api.dto.patch;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatedCalendarActivityDTO {

	private Long id;

	@NotBlank
	@Size (max = 30)
	private String title;

	private String date;

	private String startTime;

	private String endTime;

	@NotBlank
	@Size (max = 150)
	private String description;

	public boolean isValid() {
		LocalDate activityDate = LocalDate.parse(date);
		LocalTime activityStartTime = LocalTime.parse(startTime);
		LocalTime activityEndTime = LocalTime.parse(endTime);
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		return activityDate.isEqual(today) ? activityStartTime.isAfter(now) && activityEndTime.isAfter(activityStartTime) : activityDate.isAfter(today);
	}

}
