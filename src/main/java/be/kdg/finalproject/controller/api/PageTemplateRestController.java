package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.service.page.PageTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/page-templates")
public class PageTemplateRestController {

	private PageTemplateService pageTemplateService;

	public PageTemplateRestController(PageTemplateService pageTemplateService) {
		this.pageTemplateService = pageTemplateService;
	}

	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deletePageTemplate(@PathVariable Long id) {
		pageTemplateService.deletePageTemplate(id);
		return ResponseEntity.noContent().build();
	}

}
