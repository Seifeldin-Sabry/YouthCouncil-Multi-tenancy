package be.kdg.finalproject.service.callforidea;

import be.kdg.finalproject.controller.api.dto.post.NewIdeaDTO;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.callforidea.IdeaRepository;
import be.kdg.finalproject.repository.membership.UserRepository;
import be.kdg.finalproject.service.media.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IdeaService {

	private final IdeaRepository ideaRepository;
	private final UserRepository userRepository;
	private final ImageService imageService;

	public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository, ImageService imageService) {
		this.ideaRepository = ideaRepository;
		this.userRepository = userRepository;
		this.imageService = imageService;
	}

	public Idea createIdea(NewIdeaDTO newIdeaDTO, long callForIdeasId, User user) throws IOException {
		Idea idea = new Idea();
		List<String> imageSources = imageService.saveImages(newIdeaDTO.getImages());
		idea.setImage(imageSources.get(0));// for now we keep images to 1 since its not clear if we need multiple
		idea.setSubTheme(newIdeaDTO.getSubTheme());
		idea.setContent(newIdeaDTO.getDescription());

		idea.setCallForIdeasId(callForIdeasId);
		idea.setCreator(user);
		return ideaRepository.save(idea);
	}

	public List<Idea> getLikedIdeasByUser(Long likerId) {
		Set<UserIdeaLike> userIdeaLikes = userRepository.findLikedIdeasByUserId(likerId);
		if (userIdeaLikes.isEmpty()) {
			return null;
		}
		List<Idea> likedIdeas = new ArrayList<>();
		userIdeaLikes.forEach(userIdeaLike -> {
			likedIdeas.add(userIdeaLike.getIdea());
		});
		return likedIdeas;
	}

	public List<Idea> getIdeasBySearchTerm(String searchTerm, Long municipalityId) {
		return ideaRepository.findAllBySearchTermContainingIgnoreCase(searchTerm, municipalityId);
	}

	public Optional<Idea> getIdeaById(Long id) {return ideaRepository.findById(id);}

	public void deleteIdeaById(Long id) {
		ideaRepository.softDeleteById(id);
	}


	public List<Idea> getIdeasBySubtheme(SubTheme subTheme) {
		return ideaRepository.findAllBySubTheme(subTheme);
	}

	public List<Idea> getIdeasByCallForIdeas(long callForIdeasId) {
		return ideaRepository.findAllByCallForIdeasId(callForIdeasId);
	}

	public List<Idea> getIdeasByIds(List<Long> ideas) {
		return ideaRepository.findByIdIn(ideas);
	}
}
