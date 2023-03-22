package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/api/users")
public class UserRestController {

	UserService userService;


	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable long userId){
		User user = userService.getUserByID(userId);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		else {
			userService.deleteUser(user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/moderator")
	public ResponseEntity<NewUserDto> registerModerator(@RequestBody @Valid NewUserDto newUserDto){
		UserSignUpViewModel userSignUpViewModel = new UserSignUpViewModel(newUserDto.getFirst_name(),
				newUserDto.getSurname(), newUserDto.getUsername(), Integer.valueOf(newUserDto.getPostcode()),
				newUserDto.getEmail(), newUserDto.getPassword(), newUserDto.getPassword(), true);
		User user = userService.addModerator(userSignUpViewModel);
		newUserDto.setRole(user.getRole().name());
		newUserDto.setId(user.getId());
		return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
	}

	@PostMapping("/admin")
	public ResponseEntity<NewUserDto> registerAdmin(@RequestBody @Valid NewUserDto newUserDto){
		UserSignUpViewModel userSignUpViewModel = new UserSignUpViewModel(newUserDto.getFirst_name(),
				newUserDto.getSurname(), newUserDto.getUsername(), Integer.valueOf(newUserDto.getPostcode()),
				newUserDto.getEmail(), newUserDto.getPassword(), newUserDto.getPassword(), true);
		User user = userService.addYCAdmin(userSignUpViewModel);
		newUserDto.setRole(user.getRole().name());
		newUserDto.setId(user.getId());
		return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
	}
}
