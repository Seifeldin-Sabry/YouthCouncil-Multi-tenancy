package be.kdg.finalproject.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
	private final long userId;
	private final String email;
	private Long municipalityId;

	public CustomUserDetails(long userId, String username, String email, String password, Long municipalityId, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
		this.email = email;
		this.municipalityId = municipalityId;
	}

	public CustomUserDetails(long userId, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
		this.email = email;
	}
}
