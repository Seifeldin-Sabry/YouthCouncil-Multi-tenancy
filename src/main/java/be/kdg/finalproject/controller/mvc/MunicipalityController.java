package be.kdg.finalproject.controller.mvc;


import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class MunicipalityController {

	private final Logger logger = LoggerFactory.getLogger(MunicipalityController.class);
	private final MunicipalityService municipalityService;

	public MunicipalityController(MunicipalityService municipalityService) {this.municipalityService = municipalityService;}

	//	@GetMapping ("/municipalities")
	//	public String showPlayers(Model model) {
	//		logger.info("Showing the municipalities...");
	//		model.addAttribute("municipalities", municipalityService.getAllMunicipalities());
	//		return "html/manage";
	//	}
	//	@GetMapping ("/manage")
	//	public ModelAndView showAnalytics() {
	//		var mav = new ModelAndView("html/manage");
	//		mav.addObject("municipalities");
	//		return mav;
	//	}
}
