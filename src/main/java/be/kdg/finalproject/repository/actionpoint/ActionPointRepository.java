package be.kdg.finalproject.repository.actionpoint;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.theme.SubTheme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ActionPointRepository extends CrudRepository<ActionPoint, Long> {

	@Query ("""
			SELECT ap
			FROM ACTION_POINTS ap
			left join fetch ap.actionPointProposals
			left join fetch ap.linkedIdeas
			WHERE ap.uuid = :uuid
			AND ap.municipalityId = :municipalityId
			""")
	Optional<ActionPoint> findByMunicipalityIdAndUuidWithProposals(Long municipalityId, UUID uuid);

	Set<ActionPoint> findByMunicipalityId(Long municipalityId);

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
			left JOIN fetch ap.followers
			left JOIN fetch ap.likers
			WHERE ap.municipalityId = :municipalityId
			ORDER BY ap.dateCreated DESC
			""")
	List<Object[]> getActionPointsWithFollowedByUser(@Param ("municipalityId") Long municipalityId, @Param ("userId") Long userId);


	Set<ActionPoint> getActionPointBySubTheme(SubTheme subtheme);

}
