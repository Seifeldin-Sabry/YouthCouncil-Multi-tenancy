package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.post.NewReportDTO;
import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.report.Report;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.report.ReportService;
import be.kdg.finalproject.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api/reports")
public class ReportRestController {

	private final IdeaService ideaService;
	private final ReportService reportService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ReportRestController.class);

	public ReportRestController(IdeaService ideaService, ReportService reportService) {
		this.ideaService = ideaService;
		this.reportService = reportService;
	}

	@LoggedIn
	@PostMapping("/{id}/idea")
	public ResponseEntity<?> reportIdea(@MunicipalityId Long municipalityId, @ModelAttribute @Valid NewReportDTO newReportDTO,
	                                    BindingResult errors, @PathVariable long id){
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		if (errors.hasErrors()) {
			logger.debug("Error found");
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		Optional<Idea> idea = ideaService.getIdeaById(id);
		if (idea.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Report report= reportService.createReport(newReportDTO, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
