package be.kdg.finalproject.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

	private OAuth2User oAuth2User;

	@Getter
	@Setter
	private Long municipalityId;

	public CustomOAuth2User(OAuth2User oAuth2User) {
		this.oAuth2User = oAuth2User;
	}

	public CustomOAuth2User(OAuth2User oAuth2User, Long municipalityId) {
		this.oAuth2User = oAuth2User;
		this.municipalityId = municipalityId;
	}

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}

	public String getEmail() {
		return oAuth2User.getAttribute("email");
	}

	public String getGivenName() {
		return oAuth2User.getAttribute("given_name");
	}

	public String getFamilyName() {
		return oAuth2User.getAttribute("family_name");
	}
}
