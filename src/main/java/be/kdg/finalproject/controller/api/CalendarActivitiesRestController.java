package be.kdg.finalproject.controller.api;
import be.kdg.finalproject.controller.api.dto.get.CalendarActivityDTO;
import be.kdg.finalproject.controller.api.dto.post.NewCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/calendar-activities")
public class CalendarActivitiesRestController {
	private final CalendarActivitiesService calendarActivitiesService;
	private final Logger logger = LoggerFactory.getLogger(CalendarActivitiesRestController.class);

	private final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public CalendarActivitiesRestController(CalendarActivitiesService calendarActivitiesService) {
		this.calendarActivitiesService = calendarActivitiesService;
		modelMapper.createTypeMap(CalendarActivityDTO.class, CalendarActivity.class);
	}


	// UPDATE ACTIVITY
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateActivity(@PathVariable Long id,
	                                      @Valid @RequestBody NewCalendarActivityDTO updatedActivityDTO, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, List<String>> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		if (calendarActivitiesService.updateCalendarActivity(
				id,
				updatedActivityDTO.getTitle(),
				updatedActivityDTO.getDate(),
				updatedActivityDTO.getStartTime(),
				updatedActivityDTO.getEndTime(),
				updatedActivityDTO.getDescription(),
				updatedActivityDTO.getMunicipality())) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// ADD ACTIVITY
	 @PostMapping
	public ResponseEntity<?> addActivity(@RequestBody @Valid NewCalendarActivityDTO newActivityDto, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, List<String>> validate = ValidationUtils.getErrorsMap(errors);
			return ResponseEntity.badRequest().body(validate);
		}
		CalendarActivity createdActivity = calendarActivitiesService.addCalendarActivity(
				newActivityDto.getTitle(),
				newActivityDto.getDate(),
				newActivityDto.getStartTime(),
				newActivityDto.getEndTime(),
				newActivityDto.getDescription()
		);
		ModelMapper modelMapper = new ModelMapper();
		CalendarActivityDTO createdActivityDto = modelMapper.map(createdActivity, CalendarActivityDTO.class);
		return new ResponseEntity<>(createdActivityDto, HttpStatus.CREATED);
	}


	// DELETE ACTIVITY
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deleteCalendarActivity(@PathVariable Long id) {
		if (calendarActivitiesService.calendarActivityExists(id)) {
			calendarActivitiesService.deleteCalendarActivity(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
