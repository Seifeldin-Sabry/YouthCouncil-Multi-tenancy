package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import be.kdg.finalproject.service.theme.ThemeService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class IdeaController {
	private final CallForIdeasService callForIdeasService;
	private final ThemeService themeService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(IdeaController.class);

	public IdeaController(CallForIdeasService callForIdeasService, ThemeService themeService) {
		this.callForIdeasService = callForIdeasService;
		this.themeService = themeService;
	}

	@YouthCouncilAdmin
	@GetMapping("/call-for-ideas-dashboard/{callUUID}/ideas")
	public ModelAndView viewIdeas(@PathVariable UUID callUUID){
		CallForIdeas callForIdeas = callForIdeasService.getCallForIdeaByUUIDWithIdeas(callUUID);
		if (callForIdeas==null){
			logger.debug("No callForIdea found");
			throw new EntityNotFoundException("Not found");
		}
		ModelAndView mav = new ModelAndView("call-for-ideas/ideas");
		mav.addObject("ideas", callForIdeasService.getCallForIdeaByUUIDWithIdeas(callUUID).getIdeas());
		mav.addObject("themes", themeService.getAllThemes());
		return mav;
	}


}
