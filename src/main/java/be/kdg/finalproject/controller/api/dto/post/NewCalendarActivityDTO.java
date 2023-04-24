package be.kdg.finalproject.controller.api.dto.post;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewCalendarActivityDTO {

	@NotBlank
	@Size(max = 30)
	private String title;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotNull
	private LocalTime startTime;

	@NotNull
	private LocalTime endTime;

	@NotBlank
	@Size (max = 150)
	private String description;

	public boolean isValid() {
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		return date.isEqual(today) ? startTime.isAfter(now) && endTime.isAfter(startTime) : date.isAfter(today);
	}

}
