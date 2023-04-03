package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
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
public class MunicipalityServiceImpl implements MunicipalityService {
	private final MunicipalityRepository municipalityRepository;
	private final Logger logger = LoggerFactory.getLogger(MunicipalityServiceImpl.class);

	@Autowired
	public MunicipalityServiceImpl(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}

	@Override
	public List<Municipality> getAllMunicipalities() {
		return ImmutableList.copyOf(municipalityRepository.findAll());
	}

	@Override
	public List<Municipality> getAllMunicipalitiesByName(String partialName) {
		return municipalityRepository.findByNamePart(partialName);
	}

	//	@Override
	//	public List<Municipality> getAllMunicipalitiesByPostalCode(Integer partialPostalCode) {
	//		return municipalityRepository.findByPostcodePart(String.valueOf(partialPostalCode));
	//	}

	@Override
	public Municipality getMunicipalityByPostalCode(Integer postalCode) {
		return municipalityRepository.findByPostcode(postalCode);
	}

	@Override
	public Optional<Municipality> getMunicipalityByName(String name) {
		return Optional.empty();
	}

	@Override
	public List<Municipality> getAllMunicipalitiesAndMembers() {
		return municipalityRepository.findAllMunicipalitiesAndMembers();
	}

	@Override
	public Municipality getMunicipalityByUUID(UUID id) throws EntityNotFoundException {
		return municipalityRepository.findByUuid(id)
		                             .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
	}

}
