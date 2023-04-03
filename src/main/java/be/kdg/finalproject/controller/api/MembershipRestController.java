package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.patch.BanDTO;
import be.kdg.finalproject.controller.api.dto.patch.PromoteDTO;
import be.kdg.finalproject.service.membership.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/memberships")
public class MembershipRestController {

	private final MembershipService membershipService;

	public MembershipRestController(MembershipService membershipService) {this.membershipService = membershipService;}

	@PatchMapping ("/{membershipId}/ban")
	public ResponseEntity<?> banOrUnbanMembership(@PathVariable Long membershipId, @RequestBody BanDTO banDTO) {
		membershipService.updateBanStatus(membershipId, banDTO.isBan());
		return ResponseEntity.ok().build();
	}

	@PatchMapping ("/{membershipId}/promote")
	public ResponseEntity<?> promoteOrDemoteMembership(@PathVariable Long membershipId, @RequestBody PromoteDTO promoteDTO) {
		membershipService.updatePromoteStatus(membershipId, promoteDTO.isPromote());
		return ResponseEntity.ok().build();
	}
}
