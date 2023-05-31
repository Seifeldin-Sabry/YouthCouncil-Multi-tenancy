package be.kdg.finalproject.controller.api.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InformativePageListItemDTO {
	private Long id;
	private String title;
	private boolean active;
	private String pageName;
}
