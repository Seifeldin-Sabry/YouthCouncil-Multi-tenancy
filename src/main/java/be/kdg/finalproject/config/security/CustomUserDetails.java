package be.kdg.finalproject.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails extends User {
	private final long userId;
	private final String email;
	private List<Long> municipalityIds;

	public CustomUserDetails(long userId, String username, String email, String password, List<Long> municipalityIds, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
		this.email = email;
		this.municipalityIds = municipalityIds;
	}

	public CustomUserDetails(long userId, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
		this.email = email;
	}
}
