package be.kdg.finalproject.controller.api.dto.post;

import be.kdg.finalproject.domain.report.ReportReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewReportDTO {
	@NotNull (message = "Please select a report reason")
	private ReportReason reportReason;
	@NotBlank (message = "Please enter a description")
	@Size (min = 1, max = 5000, message = "Description must be between 1 and 5000 characters")
	private String reportDescription;
}
