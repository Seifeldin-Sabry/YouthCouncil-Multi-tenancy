package be.kdg.finalproject.database.operational;

import be.kdg.finalproject.domain.platform.Municipality;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.MunicipalityRepository;
import be.kdg.finalproject.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
@Profile("op")

public class UserDataInitializer implements CommandLineRunner {
    private static final int NUMBER_OF_USERS = 100;
    private final Faker faker = new Faker();
    private final Random random = new Random();
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MunicipalityRepository municipalityRepository;

    public UserDataInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, MunicipalityRepository municipalityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public void run(String... args) {
        userDataGenerator();
    }

    public void userDataGenerator() {

        List<Municipality> municipalities = (List<Municipality>) municipalityRepository.findAll();

        Stream.generate(() -> {
                    String firstName = faker.name().firstName();
                    String surname = faker.name().lastName();
                    String username = faker.name().username();
                    String email = faker.internet().emailAddress();
                    String password = faker.internet().password();
                    Role role = random.nextBoolean() ? Role.USER : Role.ADMINISTRATOR;
                    Provider provider = random.nextBoolean() ? Provider.LOCAL : Provider.GOOGLE;

                    Municipality municipality = municipalities.get(random.nextInt(municipalities.size()));

                    var newUser = new User(firstName, surname, username, email, password, role, provider, municipality);
                    newUser.setPassword(passwordEncoder.encode(password));

                    var membership = new Membership(newUser, municipality, role);
                    newUser.addMembership(membership);
                    return newUser;

                })
                .limit(NUMBER_OF_USERS)
                .forEach(user -> {

                    Optional<User> optionalUser = userRepository.findByUsernameOrEmail(user.getEmail());
                    if (optionalUser.isEmpty()) {
                        userRepository.save(user);
                    }
                });
    }
}
