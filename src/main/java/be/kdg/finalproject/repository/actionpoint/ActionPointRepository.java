package be.kdg.finalproject.repository.actionpoint;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionPointRepository extends CrudRepository<ActionPoint, Long> {
	List<ActionPoint> findByMunicipalityId(Long municipalityId);

	@Query ("""
			SELECT ap, CASE WHEN (SELECT COUNT(*)
			                        FROM USER_ACTION_POINT_FOLLOW uapf
			                        WHERE uapf.actionPoint = ap AND uapf.follower.id = :userId) > 0
			                        THEN true ELSE false END as followed,
			                        CASE WHEN (SELECT COUNT(*)
			                        FROM USER_ACTION_POINT_LIKE uapl
			                        WHERE uapl.actionPoint = ap AND uapl.liker.id = :userId) > 0
			                        THEN true ELSE false END as liked
			FROM ACTION_POINTS ap
			WHERE ap.municipalityId = :municipalityId
			ORDER BY ap.dateCreated DESC
			""")
	List<Object[]> getActionPointsWithFollowedByUser(@Param ("municipalityId") Long municipalityId, @Param ("userId") Long userId);
}
