package be.kdg.finalproject.controller.mvc.viewmodel;

import be.kdg.finalproject.controller.constraint.EmailOrUsernameConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginViewModel {

	@EmailOrUsernameConstraint
	@NotBlank (message = "Email or username required")
	private String emailOrUsername;

	@NotBlank (message = "Please enter your password")
	private String password;
}

