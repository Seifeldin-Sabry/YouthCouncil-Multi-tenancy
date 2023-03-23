package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.user.UserService;
import be.kdg.finalproject.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping ("/api/municipalities/{uuid}/members")
public class UserRestController {

	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@DeleteMapping ("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable long userId, @PathVariable String uuid) {
		User user = userService.getUserByID(userId);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			userService.deleteUser(user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GeneralAdminOnly
	@PostMapping
	public ResponseEntity<?> addUser(@PathVariable UUID uuid, @Valid @RequestBody NewUserDto user, BindingResult bindingResult) {
		logger.debug("Adding user: " + user.getEmail());
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationUtils.getErrorsMap(bindingResult));
		}
		userService.addMemberToMunicipality(uuid, user);
		return ResponseEntity.created(null).build();
	}
}
