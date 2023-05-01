package be.kdg.finalproject.repository.theme;

import be.kdg.finalproject.domain.theme.Theme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ThemeRepository extends CrudRepository<Theme, Long> {
	boolean existsByThemeNameIgnoreCase(String themeName);

	@Transactional
	@Modifying
	@Query ("update THEMES t set t.themeName = ?1 where t.id = ?2")
	int updateThemeNameById(String themeName, Long id);


	@Query ("select t from THEMES t order by t.isActive desc, t.id")
	List<Theme> findAllOrderByIdAsc();

	@Query ("select t from THEMES t where t.isActive = true")
	List<Theme> findActiveThemes();

}
