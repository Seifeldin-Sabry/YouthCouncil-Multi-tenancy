package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.config.security.CustomOAuth2User;
import be.kdg.finalproject.config.security.CustomUserDetails;
import be.kdg.finalproject.controller.api.dto.patch.UpdatedUserDTO;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.service.SessionService;
import be.kdg.finalproject.service.user.UserService;
import be.kdg.finalproject.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.Map;

@RestController
@RequestMapping ("/api/users")
public class UserRestController {

	private final UserService userService;
	private final SessionService sessionService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserRestController(UserService userService, SessionService sessionService) {
		this.userService = userService;
		this.sessionService = sessionService;
	}

	/**
	 * For updating a user's profile (PUT)
	 * Can only be done by the user himself
	 *
	 * @return 200 OK if successful, 403 FORBIDDEN if not authorized, 400 BAD REQUEST if validation errors
	 */
	@PreAuthorize ("isAuthenticated()")
	@PutMapping ("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UpdatedUserDTO updatedUserDTO, BindingResult errors, HttpServletRequest request, HttpSession session) {
		//		get user by authentication and check if his ID is the same as the ID in the path, otherwise return 403
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("Auth: {}", auth.getPrincipal());
		User user;
		Type type = auth.getPrincipal() instanceof CustomOAuth2User ? CustomOAuth2User.class : CustomUserDetails.class;
		user = type == CustomOAuth2User.class ? userService.getUserByUsernameOrEmailWithMembership(((CustomOAuth2User) auth.getPrincipal()).getEmail())
				: userService.getUserByUsernameOrEmailWithMembership(((CustomUserDetails) auth.getPrincipal()).getUsername());
		if (user == null) {
			logger.debug("User not found");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		if (!user.getId().equals(id)) {
			logger.debug("User not authorized to update this user");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		boolean emailIsDifferentButIsTaken = !userService.newEmailIsCurrentEmail(user, updatedUserDTO.getEmail()) && userService.emailExists(updatedUserDTO.getEmail());
		boolean usernameIsDifferentButIsTaken = !userService.newUsernameIsCurrentUsername(user, updatedUserDTO.getUsername()) && userService.usernameExists(updatedUserDTO.getUsername());
		if (emailIsDifferentButIsTaken) {
			errors.rejectValue("email", "email.taken");
		}
		if (usernameIsDifferentButIsTaken) {
			errors.rejectValue("username", "username.taken");
		}
		if (errors.hasErrors()) {
			Map<String, String> validate = ValidationUtils.getErrorsMap(errors);
			logger.debug("Validation errors: {}", validate);
			return ResponseEntity.badRequest().body(validate);
		}
		User updateUser = userService.updateUser(id, updatedUserDTO);
		// create a new authentication token with the updated user details and the user's authentication token
		UsernamePasswordAuthenticationToken authenticationToken;
		if (type == CustomOAuth2User.class) {
			CustomOAuth2User oauthUser = (CustomOAuth2User) auth.getPrincipal();
			authenticationToken = new UsernamePasswordAuthenticationToken(new CustomOAuth2User(new DefaultOAuth2User(oauthUser.getAuthorities(), oauthUser.getAttributes(), "email"), MunicipalityContext.getCurrentMunicipalityId()), auth.getCredentials(), auth.getAuthorities());
		} else {
			authenticationToken = new UsernamePasswordAuthenticationToken(new CustomUserDetails(updateUser.getId(), updateUser.getUsername(), updateUser.getEmail(), updateUser.getPassword(), MunicipalityContext.getCurrentMunicipalityId(), auth.getAuthorities()), auth.getCredentials(), auth.getAuthorities());
		}

		// set the authentication token in the SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		sessionService.setCredentialsChanged(true);
		return ResponseEntity.ok().build();
	}

	//TODO: implement delete user, update password
	//	@DeleteMapping ("/{userId}")
	//	public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
	//		User user = userService.getUserByID(userId);
	//		if (user == null) {
	//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	//
	//		} else {
	//			userService.deleteUser(user);
	//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	//		}
	//	}
}
