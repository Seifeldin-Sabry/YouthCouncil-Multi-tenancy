package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.SubThemeDTO;
import be.kdg.finalproject.controller.api.dto.get.ThemeDTO;
import be.kdg.finalproject.controller.api.dto.patch.ActivateDTO;
import be.kdg.finalproject.controller.api.dto.post.NewSubThemeDTO;
import be.kdg.finalproject.controller.api.dto.post.NewThemeDTO;
import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.theme.Theme;
import be.kdg.finalproject.service.theme.ThemeService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@GeneralAdminOnly
@RequestMapping ("/api/themes")
public class ThemeRestController {

	private final ThemeService themeService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ThemeRestController.class);
	private final ModelMapper modelMapper = new ModelMapper();

	public ThemeRestController(ThemeService themeService) {
		this.themeService = themeService;
	}

	@PostMapping
	public ResponseEntity<?> addTheme(@Valid @RequestBody NewThemeDTO themeDTO, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, String> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		Theme theme = themeService.addTheme(themeDTO);
		ThemeDTO dto = modelMapper.map(theme, ThemeDTO.class);
		logger.debug("Theme: {}", dto);
		return ResponseEntity.created(null).body(dto);
	}

	@PatchMapping ("/{themeId}/deactivate")
	public ResponseEntity<?> deactivateOrReactivateTheme(@PathVariable Long themeId, @RequestBody ActivateDTO activateDTO) {
		Optional<Theme> theme = themeService.getThemeById(themeId);
		if (theme.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		themeService.deactivateOrReactivateTheme(theme.get(), activateDTO);
		return ResponseEntity.noContent().build();
	}

	@PostMapping ("/{themeId}/subthemes")
	public ResponseEntity<?> addSubTheme(@PathVariable Long themeId, @Valid @RequestBody NewSubThemeDTO subThemeDTO, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, String> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		SubTheme subTheme = themeService.addSubTheme(subThemeDTO, themeId);
		logger.debug("SubTheme: {}", subTheme);
		return ResponseEntity.created(null).body(modelMapper.map(subTheme, SubThemeDTO.class));
	}

	@PatchMapping ("{themeId}")
	public ResponseEntity<?> updateTheme(@PathVariable Long themeId, @Valid @RequestBody NewThemeDTO themeDTO, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, String> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		Optional<Theme> theme = themeService.getThemeById(themeId);
		if (theme.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		themeService.updateTheme(themeId, themeDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping ("/{themeId}/subthemes")
	public ResponseEntity<?> getSubThemes(@PathVariable Long themeId) {
		Optional<Theme> theme = themeService.getThemeById(themeId);
		if (theme.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(modelMapper.map(theme.get().getSubThemes(), SubThemeDTO[].class));
	}

	@PatchMapping ("/{themeId}/subthemes/{subThemeId}/deactivate")
	public ResponseEntity<?> deleteSubTheme(@PathVariable Long themeId, @PathVariable Long subThemeId, @RequestBody ActivateDTO activateDTO) {
		themeService.deactivateOrReactivateSubthemeByIdAndThemeId(subThemeId, themeId, activateDTO);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping ("/{themeId}/subthemes/{subThemeId}")
	public ResponseEntity<?> updateSubTheme(@PathVariable Long themeId, @PathVariable Long subThemeId, @Valid @RequestBody NewSubThemeDTO subThemeDTO, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, String> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		Optional<SubTheme> subTheme = themeService.getSubThemeById(subThemeId, themeId);
		if (subTheme.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		themeService.updateSubTheme(subThemeDTO, subThemeId, themeId);
		return ResponseEntity.noContent().build();
	}

}
