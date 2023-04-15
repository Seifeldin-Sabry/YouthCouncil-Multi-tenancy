package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.*;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewCalendarActivityDTO {

	private String title;

	private LocalDate date;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String description;

	private Municipality municipality;
}
