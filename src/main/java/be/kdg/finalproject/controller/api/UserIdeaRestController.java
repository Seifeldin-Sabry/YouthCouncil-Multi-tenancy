package be.kdg.finalproject.controller.api;

import be.kdg.finalproject.controller.api.dto.patch.LikeDTO;
import be.kdg.finalproject.controller.authority.LoggedIn;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.service.callforidea.CallForIdeasService;
import be.kdg.finalproject.service.callforidea.IdeaService;
import be.kdg.finalproject.service.callforidea.UserIdeaLikeService;
import be.kdg.finalproject.service.theme.SubThemeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
@RestController
@RequestMapping ("/api/ideas")
public class UserIdeaRestController {

	private final CallForIdeasService callForIdeasService;
	private final IdeaService ideaService;
	private final SubThemeService subThemeService;
	private final UserIdeaLikeService userIdeaLikeService;
	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserIdeaRestController.class);

	public UserIdeaRestController(CallForIdeasService callForIdeasService, IdeaService ideaService,
	                              SubThemeService subThemeService, UserIdeaLikeService userIdeaLikeService) {
		this.callForIdeasService = callForIdeasService;
		this.ideaService = ideaService;
		this.subThemeService = subThemeService;
		this.userIdeaLikeService = userIdeaLikeService;
	}

	@LoggedIn
	@PatchMapping ("/{id}/like")
	public ResponseEntity<?> toggleLike(@RequestBody LikeDTO dto, @PathVariable Long id,
	                                    @ModelAttribute ("authUser") User user){

		userIdeaLikeService.likeOrUnlike(id, user, !dto.isLike());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
