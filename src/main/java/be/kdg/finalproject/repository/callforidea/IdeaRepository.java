package be.kdg.finalproject.repository.callforidea;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.theme.SubTheme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	List<Idea> findByIdIn(Collection<Long> ids);

	@Transactional
	@Modifying
	@Query ("update IDEAS i set i.deleted = true where i.id = ?1")
	void softDeleteById(Long id);

	@Query ("""
			SELECT i
			FROM IDEAS i
			WHERE i.deleted = false
			""")
	List<Idea> findAllIdeas();

	@Query ("""
			SELECT i
			FROM IDEAS i
			WHERE i.subTheme = :subTheme
			AND i.deleted = false
			""")
	List<Idea> findAllBySubTheme(SubTheme subTheme);

	@Query ("""
			SELECT i
			FROM IDEAS i
			WHERE i.callForIdeasId = :callForIdeasId
			AND i.deleted = false
			""")
	List<Idea> findAllByCallForIdeasId(long callForIdeasId);

	@Query ("""
			SELECT i
			FROM IDEAS i
			JOIN CALL_FOR_IDEAS cfi
			ON cfi.id = i.callForIdeasId
			WHERE i.deleted = false
			AND( i.content LIKE %:searchTerm%
			OR i.title LIKE %:searchTerm%)
			AND cfi.municipality.id = :municipalityId
			""")
	List<Idea> findAllBySearchTermContainingIgnoreCase(String searchTerm, long municipalityId);
}
