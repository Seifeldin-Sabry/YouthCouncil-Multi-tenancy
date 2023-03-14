package be.kdg.finalproject.domain.security;

import lombok.Getter;

@Getter
public enum Role {
	USER("ROLE_USER"),
	YOUTH_COUNCIL_MODERATOR("ROLE_YOUTH_COUNCIL_MODERATOR"),
	YOUTH_COUNCIL_ADMINISTRATOR("ROLE_YOUTH_COUNCIL_ADMINISTRATOR"),
	ADMINISTRATOR("ROLE_ADMINISTRATOR");

	private final String authority;

	Role(String authority) {
		this.authority = authority;
	}
}
