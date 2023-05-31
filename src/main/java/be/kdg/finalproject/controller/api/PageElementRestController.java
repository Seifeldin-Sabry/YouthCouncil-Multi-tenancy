package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.PageElementDTO;
import be.kdg.finalproject.controller.api.dto.post.NewPageElementDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.domain.page.elements.ListElement;
import be.kdg.finalproject.domain.page.elements.PageElement;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.page.*;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping ("/api/pages/{page}/elements")
public class PageElementRestController {
	private final PageElementService pageElementService;
	private final InformativePageService pageService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	public PageElementRestController(PageElementService pageElementService, InformativePageService pageService) {
		this.pageElementService = pageElementService;
		this.pageService = pageService;
	}

	@YouthCouncilAdmin
	@PostMapping
	public ResponseEntity<?> createNewPageElement(@MunicipalityId Long munId,
	                                              @PathVariable("page") String pageName,
	                                              @Valid @ModelAttribute NewPageElementDTO newPageElementDTO,
	                                              BindingResult errors) throws IOException {
		InformativePage page = pageService.getPageByName(munId, pageName);
		if (page.getPageName()==null){
			throw new EntityNotFoundException("Page not found in restcontroller");
		}
		if (errors.hasErrors()) {
			logger.debug("Error found");
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		PageElement pageElement;
		switch (newPageElementDTO.getElementType()) {
			case "heading" -> {
				newPageElementDTO.setFontSize(40);
				pageElement = pageElementService.addTextElement(newPageElementDTO, page);
			}
			case "paragraph" -> pageElement = pageElementService.addTextElement(newPageElementDTO, page);
			case "image" -> pageElement = pageElementService.addImageElement(newPageElementDTO, page);
			case "list" -> pageElement = pageElementService.addListElement(newPageElementDTO, page);
			default -> throw new EntityNotFoundException("Element Type not found");
		}
		return new ResponseEntity<>(modelMapper.map(pageElement, PageElementDTO.class),HttpStatus.CREATED);
	}

	@YouthCouncilAdmin
	@PatchMapping("/{id}/list-item")
	public ResponseEntity<?> updateList(@ModelAttribute("item") String listItem, @PathVariable("id") Long id){
		if (listItem==null){
			throw new EntityNotFoundException("List item is null");
		}
		if (id==null){
			throw new EntityNotFoundException("List id not found");
		}
		ListElement listElement = pageElementService.updateListElementList(listItem, id);
		return new ResponseEntity<>(modelMapper.map(listElement, PageElementDTO.class), HttpStatus.FOUND);
	}

	@YouthCouncilAdmin
	@DeleteMapping("/{id}/remove-last-item")
	public ResponseEntity<?> removeLastListItem(@PathVariable("id") Long id){
		if (id==null){
			throw new EntityNotFoundException("List id not found");
		}
		pageElementService.deleteLastListItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@YouthCouncilAdmin
	@PatchMapping("/{id}/order")
	public ResponseEntity<?> updateOrder(@ModelAttribute("order") Integer order,  @ModelAttribute("type") String type,
	                                     @PathVariable("id") Long id){
		if (order==null){
			throw new EntityNotFoundException("Order is null");
		}
		if (id==null){
			throw new EntityNotFoundException("List id not found");
		}
		pageElementService.updateOrder(order, type, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
