package be.kdg.finalproject.controller.api;
import be.kdg.finalproject.controller.api.dto.get.CalendarActivityDTO;
import be.kdg.finalproject.controller.api.dto.patch.UpdatedCalendarActivityDTO;
import be.kdg.finalproject.controller.api.dto.post.NewCalendarActivityDTO;
import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping ("/api/calendar-activities")
public class CalendarActivitiesRestController {

	private final CalendarActivitiesService calendarActivitiesService;

	public CalendarActivitiesRestController(CalendarActivitiesService calendarActivitiesService) {
		this.calendarActivitiesService = calendarActivitiesService;
	}


	//EDIT ACTIVITY
//	@PatchMapping("/{id}")
//	public ResponseEntity<Void> editActivity(@PathVariable int id,
//	                                         @Valid @RequestBody UpdatedCalendarActivityDTO updatedActivity) {
//		if (calendarActivitiesService.updateActivity(id, updatedActivity.getTitle(),updatedActivity.getStartTime().toLocalDate(),
//				updatedActivity.getStartTime(),updatedActivity.getEndTime(),updatedActivity.getDescription(),
//				updatedActivity.getMunicipality())) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}


	//ADD ACTIVITY
//	@PostMapping
//	public ResponseEntity<CalendarActivityDTO> addActivity(
//			@RequestBody @Valid NewCalendarActivityDTO newActivityDto) {
//
//		CalendarActivity createdActivity = calendarActivitiesService.addCalendarActivity(
//				newActivityDto.getTitle(),
//				newActivityDto.getDate,
//				newActivityDto.getStartTime(),
//				newActivityDto.getEndTime(),
//				newActivityDto.getDescription()
//		);
//		return new ResponseEntity<>(
//				new CalendarActivityDTO(
//						createdActivity.getId(),
//						createdActivity.getTitle(),
//						createdActivity.getDate(),
//						createdActivity.getStartTime(),
//						createdActivity.getEndTime(),
//						createdActivity.getDescription()),
//				HttpStatus.CREATED);
//	}


	// DELETE ACTIVITY
//	@DeleteMapping ("/{activityId}")
//	public ResponseEntity<Void> deleteActivity(@PathVariable int activityId) {
//		//LIKE THIS IS BETTER
//		if (calendarActivitiesService.activtivityExists(activityId)) {
//			calendarActivitiesService.deleteActivity(activityId);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}


}
