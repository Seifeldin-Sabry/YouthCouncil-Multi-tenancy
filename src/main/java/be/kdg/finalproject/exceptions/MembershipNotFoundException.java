package be.kdg.finalproject.exceptions;

public class MembershipNotFoundException extends RuntimeException {
	public MembershipNotFoundException(String reason) {
		super(reason);
	}

	public MembershipNotFoundException() {
		super();
	}
}
