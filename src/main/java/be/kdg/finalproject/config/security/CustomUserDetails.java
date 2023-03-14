package be.kdg.finalproject.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

	private final long userId;

	public CustomUserDetails(long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}
}
