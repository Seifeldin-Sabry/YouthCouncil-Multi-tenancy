package be.kdg.finalproject.controller.mvc;

import be.kdg.finalproject.controller.authority.Moderator;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.report.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashModController {

	ReportService reportService;
	IdeaService ideaService;
	CallForIdeasService callForIdeasService;

	public DashModController(ReportService reportService, IdeaService ideaService, CallForIdeasService callForIdeasService) {
		this.reportService = reportService;
		this.ideaService = ideaService;
		this.callForIdeasService = callForIdeasService;
	}

	@Moderator
	@GetMapping ("dash-mod")
	public ModelAndView getDashMod(@MunicipalityId Long municipalityId) {
		ModelAndView mav = new ModelAndView("moderator-dashboard");
		mav.addObject("reports", reportService.getAllReportsOfMunicipalityId(
				MunicipalityContext.getCurrentMunicipalityId()));
		List<CallForIdeas> calls = callForIdeasService.getCallForIdeasByMunicipalityId(
				MunicipalityContext.getCurrentMunicipalityId());
		List<Idea> ideas = new ArrayList<>();
		for (CallForIdeas call : calls) {
			ideas.addAll(ideaService.getIdeasByCallForIdeas(call.getId()));
		}
		mav.addObject("ideas", ideas);

		return mav;

	}
}
