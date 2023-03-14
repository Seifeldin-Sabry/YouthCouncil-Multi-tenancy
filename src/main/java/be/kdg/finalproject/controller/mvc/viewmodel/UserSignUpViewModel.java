package be.kdg.finalproject.controller.mvc.viewmodel;

import be.kdg.finalproject.controller.constraint.PasswordMatchConstraint;
import be.kdg.finalproject.controller.constraint.UniqueEmailConstraint;
import be.kdg.finalproject.controller.constraint.UniqueUsernameConstraint;
import be.kdg.finalproject.controller.constraint.ValidPostcodeConstraint;
import be.kdg.finalproject.controller.mvc.viewmodel.password.*;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@PasswordMatchConstraint
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence ({UserSignUpViewModel.class, SizeCheck.class, LetterCheck.class, NumberCheck.class, SpecialCharacterCheck.class})
@ToString
public class UserSignUpViewModel implements PasswordMatcher {
	@NotBlank (message = "First name is mandatory")
	@Size (min = 2, max = 20, message = "First name must be between 2 and 20 characters", groups = SizeCheck.class)
	private String firstName;

	@NotBlank (message = "Surname is mandatory")
	@Size (min = 2, max = 20, message = "Surname must be between 2 and 20 characters", groups = SizeCheck.class)
	private String surname;

	@UniqueUsernameConstraint
	@NotBlank (message = "Username is mandatory")
	private String username;

	@ValidPostcodeConstraint
	@NotNull (message = "Postcode is mandatory")
	private Integer postcode;

	@Email (message = "Email must be valid")
	@NotBlank (message = "Email is mandatory")
	@UniqueEmailConstraint
	private String email;

	@NotBlank (message = "Password is required")
	@Size (min = 8, message = "Password must be at least 8 characters long", groups = SizeCheck.class)
	@Pattern.List ({
			@Pattern (regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase character", groups = LetterCheck.class),
			@Pattern (regexp = ".*[0-9].*", message = "Password must contain at least one digit", groups = NumberCheck.class),
			@Pattern (regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "Password must contain at least one special character", groups = SpecialCharacterCheck.class),
	})
	private String password;
	@NotBlank (message = "Please confirm your password")
	private String confirmPassword;

	@AssertTrue (message = "Must agree with terms and conditions")
	private boolean termsAccepted;
}
