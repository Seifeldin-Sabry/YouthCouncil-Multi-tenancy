package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.MunicipalityDTO;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping ("/api/municipalities")
public class MunicipalityRestController {
	private final MunicipalityService municipalityService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = Logger.getLogger(MunicipalityRestController.class.getName());

	@Autowired
	public MunicipalityRestController(MunicipalityService municipalityService) {
		this.municipalityService = municipalityService;
		modelMapper.createTypeMap(MunicipalityDTO.class, Municipality.class)
		           .addMappings(new PropertyMap<>() {
			           @Override
			           protected void configure() {
				           source.setHasWebsite(destination.getMembers() != null);
			           }
		           });
	}

	@GetMapping
	public ResponseEntity<List<MunicipalityDTO>> getAllMunicipalities() {
		var allMunicipalities = municipalityService.getAllMunicipalities();
		logger.info("All municipalities: " + allMunicipalities);
		if (allMunicipalities.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		var allMunicipalitiesDto = allMunicipalities.stream()
		                                            .map(municipality -> modelMapper.map(municipality, MunicipalityDTO.class))
		                                            .toList();

		return ResponseEntity.ok(allMunicipalitiesDto);
	}
}
