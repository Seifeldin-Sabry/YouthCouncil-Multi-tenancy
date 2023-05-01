package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.api.CallForIdeasRestController;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.callforidea.UserIdeaLikeService;
import be.kdg.finalproject.service.form.FormService;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import be.kdg.finalproject.service.theme.ThemeService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class CallForIdeasController {
	private final CallForIdeasService callForIdeasService;
	private final MunicipalityService municipalityService;
	private final FormService formService;
	private final ThemeService themeService;
	private final UserIdeaLikeService userIdeaLikeService;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(CallForIdeasController.class);


	public CallForIdeasController(CallForIdeasService callForIdeasService, MunicipalityService municipalityService,
	                              FormService formService, ThemeService themeService,
	                              UserIdeaLikeService userIdeaLikeService) {
		this.callForIdeasService = callForIdeasService;
		this.municipalityService = municipalityService;
		this.formService = formService;
		this.themeService = themeService;
		this.userIdeaLikeService = userIdeaLikeService;
	}

	@YouthCouncilAdmin
	@GetMapping("/call-for-ideas-dashboard")
	public ModelAndView getCallForIdeasDashboard(){
		ModelAndView mav = new ModelAndView("call-for-ideas/call-for-ideas-dashboard");
		mav.addObject("calls", callForIdeasService.getCallForIdeasByMunicipalityId(MunicipalityContext.getCurrentMunicipalityId()));
		mav.addObject("themes", themeService.getAllThemes());
		return mav;
	}

	@GetMapping("/call-for-ideas")
	public ModelAndView getCallForIdeas(){
		ModelAndView mav = new ModelAndView("call-for-ideas/call-for-ideas");
		mav.addObject("calls", callForIdeasService.getCallForIdeasByMunicipalityId(MunicipalityContext.getCurrentMunicipalityId()));

		return mav;
	}

	@GetMapping("/call-for-ideas/{uuid}/details")
	public ModelAndView getCallForIdeasDetails(@PathVariable UUID uuid, @ModelAttribute ("authUser") User user){
		ModelAndView mav = new ModelAndView("call-for-ideas/call-for-ideas-details");
		CallForIdeas callForIdeas = callForIdeasService.getCallForIdeaByUUIDWithIdeas(uuid);
		if (callForIdeas==null){
			logger.debug("No callForIdea found");
			throw new EntityNotFoundException("Not found");
		}
		Set<Idea> ideas = callForIdeas.getIdeas();
		ideas.forEach(idea -> {
			for (UserIdeaLike liker : idea.getLikers()) {
				if (liker.getLiker().equals(user)){
					idea.setLiked();
				}
			}
		});
		mav.addObject("call", callForIdeas);
		mav.addObject("ideas", ideas);

		return mav;
	}


}
