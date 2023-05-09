package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Profile ("prod")
public class MunicipalityProdService implements MunicipalityService {
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MunicipalityProdService.class);
	private boolean uuidsDefined = false;

	@Autowired
	public MunicipalityProdService(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}

	public void assignUUIDs() {
		List<Municipality> municipalities = ImmutableList.copyOf(municipalityRepository.findAll());
		municipalities.forEach(municipality -> {
			if (municipality.getUuid() == null) {
				municipality.setUuid(UUID.randomUUID());
				municipalityRepository.save(municipality);
			}
		});
	}

	public List<Municipality> getAllMunicipalities() {
		if (!uuidsDefined) {
			assignUUIDs();
			uuidsDefined = true;
		}
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

}
