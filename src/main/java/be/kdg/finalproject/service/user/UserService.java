package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedUserDTO;
import be.kdg.finalproject.controller.api.dto.post.NewUserDto;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final MembershipService membershipService;
	private final Random random = new Random();

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, MembershipService municipalityService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.membershipService = municipalityService;
	}


	public User addYCAdmin(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(),
				userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(),
				passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.YOUTH_COUNCIL_ADMINISTRATOR, Provider.LOCAL);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode(), Role.YOUTH_COUNCIL_ADMINISTRATOR);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return userRepository.save(user);
	}


	public User addModerator(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(),
				userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(),
				passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.YOUTH_COUNCIL_MODERATOR, Provider.LOCAL);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode(), Role.YOUTH_COUNCIL_MODERATOR);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return userRepository.save(user);
	}


	public User addGA(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(),
				userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(),
				passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.ADMINISTRATOR, Provider.LOCAL);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode(), Role.ADMINISTRATOR);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return userRepository.save(user);
	}


	public User addRegularUser(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(),
				userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(),
				passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.USER, Provider.LOCAL);
		userRepository.save(user);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode(), Role.USER);
		userRepository.save(user);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return user;
	}


	public List<User> getUsersByRole(Role role) {
		return userRepository.findUsersByRole(role);
	}


	public void addMemberToMunicipality(UUID uuid, NewUserDto user) {
		//TODO: send email to user with password
		User newUser = new User(user.getFirstName(), user.getSurname(), user.getEmail(), user.getEmail(), passwordEncoder.encode(generateRandomCharacterSequence()), user.getRole(), Provider.LOCAL);
		membershipService.addMembershipByUserAndUuid(newUser, uuid, user.getRole());
	}


	public boolean newEmailIsCurrentEmail(User user, String email) {
		return Objects.equals(user.getEmail(), email);
	}


	public boolean newUsernameIsCurrentUsername(User user, String username) {
		return Objects.equals(user.getUsername(), username);
	}


	public boolean emailExists(String email) {
		return userRepository.existsByEmailIgnoreCase(email);
	}


	public boolean usernameExists(String username) {
		return userRepository.existsByUsernameIgnoreCase(username);
	}


	public List<User> getAllUsers() {
		return ImmutableList.copyOf(userRepository.findAll());
	}


	public void deleteUser(User user) {
		userRepository.delete(user);
	}


	public void processOAuthPostLoginGoogle(String email, String givenName, String familyName, Provider provider) {
		boolean existUser = userRepository.existsByEmailIgnoreCase(email);
		if (!existUser) {
			User user = new User();
			user.setEmail(email);
			user.setRole(Role.USER);
			user.setProvider(provider);
			user.setUsername(email);
			user.setFirstName(givenName);
			user.setSurname(familyName);
			userRepository.save(user);
		}
	}


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

	public User getUserByID(long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	public User getUserByUsernameOrEmail(String username) {
		return userRepository.findByUsernameOrEmail(username).orElse(null);
	}

	public User updateUser(Long userId, UpdatedUserDTO updatedUserDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
		user.setFirstName(updatedUserDTO.getFirstName());
		user.setSurname(updatedUserDTO.getSurname());
		user.setEmail(updatedUserDTO.getEmail());
		user.setUsername(updatedUserDTO.getUsername());
		return userRepository.save(user);
	}

	public void deleteAccount(long userId) {
		userRepository.deleteById(userId);
	}

	public User getUserByUsernameOrEmailWithMembership(String usernameOrEmail) {
		logger.debug("Getting user by username or email: " + usernameOrEmail);
		User user = userRepository.findByUsernameOrEmailWithMemberShips(usernameOrEmail)
		                          .orElseThrow(() -> new EntityNotFoundException("User not found"));
		return user;
	}

	private String generateRandomCharacterSequence() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		int length = 10;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}


}
