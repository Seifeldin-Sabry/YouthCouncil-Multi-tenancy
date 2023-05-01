package be.kdg.finalproject.repository.callforidea;

import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.theme.SubTheme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	@Query("""
SELECT i
FROM IDEAS i
WHERE i.uuid = :uuid
""")
	Idea findByUUID(UUID uuid);
	@Query("""
SELECT i
FROM IDEAS i
WHERE i.subTheme = :subTheme
""")
	List<Idea> findAllBySubTheme(SubTheme subTheme);
}
