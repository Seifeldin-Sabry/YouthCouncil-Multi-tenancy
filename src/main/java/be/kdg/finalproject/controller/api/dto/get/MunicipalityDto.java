package be.kdg.finalproject.controller.api.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityDto {

	private String name;
	private boolean hasWebsite;
}
