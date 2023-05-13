package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.page.template.TemplateElement;
import be.kdg.finalproject.service.page.PageTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@GeneralAdminOnly
@RequestMapping ("/dashboard/page-templates")
public class PageTemplatesController {

	private final PageTemplateService pageTemplateService;

	public PageTemplatesController(PageTemplateService pageTemplateService) {
		this.pageTemplateService = pageTemplateService;
	}

	@GetMapping
	public ModelAndView getPageTemplates() {
		return new ModelAndView("platform/platform-page-templates")
				.addObject("pageTemplates", pageTemplateService.getAllPageTemplates());
	}

	@GetMapping ("/{uuid}")
	public ModelAndView getPageTemplate(@PathVariable UUID uuid) {
		return new ModelAndView("platform/platform-page-template")
				.addObject("pageTemplate", pageTemplateService.getPageTemplateByUuid(uuid))
				.addObject("elementTypes", TemplateElement.values());
	}
}
