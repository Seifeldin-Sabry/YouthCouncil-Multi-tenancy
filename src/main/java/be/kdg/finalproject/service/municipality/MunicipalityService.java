package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MunicipalityService {

	List<Municipality> getAllMunicipalities();
	List<Municipality> getAllMunicipalitiesByName(String partialName);
	List<Municipality> getAllMunicipalitiesByPostalCode(Integer partialPostalCode);
	Optional<Municipality> getMunicipalityByPostalCode(Integer postalCode);
	Optional<Municipality> getMunicipalityByName(String name);
}
