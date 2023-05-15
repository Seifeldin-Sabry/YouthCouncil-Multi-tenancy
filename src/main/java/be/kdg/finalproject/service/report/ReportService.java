package be.kdg.finalproject.service.report;

import be.kdg.finalproject.controller.api.dto.post.NewReportDTO;
import be.kdg.finalproject.domain.report.Report;
import be.kdg.finalproject.repository.report.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ReportService {
	ReportRepository repository;

	public ReportService(ReportRepository repository) {
		this.repository = repository;
	}

	public Report createReport(NewReportDTO newReportDTO, Long ideaId){
		Report report = new Report();
		report.setReportDescription(newReportDTO.getReportDescription());
		report.setReportReason(newReportDTO.getReportReason());
		report.setIdeaId(ideaId);
		return repository.save(report);
	}

	public Set<Report> getAllReportsOfMunicipalityId(long municipalityId){
		return repository.getReportsByMunicipalityId(municipalityId);
	}
}
