package be.kdg.finalproject.repository.callforidea;

import be.kdg.finalproject.domain.idea.CallForIdeas;
import org.aspectj.weaver.ast.Call;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface CallForIdeasRepository extends CrudRepository<CallForIdeas, Long> {
	@Query("""
SELECT c
FROM CALL_FOR_IDEAS c
LEFT JOIN fetch c.ideas i
WHERE c.uuid = :uuid
""")
	CallForIdeas findByUUIDWithIdeas(UUID uuid);
	List<CallForIdeas> findCallForIdeasByMunicipalityId(long municipalityId);
	@Modifying
	@Transactional
	@Query("UPDATE CALL_FOR_IDEAS c SET c.isActive = CASE WHEN (c.isActive=TRUE) THEN FALSE ELSE TRUE END WHERE c.id = :callForIdeasId")
	void updateActiveById(long callForIdeasId);
}
