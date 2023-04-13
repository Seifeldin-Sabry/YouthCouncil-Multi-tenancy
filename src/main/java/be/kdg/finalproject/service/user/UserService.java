package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.api.dto.patch.UpdatedUserDTO;
import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Provider;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.EntityNotFoundException;
import be.kdg.finalproject.repository.membership.UserRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

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

	public List<User> getUsersByRole(Role role) {
		return userRepository.findUsersByRole(role);
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

	public User addUser(UserSignUpViewModel user) {
		User newUser = new User(user.getFirstName(), user.getSurname(), user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()), Role.USER, Provider.LOCAL);
		return userRepository.save(newUser);
	}

	public List<User> getAllUsers() {
		return ImmutableList.copyOf(userRepository.findAll());
	}


	public void deleteUser(User user) {
		userRepository.delete(user);
	}


	public void processOAuthPostLoginGoogle(String email, String givenName, String familyName, Provider provider, Long municipalityId) {
		User existUser = userRepository.findByUsernameOrEmail(email).orElse(null);
		boolean membershipExist = membershipService.memberExistByUserEmailAndMunicipalityId(email, municipalityId);
		if (existUser != null && membershipExist) {
			return;
		}
		if (existUser != null) {
			membershipService.addMembershipByUserEmailAndMunicipalityId(email, municipalityId);
			return;
		}
		User user = new User();
		user.setEmail(email);
		user.setRole(Role.USER);
		user.setProvider(provider);
		user.setUsername(email);
		user.setFirstName(givenName);
		user.setSurname(familyName);
		membershipService.addMembershipByUserAndMunicipalityId(userRepository.save(user), municipalityId, user.getRole());
	}


	public void processOAuthPostLoginFaceBook(String email, String name, Provider provider, Long municipalityId) {
		User existUser = userRepository.findByUsernameOrEmail(email).orElse(null);
		boolean membershipExist = membershipService.memberExistByUserEmailAndMunicipalityId(email, municipalityId);
		if (existUser != null && membershipExist) {
			return;
		}
		if (existUser != null) {
			membershipService.addMembershipByUserEmailAndMunicipalityId(email, municipalityId);
			return;
		}
		User user = new User();
		user.setEmail(email);
		user.setRole(Role.USER);
		user.setProvider(provider);
		user.setFirstName(name);
		user.setUsername(email);
		membershipService.addMembershipByUserAndMunicipalityId(userRepository.save(user), municipalityId, user.getRole());
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
