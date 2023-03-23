package be.kdg.finalproject.controller.api.dto.get;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotBlank
	private String username;
	@NotBlank
	private String first_name;
	@NotBlank
	private String last_name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
