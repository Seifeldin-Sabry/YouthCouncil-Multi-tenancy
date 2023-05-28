package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.IdeaDTO;
import be.kdg.finalproject.controller.authority.Moderator;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.theme.SubThemeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/ideas")
public class IdeasRestController {

	private final IdeaService ideaService;
	private final SubThemeService subThemeService;
	private ModelMapper modelMapper = new ModelMapper();
	private Logger logger = org.slf4j.LoggerFactory.getLogger(IdeasRestController.class);

	public IdeasRestController(IdeaService ideaService, SubThemeService subThemeService) {
		this.ideaService = ideaService;
		this.subThemeService = subThemeService;
	}

	@YouthCouncilAdmin
	@GetMapping
	public ResponseEntity<?> getAllIdeas(@RequestParam (name = "t") String term, @MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			throw new EntityNotFoundException("Not found");
		}
		List<Idea> ideasBySearchTerm = ideaService.getIdeasBySearchTerm(term, municipalityId);
		if (ideasBySearchTerm.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		IdeaDTO[] map = modelMapper.map(ideasBySearchTerm, IdeaDTO[].class);
		logger.debug("ideas found {}", Arrays.toString(map));
		return ResponseEntity.ok(map);
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
}
