package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.patch.BanDTO;
import be.kdg.finalproject.controller.api.dto.patch.PromoteDTO;
import be.kdg.finalproject.controller.authority.Moderator;
import be.kdg.finalproject.controller.authority.YouthCouncilAdmin;
import be.kdg.finalproject.service.membership.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/memberships")
public class MembershipRestController {

	private final MembershipService membershipService;

	public MembershipRestController(MembershipService membershipService) {this.membershipService = membershipService;}

	@Moderator
	@PatchMapping ("/{membershipId}/ban")
	public ResponseEntity<?> banOrUnbanMembership(@PathVariable Long membershipId, @RequestBody BanDTO banDTO) {
		boolean banWentThrough= membershipService.updateBanStatus(membershipId, banDTO.isBan());
		if (banWentThrough){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@YouthCouncilAdmin
	@PatchMapping ("/{membershipId}/promote")
	public ResponseEntity<?> promoteOrDemoteMembership(@PathVariable Long membershipId, @RequestBody PromoteDTO promoteDTO) {
		membershipService.updatePromoteStatus(membershipId, promoteDTO.isPromote());
		return ResponseEntity.ok().build();
	}
}
