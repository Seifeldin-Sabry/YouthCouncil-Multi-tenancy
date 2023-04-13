package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.domain.activities.CalendarActivity;
import be.kdg.finalproject.service.calendaractivities.CalendarActivitiesService;
import be.kdg.finalproject.service.municipality.MunicipalityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/api/calendar-activities")
public class CalendarActivitiesRestController {

	private final CalendarActivitiesService calendarActivitiesService;
	private final MunicipalityService municipalityService;

	public CalendarActivitiesRestController(CalendarActivitiesService calendarActivitiesService, MunicipalityService municipalityService) {
		this.calendarActivitiesService = calendarActivitiesService;
		this.municipalityService = municipalityService;
	}


	//EDIT ACTIVITY
//	@PatchMapping("/{id}")
//	public ResponseEntity<Void> editActivity(@PathVariable int id,
//	                                         @Valid @RequestBody UpdatedCalendarActivityDTO updatedActivity) {
//		if (calendarActivitiesService.changeActivity(id, updatedActivity.getTitle(),updatedActivity.getDate(),
//				updatedActivity.getStartTime(),updatedActivity.getEndTime(),updatedActivity.getDescription(),
//				updatedActivity.getMunicipality())) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}


	//ADD ACTIVITY
//	@PostMapping
//	public ResponseEntity<ActivityDto> addActivity(
//			@RequestBody @Valid NewCalendarActivityDTO newActivityDto) {
//
//		CalendarActivity createdActivity = calendarActivitiesService.addActivity(
//				NewActivityDto.getGameDate(),
//				NewActivityDto.getWinner(),
//				NewActivityDto.getStage()
//		);
//		return new ResponseEntity<>(
//				new CalendarActivityDto(
//						createdActivity.getId(),
//						createdActivity.getGameDate(),
//						createdActivity.getWinner(),
//						createdActivity.getStage(),
//						createdActivity.getTournament()),
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
