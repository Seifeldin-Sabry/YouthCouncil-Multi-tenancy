package be.kdg.finalproject.controller.api.dto.get;

import be.kdg.finalproject.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MunicipalityMemberDTO {
	private String id;
	private String firstName;
	private String surname;
	private String email;
	private Role role;
}
