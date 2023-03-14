package be.kdg.finalproject.controller.mvc.viewmodel.password;

public interface PasswordMatcher {
	default boolean passwordsMatch() {
		return getPassword().equals(getConfirmPassword());
	}

	String getPassword();

	String getConfirmPassword();
}

