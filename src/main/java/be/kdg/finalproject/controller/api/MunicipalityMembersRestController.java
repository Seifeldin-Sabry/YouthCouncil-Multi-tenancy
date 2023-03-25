package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.service.user.UserService;
import be.kdg.finalproject.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping ("/api/municipalities/{uuid}/members")
public class MunicipalityMembersRestController {

	private final UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	public MunicipalityMembersRestController(UserService userService) {
		this.userService = userService;
	}


	@GeneralAdminOnly
	@PostMapping
	public ResponseEntity<?> addYouthCouncilMember(@PathVariable UUID uuid, @Valid @RequestBody NewUserDto user, BindingResult bindingResult) {
		if (user.getRole() == null) user.setRole(Role.YOUTH_COUNCIL_ADMINISTRATOR);
		logger.debug("Adding user: " + user.getEmail());
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationUtils.getErrorsMap(bindingResult));
		}
		userService.addMemberToMunicipality(uuid, user);
		return ResponseEntity.created(null).build();
	}
}
