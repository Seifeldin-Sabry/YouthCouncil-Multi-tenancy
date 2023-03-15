package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import be.kdg.finalproject.util.BCryptPasswordUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordUtil passwordEncoder;
	private final MembershipService membershipService;

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordUtil passwordEncoder, MembershipService municipalityService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.membershipService = municipalityService;
	}

	@PreDestroy
	public void destroy() {
		userRepository.deleteAll();
	}

	@PostConstruct
	@Profile ("dev")
	public void init() {
		logger.info("Initializing users");
		User admin = new User("admin", "admin", "admin", "admin@admin.co", passwordEncoder.encode("pass"), Role.ADMINISTRATOR, Provider.LOCAL);
		User youthCouncil = new User("ycadmin", "ycadmin", "ycadmin", "ycadmin@admin.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		User moderator = new User("mod", "mod", "mod", "mod@user.co", passwordEncoder.encode("pass"), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		User user = new User("user", "user", "user", "user@user.co", passwordEncoder.encode("pass"), Role.USER, Provider.LOCAL);
		userRepository.save(admin);
		userRepository.save(youthCouncil);
		userRepository.save(moderator);
		userRepository.save(user);
	}

	@Override
	public User addRegularUser(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(), userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(), passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.USER, Provider.LOCAL);
		userRepository.save(user);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode());
		userRepository.save(user);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return user;
	}

	@Override
	public void processOAuthPostLogin(String email, String givenName, String familyName, Provider provider) {
		boolean existUser = userRepository.existsByEmailIgnoreCase(email);
		if (!existUser) {
			User user = new User();
			user.setEmail(email);
			user.setRole(Role.USER);
			user.setProvider(provider);
			user.setUsername(email.replaceAll("@.*", ""));
			userRepository.save(user);
		}
	}

	@Override
	public void processOAuthPostLoginGoogle(String email, String givenName, String familyName, Provider provider) {
		boolean existUser = userRepository.existsByEmailIgnoreCase(email);
		if (!existUser) {
			User user = new User();
			user.setEmail(email);
			user.setRole(Role.USER);
			user.setProvider(provider);
			user.setUsername(email.replaceAll("@.*", generateRandomCharacterSequence()));
			user.setFirstName(givenName);
			user.setSurname(familyName);
			userRepository.save(user);
		}
	}

	@Override
	public void processOAuthPostLoginFaceBook(String email, String name, Provider provider) {
		boolean existUser = userRepository.existsByEmailIgnoreCase(email);
		if (!existUser) {
			User user = new User();
			user.setEmail(email);
			user.setRole(Role.USER);
			user.setProvider(provider);
			user.setFirstName(name);
			user.setUsername(email.replaceAll("@.*", generateRandomCharacterSequence()));
			userRepository.save(user);
		}
	}

	private String generateRandomCharacterSequence() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 10;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
}
