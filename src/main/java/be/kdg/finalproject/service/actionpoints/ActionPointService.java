package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionPointService {

	private final ActionPointRepository actionPointRepository;

	public ActionPointService(ActionPointRepository actionPointRepository) {this.actionPointRepository = actionPointRepository;}

	public List<ActionPoint> getActionPointsByMunicipalityIdAndUserIdWithImages(long municipalityId, long userId) {
		List<Object[]> results = actionPointRepository.getActionPointsWithFollowedByUser(municipalityId, userId);
		List<ActionPoint> actionPoints = new ArrayList<>();
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

	public ActionPoint getActionPointById(Long actionPointId) {
		return actionPointRepository.findById(actionPointId)
		                            .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
	}
}
