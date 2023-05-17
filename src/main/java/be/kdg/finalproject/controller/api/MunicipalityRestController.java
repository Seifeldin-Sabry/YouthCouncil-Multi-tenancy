package be.kdg.finalproject.controller.api;
import be.kdg.finalproject.controller.api.dto.get.MunicipalityDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.media.ImageService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping ("/api/municipalities")
public class MunicipalityRestController {
	private final MunicipalityService municipalityService;
	private final ImageService imageService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = Logger.getLogger(MunicipalityRestController.class.getName());


	@Autowired
	public MunicipalityRestController(MunicipalityService municipalityService, ImageService imageService) {
		this.municipalityService = municipalityService;
		this.imageService = imageService;
	}

	@GetMapping
	public ResponseEntity<List<MunicipalityDTO>> getAllMunicipalities(@RequestParam(name = "p") boolean hasPlatform) {
		var allMunicipalities = municipalityService.getAllMunicipalities(hasPlatform);
		logger.info("All municipalities: " + allMunicipalities);
		if (allMunicipalities.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		var allMunicipalitiesDto = allMunicipalities.stream()
		                                            .map(municipality -> modelMapper.map(municipality, MunicipalityDTO.class))
		                                            .toList();

		return ResponseEntity.ok(allMunicipalitiesDto);
	}

	@YouthCouncilAdmin
	@PatchMapping("/change-election-phase")
	public ResponseEntity<?> changeElectionPhase(@MunicipalityId Long municipalityId){
		municipalityService.changeElectrionPhaseByMunicipality(municipalityId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@YouthCouncilAdmin
	@PostMapping("/logo")
	public ResponseEntity<?> uploadLogo(@ModelAttribute List<MultipartFile> images, @MunicipalityId Long municipalityId) throws IOException {
		List<String> imageSources = imageService.saveImages(images);
		String logo = imageSources.get(0);
		municipalityService.changeLogo(logo, municipalityId);
		MunicipalityDTO municipalityDTO = new MunicipalityDTO();
		municipalityDTO.setLogo(logo);
		return new ResponseEntity<>(municipalityDTO, HttpStatus.CREATED);

	}

}
