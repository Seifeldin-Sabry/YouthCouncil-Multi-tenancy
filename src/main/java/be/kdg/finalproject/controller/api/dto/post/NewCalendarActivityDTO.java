package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

}
