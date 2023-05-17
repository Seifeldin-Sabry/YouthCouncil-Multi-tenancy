package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.report.ReportReason;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.theme.ThemeService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class IdeaController {
	private final CallForIdeasService callForIdeasService;
	private final ThemeService themeService;
	private final IdeaService ideaService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(IdeaController.class);

	public IdeaController(CallForIdeasService callForIdeasService, ThemeService themeService, IdeaService ideaService) {
		this.callForIdeasService = callForIdeasService;
		this.themeService = themeService;
		this.ideaService = ideaService;
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

	@LoggedIn
	@GetMapping("/call-for-ideas/ideas/liked")
	public ModelAndView viewLikedIdeas(@ModelAttribute ("authUser") User user){
		ModelAndView mav = new ModelAndView("call-for-ideas/liked-ideas");
		logger.debug("Searching for liked ideas by user ID: "+user.getId());
		List<Idea> ideas = ideaService.getLikedIdeasByUser(user.getId());
		if (ideas!=null){
			ideas.forEach(idea -> {
				for (UserIdeaLike liker : idea.getLikers()) {
					if (liker.getLiker().equals(user)){
						idea.setLiked();
					}
				}
			});
		}

		mav.addObject("ideas", ideas);
		mav.addObject("reportReasons", ReportReason.values());
		return mav;
	}


}
