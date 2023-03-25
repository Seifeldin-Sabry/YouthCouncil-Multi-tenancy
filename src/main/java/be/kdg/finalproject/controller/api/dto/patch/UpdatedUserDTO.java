package be.kdg.finalproject.controller.api.dto.patch;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatedUserDTO {
	@NotBlank
	private String firstName;
	@NotBlank
	private String surname;
	@NotBlank (message = "Username is required")
	private String username;
	@NotBlank (message = "Email is required")
	@Email (message = "Email must be valid")
	private String email;

}
