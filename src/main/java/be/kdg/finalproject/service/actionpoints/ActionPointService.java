package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.controller.api.dto.post.NewActionPointDTO;
import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ActionPointService {

	private final ActionPointRepository actionPointRepository;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointService.class);

	public ActionPointService(ActionPointRepository actionPointRepository) {
		this.actionPointRepository = actionPointRepository;
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

	public ActionPoint createActionPoint(@Valid NewActionPointDTO actionPointViewModel, Long municipalityId) {
		ActionPoint actionPoint = new ActionPoint();
		actionPoint.setTitle(actionPointViewModel.getTitle());
		actionPoint.setDescription(actionPointViewModel.getDescription());
		actionPoint.setMunicipalityId(municipalityId);
		actionPoint.setSubTheme(actionPointViewModel.getSubTheme());
		actionPoint.setImages(new HashSet<>(actionPointViewModel.getImageSources()));
		actionPointViewModel.getActionPointProposals().forEach(actionPoint::addProposal);
		return actionPointRepository.save(actionPoint);
	}

	public ActionPoint getActionPointByUUID(Long municipalityId, UUID uuid) {
		logger.debug("Getting action point with uuid: " + uuid);
		logger.debug("Getting action point with municipalityId: " + municipalityId);
		return actionPointRepository.findByMunicipalityIdAndUuidWithProposals(municipalityId, uuid)
		                            .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
	}
}
