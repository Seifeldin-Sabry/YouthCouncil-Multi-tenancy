package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipalityServiceImpl implements MunicipalityService{
	private final MunicipalityRepository municipalityRepository;

	@Autowired
	public MunicipalityServiceImpl(MunicipalityRepository municipalityRepository) {
		this.municipalityRepository = municipalityRepository;
	}

	@Override
	public List<Municipality> getAllMunicipalities() {
		return municipalityRepository.findAll();
	}

	@Override
	public List<Municipality> getAllMunicipalitiesByName(String partialName) {
		return municipalityRepository.findByNamePart(partialName);
	}

	@Override
	public List<Municipality> getAllMunicipalitiesByPostalCode(Integer partialPostalCode) {
		return municipalityRepository.findByPostcodePart(String.valueOf(partialPostalCode));
	}

	@Override
	public Optional<Municipality> getMunicipalityByPostalCode(Integer postalCode) {
		return municipalityRepository.findByPostcode(postalCode).stream().findFirst();
	}

	@Override
	public Optional<Municipality> getMunicipalityByName(String name) {
		return Optional.empty();
	}

}
