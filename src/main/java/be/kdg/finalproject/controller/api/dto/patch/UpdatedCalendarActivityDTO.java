package be.kdg.finalproject.controller.api.dto.patch;

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
public class UpdatedCalendarActivityDTO {

	private Long id;

	private String title;

	private String date;

	private String startTime;

	private String endTime;

	private String description;


}
