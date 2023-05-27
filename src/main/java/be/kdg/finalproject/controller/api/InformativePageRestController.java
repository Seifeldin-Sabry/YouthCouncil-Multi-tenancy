package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.InformativePageListItemDTO;
import be.kdg.finalproject.controller.api.dto.patch.ActivateDTO;
import be.kdg.finalproject.controller.api.dto.post.NewInformativePageDTO;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.page.InformativePageService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/pages")
public class InformativePageRestController {

	private final InformativePageService pageService;
	private final ModelMapper modelMapper = new ModelMapper();

	public InformativePageRestController(InformativePageService pageService) {this.pageService = pageService;}

	@GetMapping
	public ResponseEntity<List<InformativePage>> getAllPages(@MunicipalityId Long muid) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ResponseEntity<>(pageService.getAllPages(muid), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InformativePageListItemDTO> addPage(@RequestBody @Valid NewInformativePageDTO newPage, @MunicipalityId Long muid) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		InformativePage page = pageService.addPage(newPage, muid);
		return new ResponseEntity<>(modelMapper.map(page, InformativePageListItemDTO.class), HttpStatus.CREATED);
	}

	@PutMapping ("/{id}")
	public ResponseEntity<Void> updatePage(@PathVariable Long id, @RequestBody InformativePage page) {
		InformativePage updatedPage = pageService.updatePage(id, page);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping ("/{id}/active")
	public ResponseEntity<Void> updatePageActive(@PathVariable Long id, @RequestBody ActivateDTO page) {
		pageService.updatePageActive(id, page);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deletePage(@PathVariable Long id) {
		pageService.deletePage(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
