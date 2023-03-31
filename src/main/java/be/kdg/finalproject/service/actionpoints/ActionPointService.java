package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.repository.ActionPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionPointService {

	private final ActionPointRepository actionPointRepository;

	public ActionPointService(ActionPointRepository actionPointRepository) {this.actionPointRepository = actionPointRepository;}

	public List<ActionPoint> getActionPointsByMunicipalityId(long municipalityId) {
		return actionPointRepository.findByMunicipality_Id(municipalityId);
	}
}
