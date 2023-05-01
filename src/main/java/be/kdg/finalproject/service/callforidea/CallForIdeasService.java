package be.kdg.finalproject.service.callforidea;

import be.kdg.finalproject.controller.api.dto.post.NewCallForIdeasDTO;
import be.kdg.finalproject.domain.idea.CallForIdeas;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.repository.callforidea.CallForIdeasRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CallForIdeasService{

	CallForIdeasRepository callForIdeasRepository;

	public CallForIdeasService(CallForIdeasRepository callForIdeasRepository) {
		this.callForIdeasRepository = callForIdeasRepository;
	}

	public CallForIdeas getCallForIdeaByUUIDWithIdeas(UUID uuid){
		return callForIdeasRepository.findByUUIDWithIdeas(uuid);
	}

	public CallForIdeas createCallForIdea(NewCallForIdeasDTO newCallForIdeasDTO) {
		CallForIdeas callForIdeas = new CallForIdeas();
		callForIdeas.setTitle(newCallForIdeasDTO.getTitle());
		callForIdeas.setDescription(newCallForIdeasDTO.getDescription());
		callForIdeas.setTheme(newCallForIdeasDTO.getTheme());

		callForIdeas.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
		callForIdeas.setMunicipality(MunicipalityContext.getCurrentMunicipality());

		return callForIdeasRepository.save(callForIdeas);
	}


	public Optional<CallForIdeas> getCallForIdeaById(long callId) {
		return callForIdeasRepository.findById(callId);
	}


	public List<CallForIdeas> getCallForIdeasByMunicipalityId(long munId) {
		return callForIdeasRepository.findCallForIdeasByMunicipalityId(munId);
	}


	public CallForIdeas getActiveCallByMunicipalityId(long munId) {
		List<CallForIdeas> calls = getCallForIdeasByMunicipalityId(munId);
		for (CallForIdeas call : calls) {
			if (call.isActive()){
				return call;
			}
		}
		return null;
	}

	public void changeActiveById(long callId){
		callForIdeasRepository.updateActiveById(callId);
	}
}
