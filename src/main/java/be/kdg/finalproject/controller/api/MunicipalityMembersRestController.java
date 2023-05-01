package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.get.MunicipalityMemberDTO;
import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.authority.GeneralAdminOnly;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.util.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/api/municipalities/{id}/members")
public class MunicipalityMembersRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final MembershipService membershipService;
	private final ModelMapper modelMapper = new ModelMapper();

	public MunicipalityMembersRestController(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	@GeneralAdminOnly
	@PostMapping
	public ResponseEntity<?> addYouthCouncilMember(@PathVariable Long id, @Valid @RequestBody NewUserDto user, BindingResult bindingResult) {
		if (user.getRole() == null) user.setRole(Role.YOUTH_COUNCIL_ADMINISTRATOR);
		logger.debug("Adding user: " + user.getEmail());
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationUtils.getErrorsMap(bindingResult));
		}
		Membership membership = membershipService.getMembershipByIdAndEmail(id, user.getEmail());
		if (membership != null) {
			membership.setRole(user.getRole());
			membership.setBanned(false);
			membership.getMunicipality().addMember(membership.getUser());
			membershipService.update(membership);
			logger.debug("User {}", membership.getUser());
			return new ResponseEntity<>(modelMapper.map(membership.getUser(), MunicipalityMemberDTO.class), HttpStatus.CREATED);
		}
		User member = membershipService.addNewMembershipByUserAndId(user, id, user.getRole());
		return new ResponseEntity<>(modelMapper.map(member, MunicipalityMemberDTO.class), HttpStatus.CREATED);
	}
}
