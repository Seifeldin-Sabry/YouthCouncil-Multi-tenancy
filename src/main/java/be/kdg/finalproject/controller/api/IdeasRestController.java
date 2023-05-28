package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.IdeaDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.IdeaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ("/api/ideas")
public class IdeasRestController {

	private final IdeaService ideaService;
	private ModelMapper modelMapper = new ModelMapper();
	private Logger logger = org.slf4j.LoggerFactory.getLogger(IdeasRestController.class);

	public IdeasRestController(IdeaService ideaService) {this.ideaService = ideaService;}

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
}
