package be.kdg.finalproject.config.security;

import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Loading user: " + username);
		logger.debug("User found: " + userRepository.findByUsernameOrEmail(username));
		User user = userRepository.findByUsernameOrEmail(username)
		                          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		logger.debug("User found: " + user.getUsername());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
		logger.debug("User authorities: " + authorities);
		return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}
}
