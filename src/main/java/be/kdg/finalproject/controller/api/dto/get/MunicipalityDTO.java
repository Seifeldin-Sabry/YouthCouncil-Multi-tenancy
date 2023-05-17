package be.kdg.finalproject.controller.api.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityDTO {

	private String name;
	private boolean hasPlatform;
	private String logo;
	private double latitude;
	private double longitude;
}
