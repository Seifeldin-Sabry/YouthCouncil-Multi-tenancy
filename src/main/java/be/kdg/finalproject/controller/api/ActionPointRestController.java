package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.ActionPointDTO;
import be.kdg.finalproject.controller.api.dto.post.NewActionPointDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.actionpoints.ActionPointService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping ("/api/action-points")
public class ActionPointRestController {

	private final ActionPointService actionPointService;
	private final Logger logger = LoggerFactory.getLogger(ActionPointRestController.class);
	private final ModelMapper modelMapper = new ModelMapper();

	public ActionPointRestController(ActionPointService actionPointService) {
		this.actionPointService = actionPointService;
	}

	@YouthCouncilAdmin
	@PostMapping (consumes = {"multipart/form-data"})
	public ResponseEntity<?> addActionPoint(@MunicipalityId Long municipalityId,
	                                        @ModelAttribute @Valid NewActionPointDTO newActionPointDTO,
	                                        BindingResult errors) throws IOException {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		if (errors.hasErrors()) {
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		logger.debug(newActionPointDTO.toString());
		logger.debug("{} images found", newActionPointDTO.getImages().size());

		ActionPoint actionPoint = actionPointService.createActionPoint(newActionPointDTO, municipalityId);
		ActionPointDTO actionPointDTO = modelMapper.map(actionPoint, ActionPointDTO.class);
		return new ResponseEntity<>(actionPointDTO, org.springframework.http.HttpStatus.CREATED);
	}
}
