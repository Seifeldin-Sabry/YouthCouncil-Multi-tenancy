package be.kdg.finalproject.service.municipality;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface MunicipalityService {

	List<Municipality> getAllMunicipalities();

	List<Municipality> getAllMunicipalitiesByName(String partialName);
	//	List<Municipality> getAllMunicipalitiesByPostalCode(Integer partialPostalCode);

	Municipality getMunicipalityByPostalCode(Integer postalCode);

	Optional<Municipality> getMunicipalityByName(String name);

	List<Municipality> getAllMunicipalitiesAndMembers();

	Municipality getMunicipalityByUUID(UUID id) throws EntityNotFoundException;
}
