package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.controller.api.dto.post.NewActionPointDTO;
import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import be.kdg.finalproject.repository.theme.SubThemeRepository;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.media.ImageService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ActionPointService {

	private final ActionPointRepository actionPointRepository;

	private final SubThemeRepository subThemeRepository;
	private final ImageService imageService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointService.class);

	private IdeaService ideaService;

	public ActionPointService(ActionPointRepository actionPointRepository, SubThemeRepository subThemeRepository, ImageService imageService, IdeaService ideaService) {
		this.actionPointRepository = actionPointRepository;
		this.subThemeRepository = subThemeRepository;
		this.imageService = imageService;
		this.ideaService = ideaService;
	}

	public Set<ActionPoint> getActionPointsByMunicipalityIdAndUserIdWithImages(long municipalityId, long userId) {
		List<Object[]> results = actionPointRepository.getActionPointsWithFollowedByUser(municipalityId, userId);
		Set<ActionPoint> actionPoints = new HashSet<>();
		for (Object[] result : results) {
			ActionPoint actionPoint = (ActionPoint) result[0];
			boolean followed = (boolean) result[1];
			boolean liked = (boolean) result[2];
			actionPoint.setFollowed(followed);
			actionPoint.setLiked(liked);
			actionPoints.add(actionPoint);
		}
		return actionPoints;
	}

	public Set<ActionPoint> getActionPointsByMunicipalityId(long municipalityId) {
		return actionPointRepository.findByMunicipalityId(municipalityId);
	}

	public ActionPoint getActionPointById(Long actionPointId) {
		return actionPointRepository.findById(actionPointId)
		                            .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
	}

	public ActionPoint createActionPoint(@Valid NewActionPointDTO actionPointViewModel, Long municipalityId) throws IOException {
		ActionPoint actionPoint = new ActionPoint();
		List<String> imageSources = imageService.saveImages(actionPointViewModel.getImages());
		actionPoint.setTitle(actionPointViewModel.getTitle());
		actionPoint.setDescription(actionPointViewModel.getDescription());
		actionPoint.setMunicipalityId(municipalityId);
		actionPoint.setSubTheme(actionPointViewModel.getSubTheme());
		actionPoint.setImages(new HashSet<>(imageSources));
		actionPoint.setLinkedIdeas(ideaService.getIdeasByIds(actionPointViewModel.getIdeas()));
		actionPointViewModel.getActionPointProposals().forEach(actionPoint::addProposal);
		logger.debug("Creating action point: " + actionPoint);
		return actionPointRepository.save(actionPoint);
	}

	public ActionPoint getActionPointByUUID(Long municipalityId, UUID uuid) {
		logger.debug("Getting action point with uuid: " + uuid);
		logger.debug("Getting action point with municipalityId: " + municipalityId);
		return actionPointRepository.findByMunicipalityIdAndUuidWithProposals(municipalityId, uuid)
		                            .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
	}

	public Set<ActionPoint> findBySubThemeId(Long subThemeId) {
		return actionPointRepository.getActionPointBySubTheme(subThemeRepository.findById(subThemeId)
		                                                                        .orElseThrow(() -> new EntityNotFoundException("Subtheme not found!")));
	}
}
