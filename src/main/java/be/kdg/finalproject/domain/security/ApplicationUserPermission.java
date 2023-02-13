package be.kdg.finalproject.domain.security;

public enum ApplicationUserPermission {

	ACTION_READ("action:read"),
	ACTION_WRITE("action:write"),
	IDEA_READ("idea:read"),
	IDEA_WRITE("idea:write");


	private final String permission;

	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
