package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.CallForIdeasDTO;
import be.kdg.finalproject.controller.api.dto.post.NewCallForIdeasDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.form.FormService;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.theme.ThemeService;
import be.kdg.finalproject.service.user.UserService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/call-for-ideas")
public class CallForIdeasRestController {
	private final CallForIdeasService callForIdeasService;
	private final UserService userService;
	private final IdeaService ideaService;
	private final FormService formService;
	private final ThemeService themeService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(CallForIdeasRestController.class);

	public CallForIdeasRestController(CallForIdeasService callForIdeasService, UserService userService, IdeaService ideaService, FormService formService, ThemeService themeService) {
		this.callForIdeasService = callForIdeasService;
		this.userService = userService;
		this.ideaService = ideaService;
		this.formService = formService;
		this.themeService = themeService;
	}

	@YouthCouncilAdmin
	@PostMapping
	public ResponseEntity<?> addCallForIdea(@MunicipalityId Long municipalityId, @ModelAttribute @Valid NewCallForIdeasDTO newCallForIdeasDTO, BindingResult errors){
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
	@PatchMapping("/{id}/active")
	public ResponseEntity<?> swapActive(@PathVariable Long id){
		Optional<CallForIdeas> callForIdeas = callForIdeasService.getCallForIdeaById(id);
		if (callForIdeas.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		callForIdeasService.changeActiveById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
