package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.page.InformativePage;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.page.InformativePageService;
import com.google.api.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import java.util.List;


@Controller
public class InformativePagesController {

	private final InformativePageService pageService;

	public InformativePagesController(InformativePageService pageService) {
		this.pageService = pageService;
	}

	@YouthCouncilAdmin
	@GetMapping("/pages")
	public ModelAndView getPages(@MunicipalityId Long muid) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		return new ModelAndView("municipality/municipality-pages")
				.addObject("pages", pageService.getAllPages(muid));
	}

	@GetMapping ("/{page-name}")
	public ModelAndView getActivePage(@MunicipalityId Long muid, @PathVariable ("page-name") String pageName,
	                                  @ModelAttribute ("currentMembership") Membership membership) {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		if (membership.getRole() == Role.YOUTH_COUNCIL_ADMINISTRATOR){
			return new ModelAndView("municipality/municipality-page")
					.addObject("page", pageService.getPageByName(muid, pageName));
		}
		return new ModelAndView("municipality/municipality-page")
				.addObject("page", pageService.getActivePageByName(muid, pageName));
	}

	@YouthCouncilAdmin
	@GetMapping ("/builder/{page-name}")
	public ModelAndView getBuilderPage(@MunicipalityId Long muid, @PathVariable ("page-name") String pageName,
	                                  @ModelAttribute ("currentMembership") Membership membership) throws AuthenticationException {
		if (muid == null) {
			throw new EntityNotFoundException("Page not found");
		}
		if (membership.getRole() == Role.YOUTH_COUNCIL_ADMINISTRATOR){
			return new ModelAndView("municipality/municipality-page-builder")
					.addObject("page", pageService.getPageByName(muid, pageName));
		}
		throw new AuthenticationException("Only yc admin is allowed to access this page");
	}
}
