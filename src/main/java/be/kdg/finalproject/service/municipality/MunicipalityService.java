package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.ElectionPhase;
import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.municipalities.MunicipalityId;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MunicipalityService {
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MunicipalityService.class);

	@Autowired
	public MunicipalityService(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}


	public List<Municipality> getAllMunicipalities() {
		return ImmutableList.copyOf(municipalityRepository.findAll());
	}


	public List<Municipality> getAllMunicipalitiesByName(String partialName) {
		return municipalityRepository.findByNamePart(partialName);
	}

	//
	//	public List<Municipality> getAllMunicipalitiesByPostalCode(Integer partialPostalCode) {
	//		return municipalityRepository.findByPostcodePart(String.valueOf(partialPostalCode));
	//	}


	public Municipality getMunicipalityByPostalCode(Integer postalCode) {
		return municipalityRepository.findByPostcode(postalCode);
	}


	public Optional<Municipality> getMunicipalityByName(String name) {
		return Optional.empty();
	}


	public List<Municipality> getAllMunicipalitiesAndMembers() {
		return municipalityRepository.findAllMunicipalitiesWithMembers();
	}


	public Municipality getMunicipalityByUUID(UUID id) throws EntityNotFoundException {
		return municipalityRepository.findByUuidWithMembers(id)
		                             .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
	}


	public Municipality getMunicipalityByIdWithSocialMediaLinks(Long municipalityId) {
		return municipalityRepository.findMunicipalityByIdWithSocials(municipalityId)
		                             .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
	}

	public void changeElectrionPhaseByMunicipality(Long municipalityId){
		Municipality municipality = municipalityRepository.findById(municipalityId)
		                                                  .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
		ElectionPhase currentElectionPhase = municipality.getElectionPhase();
		ElectionPhase newElectionPhase;
		if (currentElectionPhase==ElectionPhase.BEFORE_ELECTION){
			newElectionPhase= ElectionPhase.AFTER_ELECTION;
		}
		else {
			newElectionPhase= ElectionPhase.BEFORE_ELECTION;
		}
		municipalityRepository.updateElectionPhase(newElectionPhase, municipalityId);
	}

	public void changeLogo(String image, long municipalityId){
		municipalityRepository.updateImage(image, municipalityId);
	}

}
