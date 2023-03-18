package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final MembershipService membershipService;

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, MembershipService municipalityService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.membershipService = municipalityService;
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

	@Override
	public User getUserByID(long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FormSubmission not found"));
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
