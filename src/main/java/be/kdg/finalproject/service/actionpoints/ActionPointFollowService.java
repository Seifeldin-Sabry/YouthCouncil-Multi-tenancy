package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.interaction.follow.UserActionPointFollow;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import be.kdg.finalproject.repository.actionpoint.UserActionPointFollowRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ActionPointFollowService {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ActionPointFollowService.class);
	private final UserActionPointFollowRepository followRepository;
	private ActionPointRepository actionPointRepository;

	public ActionPointFollowService(UserActionPointFollowRepository followRepository, ActionPointRepository actionPointRepository) {
		this.followRepository = followRepository;
		this.actionPointRepository = actionPointRepository;
	}

	public void followOrUnfollowActionPoint(Long actionPointId, User user, boolean follow) {
		ActionPoint actionPoint = actionPointRepository.findById(actionPointId)
		                                               .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
		UserActionPointFollow followInteraction = followRepository.findByFollowerAndActionPoint(user, actionPoint)
		                                                          .orElse(null);
		if (follow && followInteraction == null) {
			followInteraction = new UserActionPointFollow();
			actionPoint.addFollower();
			followInteraction.setActionPoint(actionPoint);
			followInteraction.setFollower(user);
			followRepository.save(followInteraction);
			logger.debug("Follower count: " + actionPoint.getFollowCount());
			return;
		}
		if (!follow && followInteraction != null) {
			actionPoint.removeFollower();
			followRepository.deleteById(followInteraction.getId());
			actionPointRepository.save(actionPoint);
		}
	}
}
