package be.kdg.finalproject.service.actionpoints;

import be.kdg.finalproject.domain.actionpoint.ActionPoint;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.actionpoint.ActionPointRepository;
import be.kdg.finalproject.repository.actionpoint.UserActionPointLikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActionPointLikeService {

	private final UserActionPointLikeRepository likeRepository;
	private final ActionPointRepository actionPointRepository;

	public ActionPointLikeService(UserActionPointLikeRepository likeRepository, ActionPointRepository actionPointRepository) {
		this.likeRepository = likeRepository;
		this.actionPointRepository = actionPointRepository;
	}

	public void likeOrUnlike(Long actionPointId, User user, boolean like) {
		ActionPoint actionPoint = actionPointRepository.findById(actionPointId)
		                                               .orElseThrow(() -> new EntityNotFoundException("ActionPoint not found"));
		UserActionPointLike likeInteraction = likeRepository.findByLikerAndActionPoint(user, actionPoint)
		                                                    .orElse(null);
		if (like && likeInteraction == null) {
			likeInteraction = new UserActionPointLike();
			actionPoint.addLiker();
			likeInteraction.setActionPoint(actionPoint);
			likeInteraction.setLiker(user);
			likeRepository.save(likeInteraction);
			return;
		}
		if (!like && likeInteraction != null) {
			actionPoint.removeLiker();
			likeRepository.deleteById(likeInteraction.getId());
			actionPointRepository.save(actionPoint);
		}
	}
}
