package be.kdg.finalproject.controller.api.dto.post;


import be.kdg.finalproject.controller.constraint.UniqueEmailConstraint;
import be.kdg.finalproject.controller.constraint.UniqueUsernameConstraint;
import be.kdg.finalproject.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {

	@NotBlank
	private String firstName;
	@NotBlank
	private String surname;

	@UniqueUsernameConstraint
	@UniqueEmailConstraint
	@NotBlank
	private String email;

	@NotNull
	private Role role;
}
