package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.IdeaDTO;
import be.kdg.finalproject.controller.api.dto.post.NewIdeaDTO;
import be.kdg.finalproject.controller.api.dto.post.NewReportDTO;
import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.controller.authority.Moderator;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.report.ReportReason;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.report.ReportService;
import be.kdg.finalproject.service.theme.SubThemeService;
import be.kdg.finalproject.service.user.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping ("/api/call-for-ideas/{callForIdeasId}/ideas")
public class IdeaRestController {
	private final CallForIdeasService callForIdeasService;
	private final IdeaService ideaService;
	private final SubThemeService subThemeService;
	private final UserService userService;
	private final ReportService reportService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(IdeaRestController.class);

	public IdeaRestController(CallForIdeasService callForIdeasService, IdeaService ideaService, SubThemeService subThemeService, UserService userService, ReportService reportService) {
		this.callForIdeasService = callForIdeasService;
		this.ideaService = ideaService;
		this.subThemeService = subThemeService;
		this.userService = userService;
		this.reportService = reportService;
	}

	@LoggedIn
	@PostMapping
	public ResponseEntity<?> addIdea(@PathVariable long callForIdeasId,
	                                 @ModelAttribute @Valid NewIdeaDTO newIdeaDTO, BindingResult errors, @MunicipalityId Long municipalityId,
	                                 @ModelAttribute ("authUser") User user) throws IOException {
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
		Idea idea = new Idea();
		if (user.getRole() == Role.YOUTH_COUNCIL_ADMINISTRATOR) {
			idea = ideaService.createIdea(newIdeaDTO, callForIdeasId, userService.getUserByUsernameOrEmail("offline_idea"));
		} else {
			idea = ideaService.createIdea(newIdeaDTO, callForIdeasId, user);
		}
		String s = null;
		String param=idea.getContent();
		Process p = Runtime.getRuntime().exec("python src\\pythonModule\\python\\input.py \""+ param+"\"");
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
		if (stringBuilder.length()==0){
			logger.debug("stringbuilder is length 0, output input didnt funvtion correctly");
		}
		else {
			if (stringBuilder.charAt(stringBuilder.length()-1)=='1'){
				logger.debug("this should be reported because prediction is: "+stringBuilder.charAt(stringBuilder.length()-1));
				reportService.createReport(new NewReportDTO(ReportReason.OTHER, "flagged by AI moderation"), idea.getId());
			}
			if (stringBuilder.charAt(stringBuilder.length()-1)=='0'){
				logger.debug("this should not be reported because prediction is: "+stringBuilder.charAt(stringBuilder.length()-1));
			}
		}

		IdeaDTO ideaDTO = modelMapper.map(idea, IdeaDTO.class);
		return new ResponseEntity<>(ideaDTO, HttpStatus.CREATED);
	}

	@GetMapping ("/sub-theme/{subThemeId}")
	public ResponseEntity<?> getIdeasBySubtheme(@PathVariable long callForIdeasId, @PathVariable int subThemeId, @MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		List<Idea> ideas;
		if (subThemeId == 0) {
			ideas = ideaService.getIdeasByCallForIdeas(callForIdeasId);
		} else {
			SubTheme subTheme = subThemeService.getAllSubThemes().get((subThemeId - 1));
			ideas = ideaService.getIdeasBySubtheme(subTheme);
		}

		List<IdeaDTO> ideaDTOS = new ArrayList<>();
		ideas.forEach(idea -> {
			IdeaDTO ideaDTO = modelMapper.map(idea, IdeaDTO.class);
			ideaDTOS.add(ideaDTO);
		});
		return new ResponseEntity<>(ideaDTOS, HttpStatus.FOUND);
	}

	@Moderator
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deleteIdea(@PathVariable Long id) {
		Optional<Idea> idea = ideaService.getIdeaById(id);
		if (idea.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ideaService.deleteIdeaById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
