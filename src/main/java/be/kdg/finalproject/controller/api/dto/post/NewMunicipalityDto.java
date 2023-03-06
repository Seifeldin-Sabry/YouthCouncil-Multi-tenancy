package be.kdg.finalproject.controller.api.dto.post;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class NewMunicipalityDto {
	@NotNull
	private String name;
	@NotNull
	private List<Long> postcodes;

}
