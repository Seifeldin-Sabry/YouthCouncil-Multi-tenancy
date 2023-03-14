package be.kdg.finalproject.service.user;

import be.kdg.finalproject.controller.mvc.viewmodel.UserSignUpViewModel;
import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.UserRepository;
import be.kdg.finalproject.service.membership.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final MembershipService membershipService;

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MembershipService municipalityService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.membershipService = municipalityService;
	}

	@Override
	public User addRegularUser(UserSignUpViewModel userSignUpViewModel) {
		logger.debug("Adding user: " + userSignUpViewModel.getEmail());
		logger.debug("{}", userSignUpViewModel);
		User user = new User(userSignUpViewModel.getFirstName(), userSignUpViewModel.getSurname(), userSignUpViewModel.getUsername(), userSignUpViewModel.getEmail(), passwordEncoder.encode(userSignUpViewModel.getPassword()), Role.USER);
		userRepository.save(user);
		membershipService.addMembershipByUserAndPostCode(user, userSignUpViewModel.getPostcode());
		userRepository.save(user);
		logger.info("User added: " + user.getEmail());
		logger.info("Membership added for user: " + user.getEmail() + " and municipality: " + user.getMemberships());
		logger.debug("{}", user.getMemberships());
		return user;
	}
}
