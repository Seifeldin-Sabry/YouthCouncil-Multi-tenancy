package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.IdeaDTO;
import be.kdg.finalproject.controller.api.dto.get.UserDTO;
import be.kdg.finalproject.controller.api.dto.post.NewIdeaDTO;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.theme.SubThemeService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.search.SearchTerm;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping ("/api/call-for-ideas/{callForIdeasId}/ideas")
public class IdeaRestController {
	private final CallForIdeasService callForIdeasService;
	private final IdeaService ideaService;
	private final SubThemeService subThemeService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(IdeaRestController.class);

	public IdeaRestController(CallForIdeasService callForIdeasService, IdeaService ideaService, SubThemeService subThemeService) {
		this.callForIdeasService = callForIdeasService;
		this.ideaService = ideaService;
		this.subThemeService = subThemeService;
	}

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
		Idea idea = ideaService.createIdea(newIdeaDTO, callForIdeasId, user);
		IdeaDTO ideaDTO = modelMapper.map(idea, IdeaDTO.class);
		return new ResponseEntity<>(ideaDTO, HttpStatus.CREATED);
	}

	@GetMapping("/sub-theme/{subThemeId}")
	public ResponseEntity<?> getIdeasBySubtheme(@PathVariable long callForIdeasId,@PathVariable int subThemeId, @MunicipalityId Long municipalityId){
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		List<Idea> ideas = new ArrayList<>();
		if (subThemeId==0){
			if (callForIdeasService.getCallForIdeaById(callForIdeasId).isPresent()){
				ideas = callForIdeasService.getCallForIdeaById(callForIdeasId).get().getIdeas().stream().toList();
			}
		}
		else {
			SubTheme subTheme = subThemeService.getAllSubThemes().get((subThemeId-1));
			ideas = ideaService.getIdeasBySubtheme(subTheme);
		}

		List<IdeaDTO> ideaDTOS = new ArrayList<>();
		ideas.forEach(idea -> {
			IdeaDTO ideaDTO = modelMapper.map(idea, IdeaDTO.class);
			ideaDTOS.add(ideaDTO);
		});
		return new ResponseEntity<>(ideaDTOS, HttpStatus.FOUND);
	}
}
