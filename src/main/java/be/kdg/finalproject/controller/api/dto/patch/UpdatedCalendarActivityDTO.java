package be.kdg.finalproject.controller.api.dto.patch;
import lombok.*;

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
