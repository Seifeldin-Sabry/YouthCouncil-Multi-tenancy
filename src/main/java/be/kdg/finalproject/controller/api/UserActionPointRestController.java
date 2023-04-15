package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.patch.FollowDTO;
import be.kdg.finalproject.controller.api.dto.patch.LikeDTO;
import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.service.actionpoints.ActionPointFollowService;
import be.kdg.finalproject.service.actionpoints.ActionPointLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/action-points")
@PreAuthorize ("isAuthenticated()")
public class UserActionPointRestController {

	private final ActionPointFollowService followService;
	private final ActionPointLikeService likeService;

	public UserActionPointRestController(ActionPointFollowService followService, ActionPointLikeService likeService) {
		this.followService = followService;
		this.likeService = likeService;
	}

	@LoggedIn
	@PatchMapping ("/{actionPointId}/follow")
	public ResponseEntity<?> followOrUnfollowActionPoint(@PathVariable Long actionPointId, @RequestBody FollowDTO dto, @ModelAttribute ("authUser") User user) {
		followService.followOrUnfollowActionPoint(actionPointId, user, dto.isFollow());
		return ResponseEntity.noContent().build();
	}

	@LoggedIn
	@PatchMapping ("/{actionPointId}/like")
	public ResponseEntity<?> likeOrUnlikeActionPoint(@PathVariable Long actionPointId, @RequestBody LikeDTO dto, @ModelAttribute ("authUser") User user) {
		likeService.likeOrUnlike(actionPointId, user, dto.isLike());
		return ResponseEntity.noContent().build();
	}
}
