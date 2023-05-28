package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.CallForIdeasDTO;
import be.kdg.finalproject.controller.api.dto.get.IdeaDTO;
import be.kdg.finalproject.controller.api.dto.post.NewCallForIdeasDTO;
import be.kdg.finalproject.controller.api.dto.post.NewIdeaDTO;
import be.kdg.finalproject.controller.api.dto.post.NewReportDTO;
import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.report.ReportReason;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.report.ReportService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api/call-for-ideas")
public class CallForIdeasRestController {
	private final CallForIdeasService callForIdeasService;
	private final IdeaService ideaService;
	private final ReportService reportService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(CallForIdeasRestController.class);

	public CallForIdeasRestController(CallForIdeasService callForIdeasService, IdeaService ideaService, ReportService reportService) {
		this.callForIdeasService = callForIdeasService;
		this.ideaService = ideaService;
		this.reportService = reportService;
	}

	@YouthCouncilAdmin
	@PostMapping
	public ResponseEntity<?> addCallForIdea(@MunicipalityId Long municipalityId, @ModelAttribute @Valid NewCallForIdeasDTO newCallForIdeasDTO, BindingResult errors) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		if (errors.hasErrors()) {
			logger.debug("Error found");
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}

		CallForIdeas callForIdeasSaved = callForIdeasService.createCallForIdea(newCallForIdeasDTO);
		CallForIdeasDTO callForIdeasDTO = modelMapper.map(callForIdeasSaved, CallForIdeasDTO.class);
		return new ResponseEntity<>(callForIdeasDTO, HttpStatus.CREATED);
	}

	@YouthCouncilAdmin
	@PatchMapping ("/{id}/active")
	public ResponseEntity<?> swapActive(@PathVariable Long id) {
		Optional<CallForIdeas> callForIdeas = callForIdeasService.getCallForIdeaById(id);
		if (callForIdeas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		callForIdeasService.changeActiveById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@LoggedIn
	@PostMapping ("/{callForIdeasId}/ideas")
	public ResponseEntity<?> addIdea(@PathVariable long callForIdeasId,
	                                 @ModelAttribute @Valid NewIdeaDTO newIdeaDTO, BindingResult errors, @MunicipalityId Long municipalityId,
	                                 @ModelAttribute ("authUser") User authUser) throws IOException {
		// for now image is not implemented yet, so empty string is passed
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		if (errors.hasErrors()) {
			logger.debug("Error found");
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		Idea idea = ideaService.createIdea(newIdeaDTO, callForIdeasId, authUser);
		String s;
		String param = idea.getContent();
		Process p = Runtime.getRuntime().exec("python src\\pythonModule\\python\\input.py \"" + param + "\"");
		BufferedReader stdInput = new BufferedReader(new
				InputStreamReader(p.getInputStream()));

		BufferedReader stdError = new BufferedReader(new
				InputStreamReader(p.getErrorStream()));
		StringBuilder stringBuilder = new StringBuilder();

		// read the output from the command
		while ((s = stdInput.readLine()) != null) {
			stringBuilder.append(s);
			logger.debug(s);
		}
		if (stringBuilder.length() == 0) {
			logger.debug("stringbuilder is length 0, output input didnt funvtion correctly");
		} else {
			if (stringBuilder.charAt(stringBuilder.length() - 1) == '1') {
				logger.debug("this should be reported because prediction is: " + stringBuilder.charAt(stringBuilder.length() - 1));
				reportService.createReport(new NewReportDTO(ReportReason.OTHER, "flagged by AI moderation"), idea.getId());
			}
			if (stringBuilder.charAt(stringBuilder.length() - 1) == '0') {
				logger.debug("this should not be reported because prediction is: " + stringBuilder.charAt(stringBuilder.length() - 1));
			}
		}

		IdeaDTO ideaDTO = modelMapper.map(idea, IdeaDTO.class);
		return new ResponseEntity<>(ideaDTO, HttpStatus.CREATED);
	}
}
