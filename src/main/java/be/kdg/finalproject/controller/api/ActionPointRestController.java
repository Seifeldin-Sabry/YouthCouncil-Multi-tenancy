package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.ActionPointDTO;
import be.kdg.finalproject.controller.api.dto.post.NewActionPointDTO;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.actionpoints.ActionPointService;
import be.kdg.finalproject.service.media.ImageService;
import be.kdg.finalproject.service.media.ImageServiceDevImpl;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/actionpoints")
public class ActionPointRestController {

	private final ActionPointService actionPointService;
	private final ImageService imageServiceImpl;
	private final Logger logger = LoggerFactory.getLogger(ActionPointRestController.class);
	private final ModelMapper modelMapper = new ModelMapper();

	public ActionPointRestController(ActionPointService actionPointService, ImageServiceDevImpl imageServiceImpl) {
		this.actionPointService = actionPointService;
		this.imageServiceImpl = imageServiceImpl;
	}

	@YouthCouncilAdmin
	@PostMapping (consumes = {"multipart/form-data"})
	public ResponseEntity<?> addActionPoint(@ModelAttribute @Valid NewActionPointDTO newActionPointDTO,
	                                        BindingResult errors,
	                                        @RequestPart (value = "images", required = false) List<MultipartFile> images,
	                                        @MunicipalityId Long municipalityId) {
		if (municipalityId == null) {
			logger.debug("No municipality ID found");
			throw new EntityNotFoundException("Not found");
		}
		if (errors.hasErrors()) {
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		if (images == null || images.isEmpty()) {
			logger.debug("No images found");
			errors.rejectValue("images", "images.empty", "No images found");
			Map<String, String> errorMap = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(errorMap);
		}
		logger.debug(newActionPointDTO.toString());
		logger.debug("{} images found", images.size());

		List<String> savedImages = imageServiceImpl.saveImages(images);
		newActionPointDTO.setImageSources(savedImages);
		ActionPoint actionPoint = actionPointService.createActionPoint(newActionPointDTO, municipalityId);
		ActionPointDTO actionPointDTO = modelMapper.map(actionPoint, ActionPointDTO.class);
		return new ResponseEntity<>(actionPointDTO, org.springframework.http.HttpStatus.CREATED);
	}
}
