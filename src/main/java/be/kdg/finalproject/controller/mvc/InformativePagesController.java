package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.page.InformativePageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class InformativePagesController {

	private final InformativePageService pageService;

	public InformativePagesController(InformativePageService pageService) {
		this.pageService = pageService;
	}

	@YouthCouncilAdmin
	@GetMapping ("/pages")
	public ModelAndView getPages(@MunicipalityId Long muid) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ModelAndView("municipality/municipality-pages")
				.addObject("pages", pageService.getAllPages(muid));
	}

	@GetMapping ("/{page-name}")
	public ModelAndView getPage(@MunicipalityId Long muid, @PathVariable ("page-name") String pageName) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ModelAndView("municipality/municipality-page")
				.addObject("page", pageService.getPageByName(muid, pageName));
	}
}
