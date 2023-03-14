//package be.kdg.finalproject.controller.api;
//
//import be.kdg.finalproject.controller.api.dto.get.LoginDto;
//import be.kdg.finalproject.controller.api.dto.get.UserDto;
//import be.kdg.finalproject.domain.user.User;
//import be.kdg.finalproject.repository.UserRepository;
//import be.kdg.finalproject.service.user.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping ("/api")
//public class AuthenticationController {
//
//	private final UserService userService;
//	private final UserRepository userRepository;
//
//	public AuthenticationController(UserService userService, UserRepository userRepository) {
//		this.userService = userService;
//		this.userRepository = userRepository;
//	}
//
//	@PostMapping ("/signUp")
//	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
//		if (userRepository.existsByUsername(userDto.getUsername())) {
//			return ResponseEntity.badRequest().body("Username already exists");
//		}
//
//		if (userRepository.existsByEmail(userDto.getEmail())) {
//			return ResponseEntity.badRequest().body("Email already exists");
//		}
//
//		User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
//		userRepository.save(user);
//
//		return ResponseEntity.ok("User registered successfully");
//	}
//
//	@PostMapping ("/login")
//	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
//		String name = loginDto.getUsername();
//		String password = loginDto.getPassword();
//
//		User user = userService.findUserByName(name);
//
//		if (user == null) {
//			return ResponseEntity.badRequest().body("User not found");
//		}
//
//		if (!user.getPassword().equals(password)) {
//			return ResponseEntity.badRequest().body("Invalid password");
//		}
//
//		return ResponseEntity.ok().body("Login successful");
//	}
//}
