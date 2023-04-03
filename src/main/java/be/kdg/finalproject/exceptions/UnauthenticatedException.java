package be.kdg.finalproject.exceptions;

public class UnauthenticatedException extends RuntimeException {
	public UnauthenticatedException(String message) {
		super(message);
	}

	public UnauthenticatedException() {
		super("You are not authenticated");
	}
}
