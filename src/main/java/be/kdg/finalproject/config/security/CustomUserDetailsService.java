package be.kdg.finalproject.config.security;

import be.kdg.finalproject.domain.security.Role;
import be.kdg.finalproject.domain.user.Membership;
import be.kdg.finalproject.domain.user.User;
import be.kdg.finalproject.exceptions.UserBannedException;
import be.kdg.finalproject.municipalities.MunicipalityContext;
import be.kdg.finalproject.repository.membership.MembershipRespository;
import be.kdg.finalproject.repository.membership.UserRepository;
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
	private final MembershipRespository membershipRepository;
	private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	public CustomUserDetailsService(UserRepository userRepository, MembershipRespository membershipRepository) {
		this.userRepository = userRepository;
		this.membershipRepository = membershipRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Loading user: " + username);
		User user = userRepository.findByUsernameOrEmail(username)
		                          .orElseThrow(() -> new UsernameNotFoundException("User not found"));

		logger.debug("User found: " + user);
		logger.debug("User found: " + user.getUsername());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
		logger.debug("User authorities: " + authorities);
		Long municipalityId = MunicipalityContext.getCurrentMunicipalityId();
		if (municipalityId == null) {
			if (!user.getRole().equals(Role.ADMINISTRATOR)) throw new UsernameNotFoundException("User not found");
			return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
		} else {
			Membership membership = membershipRepository.findByUser_IdAndMunicipality_Id(user.getId(), MunicipalityContext.getCurrentMunicipalityId())
			                                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
			if (membership.isBanned()) {
				throw new UserBannedException("User is banned");
			}
			authorities.clear();
			authorities.add(new SimpleGrantedAuthority(membership.getRole().getAuthority()));
			return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), municipalityId, authorities);
		}
	}
}
