package be.kdg.finalproject.domain.report;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity (name = "REPORTS")
@Getter
@Setter
@ToString
public class Report {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "report_id", nullable = false)
	private Long report_id;

	@Column (name = "report_reason", nullable = false)
	@Enumerated (EnumType.STRING)
	private ReportReason reportReason;

	@Column (name = "report_description")
	private String reportDescription;

	@Column (name = "report_date", nullable = false)
	private LocalDate reportDate;
}

