package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewCalendarActivityDTO {

	@NotBlank
	private String title;

	@NotNull
	private LocalDate date;

	@NotNull()
	private LocalDateTime startTime;

	@NotNull()
	private LocalDateTime endTime;

	@NotBlank
	private String description;

}
