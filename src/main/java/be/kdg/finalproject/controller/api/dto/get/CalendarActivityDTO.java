package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.platform.Municipality;
import lombok.*;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarActivityDTO {

	private Long id;

	private String title;

	private LocalDate date;

	private LocalTime startTime;

	private LocalTime endTime;

	private String description;


}
