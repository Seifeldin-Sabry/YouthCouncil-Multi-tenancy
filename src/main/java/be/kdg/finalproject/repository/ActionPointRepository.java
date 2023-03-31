package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionPointRepository extends CrudRepository<ActionPoint, Long> {
	List<ActionPoint> findByMunicipality_Id(Long id);
}
