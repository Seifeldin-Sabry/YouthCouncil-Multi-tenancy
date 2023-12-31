package be.kdg.finalproject.repository.municipality;

import be.kdg.finalproject.domain.platform.PostCode;
import org.springframework.data.repository.CrudRepository;

public interface PostCodeRepository extends CrudRepository<PostCode, Long> {
	boolean existsByPostcode(Integer postcode);
}
