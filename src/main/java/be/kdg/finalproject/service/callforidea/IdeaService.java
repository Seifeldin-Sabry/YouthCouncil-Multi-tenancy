package be.kdg.finalproject.service.callforidea;

import be.kdg.finalproject.controller.api.dto.post.NewIdeaDTO;
import be.kdg.finalproject.domain.idea.Idea;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.callforidea.IdeaRepository;
import be.kdg.finalproject.service.media.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class IdeaService{

	IdeaRepository ideaRepository;
	ImageService imageService;

	public IdeaService(IdeaRepository ideaRepository, ImageService imageService) {
		this.ideaRepository = ideaRepository;
		this.imageService = imageService;
	}

	public Idea createIdea(NewIdeaDTO newIdeaDTO, long callForIdeasId, User user) throws IOException {
		Idea idea =  new Idea();
		List<String> imageSources = imageService.saveImages(newIdeaDTO.getImages());
		idea.setImage(imageSources.get(0));// for now we keep images to 1 since its not clear if we need multiple
		idea.setSubTheme(newIdeaDTO.getSubTheme());
		idea.setContent(newIdeaDTO.getDescription());

		idea.setCallForIdeasId(callForIdeasId);
		idea.setCreator(user);
		return ideaRepository.save(idea);
	}

	public Idea getIdeaByUUID(UUID uuid){
		return ideaRepository.findByUUID(uuid);
	}

	public List<Idea> getIdeasBySubtheme(SubTheme subTheme){
		return ideaRepository.findAllBySubTheme(subTheme);
	}
}
