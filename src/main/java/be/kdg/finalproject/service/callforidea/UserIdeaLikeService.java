package be.kdg.finalproject.service.callforidea;

import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserActionPointLike;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.callforidea.IdeaRepository;
import be.kdg.finalproject.repository.callforidea.UserIdeaLikeRepository;
import be.kdg.finalproject.repository.membership.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserIdeaLikeService {
	private final UserIdeaLikeRepository userIdeaLikeRepository;
	private final IdeaRepository ideaRepository;
	private final UserRepository userRepository;

	public UserIdeaLikeService(UserIdeaLikeRepository userIdeaLikeRepository, IdeaRepository ideaRepository, UserRepository userRepository) {
		this.userIdeaLikeRepository = userIdeaLikeRepository;
		this.ideaRepository = ideaRepository;
		this.userRepository = userRepository;
	}

	public void likeOrUnlike(Long ideaId, User user) {
		Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new EntityNotFoundException("Idea not found"));
		User actualUser = userRepository.findByUsernameOrEmail(user.getUsername()).orElseThrow(
				() -> new EntityNotFoundException("User not found"));
		UserIdeaLike userIdeaLike = userIdeaLikeRepository.findByIdeaAndLiker(ideaId, actualUser.getId());
		if (userIdeaLike == null) {
			userIdeaLike = new UserIdeaLike();
			idea.addLiker();
			userIdeaLike.setIdea(idea);
			userIdeaLike.setLiker(actualUser);
			userIdeaLikeRepository.save(userIdeaLike);
		}
		else {
			idea.removeLiker();
			idea.setLiked(false);
			userIdeaLikeRepository.deleteById(userIdeaLike.getId());
		}
	}
}
