package be.kdg.finalproject.repository;

import be.kdg.finalproject.domain.theme.SubTheme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SubThemeRepository extends CrudRepository<SubTheme, Long> {
	@Transactional
	@Modifying
	@Query ("delete from SUB_THEMES s where s.id = ?1 and s.theme.id = ?2")
	int deleteByIdAndTheme_Id(Long subThemeId, Long themeId);

	Optional<SubTheme> findByIdAndTheme_IdOrderByIdAsc(Long subThemeId, Long themeId);

	boolean existsBySubThemeNameIgnoreCase(String subThemeName);

	@Transactional
	@Modifying
	@Query ("update SUB_THEMES s set s.subThemeName = ?1 where s.id = ?2 and s.theme.id = ?3")
	int updateSubThemeNameById(String subThemeName, Long subthemeId, Long themeId);

}
