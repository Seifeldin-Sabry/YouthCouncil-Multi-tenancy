package be.kdg.finalproject.exceptions;

public class UserBannedException extends RuntimeException {
	public UserBannedException(String reason) {
		super(reason);
	}
}
