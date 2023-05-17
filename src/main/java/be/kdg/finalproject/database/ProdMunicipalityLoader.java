package be.kdg.finalproject.database;

import be.kdg.finalproject.repository.municipality.MunicipalityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("prod")
public class ProdMunicipalityLoader {

	private final MunicipalityRepository munRepo;
	private final MunicipalitySeeder munSeeder;

	public ProdMunicipalityLoader(MunicipalityRepository munRepo, MunicipalitySeeder munSeeder) {
		this.munRepo = munRepo;
		this.munSeeder = munSeeder;
	}

	@PostConstruct
	public void init(){
		if (munRepo.findById(1L).isPresent()) return;
		munSeeder.seed();
	}
}
