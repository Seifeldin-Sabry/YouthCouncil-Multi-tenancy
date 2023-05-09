package be.kdg.finalproject.controller.api.dto.post;


import be.kdg.finalproject.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {

	@NotBlank
	private String firstName;
	@NotBlank
	private String surname;

	@Email
	private String email;
	private Role role;
}
