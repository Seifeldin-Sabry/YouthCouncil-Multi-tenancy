package be.kdg.finalproject.database.operational;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.platform.PostCode;
import be.kdg.finalproject.repository.MunicipalityRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
@Profile("op")
public class MunicipalityDataInitializer implements CommandLineRunner {
    final int NUMBER_OF_MUNICIPALITIES = 20;
    private final Faker faker = new Faker();
    private final MunicipalityRepository municipalityRepository;

    public MunicipalityDataInitializer(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateMunicipalities(NUMBER_OF_MUNICIPALITIES);
    }

    public void generateMunicipalities(int numMunicipalities) {

/*
       List<String> municipalityList = Arrays.asList("Antwerp", "Brussels", "Ghent", "Leuven", "Mechelen", "Namur", "Ostend", "Tournai", "Ypres", "Zottegem");
        List<Municipality> municipalities = municipalityList
                .stream()
                .map(Municipality::new)
                .toList();
*/

        List<Municipality> municipalities = IntStream.range(0, numMunicipalities)
                .mapToObj(i -> new Municipality(faker.address().cityName()))
                .toList();

        municipalityRepository.saveAll(municipalities);

        municipalities.forEach(municipality -> {
            Set<PostCode> postCodes = IntStream.range(0, faker.number().numberBetween(1, 10))
                    .mapToObj(i -> new PostCode(faker.number().numberBetween(2000, 3000)))
                    .collect(Collectors.toSet());
            postCodes.forEach(municipality::addPostCode);
            municipalityRepository.save(municipality);
        });
    }
}