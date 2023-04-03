package be.kdg.finalproject.domain.security;

import lombok.Getter;

@Getter
public enum Role {
	USER("ROLE_USER", "User"),
	YOUTH_COUNCIL_MODERATOR("ROLE_YOUTH_COUNCIL_MODERATOR", "Youth Council Moderator"),
	YOUTH_COUNCIL_ADMINISTRATOR("ROLE_YOUTH_COUNCIL_ADMINISTRATOR", "Youth Council Administrator"),
	ADMINISTRATOR("ROLE_ADMINISTRATOR", "Administrator");

	private final String authority;
	private final String niceName;

	Role(String authority, String niceName) {
		this.authority = authority;
		this.niceName = niceName;
	}

	public String getNiceName() {
		return niceName;
	}
}
